#!/bin/bash

npm install

npm run build 

docker build -t grand-projet/client . 