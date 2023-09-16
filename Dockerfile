FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
ARG JAR_FILE=build/libs/oxygen-spring-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} oxygen-spring.jar
ENTRYPOINT [ "java", "-jar", "/oxygen-spring.jar" ]