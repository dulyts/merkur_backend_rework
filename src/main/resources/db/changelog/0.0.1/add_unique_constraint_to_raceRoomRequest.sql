alter table room_assignment.race_room_request add constraint race_room_request_unique_constraint unique(user_id, period_id);