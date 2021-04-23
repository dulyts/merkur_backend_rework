drop schema if exists event_tracker CASCADE;
create schema event_tracker AUTHORIZATION postgres;

create table event_tracker.event_data(
	id uuid primary key
	, description text
	, start_time timestamp without time zone not null
	, end_time timestamp without time zone not null
	, place_id uuid not null references common.place
	, expected_number_of_outsiders int not null
	, expected_number_of_dormitory_student int not null
	, paid_for_outsiders boolean not null
	, motivation text not null
	, created_by_id uuid not null references common.user
	, created_date timestamp without time zone not null
);
