# Stage 1: Build con JDK 21 + Maven
FROM eclipse-temurin:21-jdk AS BUILD
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Runtime con JRE 21 y wait-for-it
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=BUILD /app/target/*.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

EXPOSE 8080

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "-t", "300", "--", "java", "-jar", "app.jar"]
