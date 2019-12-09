MariaDB Integration Testing
https://objectpartners.com/2017/06/19/using-mariadb4j-for-a-spring-boot-embedded-database/

Docker JDK
https://spring.io/guides/gs/spring-boot-docker/

MariaDB Docker
https://hub.docker.com/_/mariadb/

version '3.7'

services:

  db:
    restart: always
    image: mariadb
    expose: 3306
    ports: '3306:3306'
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes:
      - ./dbdata:/var/lib/mysql
      - ./src/main/resources/sql-scripts/init.sql:/script/init.sql
    command: "--init-file /script/init.sql"
      
 backend:
    restart: always
    build: .
    expose: 32411
    ports:
      - '32411:32411'
    links:
      - db
      
 frontend:
    restart: always
    build:
      - context: ../sample-login-react/
    expose: 3001
    ports:
      - '3001:3001'
    links:
      - backend
      
      
FROM openjdk:8-jdk-alpine

WORKDIR /backend
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /backend/app.jar
ENTRYPOINT ['java', '-Dspring.profiles.active=SIT', '-jar', '/backend/app.jar']


FROM node:12.2.0-alpine

WORKDIR /frontend
# add `/frontend/node_modules/.bin` to $PATH
ENV PATH /frontend/node_modules/.bin:$PATH
# install and cache app dependencies
COPY package.json /frontend/package.json
RUN npm install --silent
# RUN npm install react-scripts@3.0.1 -g --silent
# start app
CMD ["npm", "start"]
