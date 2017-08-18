# MicroService for Ethereum
 
Developed during Blockchain Summer School Hackathon 2017 Copenhagen, Denmark

## Preconditions:

a. Oracle Java JDK 1.8 (set JAVA_HOME)

b. Apache Maven 3 (set MAVEN_HOME or M2_HOME)


## generate java class for solidity contract (.sol to .java)

1. copy your solidity file into `src\main\resource\`
2. generate by: `mvn web3j:generate-sources`
3. if build sucess, then go to package `generated.contract`

## execute microservice

1. from  dropwizard-ethereum\src\main\resources\config.yml have a look at mysql credentials
2. `cd dropwizard-ethereum`
3. `mvn clean package -U`
4. `java -jar target/bix-api-auth.jar server src/main/resources/config.yml`
5. Web-UI access to bix-api-auth - `http://localhost:9000/swagger`