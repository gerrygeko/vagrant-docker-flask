#!/usr/bin/env bash
docker build -t flask-webapp .

docker rmi $(docker images | grep "^<none>")
