#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "hire-a-senior" -f /data/schema.sql
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "hire-a-senior" -f /data/data.sql