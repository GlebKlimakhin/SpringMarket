create table users (
                       id                          bigserial primary key,
                       email                       varchar(100),
                       password                    varchar(100),
                       created_at                  timestamp default current_timestamp,
                       updated_at                  timestamp default current_timestamp
);

-- insert into users (email, password)
-- values
--     ('Max@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq'),
--     ('Alex@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq'),
--     ('John@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq'),
--     ('Anna@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq'),
--     ('Jakob@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq'),
--     ('Chloe@rr.ru', '$2a$12$6eRVJJhUq0pKVEb5PuN9iumO9sW7maZiEENeYxsRXZxpAh1v.7LSq');

create table roles(
                      id                      serial primary key,
                      name                    varchar(50) not null
);

insert into roles (name) values ('ROLE_USER');

create table users_roles(
                            user_id             bigint,
                            role_id             int,

                            primary key (user_id, role_id),
                            foreign key (user_id) references users (id),
                            foreign key (role_id) references roles (id)
);

-- insert into users_roles (user_id, role_id)
-- values
--     (1, 1),
--     (2, 2),
--     (5, 3);