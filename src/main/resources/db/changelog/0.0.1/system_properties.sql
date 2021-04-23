CREATE TABLE common.module(
	id uuid primary key
	, key varchar(100) not null
	, name varchar(100) not null
);

CREATE TABLE common.system_property(
	id uuid primary key
	, key varchar(100) not null
	, value varchar(250) not null
	, type varchar(20) not null -- string, int, float, boolean, date
	, module_id uuid not null references common.module --TODO
	, dormitory_id uuid not null references common.dormitory
	, created_by_id uuid not null references common.user
	, created_date timestamp without time zone not null
	, last_modified_by_id uuid not null references common.user
	, last_modified_date timestamp without time zone not null
);

insert into common.module values(uuid_generate_v4(), 'washing', 'Mosógépfoglaló');
--
insert into common.system_property values(
	uuid_generate_v4(), 'min_reservation_minute', '30', 'int'
	, (select id from common.module where key = 'washing'), (select id from common.dormitory where short_name = 'KCSSK')
	, (select id from common.user where neptun = 'CUO1DG'), current_timestamp
	, (select id from common.user where neptun = 'CUO1DG'), current_timestamp);
insert into common.system_property values(
	uuid_generate_v4(), 'max_reservation_minute', '240', 'int'
	, (select id from common.module where key = 'washing'), (select id from common.dormitory where short_name = 'KCSSK')
	, (select id from common.user where neptun = 'CUO1DG'), current_timestamp
	, (select id from common.user where neptun = 'CUO1DG'), current_timestamp);