CREATE SEQUENCE IF NOT EXISTS issue_tracker.issue_number_seq;
/;

CREATE OR REPLACE FUNCTION bi_issue() RETURNS trigger AS
$BODY$
BEGIN
 NEW.issue_number = nextval('issue_tracker.issue_number_seq');
 RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;
/;

CREATE OR REPLACE TRIGGER bi_issue
  BEFORE INSERT
  ON issue_tracker.issue
  FOR EACH ROW
  EXECUTE PROCEDURE bi_issue();
/;