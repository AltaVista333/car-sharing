create sequence client_sequence start with 1 increment by 1 cache 1;
alter sequence client_sequence owner to postgres;

create sequence bill_sequence start with 1 increment by 1 cache 1;
alter sequence bill_sequence owner to postgres;

create table client (
    id         bigint       not null
        constraint client_pkey
            primary key,
    name     varchar(255) not null,
    surname     varchar(255) not null,
    email     varchar(255) not null unique);

alter table client owner to postgres;
alter sequence client_sequence owned by client.id;

create table bill
(
    id        bigint         not null
        constraint bill_pkey
            primary key,
    amount    numeric(19, 2) not null,
    paid_off  boolean        not null,
    rent_id   bigint         not null,
    client_id bigint         not null
        constraint fk15eeb38ihayj5k2th7twinfju
            references client
);

alter table bill owner to postgres;
alter sequence bill_sequence owned by bill.id;