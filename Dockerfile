ARG image_name=techecommerceserver

FROM openjdk:17
EXPOSE 8888
ADD target/TechEcommerceServer-0.0.1-SNAPSHOT.jar TechEcommerceServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TechEcommerceServer-0.0.1-SNAPSHOT.jar"]
#CMD["--spring.datasource.url=jdbc:mysql://192.168.0.2:3300/techecommerce"]
LABEL image.name=$image_name

#docker build -t techecommerceserver .
#docker run -d -p 8888:8888 --name techecommerceserver techecommerceserver
