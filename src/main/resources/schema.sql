create table authorities
(
    id         int auto_increment
        primary key,
    name       varchar(50)                           not null,
    created_at timestamp                             not null,
    updated_at timestamp default current_timestamp() not null,
    deleted_at timestamp                             null
);

create index authorities_name_index
    on authorities (name);

create table user_authorities
(
    id           int auto_increment
        primary key,
    user_id      int                                   not null,
    authority_id int                                   not null,
    created_at   timestamp                             not null,
    updated_at   timestamp default current_timestamp() not null,
    deleted_at   timestamp                             null
);

create index user_authorities_user_id_authority_id_index
    on user_authorities (user_id, authority_id);

create table users
(
    id           int auto_increment
        primary key,
    name         varchar(50)  not null,
    username     varchar(50)  not null,
    email        varchar(255) not null,
    phone_number varchar(15)  null,
    password     varchar(255) not null,
    created_at   timestamp    not null,
    updated_at   timestamp    not null,
    deleted_at   timestamp    null
);

create index users_id_username_email_phone_number_index
    on users (id, username, email, phone_number);


