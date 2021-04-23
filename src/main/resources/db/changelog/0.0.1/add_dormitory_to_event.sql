alter table event_tracker.event_data add column dormitory_id uuid not null references common.dormitory;
