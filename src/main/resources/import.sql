insert into shiptype (shipTypeCode, description, version) values ('FFE', 'Fast Ferry', 1);
insert into shiptype (shipTypeCode, description, version) values ('FER', 'Ferry', 1);
insert into shiptype (shipTypeCode, description, version) values ('CON', 'Container Ship', 1);
insert into countries (iso_code, name, version) values ('DK', 'Denmark', 1);
insert into countries (iso_code, name, version) values ('ES', 'Spain', 1);
insert into ships (ship_id, imo_code, name, gross_tons, date_built, flag_ISO_CODE, shipType_shipTypeCode, version) values (-1, 9043952, 'Patricia Olivia', 3454, '1992-07-01', 'ES', 'FFE', 1);
insert into ships (ship_id, imo_code, name, gross_tons, date_built, flag_ISO_CODE, shipType_shipTypeCode, version) values (-2, 9304631, 'Albaycin', 7499, '2004-01-01', 'ES', 'FER', 1);
insert into ships (ship_id, imo_code, name, gross_tons, date_built, flag_ISO_CODE, shipType_shipTypeCode, version) values (-3, 9321483, 'Emma Maersk', 170794, '2006-01-01', 'DK', 'CON', 1);