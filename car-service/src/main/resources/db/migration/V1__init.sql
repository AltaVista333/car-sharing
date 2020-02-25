create sequence car_sequence start with 1 increment by 1 cache 1;

alter sequence car_sequence owner to postgres;

create table car
(
    id         bigint       not null
        constraint car_pkey
            primary key,
    adress     varchar(255) not null,
    car_status varchar(255) not null,
    model      varchar(255) not null
);

alter table car
    owner to postgres;

alter sequence car_sequence owned by car.id;
