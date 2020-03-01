create sequence rent_sequence start with 1 increment by 1 cache 1;;
alter sequence rent_sequence owner to postgres;

create table rent (
    id         bigint       not null
        constraint rent_pkey
            primary key,
    car_id     bigint       not null,
    end_rent   timestamp,
    start_rent timestamp    not null,
    status     varchar(255) not null,
    user_id    bigint       not null);
alter table rent
    owner to postgres;

alter sequence rent_sequence owned by rent.id;

