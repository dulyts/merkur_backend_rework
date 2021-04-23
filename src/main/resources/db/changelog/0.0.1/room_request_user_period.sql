create table room_assignment.user_period(
	user_id uuid NOT NULL references common.user
	, period_id uuid NOT NULL references room_assignment.period
	, PRIMARY KEY(user_id, period_id)
);