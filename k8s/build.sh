#!/bin/bash
rm Dockerfile*
rm java-sftpserver-0.0.1-SNAPSHOT.jar*w
wget http://$ipaddr:9000/java-sftpserver/k8s/Dockerfile
wget http://$ipaddr:9000/java-sftpserver/target/java-sftpserver-0.0.1-SNAPSHOT.jar
docker build -t javasftp .
# docker kill mytest
# docker rm mytest
# docker run --name mytest -t javasftp:latest -p 8888:8888 
# minikube image load javasftp:latest