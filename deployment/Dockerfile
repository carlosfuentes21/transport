FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8081

COPY applications/app-service/build/libs/transport.jar app.jar
CMD ["/bin/sh"]
ENTRYPOINT ["java", "-jar","-Duser.timezone=America/Bogota", "/app.jar"]
