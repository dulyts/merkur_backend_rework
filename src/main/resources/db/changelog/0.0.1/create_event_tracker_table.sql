create schema event_tracker AUTHORIZATION postgres;

create table event_tracker.place(
	id uuid primary key
	, short_name varchar(50) not null
	, name varchar(255) not null
	, size int DEFAULT 99999 -- default 99999
);

create table event_tracker.event(
	id uuid primary key
	, place_id uuid references event_tracker.place;
	, name varchar(255) not null
	, shortDescription text
	, organizer uuid not null references common.code
	, organizer_other varchar(255)
	, start_time timestamp not null
	, end_time timestamp not null
	, calculated_number_of_people int not null
	, supporters varchar(1000)
	, patrons varhcar(1000)
	, financing uuid not null references common.code
	, press_release boolean not null
);

create table event_tracker.equippment(
	id uuid primary key
	, name varchar(255)
	, java_type text
	, code_type_id references common.code_type -- only if selectable
);

create table event_tracker.equippment_request(
	id uuid primary key
	, equippment_id uuid references event_tracker.equippment
	, event_id uuid references event_tracker.event
	, count int -- only if countable equippment
);