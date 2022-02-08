#!/bin/sh

export DOCKER_HOST_IP=$(hostname -I | awk '{print $1}')

echo ------------------------------------------------
echo Build Kafka Cluster
echo ------------------------------------------------
docker-compose --compatibility -f docker-compose.yml up --build -d