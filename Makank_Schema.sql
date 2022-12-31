create schema Makank_DB;
use Makank_DB;

create table users (
user_id int unsigned auto_increment,
name varchar(32) not null,
username varchar(32) not null,
email varchar(255),
phone_number varchar(11),
address varchar(128),
profile_pic_link varchar(255),
user_password varchar(64) not null,
user_description varchar(255),
constraint users_pk primary key (user_id),
constraint email_check check (email is null or email like '%_@__%.__%'),
constraint username_unique unique(username),
constraint email_unique unique(email),
constraint phone_digit check (char_length(phone_number) = 9 or (phone_number REGEXP '^[0-9]{11}$')), 
constraint homephone_digit check (char_length(phone_number) = 11 or (phone_number REGEXP '^[0-9]{9}$')) 
);

create table property (
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
constraint posts_pk primary key (post_id),
constraint posts_users_fk foreign key (seller_id) references users(user_id) on update restrict on delete restrict
);

create table saved_items (
user_id int unsigned,
post_id int unsigned,
constraint saved_pk primary key (user_id, post_id),
constraint saved_users_fk foreign key (user_id) references users(user_id) on update restrict on delete restrict,
constraint saved_posts_fk foreign key (post_id) references property(post_id) on update cascade on delete cascade
);

create table property_pictures (
post_id int unsigned,
pic_link varchar(255),
constraint saved_pk primary key (post_id, pic_link),
constraint property_posts_fk foreign key (post_id) references property(post_id) on update cascade on delete cascade
);

create table apartment (
post_id int unsigned,
has_elevator bit(1) not null,
apartment_level tinyint unsigned,
for_students bit(1) not null,
constraint apartment_pk primary key (post_id),
constraint apartment_posts_fk foreign key (post_id) references property(post_id) on update cascade on delete cascade
);

create table villa (
post_id int unsigned,
villa_levels tinyint unsigned,
has_pool bit(1),
has_garden bit(1),
constraint villa_pk primary key (post_id),
constraint villa_posts_fk foreign key (post_id) references property(post_id) on update cascade on delete cascade
);

create view login_credentials as
select user_id, username, user_password
from users;

create or replace function post_large_view(postID int8)
returns json as $$

declare propertyType varchar(9);
declare result json;
 begin
        select property.property_type into propertyType
        from property
        where property.post_id = postID;
        if(propertyType = 'villa') then
                select json_build_object('seller_id', p.seller_id, 'seller_name', u.name, 'phone_number', u.phone_number, 'email', u.email, 
                'seller_profile_pic', u.profile_pic_link, 'property_address', p.property_address, 'price', p.price, 'city', p.city,
                'area', p.area, 'rooms', p.rooms, 'bathrooms', p.bathrooms, 'date', p.post_date, 'info', p.info, 'rent', p.for_rent,
                'type', p.property_type, 'garden', v.has_garden, 'pool', v.has_pool, 'level', v.villa_levels, 'pictures', array_agg(pp.pic_link))
                as temp into result
                from property p natural join villa v join users u on p.seller_id = u.user_id left outer join property_pictures pp on pp.post_id = p.post_id
                where p.post_id = postID
                group by p.post_id, u.user_id, v.post_id;
                return result;  
        else
                select json_build_object('seller_id', p.seller_id, 'seller_name', u.name, 'phone_number', u.phone_number, 'email', u.email,
                'seller_profile_pic', u.profile_pic_link, 'property_address', p.property_address, 'price', p.price, 'city', p.city,
                'area', p.area, 'rooms', p.rooms, 'bathrooms', p.bathrooms, 'date', p.post_date, 'info', p.info, 'rent', p.for_rent,
                'type', p.property_type, 'level', a.apartment_level, 'student_housing', a.for_students, 'elevator', a.has_elevator, 
                'pictures', array_agg(pp.pic_link)) as temp into result
                from property p natural join apartment a join users u on p.seller_id = u.user_id left outer join property_pictures pp on pp.post_id = p.post_id
                where p.post_id = postID
                group by p.post_id, u.user_id, a.post_id;
                return result;  
              
        end if;
       
 end;
$$ language plpgsql;
