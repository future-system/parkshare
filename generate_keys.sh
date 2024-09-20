#!/bin/bash

cd src/main/resources;

openssl genrsa -out rsa.private 2048;
openssl rsa -in rsa.private -out rsa.public -pubout -outform PEM;

ls -l;

cd ../../..;

echo "Keys generated successfully";