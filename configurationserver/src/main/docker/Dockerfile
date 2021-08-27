FROM openjdk:8-jdk-alpine
#RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/configuracionservice
ADD configurationserver-0.0.1-SNAPSHOT.jar /usr/local/configuracionservice/
ADD run.sh run.sh
RUN chmod +x run.sh
RUN apk update
RUN apk add git
RUN apk add --no-cache bash
CMD ./run.sh