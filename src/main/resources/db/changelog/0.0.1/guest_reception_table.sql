create schema guest AUTHORIZATION postgres;

create table guest.guest(
	id uuid primary key
	, resident_id uuid not null references common.user
	, guest_name varchar(255) not null
	, guest_id_card_number varchar(20) not null
	, guest_address varchar(500) not null
	
	, created_by_id uuid not null references common.user
	, created_date timestamp without time zone not null
	, last_modified_by_id uuid references common.user
	, last_modified_date timestamp without time zone
	
	, UNIQUE(resident_id, guest_id_card_number)
);

create table guest.guest_log(
	id uuid primary key
	, guest_id uuid not null references guest.guest
	, log_in_date timestamp without time zone not null
	, log_out_date timestamp without time zone
	, dormitory_id uuid not null references common.dormitory
	
	, created_by_id uuid not null references common.user
	, created_date timestamp without time zone not null
	, last_modified_by_id uuid references common.user
	, last_modified_date timestamp without time zone
);
