create table wishlist
(
    id    uuid primary key default uuid_generate_v1mc(),
    title text not null
);

grant select, insert, delete, update on table wishlist to anonymous;
