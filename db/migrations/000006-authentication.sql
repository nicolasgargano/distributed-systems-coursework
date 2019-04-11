-- JWT Type


create type jwt_token as (
    role text,
    account_id uuid
    );