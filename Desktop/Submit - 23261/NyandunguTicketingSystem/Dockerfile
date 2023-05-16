FROM openjdk:17-jdk
COPY NyandunguTicketingSystem/target/NyandunguTicketingSystem-1.0-SNAPSHOT.jar /app.jar
COPY NyandunguTicketingSystem/src/main/resources/application.yml /application.yml
EXPOSE 1020
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=?Mfptnj123@hview2022
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/test?useUnicode=true&characterEncoding=UTF-8
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]