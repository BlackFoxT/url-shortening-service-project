# URL Shortening Service

## Overview
This project is a simple URL shortening service built with Spring Boot and MySQL. It allows users to shorten long URLs, retrieve the original URL, update existing short URLs, delete URLs, and get statistics on the number of times a short URL has been accessed.

## Features
- Create a short URL
- Retrieve the original URL
- Update an existing short URL
- Delete an existing short URL
- Get statistics on a short URL (access count)

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/BlackFoxT/url-shortening-service-project
   ```
2. Navigate to the project directory:
   ```sh
   cd url-shortening-service-project
   ```
3. Configure the `application.properties` file for MySQL connection:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   spring.datasource.username=root
   spring.datasource.password=password
   ```
4. Build and run the project:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### 1. Create Short URL
**Endpoint:** `POST /shorten`
**Request Body:**
```json
{
  "url": "https://www.example.com/some/long/url"
}
```
**Response:**
```json
{
   "id": 1,
   "url": "https://www.example.com/some/long/url",
   "shortCode": "bc2afc",
   "createdAt": "2025-03-05T03:39:19.4872767",
   "updatedAt": "2025-03-05T03:39:19.4872767"
}
```

### 2. Retrieve Original URL
**Endpoint:** `GET /shorten/{shortCode}`
Returns the original URL associated with the given short code.

### 3. Update Short URL
**Endpoint:** `PUT /shorten/{shortCode}`
**Request Body:**
```json
{
  "url": "https://www.example.com/some/updated/url"
}
```
Updates the URL associated with the given short code.

### 4. Delete Short URL
**Endpoint:** `DELETE /shorten/{shortCode}`
Deletes the short URL.

### 5. Get URL Statistics
**Endpoint:** `GET /shorten/{shortCode}/stats`
**Response:**
```json
{
   "id": 1,
   "url": "https://www.example.com/some/updated/url",
   "shortCode": "bc2afc",
   "createdAt": "2025-03-05T03:39:19.487277",
   "updatedAt": "2025-03-05T03:41:35.517996",
   "accessCount": 2
}
```
Returns the statistics of the given short URL, including the access count.

