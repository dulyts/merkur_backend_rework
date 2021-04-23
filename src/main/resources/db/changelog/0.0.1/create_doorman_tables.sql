create schema key_publish AUTHORIZATION postgres;

create table key_publish.key(
	id uuid primary key
	, place_id uuid not null references common.place
);

create table key_publish.rent_log(
	id uuid primary key
	, key_id uuid not null references key_publish.key
	, renter_id uuid references common.user
	, rent_start_date timestamp without time zone not null
	, rent_end_date timestamp without time zone
	
	, created_by_id uuid not null references common.user
	, created_date timestamp without time zone not null
	, last_modified_by_id uuid references common.user
	, last_modified_date timestamp without time zone
);

create table key_publish.place_permission(
	id uuid primary key
	, place_id uuid not null references common.place
	, user_id uuid not null references common.user
);
