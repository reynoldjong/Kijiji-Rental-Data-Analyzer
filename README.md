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
- Get your Google API Key, enable GeoCoding and Google Map
- ${your_api_key}: your Google API Key
```sh
sed -i '' 's/api_key/${your_api_key}/g' frontend/src/config/secret.json
mvn clean install
```

### Crawl Data And Enable View
- ${depth}: Depth of your own choice in numerical value
```sh
mvn spring-boot:run -Dspring-boot.run.arguments="https://www.kijiji.ca/b-apartments-condos/canada/c37l0 ${depth}"
```

### Development

```sh
mvn spring-boot:run
```
