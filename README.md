# Library Management System API

## Introduction

The **Library Management System API** is a Spring Boot application designed to manage books, patrons, and borrowing records. It provides RESTful endpoints that allow CRUD operations on these entities, enabling librarians to effectively manage the library's inventory and track borrowing activities. The system uses an H2 in-memory database for storage, ensuring ease of setup and testing.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed on your local machine:

- **Java Development Kit (JDK)** 17 or later
- **Apache Maven** 3.6.0 or later
- **Git**

### Installation

1. **Clone the Repository:**
   ```bash
   git clone git@github.com:Mahmoudovic26/Library_Managemnet_System_API.git
   cd Library_Management_API
   ```

2. **Build the Project:**
   Use Maven to build the project and resolve dependencies.
   ```bash
   mvn clean install
   ```

3. **Run the Application:**
   Start the Spring Boot application.
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080`.

### H2 Database Console

The application uses an H2 in-memory database. You can access the H2 database console at:
```
http://localhost:8080/h2-console
```
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## API Documentation

The API provides the following endpoints:

### Book Management Endpoints

- **GET** `/api/books`
  - **Description**: Retrieve a list of all books.
  - **Response**: JSON array of books.

- **GET** `/api/books/{id}`
  - **Description**: Retrieve details of a specific book by ID.
  - **Response**: JSON object of the book.

- **POST** `/api/books`
  - **Description**: Add a new book to the library.
  - **Request Body**: JSON object containing book details.
  - **Sample Request**:
    ```json
    {
      "title": "Effective Java",
      "author": "Joshua Bloch",
      "publicationYear": 2018,
      "isbn": "9780134685991"
    }
    ```

- **PUT** `/api/books/{id}`
  - **Description**: Update an existing book's information.
  - **Request Body**: JSON object containing updated book details.
  - **Sample Request**:
    ```json
    {
      "title": "Effective Java, 3rd Edition",
      "author": "Joshua Bloch",
      "publicationYear": 2018,
      "isbn": "9780134685991"
    }
    ```

- **DELETE** `/api/books/{id}`
  - **Description**: Remove a book from the library.

### Patron Management Endpoints

- **GET** `/api/patrons`
  - **Description**: Retrieve a list of all patrons.
  - **Response**: JSON array of patrons.

- **GET** `/api/patrons/{id}`
  - **Description**: Retrieve details of a specific patron by ID.
  - **Response**: JSON object of the patron.

- **POST** `/api/patrons`
  - **Description**: Add a new patron to the system.
  - **Request Body**: JSON object containing patron details.
  - **Sample Request**:
    ```json
    {
      "name": "John Doe",
      "contactInformation": "john.doe@example.com"
    }
    ```

- **PUT** `/api/patrons/{id}`
  - **Description**: Update an existing patron's information.
  - **Request Body**: JSON object containing updated patron details.
  - **Sample Request**:
    ```json
    {
      "name": "John Doe",
      "contactInformation": "john.doe@newemail.com"
    }
    ```

- **DELETE** `/api/patrons/{id}`
  - **Description**: Remove a patron from the system.

### Borrowing Endpoints

- **POST** `/api/borrow/{bookId}/patron/{patronId}`
  - **Description**: Allow a patron to borrow a book.
  - **Response**: JSON object of the borrowing record.
  - **Sample Request**:
    ```json
    {
      "bookId": 1,
      "patronId": 2
    }
    ```

- **PUT** `/api/return/{bookId}/patron/{patronId}`
  - **Description**: Record the return of a borrowed book by a patron.
  - **Response**: JSON object of the updated borrowing record.
  - **Sample Request**:
    ```json
    {
      "returnDate": "2024-08-10"
    }
    ```

## Postman Collection

You can find the Postman collection for this API [here](https://documenter.getpostman.com/view/36637805/2sA3s1osD9). This collection includes all the endpoints, request bodies, and sample responses. To use it:

1. **Import the Collection**:
   - Download the collection as a JSON file or directly import it into Postman using the link provided.
   
2. **Test the API**:
   - Use the collection to test the API endpoints, view responses, and understand the expected data format.

## Running Tests

The project includes unit tests to ensure the functionality of the API endpoints. You can run the tests using Maven:

```bash
mvn test
```

This command will execute all the tests and provide a summary of the results.

## Optional Features

The API is designed with the following optional features in mind:
- **Logging**: Aspect-Oriented Programming (AOP) can be used for logging method calls, exceptions, and performance metrics.
- **Caching**: Spring's caching mechanisms can be utilized to improve performance by caching frequently accessed data.
- **Transaction Management**: Declarative transaction management is implemented using Spring's `@Transactional` annotation to ensure data integrity.

## Conclusion

This Library Management System API provides a solid foundation for managing a library's inventory and borrowing records. By following the documentation and using the provided Postman collection, you can easily interact with the API and extend its functionality as needed. 

Feel free to explore, test, and customize the application according to your requirements. If you encounter any issues or have suggestions for improvements, contributions are welcome via pull requests.
