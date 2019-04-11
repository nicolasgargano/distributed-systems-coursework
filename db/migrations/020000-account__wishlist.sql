create table account__wishlist
(
    id uuid primary key default uuid_generate_v1mc(),
    account_id  uuid not null references account (id),
    wishlist_id uuid not null references wishlist (id)
);

grant select, insert, delete, update on table account__wishlist to anonymous;
