FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/customerservice/
ADD customer-0.0.1-SNAPSHOT.jar /usr/local/customerservice/
ADD run.sh run.sh
RUN chmod +x run.sh
RUN apk add --no-cache bash
CMD ./run.sh