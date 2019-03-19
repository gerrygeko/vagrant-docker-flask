#!/usr/bin/env bash
docker run -d -p 8080:80 --name webapp --network mynet jetty-webapp
docker run -d -p 5000:80 --name  webserver --network mynet py-webserver
