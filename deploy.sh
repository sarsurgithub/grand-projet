#!/bin/bash

cd ./spring-server
./build-image.sh

cd ../client/grand-projet
./build-image.sh

cd ../..
docker-compose -f docker-compose-prod.yml up -d