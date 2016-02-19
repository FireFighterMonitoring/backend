#!/usr/bin/env bash

PG_VERSION=9.5
PG_CONF="/etc/postgresql/$PG_VERSION/main/postgresql.conf"
PG_HBA="/etc/postgresql/$PG_VERSION/main/pg_hba.conf"
PG_DIR="/var/lib/postgresql/$PG_VERSION/main"

if [ ! -f /opt/flags/root_init_done ]; then
  export DEBIAN_FRONTEND=noninteractive

  echo 'deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main' > /etc/apt/sources.list.d/pgdg.list
  wget --quiet -O - http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc | apt-key add -

  apt-get update
  apt-get upgrade
  apt-get -y install "postgresql-$PG_VERSION" "postgresql-contrib-$PG_VERSION"

  sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" "$PG_CONF"

  echo 'host all all all md5' >> "$PG_HBA"

  service postgresql restart
  su - postgres -c psql < /vagrant/postgres-init.sql

  mkdir -p /opt/flags
  touch /opt/flags/root_init_done
fi

