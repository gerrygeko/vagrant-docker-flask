#!/usr/bin/env bash
docker build -t py-webserver .

docker rmi $(docker images | grep "^<none>")
