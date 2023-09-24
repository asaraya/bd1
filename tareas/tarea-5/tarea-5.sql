-- esquema 

drop database if exists blockbuster;
create database if not exists blockbuster;
use blockbuster;

drop table if exists customer;
drop table if exists movie_category;
drop table if exists movie;
drop table if exists rental;

create table if not exists customer (
	id int primary key auto_increment, -- cedula
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    cell_phone varchar(15) not null,
    address varchar(200) default 'N/A'
);

create table if not exists movie_category(
 id int primary key auto_increment,
 category_name varchar (30) not null
);

create table  if not exists movie (
	id int primary key auto_increment,
    title varchar(100) not null,
    release_date timestamp not null,
    category_id int not null,
    constraint category_id_positive_value check (category_id > 0),
    constraint movie_category_id_fk foreign key (category_id) references movie_category (id)
);

create table if not exists rental (
	id int not null auto_increment unique,
    customer_id int not null,
    movie_id int not null unique,
    rental_date timestamp not null,
    return_date timestamp not null,
    primary key (customer_id, movie_id),
    constraint customer_id_positive_value check (customer_id > 0),
    constraint movie_id_positive_value check (movie_id > 0), 
	constraint rental_customer_id_fk foreign key (customer_id) references customer (id),
	constraint rental_movie_id_fk foreign key (movie_id) references movie(id)
);


-- Datos
-- Clientes
insert into customer(first_name, last_name, cell_phone) values ('Juan', 'Perez', '4444444');
insert into customer(first_name, last_name, cell_phone) values ('Maria', 'Rodríguez', '4444444');
insert into customer(first_name, last_name, cell_phone, address) values ('Raquel', 'Blanco', '4444444', 'Limon');
insert into customer(first_name, last_name, cell_phone, address) values ('Adriana', 'Solano', '4444444', 'Coronado');
insert into customer(first_name, last_name, cell_phone) values ('Emmanuel', 'Ramírez', '4444444');
insert into customer(first_name, last_name, cell_phone, address) values ('Wilson', 'Cen', '4444444', 'Moravia');
insert into customer(first_name, last_name, cell_phone) values ('Jason', 'Salazar', '4444444');
insert into customer(first_name, last_name, cell_phone) values ('Gabriel', 'Aguilar', '4444444');
insert into customer(first_name, last_name, cell_phone, address) values ('Nathalie', 'Cruz', '4444444', 'Tibás');
insert into customer(first_name, last_name, cell_phone, address) values ('Andrés', 'Salas', '4444444', 'San José');

-- Categorías
insert into movie_category(category_name) values ('Comedy');
insert into movie_category(category_name) values ('Drama');
insert into movie_category(category_name) values ('Sci-Fi');
insert into movie_category(category_name) values ('Terror');
insert into movie_category(category_name) values ('Romance');
insert into movie_category(category_name) values ('Adventure');
insert into movie_category(category_name) values ('Action');
insert into movie_category(category_name) values ('Horror');
insert into movie_category(category_name) values ('Suspense');
insert into movie_category(category_name) values ('Fantasy');

select * from rental;

-- Películas
insert into movie(title, release_date, category_id) values ('Ace Ventura', '1998-11-11 12:00:00', 1);
insert into movie(title, release_date, category_id) values ('Oppenheimer', '2023-08-11 12:00:00', 2);
insert into movie(title, release_date, category_id) values ('The Matrix', '1999-11-11 12:00:00', 3);
insert into movie(title, release_date, category_id) values ('Paranormal Activity', '2005-11-11 12:00:00', 4);
insert into movie(title, release_date, category_id) values ('The Fault in Our Stars', '2014-06-06 12:00:00', 5);
insert into movie(title, release_date, category_id) values ('Uncharted', '2022-02-18 12:00:00', 6);
insert into movie(title, release_date, category_id) values ('Gantz: O', '2016-09-14 12:00:00', 7);
insert into movie(title, release_date, category_id) values ('Perfect Blue', '1998-02-28 12:00:00', 8);
insert into movie(title, release_date, category_id) values ('Another', '2012-08-04 12:00:00', 9);
insert into movie(title, release_date, category_id) values ('Spirited Away', '2001-07-20 12:00:00', 10);

-- Rentar
insert into rental(customer_id, movie_id, rental_date, return_date) values(1, 1, now(), '2023-09-25 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(1, 2, '2023-09-17 17:00:00', '2023-09-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(2, 3, '2023-08-17 17:00:00', '2023-08-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(3, 4, now(), '2023-09-25 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(3, 5, '2023-09-17 17:00:00', '2023-09-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(3, 6, '2023-08-17 17:00:00', '2023-08-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(6, 7, now(), '2023-09-25 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(7, 8, '2023-09-17 17:00:00', '2023-09-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(8, 9, '2023-08-17 17:00:00', '2023-08-20 17:00:00');
insert into rental(customer_id, movie_id, rental_date, return_date) values(10, 10, '2023-08-17 17:00:00', '2023-08-20 17:00:00');


-- Mostrar la Cédula, Nombre, Apellido y Teléfono Celular de los clientes que han pedido prestada una película al video, no importa si el préstamo está activo o no.

select 
c.id, c.first_name, c.last_name, c.cell_phone, r.movie_id, m.title
from 
customer c 
inner join rental r on c.id = r.customer_id
left join movie m on r.movie_id = m.id;

-- Mostrar la cantidad de préstamos activos por cédula.
select 
c.id, 
r.rental_date,
m.title,
datediff(now(), r.rental_date) as days_in_rental
from 
rental as r
inner join customer c on r.customer_id = c.id
inner join movie m on r.movie_id = m.id
where 
datediff(now(), r.rental_date) <= 3;

-- Mostrar la cantidad de préstamos inactivos existentes por cédula.
select 
c.id, 
r.rental_date,
m.title,
datediff(now(), r.rental_date) as days_in_rental
from 
rental as r
inner join customer c on r.customer_id = c.id
inner join movie m on r.movie_id = m.id
where 
datediff(now(), r.rental_date) > 3;

-- Mostrar el total de préstamos inactivos existentes .
select 
count(*)
from 
rental r 
where
datediff(now(), r.rental_date) > 3;

-- Mostrar a todos aquellos clientes que nunca han realizado un préstamo.
select 
c.id, 
c.first_name, 
c.last_name, 
c.cell_phone, 
r.id, 
r.movie_id
from 
customer c 
left join rental r on c.id = r.customer_id 
where r.id is null;

-- Actualizar el campo Dirección y poner Guanacaste, del cliente con cédula 10.
update
customer
set
address = "Guanacaste"
where
id = 10;

-- Mostrar la cédula, nombre, apellido de los clientes que tienen entre 1 y 3 préstamos activos.
select
c.id,
concat(c.first_name, ' ', c.last_name) as customer,
count(*) as number_of_rentals
from 
customer c
inner join rental r on c.id = r.customer_id
group by 
r.customer_id
having
number_of_rentals >= 0 and number_of_rentals <= 3;

-- Listar todas las películas de la categoría “Comedia”.
select
* 
from 
movie as m inner join movie_category as c on m.category_id = c.id 
where c.category_name = 'Comedy';

-- Listas todas las películas prestadas de la categoría “Comedia”.
select
* 
from 
rental as r inner join movie m on r.movie_id = m.id 
inner join movie_category as c on m.category_id = c.id 
where c.category_name = 'Comedy';
 
-- Listar cuántas películas existen por categoría, por ejemplo:
-- 3 de comedia, 2 de drama, 4 de ciencia ficción
select
c.category_name as Categoria,
count(m.id) as "Cantidad de peliculas"
from
movie_category c
left join movie m on c.id = m.category_id
group by
categoria;













-- insert into pelicula(titulo, fecha_lanzamiento, categoria) values ('James Bond 007', now(), 'Accion');
-- insert into pelicula(titulo, fecha_lanzamiento, categoria) values ('Matrix', now(), 'Ciencia Ficcion');
-- insert into pelicula(titulo, fecha_lanzamiento, categoria) values ('Avatar', now(), 'Accion');
-- insert into pelicula(titulo, fecha_lanzamiento, categoria) values ('Spider Man', now(), 'Accion');

-- -- INSERT
-- -- SELECTS


-- drop procedure if exists add_course;
-- delimiter $$
-- create procedure add_course(in course_id int, in course_name varchar(50), in course_credits int, in course_dept varchar(50)) 
-- begin
-- 	insert into curso(id, nombre, creditos, departamento) values(course_id, course_name, course_credits, course_dept);
-- end
-- $$
-- delimiter ;


-- drop procedure if exists delete_movie;
-- delimiter $$
-- create procedure delete_movie(in movie_id int) 
-- begin
-- 	start transaction;
-- 		-- region critica
--         delete from pelicula where codigo = movie_id;
--         
-- 	commit;
-- end
-- $$
-- delimiter ;

-- select * from pelicula where titulo like 'Spider Man';



-- use blockbuster;


