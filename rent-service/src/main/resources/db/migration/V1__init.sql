create sequence event_sequence;
alter sequence event_sequence owner to postgres;

create sequence rent_sequence;
alter sequence rent_sequence owner to postgres;

create table rent
(
    id      bigint not null
        constraint rent_pkey
            primary key,
    car_id  bigint,
    user_id bigint
);

alter table rent
    owner to postgres;

create table event
(
    id              bigint not null
        constraint event_pkey
            primary key,
    local_date_time timestamp,
    name            varchar(255),
    rent_id         bigint not null
        constraint fkcp0do5bid2si3q33jrejqxl48
            references rent
);

alter table event
    owner to postgres;

create table rent_event_list
(
    rent_id       bigint not null
        constraint fkqf1j4ony8xvpnl6vbn9es3lup
            references rent,
    event_list_id bigint not null
        constraint uk_17pfs3n6e59j01p2oofisfedt
            unique
        constraint fkiisa0pulg80oqe0o4l02dqmxo
            references event,
    constraint rent_event_list_pkey
        primary key (rent_id, event_list_id)
);

alter table rent_event_list
    owner to postgres;

