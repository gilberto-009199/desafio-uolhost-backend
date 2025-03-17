#!/bin/sh
sudo docker build --no-cache --tag=myserver_uolhost:latest .

sudo docker run --rm -it -p 8080:8080 myserver_uolhost:latest