drop table fire_fighter_data cascade;

create table fire_fighter_data (
  id bigserial primary key,
  ff_id text not null,
  status text not null check (status in ('OK', 'NO_DATA')),
  timestamp timestamptz not null,
  read boolean not null default 'false',
  heart_rate int,
  step_count int
);

alter sequence fire_fighter_data_id_seq restart with 5001;
