create table todo_list_users (
  id serial primary key,
  name varchar(200) unique,
  email varchar(200) unique,
  password varchar(200)
);