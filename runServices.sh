#!/usr/bin/env bash
docker run -d -p 80:80 --name webapp jetty-webapp
docker run -d -p 5000:80 --name  webserver py-webserver
