create table room_assignment.race_room_request(
	id uuid primary key
	, user_id uuid not null references common.user
    , room_id uuid references common.place
    , request_time timestamp NOT NULL
    , period_id uuid NOT NULL references room_assignment.period
);
