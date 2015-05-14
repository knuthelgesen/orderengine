create sequence seq_global_version;
create table EVENTS (
  global_version number,
  aggregate_id varchar(255),
  version number,
  aggregate_type varchar(255),
  data blob
);
