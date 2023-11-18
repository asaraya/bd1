-- Base de Datos
drop database if exists weather_service;
create database if not exists weather_service;
use weather_service;

-- Tablas
drop table if exists country;
drop table if exists state;
drop table if exists city;
drop table if exists forecast;
drop table if exists logbook;

create table if not exists country (
    country_code int primary key auto_increment,
    country_name varchar(200) not null
);
create table if not exists state (
    state_code int primary key auto_increment,
    country_code int not null,
    state_name varchar(200) not null,
    foreign key (country_code) references country (country_code)
);
create table if not exists city (
    city_code int primary key auto_increment,
    city_name varchar(200) not null,
    zipcode int,
    state_code int not null,
    foreign key (state_code) references state (state_code)
);
create table if not exists forecast (
    forecast_code int primary key auto_increment,
    city_code int not null,
    zipcode int not null,
    temperature float not null,
    forecast_date date not null,
    foreign key (city_code) references city (city_code)
);
create table if not exists forecast_log (
    id int primary key auto_increment,
    entry_text varchar(200) not null
);

-- Usuario
drop user "weatherappuser"@"localhost";
create user "weatherappuser"@"localhost" identified by "weatherapppass";
grant insert, delete, update, execute on weather_service.* to 'weatherappuser'@'localhost';
flush privileges;

-- Procedimientos
-- Country CRUD
delimiter //
create procedure find_all_countries(out result int)
begin
    select * from country;
end //

create procedure create_country(in p_name varchar(255), out new_country_id int)
begin
	declare country_exists int;
    select count(*) into country_exists from country where country_name = p_name;
    if country_exists = 0 then
		insert into country(country_name) values (p_name);
        select last_insert_id() into new_country_id;
	else
		select -1 into new_country_id;
	end if;
end //

create procedure delete_country(in p_code int)
begin
    delete from country where country_code = p_code;
end //

create procedure update_country(in p_code int, in p_name varchar(255))
begin
    update country set country_name = p_name where country_code = p_code;
end //

-- State CRUD
delimiter //
create procedure find_all_states(out result int)
begin
    select state_code, state_name, country_code from state;
end //

create procedure create_state(in p_name varchar(200), in p_country_code int, out new_state_code int)
begin
    insert into state(state_name, country_code) values (p_name, p_country_code);
    select last_insert_id() into new_state_code;
end //

create procedure delete_state(in p_code int)
begin
    delete from state where state_code = p_code;
end //

create procedure update_state(in p_code int, in p_name varchar(200))
begin
    update state set state_name = p_name where state_code = p_code;
end //

-- City CRUD
DELIMITER //
create procedure find_city_by_id(in p_code int, in p_state_code int, out result int)
begin
    select city_code into result from city where city_code = p_code and state_code = p_state_code limit 1;
end //

create procedure find_all_cities(out result int)
begin
    select * from city;
end //

create procedure create_city(in p_name varchar(200), in p_zipcode varchar(200), in p_state_code int, out new_city_id int)
begin
    declare zipcode_exists int;
    select count(*) into zipcode_exists from city where zipcode = p_zipcode;

    if zipcode_exists = 0 then
		
        insert into city(city_name, zipcode, state_code) values (p_name, p_zipcode, p_state_code);
        select last_insert_id() into new_city_id;
    else
        set new_city_id = -1;
    end if;
end //


create procedure delete_city(in p_code int)
begin
    delete from city where city_code = p_code;
end //

create procedure update_city(in p_code int, in p_name varchar(255), in p_zipcode varchar(10))
begin
    update city set city_name = p_name, zipcode = p_zipcode where city_code = p_code;
end //

-- Forecast CRUD
delimiter //
create procedure find_forecast_by_id(in p_forecast_id int)
begin
	select * from forecast where forecast_code = p_forecast_id;
end //

create procedure find_all_forecasts(out result int)
begin
    select * from forecast;
end //

create procedure create_forecast(in p_city_code int, in p_temperature float, in p_forecast_date date, out new_forecast_code int)
begin
    declare city_zipcode varchar(200);
    select count(*) into new_forecast_code from city where city_code = p_city_code;

    if new_forecast_code > 0 then
        select zipcode into city_zipcode from city where city_code = p_city_code;
        start transaction;
        insert into forecast(city_code, zipcode, temperature, forecast_date) 
        values (p_city_code, city_zipcode, p_temperature, p_forecast_date);
        select last_insert_id() into new_forecast_code;
        commit;
    else
        set new_forecast_code = -1;
    end if;
end //

create procedure delete_forecast(in p_code int)
begin
    delete from forecast where forecast_code = p_code;
end //

create procedure update_forecast(in p_code int, in p_temperature float, in p_forecast_date date)
begin
    start transaction;
    update forecast 
    set temperature = p_temperature, forecast_date = p_forecast_date
    where forecast_code = p_code;
    commit;
end //


-- Trigger AFTER INSERT en la tabla forecast
delimiter //
create trigger after_forecast_insert
after insert on forecast
for each row
begin
    insert into forecast_log(entry_text) values (
        CONCAT('New forecast added for city ', CAST(NEW.city_code AS CHAR), ' on ', DATE_FORMAT(NEW.forecast_date, '%Y-%m-%d'))
    );
end;
//

-- Triggers
delimiter //
create trigger after_forecast_delete
after delete on forecast
for each row
begin
    insert into forecast_log(entry_text) values (
        CONCAT('Forecast deleted for city ', CAST(OLD.city_code AS CHAR), ' on ', DATE_FORMAT(OLD.forecast_date, '%Y-%m-%d'))
    );
end //


create trigger after_forecast_update
after update on forecast
for each row
begin
    insert into forecast_log(entry_text) values (
        CONCAT('Forecast updated for city ', CAST(NEW.city_code AS CHAR), ' on ', DATE_FORMAT(NEW.forecast_date, '%Y-%m-%d'))
    );
end //

create procedure find_logs(in n int, out result int)
begin
    if n is null then
        -- Mostrar todos los registros
        select * from forecast_log;
    else
        -- Mostrar los últimos n registros
        select * from forecast_log
        order by id desc
        limit n;
    end if;
end //


-- Agregar un país
call create_country("USA", @country_last_id);

-- Agregar un estado
call create_state("New York", @country_last_id, @state_last_id);

-- Agregar una ciudad
call create_city("New York City", "10001", @state_last_id, @city_last_id);

select * from COUNTRY;
select * from STATE;
select * from CITY;

call find_city_by_id(1, @state_last_id, @city_exists);
select @city_exists;
-- Agregar un pronóstico

call create_forecast(1, 25.5, "2023-01-01", @forecast_last_id);
call create_forecast(1, 28.5, "2023-01-01", @forecast_last_id);
call create_forecast(1, 235.5, "2023-01-01", @forecast_last_id);
call create_forecast(1, 2.6, "2023-01-01", @forecast_last_id);
call create_forecast(1, 25.5, "2023-01-01", @forecast_last_id);

select * from COUNTRY;
select * from STATE;
select * from CITY;
select * from FORECAST;
CALL find_logs(5, @result);

SET autocommit=OFF;