drop database if exists ImportacionesBD;
create database if not exists ImportacionesBD;
use ImportacionesBD;

drop table if exists COMPAÑIA;
drop table if exists SEG_CLIENTE;
drop table if exists ARTICULO;
drop table if exists PRECIO_VENTA;
drop table if exists PROVEEDOR;
drop table if exists ORDEN_PEDIDO;
drop table if exists LINEA_ARTICULO;

create table if not exists COMPAÑIA(
	CodigoCompañia int primary key auto_increment,
    NombreCompañia varchar(200),
    Direccion varchar(200),
    NumeroPatronalComp int,
    CedulaGerente int,
    NombreGerente varchar(200),
    CedulaPresidenteComp int,
    NombrePresidenteComp varchar(200)
);
create table if not exists SEG_CLIENTE(
	CodigoSegCliente int primary key auto_increment,
    Descripcion varchar(200),
    NombreGerente varchar(200),
    UltimaVisitaDoctor date,
    codigo_compañia int,
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia)
);
create table if not exists ARTICULO(
	CodigoArticulo int primary key auto_increment,
    DescripcionArticulo varchar(200),
    TipoArticulo varchar(50),
    PrecioUnitario int,
    PrecioVenta int,
    Stock int,
    FechaUltimaCompra date,
    FechaUltimoInventario date,
    codigo_compañia int,
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia)
);
create table if not exists PRECIO_VENTA(
	NumeroPrecioVenta int primary key auto_increment,
    PrecioVentaSegmento int,
    Codigo_Articulo int,
    Codigo_SegCliente int,
    codigo_compañia int,
    foreign key (Codigo_Articulo) references ARTICULO(CodigoArticulo),
    foreign key (Codigo_SegCliente) references SEG_CLIENTE(CodigoSegCliente),
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia)
);
create table if not exists PROVEEDOR(
	CodigoProveedor int primary key auto_increment,
    Nombre varchar(200),
    Cedula varchar(200),
    TipoProveedor varchar(50),
    Telefono varchar(200),
    Direccion varchar(200),
    NombreContacto varchar(200),
    codigo_compañia int,
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia)
);
create table if not exists ORDEN_PEDIDO( 
	NumeroOrden int primary key auto_increment,
    FechaOrden date,
    UsuarioComprador varchar(200),
    UsuarioAprobador varchar(200),
    MontoTotalOrden int,
    TotalPorLinea int,
    codigo_compañia int,
    codigo_proveedor int,
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia),
    foreign key (codigo_proveedor) references PROVEEDOR (CodigoProveedor)
);
create table if not exists LINEA_ARTICULO(
	LineaArticulo int primary key auto_increment,
    CantidadOrdenada int,
    PrecioArticulo int,
    codigo_compañia int, 
    codigo_orden int,
    codigo_articulo int,
    foreign key (codigo_compañia) references COMPAÑIA (CodigoCompañia),
    foreign key (codigo_orden) references ORDEN_PEDIDO (NumeroOrden),
    foreign key (codigo_articulo) references ARTICULO (CodigoArticulo)
);

INSERT INTO COMPAÑIA (NombreCompañia, Direccion, NumeroPatronalComp, CedulaGerente, NombreGerente, CedulaPresidenteComp, NombrePresidenteComp)VALUES
    ('Zona Gadget', 'San Jose', 12345, 87654, 'Raúl', 102345678, 'Juan'),
    ('Tech Market', 'San Jose', 54321, 65432, 'Ramón', 209876543, 'María'),
    ('Planeta Digital', 'San Jose', 24680, 23456, 'Horacio', 314567890, 'Carlos'),
    ('Genius Store', 'San Jose', 11122, 76543, 'Martín', 408765432, 'Ana'),
    ('Robot Shop', 'Heredia', 22233, 34567, 'José', 512345678, 'Luis'),
    ('Todo con circuitos', 'Heredia', 33344, 87654, 'Claudio', 617890123, 'Laura'),
    ('Nova Tech', 'Heredia', 44455, 45678, 'Lucía', 722345678, 'Pedro'),
    ('Happy Computer', 'Puntarenas', 55566, 98765, 'Daniel', 825678901, 'Sofia'),
    ('Karma HD Nets', 'Puntarenas', 66677, 54321, 'Graciela', 931234567, 'Diego'),
    ('TopRank Watches', 'Puntarenas', 77788, 12345, 'Patricia', 103456789, 'Valentina');

INSERT INTO SEG_CLIENTE (Descripcion, NombreGerente, UltimaVisitaDoctor, codigo_compañia)VALUES
    ('Clientes Frecuentes de Zona Gadget', 'Raúl', '2023-01-15', 1),
    ('Amantes de la Tecnología de Tech Market', 'Ramón', '2023-02-20', 2),
    ('Compradores de Alta Tecnología de Planeta Digital', 'Horacio', '2023-03-15', 3),
    ('Aficionados a la Electrónica de Genius Store', 'Martín', '2023-04-20', 4),
    ('Entusiastas de la Robótica de Robot Shop', 'José', '2023-05-15', 5),
    ('Apasionados por la Electrónica de Todo con Circuitos', 'Claudio', '2023-06-20', 6),
    ('Clientes de Tecnología de Vanguardia de Nova Tech', 'Lucía', '2023-07-15', 7),
    ('Consumidores de Equipos Informáticos de Happy Computer', 'Daniel', '2023-08-20', 8),
    ('Fanáticos de la Conectividad de Karma HD Nets', 'Graciela', '2023-09-15', 9),
    ('Amantes de los Relojes de TopRank Watches', 'Patricia', '2023-10-20', 10);

INSERT INTO ARTICULO (DescripcionArticulo, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario, codigo_compañia)VALUES
    ('Smartphone Samsung Galaxy S21', 'Electrónico', 699, 899, 50, '2023-03-15', '2023-03-01', 1),
    ('Portátil HP Envy 15', 'Electrónico', 1099, 1299, 25, '2023-03-10', '2023-02-25', 1),
    ('Refrigeradora LG Inverter', 'Electrodoméstico', 799, 999, 30, '2023-03-20', '2023-03-05', 2),
    ('Lavadora Whirlpool 6 kg', 'Electrodoméstico', 499, 599, 15, '2023-03-18', '2023-03-02', 2),
    ('Cafetera Nespresso Vertuo', 'Electrodoméstico', 149, 199, 40, '2023-03-22', '2023-03-08', 3),
    ('Laptop Dell XPS 13', 'Electrónico', 1199, 1499, 20, '2023-03-12', '2023-03-04', 3),
    ('Tablet Apple iPad Pro', 'Electrónico', 799, 999, 35, '2023-03-17', '2023-03-03', 4),
    ('Smart TV Sony Bravia 55"', 'Electrónico', 799, 999, 10, '2023-03-14', '2023-03-07', 4),
    ('Mesa de Comedor de Madera', 'Muebles', 349, 499, 5, '2023-03-19', '2023-03-06', 5),
    ('Silla de Oficina Ergonómica', 'Muebles', 199, 249, 12, '2023-03-16', '2023-03-09', 5);

INSERT INTO PRECIO_VENTA (PrecioVentaSegmento, Codigo_Articulo, Codigo_SegCliente, codigo_compañia)VALUES
    (899, 1, 1, 1),
    (1299, 2, 2, 2),
    (999, 3, 3, 3),
    (599, 4, 4, 4),
    (199, 5, 5, 5),
    (1499, 6, 6, 6),
    (999, 7, 7, 7),
    (999, 8, 8, 8),
    (499, 9, 9, 9),
    (249, 10, 10, 10);
    
INSERT INTO PROVEEDOR (Nombre, Cedula, TipoProveedor, Telefono, Direccion, NombreContacto, codigo_compañia)VALUES
	('Ana', '1234567891', 'Electrónica', '11111111', '124 Main Street', 'Eduardo', 1),
    ('Pedro', '2345678902', 'Mecánica', '22222222', '457 Elm Street', 'Isabel', 2),
    ('Laura', '3456789013', 'Electrónica', '33333333', '790 Oak Street', 'Ricardo', 3),
    ('Diego', '4567890124', 'Químicos', '44444444', '322 Pine Street', 'Olivia', 4),
    ('Sofía', '5678901235', 'Muebles', '55555555', '655 Cedar Street', 'Mariano', 5),
    ('Adriana', '6789012346', 'Alimentos', '66666666', '988 Birch Street', 'Natalia', 6),
    ('Valentina', '7890123457', 'Bebidas', '77777777', '556 Maple Street', 'Fernando', 7),
    ('Daniel', '8901234568', 'Herramientas', '88888888', '223 Willow Street', 'Miguel', 8),
    ('Carolina', '9012345679', 'Oficina', '99999999', '778 Birch Street', 'Patricio', 9),
    ('Nicolás', '0123456780', 'Limpieza', '11112222', '889 Oak Street', 'Carolina', 10);

INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioComprador, UsuarioAprobador, MontoTotalOrden, TotalPorLinea, codigo_compañia, codigo_proveedor)VALUES
    ('2023-03-20', 'Adriana', 'Eduardo', 500, 0, 1, 1),
    ('2023-03-25', 'Andres', 'Isabel', 400, 0, 2, 2),
    ('2023-04-20', 'Nicole', 'Ricardo', 600, 0, 3, 3),
    ('2023-04-25', 'Nathalie', 'Olivia', 300, 0, 4, 4),
    ('2023-05-20', 'Raquel', 'Mariano', 700, 0, 5, 5),
    ('2023-05-25', 'Ximena', 'Natalia', 100, 0, 6, 6),
    ('2023-06-20', 'Fabiola', 'Fernando', 800, 0, 7, 7),
    ('2023-06-25', 'Jimena', 'Miguel', 900, 0, 8, 8),
    ('2023-07-20', 'Jason', 'Patricio', 240, 0, 9, 9),
    ('2023-07-25', 'Wilson', 'Carolina', 870, 0, 10, 10);

INSERT INTO LINEA_ARTICULO (CantidadOrdenada, PrecioArticulo, codigo_compañia, codigo_orden, codigo_articulo)VALUES
    (50, 15, 1, 1, 1),
    (75, 18, 2, 2, 2),
    (30, 15, 3, 3, 3),
    (67, 18, 4, 4, 4),
    (512, 15, 5, 5, 5),
    (35, 18, 6, 6, 6),
    (160, 15, 7, 7, 7),
    (742, 18, 8, 8, 8),
    (514, 15, 9, 9, 9),
    (725, 18, 10, 10, 10);
