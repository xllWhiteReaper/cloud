#!/bin/bash

export LOCALSTACK_DOCKER_NAME=fake_aws
docker-compose -f ../docker-files/docker-compose.dev.yml up --build