
create table user
(
   id integer not null,
   email varchar(255) not null,
   password varchar(255) not null,
   primary key(id)
);

create table product
(
   id integer not null,
   title varchar(255) not null,
   quantity integer not null,
   price float not null,
   primary key(id)
);


create table cart
(
session_id integer not null,
product_id integer not null,
quantity integer not null,

)

