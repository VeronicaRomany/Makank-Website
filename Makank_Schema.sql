create schema Makank_DB;
use Makank_DB;

create table users (
user_id int unsigned auto_increment,
name varchar(32),
username varchar(32) not null,
email varchar(255),
address varchar(128),
profile_pic_link varchar(255),
user_password varchar(64) not null,
user_description varchar(255),
constraint users_pk primary key (user_id),
constraint email_check check (email like '%_@__%.__%'),
constraint username_unique unique(username),
constraint email_unique unique(email)
);

create table phone_numbers (
user_id int unsigned,
phone_number varchar(11),
constraint phone_pk primary key (user_id, phone_number),
constraint phone_users_fk foreign key (user_id) references users(user_id) on update restrict on delete restrict,
constraint phone_digit check (phone_number REGEXP '^[0-9]{12}$')
);

create table posts (
post_id int unsigned auto_increment,
seller_id int unsigned,
post_date date not null,
proprty_type varchar(10) not null,
price int unsigned not null,
city varchar(20) not null,
property_address varchar(128),
area smallint unsigned not null,
info varchar(255),
has_pictures bit(1),
rooms tinyint unsigned not null,
bathrooms tinyint unsigned,
rent bit(1),
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

