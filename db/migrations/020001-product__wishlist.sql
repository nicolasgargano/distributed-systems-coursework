create table product__wishlist
(
    id          uuid primary key default uuid_generate_v1mc(),
    product_id  uuid not null references product (id),
    wishlist_id uuid not null references wishlist (id)
);

grant select, insert, delete, update on table product__wishlist to anonymous;
