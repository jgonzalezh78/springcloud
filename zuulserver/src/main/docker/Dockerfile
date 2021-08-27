FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/zuulservice
ADD zuulserver-0.0.1-SNAPSHOT.jar /usr/local/zuulservice/
ADD run.sh run.sh
RUN chmod +x run.sh
RUN apk add --no-cache bash
CMD ./run.sh
