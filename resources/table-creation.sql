-- This is a single line SQL comment

/*
 * This is a multi-line
 * SQL comment
 */

drop table if exists user_roles;
drop table if exists app_users;

create table user_roles(
    id 			VARCHAR primary key,
    role_name	VARCHAR unique not null
);

create table app_users(
    id			VARCHAR,
    first_name	VARCHAR not null,
    last_name	VARCHAR not null,
    email		VARCHAR(256) unique not null,
    username    VARCHAR(25) unique not null,
    role		VARCHAR,
    
    constraint app_users_pk
    primary key (id)
    
);

alter table app_users 
add column password VARCHAR not null;

alter table app_users 
add foreign key (role) 
references user_roles(id);

