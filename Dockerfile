FROM openjdk:17
ADD target/springboot-testdrivendev.jar springboot-testdrivendev.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar","springboot-testdrivendev.jar"]