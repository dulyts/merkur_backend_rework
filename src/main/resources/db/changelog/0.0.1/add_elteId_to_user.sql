alter table common.user add column elte_id varchar(10);
update common.user set elte_id = 'null';
alter table common.user alter elte_id set not null;