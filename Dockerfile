FROM amazoncorretto:21.0.8-alpine3.22

WORKDIR /app

COPY /target/expense_tracker-0.0.1-SNAPSHOT.jar app.jar

CMD ["java","-jar","app.jar","--spring.profiles.active=docker"]