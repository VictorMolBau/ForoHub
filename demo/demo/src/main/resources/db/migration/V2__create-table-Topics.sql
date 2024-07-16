
create table topico(
    id bigint not null auto_increment,
    titulo varchar (100) not null unique,
    mensaje varchar (700) not null unique,
    fecha_creacion datetime not null,
    estado varchar (100) not null,
    autor varchar (200) not null,
    curso varchar (100) not null,


    primary key (id)


);