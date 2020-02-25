create sequence client_sequence start with 1 increment by 1 cache 1;

alter sequence client_sequence owner to postgres;

create table client
(
    id         bigint       not null
        constraint client_pkey
            primary key,
    name     varchar(255) not null,
    surname     varchar(255) not null,
    email     varchar(255) not null unique,
    status varchar(255) not null
);

alter table client owner to postgres;

alter sequence client_sequence owned by client.id;
