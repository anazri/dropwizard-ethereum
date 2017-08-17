# MicroService for Ethereum
 
Developed during Blockchain Summer School Hackathon 2017 Copenhagen, Denmark

## Preconditions:

a. Oracle Java JDK 1.8 (set JAVA_HOME)

b. Apache Maven 3 (set MAVEN_HOME or M2_HOME)

## execute microservice

1. from  dropwizard-ethereum\src\main\resources\config.yml have a look at mysql credentials
2. `cd dropwizard-ethereum`
3. `mvn clean package -U`
4. `java -jar target/bix-api-auth.jar server src/main/resources/config.yml`
5. Web-UI access to bix-api-auth - `http://localhost:9000/swagger`