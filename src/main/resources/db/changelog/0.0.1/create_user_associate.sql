CREATE TABLE common.user_associate (
	id uuid primary key
	, neptun varchar(6) not null
	, gender_id uuid  not null
	, dormitory_id uuid not null
);