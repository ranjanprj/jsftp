FROM openjdk:17.0.1-jdk-slim
WORKDIR /opt
ENV PORT 8888
EXPOSE 8888
COPY java-sftpserver-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
CMD ["java", "-jar", "app.jar"]