alter user postgres with password 'postgres';
create role feuer_moni login password 'feuer_moni';
create database feuer_moni with encoding='UTF8' owner=feuer_moni;
