drop database if exists GestionTiendaLibros;
create database if not exists GestionTiendaLibros;
use GestionTiendaLibros;

drop table if exists LIBROS;
drop table if exists CLIENTES;
drop table if exists PEDIDOS;

create table if not exists LIBROS(
	id int primary key auto_increment,
    titulo varchar(200),
    autor varchar(200),
    genero varchar(200),
    precio float,
    stock int,
    index titulo_index(titulo)
);

create table if not exists CLIENTES(
	id int primary key auto_increment,
    nombre varchar(200),
    correo_electronico varchar(200),
    index correo_index(correo_electronico)
);

create table if not exists PEDIDOS(
	id int primary key auto_increment,
    id_cliente int,
    fecha_pedido date,
    foreign key (id_cliente) references CLIENTES (id),
    index pedido_index(id_cliente)
);

-- Stored Procedures
DELIMITER //
create procedure agregar_libro(p_titulo varchar(200), p_autor varchar(200), p_genero varchar(200), p_precio float)
begin
	declare cantidad_stock int;
    select count(*) into cantidad_stock from LIBROS where titulo = p_titulo;
    
    if cantidad_stock > 0 then
		update LIBROS set stock = stock + 1 where titulo = p_titulo;
    else
		 insert into LIBROS (titulo, autor, genero, precio, stock) values (p_titulo, p_autor, p_genero, p_precio, 1);
	end if;
    
end //

DELIMITER //
create procedure realizar_pedido(p_id_cliente int, p_titulo_libro varchar(200))
begin
    update LIBROS set stock = stock - 1 where titulo = p_titulo_libro;
    insert into PEDIDOS (id_cliente, fecha_pedido) values (p_id_cliente, NOW());    
end //

-- funcion
DELIMITER //
create function calcular_total_pedido(p_id_pedido int) returns float deterministic
begin
    declare total_pedido FLOAT;
    
    select sum(L.precio)
    into total_pedido
    from PEDIDOS P
    join LIBROS L on P.id = p_id_pedido;
    
    return total_pedido;
end //

-- trigger
DELIMITER //
create trigger actualizar_stock_pedido after insert on pedidos
for each row
begin
    declare pedido_titulo varchar(200);
    select titulo into pedido_titulo from LIBROS where titulo = pedido_titulo;
    update LIBROS set stock = stock - 1 where titulo = pedido_titulo;
end //


insert into clientes (nombre, correo_electronico) values
    ('Adriana', 'Adriana@hotmail.com'),
    ('Nicole', 'Nicole@hotmail.com'),
    ('Ingrid', 'Ingrid@hotmail.com');

call agregar_libro('GATE', 'Marco', 'Fantasia', 1499.99);
call agregar_libro('GATE', 'Marco', 'Fantasia', 1499.99);
call agregar_libro('GATE', 'Marco', 'Fantasia', 1499.99);
call agregar_libro('GATE', 'Marco', 'Fantasia', 1499.99);

call realizar_pedido(1, 'GATE');
call realizar_pedido(1, 'GATE');

select calcular_total_pedido(1);

select * from CLIENTES;
select * from LIBROS;
select * from PEDIDOS;

