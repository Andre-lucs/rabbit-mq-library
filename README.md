# Rabbit Library API

## Overview

The Rabbit Library API allows users to request loans for books and check the status of their loan requests. This document describes the flow for requesting a book loan and checking if the request was accepted.

## Setup

**Docker compose**

Run this to start the RabbitMQ server and the API server:

````shell
docker-compose up
````

If you already have a RabbitMQ server running, you can start the API server with the following command:

**Windows**

````shell
./mvnw.cmd spring-boot:run
````

**Linux**

````shell
./mvnw spring-boot:run
````

## API Endpoints

### 1. View Available Books

**Request:**

```http request
GET http://localhost:8080/books
```

**Response:**

Returns a list of available books in the library.

### 2. Request a Book Loan

**Request:**

```http request
POST http://localhost:8080/loans
```

**Body:**

```json
{
  "bookTitle": "1984",
  "requester": "Andre"
}
```

**Response:**

- **201 Created:** Loan request sent successfully. The response includes a `Location` header with the URL to check the requester's loan requests.
- **404 Not Found:** If the requested book does not exist.

**Example Response Header:**

```
Location: http://localhost:8080/loans/requester/Andre
```



### 3. Check Loan Requests by Requester

**Request:**

```http request
GET http://localhost:8080/loans/requester/Andre
```

**Response:**

Returns a list of loan requests made by the specified requester, including their status.
