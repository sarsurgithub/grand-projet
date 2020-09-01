#!/bin/bash

mvn clean package

docker build -t grand-projet/server .