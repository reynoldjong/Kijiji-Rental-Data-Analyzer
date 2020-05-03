# Kijiji-Rental-Data-Analyzer

A Web Application displaying Kijiji's Long Term Rental Listing statistics. 
Chart View consists of scatter plots, pie charts and table using the details of rental listings.
Map View displays the location of rental listings on Google Map.
Data will be saved after each crawling.

Server runs on localhost:8080.

## Overview

### frontend
- Front-end application using React

### src
- Back-end application using Java Spring Boot

### Design Pattern
- Builder (Model)
- Strategy (Crawler)
- Singleton (Google GeoCoding)

### First Time Running
- ${depth}: Depth of your own choice in numerical value
- Only second command needed for data update

```sh
mvn clean install 
mvn spring-boot:run -Dspring-boot.run.arguments="https://www.kijiji.ca/b-apartments-condos/canada/c37l0 ${depth}"
```

### Development

```sh
mvn spring-boot:run
```
