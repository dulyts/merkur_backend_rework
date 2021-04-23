ALTER TABLE common."user"
    ALTER COLUMN kar TYPE character varying (100) COLLATE pg_catalog."default";

ALTER TABLE common."user"
    ALTER COLUMN szak TYPE character varying (100) COLLATE pg_catalog."default";

ALTER TABLE common."user"
    ADD COLUMN card_code character varying(16);