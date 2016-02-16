create table fire_fighter_data (
  id bigserial primary key,
  ff_id text not null,
  heart_rate int not null,
  timestamp timestamptz not null
);

alter sequence fire_fighter_data_id_seq restart with 5001;
