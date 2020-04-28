# Kijiji-Data-Analyzer

A Web Application displaying Kijiji's Long Term Rental Listing statistics. 
Chart View displays graphical representation of the details of rental listings.
Map View displays where the listings are located on Google Map.
Data will be saved after each crawling.

Server runs on localhost:8080.

## Overview

### frontend
- Front-end application using React

### src
- Back-end application using Java Spring Boot

### First Time Running
- {depth}: Depth of your own choice in numerical value
- Only second command needed for data update

```sh
mvn clean install 
mvn spring-boot:run -Dspring-boot.run.arguments="https://www.kijiji.ca/b-apartments-condos/canada/c37l0 {depth}"
```

### Development

```sh
mvn spring-boot:run
```

### Production 

```sh
mvn package && java -jar target/Kijiji-Data-Analyzer-0.1.0.jar
```
