ARG image_name=techecommerceserver

FROM openjdk:17
EXPOSE 8888
ADD target/TechEcommerceServer-0.0.1-SNAPSHOT.jar TechEcommerceServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TechEcommerceServer-0.0.1-SNAPSHOT.jar"]

LABEL image.name=$image_name

#docker-compose up -d