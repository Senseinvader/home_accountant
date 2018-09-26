CREATE TABLE IF NOT EXISTS app_user (
  login varchar(20) not null primary key,
  pass varchar(20) not null
);

CREATE TABLE IF NOT EXISTS categories (
  category varchar(20) primary key
);

CREATE TABLE IF NOT EXISTS expences (
  id_amount serial primary key,
  purchase_date date,
  amount int not null,
  comment varchar(50),
  login varchar(20),
  category varchar(20),
  foreign key (login) references app_user(login),
  foreign key (category) references categories (category)
);

insert into app_user (login, pass)
values ('dima', '1234'), ('lisa', '5678');

insert into categories (category)
values ('rent'),('food'),('clothes');

insert into expences (purchase_date, amount, login, category)
values ('2018-09-05',100, 'dima', 'food'),
       ('2018-09-01', 150, 'lisa', 'clothes')
       ('2018-09-09', 100, 'dima', 'foo');

select * from expences;
TRUNCATE TABLE expences;

select login, purchase_date, category, amount from app_user
join expences on app_user.id_user=expences.id_user
join categories on categories.id_category=expences.id_category
order by purchase_date;