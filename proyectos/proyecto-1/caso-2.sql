drop database if exists ModeloRelacional;
create database if not exists ModeloRelacional;
use ModeloRelacional;

drop table if exists Persona;
drop table if exists Persona_Fisica;
drop table if exists Cuenta_por_Cobrar;
drop table if exists Abono;
drop table if exists Forma_de_Pago;

create table if not exists Persona (
    Cedula int primary key auto_increment,
    Tipo varchar(50),
    Nombre varchar(200)
);

create table if not exists Persona_Fisica (
    Cedula int primary key auto_increment,
    FechaNacimiento date,
    foreign key (Cedula) references Persona(Cedula)
);

create table if not exists Persona_Juridica (
    Cedula int primary key auto_increment,
    NombreComercial varchar(200),
    foreign key (Cedula) references Persona(Cedula)
);

create table if not exists Cuenta_por_cobrar (
    Numero int primary key auto_increment,
    Monto int,
    FechaVencimiento date,
    Cedula_Persona int,
    foreign key (Cedula_Persona) references Persona(Cedula)
);

create table if not exists Forma_Pago (
    Codigo int primary key auto_increment,
    Nombre varchar(100)
);

create table if not exists Abono (
    NumCuota int,
    Fecha date,
    Monto int,
    Numero_Cuenta int,
    codigo_pago int,
    primary key (NumCuota, Numero_Cuenta),
    foreign key (Numero_Cuenta) references Cuenta_por_cobrar(Numero),
    foreign key (codigo_pago) references Forma_Pago(Codigo)
);

INSERT INTO Persona (Tipo, Nombre) VALUES
    ('Física', 'Andres'),
    ('Física', 'Adriana'),
    ('Física', 'Elmer'),
    ('Física', 'Raquel'),
    ('Física', 'Nathalie'),
    ('Física', 'Jason'),
    ('Física', 'Salim'),
    ('Física', 'Gabriel'),
    ('Física', 'Viviana'),
    ('Física', 'Daniela'),
    ('Juridica', 'María'),
    ('Juridica', 'Juan'),
    ('Juridica', 'Ana'),
    ('Juridica', 'Carlos'),
    ('Juridica', 'Laura'),
    ('Juridica', 'Luis'),
    ('Juridica', 'Sofia'),
    ('Juridica', 'David'),
    ('Juridica', 'Gabriela'),
    ('Juridica', 'Alejandro');

INSERT INTO Persona_Fisica (FechaNacimiento, cedula) VALUES
    ('1990-05-15', 1),
    ('1991-06-20', 2),
    ('1992-07-15', 3),
    ('1983-08-20', 4),
    ('1994-09-15', 5),
    ('1995-10-20', 6),
    ('1996-11-15', 7),
    ('1987-11-20', 8),
    ('1998-12-15', 9),
    ('1999-10-20', 0);

INSERT INTO Persona_Juridica (NombreComercial, cedula) VALUES
    ('Distribuidora de Productos Químicos S.A.', 10),
    ('Grupo Financiero Internacional S.A.', 11),
    ('Innovación Tecnológica Ltda.', 12),
    ('Consultoría Legal y Asociados S.A.', 13),
    ('Compañía de Logística Global S.A.', 14),
    ('Construcciones y Desarrollos Inmobiliarios S.A.', 15),
    ('Importaciones y Exportaciones Internacionales S.A.', 16),
    ('Servicios de Ingeniería Avanzada S.A.', 17),
    ('Agencia de Publicidad Creativa Ltda.', 18),
    ('Distribuidora de Productos Químicos S.A.', 19);

INSERT INTO Cuenta_por_cobrar (Monto, FechaVencimiento, Cedula_Persona) VALUES
    (1000, '2023-10-05', 10),
    (1500, '2023-11-11', 1),
    (2000, '2023-10-15', 2),
    (2500, '2023-11-17', 3),
    (3000, '2023-10-25', 4),
    (4500, '2023-11-21', 5),
    (5000, '2023-10-09', 6),
    (5500, '2023-11-01', 7),
    (6000, '2023-10-18', 8),
    (6500, '2023-11-24', 9);

INSERT INTO Forma_Pago (Nombre) VALUES
    ('Tarjeta de Crédito'),
    ('Transferencia Bancaria'),
    ('Sinpe'),
    ('Tarjeta de Crédito'),
    ('Tarjeta de Crédito'),
    ('Tarjeta de Crédito'),
    ('Tarjeta de Crédito'),
    ('Sinpe'),
    ('Sinpe'),
    ('Transferencia Bancaria');

INSERT INTO Abono (NumCuota, Fecha, Monto, Numero_Cuenta, codigo_pago) VALUES
    (1, '2023-01-20', 1000, 1, 1),
    (2, '2023-02-05', 1500, 2, 2),
    (3, '2023-03-20', 2000, 3, 3),
    (4, '2023-04-05', 2500, 4, 4),
    (5, '2023-05-20', 3000, 5, 5),
    (6, '2023-06-05', 3500, 6, 6),
    (7, '2023-07-20', 4000, 7, 7),
    (8, '2023-08-05', 4500, 8, 8),
    (9, '2023-09-20', 5000, 9, 9),
    (10, '2023-10-05', 5500, 10, 10);
