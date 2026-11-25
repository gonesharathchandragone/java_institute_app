FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM tomcat:9.0-jdk11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/sharath-devops-institute.war /usr/local/tomcat/webapps/ROOT.war
ENV DB_URL="jdbc:mysql://db:3306/sharath_institute?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
EXPOSE 8080
CMD ["catalina.sh", "run"]