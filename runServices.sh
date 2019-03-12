#!/usr/bin/env bash
docker run -d -p 5000:80 jetty-webapp
docker run -d -p 80:80 py-webserver