FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-arithmetic-calculator

COPY ./pom.xml /app
COPY ./msvc-arithmetic-calculator/.mvn ./.mvn
COPY ./msvc-arithmetic-calculator/mvnw .
COPY ./msvc-arithmetic-calculator/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./msvc-arithmetic-calculator/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-arithmetic-calculator/target/msvc-arithmetic-calculator-1.0-SNAPSHOT.jar .
EXPOSE 8001

CMD ["java", "-jar", "msvc-arithmetic-calculator-1.0-SNAPSHOT.jar"]