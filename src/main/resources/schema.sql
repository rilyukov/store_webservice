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

create table session
(
id integer not null,
user_id integer not null,
exp_time date not null,
cart_id integer not null
primary key(id),
FOREIGN KEY (user_id) REFERENCES user(id),
foreign key (cart_id) references cart(id)
);

create table cart
(
id integer not null,

)
