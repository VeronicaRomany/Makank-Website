create schema Makank_DB;
use Makank_DB;

create table users (
user_id int unsigned auto_increment,
name varchar(32) not null,
username varchar(32) not null,
email varchar(255),
address varchar(128),
profile_pic_link varchar(255),
user_password varchar(64) not null,
user_description varchar(255),
constraint users_pk primary key (user_id),
constraint email_check check (email is null or email like '%_@__%.__%'),
constraint username_unique unique(username),
constraint email_unique unique(email)
);

create table phone_numbers (
user_id int unsigned,
phone_number varchar(11),
constraint phone_pk primary key (user_id, phone_number),
constraint phone_users_fk foreign key (user_id) references users(user_id) on update restrict on delete restrict,
constraint phone_digit check (char_length(phone_number) = 9 or (phone_number REGEXP '^[0-9]{11}$')), 
constraint homephone_digit check (char_length(phone_number) = 11 or (phone_number REGEXP '^[0-9]{9}$')) 
);

create table posts (
post_id int unsigned auto_increment,
seller_id int unsigned,
post_date date not null default (current_date),
proprty_type varchar(9) not null,
price int unsigned not null,
city varchar(20) not null,
property_address varchar(128) not null,
area smallint unsigned not null,
info varchar(255),
has_pictures bit(1) not null,
rooms tinyint unsigned not null,
bathrooms tinyint unsigned not null,
for_rent bit(1) not null,
g_maps_link varchar(255),
constraint posts_pk primary key (post_id),
constraint posts_users_fk foreign key (seller_id) references users(user_id) on update restrict on delete restrict
);

create table saved_items (
user_id int unsigned,
post_id int unsigned,
constraint saved_pk primary key (user_id, post_id),
constraint saved_users_fk foreign key (user_id) references users(user_id) on update restrict on delete restrict,
constraint saved_posts_fk foreign key (post_id) references posts(post_id) on update cascade on delete cascade
);

create table property_pictures (
post_id int unsigned,
pic_link varchar(255),
constraint saved_pk primary key (post_id, pic_link),
constraint property_posts_fk foreign key (post_id) references posts(post_id) on update cascade on delete cascade
);

create table apartment (
post_id int unsigned,
has_elevator bit(1) not null,
apartment_level tinyint unsigned,
for_students bit(1) not null,
constraint apartment_pk primary key (post_id),
constraint apartment_posts_fk foreign key (post_id) references posts(post_id) on update cascade on delete cascade
);

create table villa (
post_id int unsigned,
villa_levels tinyint unsigned,
has_pool bit(1),
has_garden bit(1),
constraint villa_pk primary key (post_id),
constraint villa_posts_fk foreign key (post_id) references posts(post_id) on update cascade on delete cascade
);

create view login_credentials as
select user_id, username, user_password
from users;

select *
from login_credentials;

/*
insert into users values(null, 'yara hossam', 'yaraboo', 'yarahossam@gmail.com', 'alex', null, 'passwordyara', null);
insert into users values(null, 'yara hossam', 'yaraboo2', 'yarahossam@alexu.edu.eg', 'alex', null, 'passwordyara', null);
insert into users values(null, 'yara hossam', 'yaraboo3', 'yarahossam@alex.eg', 'alex', null, 'passwordyara', null);
insert into users values(null, 'yara hossam', 'yaraboo4', 'yarahossam@alex.eg', 'alex', null, 'passwordyara', null);
*/
select *
from users;

select *
from phone_numbers;

