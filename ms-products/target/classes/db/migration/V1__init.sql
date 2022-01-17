create table products (
                          id                          bigserial primary key,
                          title                       varchar(100),
                          price                       int,
                          created_at                  timestamp default current_timestamp,
                          updated_at                  timestamp default current_timestamp
);

insert into products (title, price)
values
    ('Chair', 100),
    ('Table', 200),
    ('Bookshelf', 150),
    ('Fridge', 1000),
    ('TV', 2000),
    ('Sofa', 300);