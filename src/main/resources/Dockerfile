FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD WebSSH.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]