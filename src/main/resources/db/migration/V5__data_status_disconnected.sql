alter table fire_fighter_data drop constraint fire_fighter_data_status_check;
alter table fire_fighter_data add constraint fire_fighter_data_status_check check (status in ('OK', 'NO_DATA', 'DISCONNECTED'));
