#!/bin/bash

npm run build 

docker build -t grand-projet/client . 