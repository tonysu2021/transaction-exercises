#!/bin/sh

echo ------------------------------------------------
echo Create rabbitmq service
echo ------------------------------------------------
docker-compose --compatibility -f rabbitmq.yml up --build -d