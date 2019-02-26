#!/usr/bin/env bash
docker build -t jetty-webapp .

docker rmi $(docker images | grep "^<none>")