insert into common.code_type values(uuid_generate_v4(), 'PLACE_TYPE', 'Helység típusok');

insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'ERASMUS', 'Erasmus', 'Erasmus-os szoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'KLUB', 'Klub', 'Klubszoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'FOGORVOSI_RENDELO', 'Fogorvosi rendelő', 'Fogorvosi rendelő');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'KONYHA', 'Konyha', 'Konyha');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'DB_IRODA', 'DB Iroda', 'Diákbizottsági iroda');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'SZOLGALATI', 'Szolgálati', 'Szolgálati szoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'SZEMETLEDOBO', 'Szemétledobó', 'Szemétledobó');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'SZERVERTEREM', 'Szerverterem', 'Szerverterem');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'BETEG', 'Betegszoba', 'Betegszoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'MOSDO', 'Mosdó', 'Mosdó');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'SZOBA', 'Szoba', 'Lakószoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'TANULO', 'Tanuló', 'Tanulószoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'VENDEG', 'Vendégszoba', 'Vendégszoba');
insert into common.code values(uuid_generate_v4(), (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE'), 'LEPCSOHAZ', 'Lépcsőház', 'Lépcsőház');

alter table common.place add column place_type_id uuid references common.code;

update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'ERASMUS') WHERE room_type = 'ERASMUS';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'KLUB') WHERE room_type = 'KLUB';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'FOGORVOSI_RENDELO') WHERE room_type = 'FOGORVOSI RENDELŐ';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'KONYHA') WHERE room_type = 'KONYHA';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'DB_IRODA') WHERE room_type = 'DB_IRODA';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'SZOLGALATI') WHERE room_type = 'SZOLGALATI';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'SZEMETLEDOBO') WHERE room_type = 'SZEMETLEDOBO';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'SZERVERTEREM') WHERE room_type = 'SZERVERTEREM';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'BETEG') WHERE room_type = 'BETEG';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'MOSDO') WHERE room_type = 'MOSDO';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'SZOBA') WHERE room_type = 'SZOBA';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'TANULO') WHERE room_type = 'TANULO';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'VENDEG') WHERE room_type = 'VENDEG';
update common.place set place_type_id = (SELECT id FROM common.code WHERE code_type_id = (SELECT id FROM common.code_type WHERE name = 'PLACE_TYPE') AND code = 'LEPCSOHAZ') WHERE room_type = 'LEPCSOHAZ';

alter table common.place alter column place_type_id SET NOT NULL;
alter table common.place drop column room_type;