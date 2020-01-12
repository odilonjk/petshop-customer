FROM adoptopenjdk/openjdk11-openj9:latest
RUN mkdir /opt/app
COPY build/libs/shop-platform-customer.jar /opt/app
CMD ["java", "-jar", "/opt/app/shop-platform-customer.jar"]