create table account
(
    id   uuid primary key default uuid_generate_v1mc(),
    name text not null
);

grant select, insert, delete, update on table account to anonymous;