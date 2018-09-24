CREATE TABLE IF NOT EXISTS app_user (
  id_user serial primary key,
  login varchar(20) not null,
  pass varchar(20) not null
);

CREATE TABLE IF NOT EXISTS categories (
  id_category serial primary key,
  category varchar(20)
);

CREATE TABLE IF NOT EXISTS expences (
  id_amount serial primary key,
  purchase_date date,
  amount int not null,
  comment varchar(50),
  id_user int,
  id_category int,
  foreign key (id_user) references app_user(id_user),
  foreign key (id_category) references categories (id_category)
);

insert into app_user (login, pass)
values ('dima', '1234'), ('lisa', '5678');

insert into categories (category)
values ('rent'),('food'),('clothes');

insert into expences (purchase_date, amount, id_user, id_category)
values ('2018-09-05',100, 1, 2),
       ('2018-09-01', 150, 2, 3);

select * from expences;

select login, purchase_date, category, amount from app_user
join expences on app_user.id_user=expences.id_user
join categories on categories.id_category=expences.id_category
order by purchase_date;