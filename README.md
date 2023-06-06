## ReadFile

This is a sample project for the task of counting the number of pages in files of different formats.

### Introduction

The purpose of this project is to develop a Java application that can determine the total number of pages in a given directory structure. The application supports files in the formats of Word (docx) and PDF.

The application can be run as a console application or as a REST service. It uses the Spring Framework for building the REST service.

### Requirements

- Java JDK 11 or higher
- Maven

### Getting Started

Follow the steps below to get started with the project:

1. Clone the repository:

   ```bash
   git clone https://github.com/GitDevLoR/readfile.git
   ```

2. Navigate to the project directory:

   ```bash
   cd readfile
   ```

3. Build the project using Maven:

   ```bash
   mvn clean package
   ```

### Running as a Console Application

To run the application as a console application, use the following command:

```bash
java -jar target/readfile-1.0.0.jar <directory-path>
```

Replace `<directory-path>` with the path to the root folder containing the documents you want to count the pages for.

### Running as a REST Service

To run the application as a REST service, use the following command:

```bash
java -jar target/readfile-1.0.0.jar
```

The REST service will be available at `http://localhost:8080`. You can use API clients like cURL or Postman to make HTTP requests to the service.

#### API Endpoints

- `GET /count?directoryPath=<directory-path>`: Returns the total number of documents and pages in the specified directory. Replace `<directory-path>` with the path to the root folder containing the documents.

You can also access the Swagger UI documentation to explore and interact with the API. Open the following URL in your web browser:

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Extensibility

The application can be easily extended to support other types of document formats by implementing additional document parsers. The existing implementation supports Word (docx) and PDF formats.

### Testing

The project includes JUnit tests for the `DocumentService` and `DocumentController` classes. You can run the tests using the following command:

```bash
mvn test
```
