@echo off

FOR /F "tokens=4 delims= " %%i in ('route print ^| find " 0.0.0.0"') do set localIp=%%i

echo Your IP Address is: %localIp%

mvn clean install && minikube ssh "export ipaddr=%localIp% && rm -f build.sh && wget http://$ipaddr:9000/java-sftpserver/k8s/build.sh && sh build.sh" && kubectl apply -f k8s/