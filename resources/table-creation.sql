drop table if exists user_roles;
drop table if exists app_users;

create table user_roles(
    id 			VARCHAR primary key,
    role_name	VARCHAR unique not null
);

create table app_users(
    id			varchar,
    first_name	varchar not null,
    last_name	varchar not null,
    email		varchar(256) unique not null,
    username    varchar(25) unique not null,
    role		varchar,
    
    constraint app_users_pk
    primary key (id)
    
);

alter table app_users 
add column password varchar not null;

alter table app_users 
add foreign key (role) 
references user_roles(id);

create table categories(
    id              varchar primary key,
    category_name   varchar unique not null
);

create table flashcards(
    id              varchar primary key,
    question_text   varchar not null,
    answer_text     varchar not null,
    creator_id      varchar not null,
    category        varchar not null,

    constraint creator_fk
    foreign key (creator_id)
    references app_users (id),

    constraint category_fk
    foreign key (category)
    references categories (id)
);

create table study_sets(
    id          varchar primary key,
    name        varchar not null,
    owner_id    varchar not null,
    category    varchar not null,

    constraint owner_fk
    foreign key (owner_id)
    references app_users (id),

    constraint category_fk
    foreign key (category)
    references categories (id)
);

create table study_set_cards(
    study_set_id varchar,
    flashcard_id varchar,

    constraint study_set_cards_pk
    primary key (study_set_id, flashcard_id),

    constraint study_set_fk
    foreign key (study_set_id)
    references study_sets (id),

    constraint flashcard_fk
    foreign key (flashcard_id)
    references flashcards (id)
);