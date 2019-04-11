create role postgraphile login password 'xyz';

create role anonymous;
grant anonymous to postgraphile;

-- Revoke privileges to force whitelisting
alter default privileges revoke execute on functions from public;

grant usage on schema public to anonymous