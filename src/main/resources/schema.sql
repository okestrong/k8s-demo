create table if not exists posts
(
    id         bigint auto_increment
        primary key,
    title      varchar(100)                          not null,
    content    varchar(1000)                         not null,
    created_at timestamp default current_timestamp() not null
);

