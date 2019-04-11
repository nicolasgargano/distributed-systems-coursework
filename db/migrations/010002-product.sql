create table product
(
    id          uuid primary key default uuid_generate_v1mc(),
    name        text not null,
    description text not null
);

grant select, insert, delete, update on table product to anonymous;
