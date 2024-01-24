# Online Calculator Test Automation

This repository contains automated tests for an online calculator. The tests are developed using Selenium WebDriver with Java, TestNG framework, and Docker for containerization.

## Prerequisites

To run the tests locally, ensure the following prerequisites are met:

1. **Java 17:**
    - [Download and Install Java](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Mozilla Firefox:**
    - [Download and Install Mozilla Firefox](https://www.mozilla.org/en-US/firefox/new/)

3. **Apache Maven:**
    - [Download and Install Apache Maven](https://maven.apache.org/download.cgi)
    - [Maven Installation Guide](https://maven.apache.org/install.html)

## Setup and Configuration

Follow these steps to set up and configure the required tools:

### 1. Java 17

Ensure Java 17 is installed and configured on your machine. You can check the Java version using the following command:

```bash
java -version
```

### 2. Mozilla Firefox

Ensure Mozilla Firefox is installed on your machine. You can download it from the [official website](https://www.mozilla.org/en-US/firefox/new/).

### 3. Apache Maven

Ensure Apache Maven is installed and configured on your machine. You can check the Maven version using the following command:

```bash
mvn -version
```

## Running the Tests

To run the tests locally, use the following Maven command: NB: Make sure you are in the project directory to run the commands below.

```bash
mvn test -P<ProfileName>
```

Replace `<ProfileName>` with the desired Maven profile. Maven profiles have been created for different test suites as outlined in the [Test Plan](#test-plan). For example:

- To run positive tests:

  ```bash
  mvn test -PPositiveTestCases
  ```

- To run negative tests:

  ```bash
  mvn test -PNegativeTestCases
  ```

- To run boundary tests:

  ```bash
  mvn test -PBoundaryTestCases
  ```

## Tools Used

The following tools and frameworks are used for test automation:

1. **Selenium WebDriver using Java:**
    - [Selenium WebDriver Documentation](https://www.selenium.dev/documentation/en/webdriver/)

2. **TestNG Framework:**
    - [TestNG Documentation](https://testng.org/doc/documentation-main.html)

3. **TestNG Listeners:**
    - [TestNG Listeners Documentation](https://testng.org/doc/documentation-main.html#testng-listeners)

4. **Docker for Containerization:**
    - [Docker Documentation](https://docs.docker.com/)

5. **Maven:**
    - [Apache Maven Documentation](https://maven.apache.org/guides/index.html)

## Contributors

- Stephen K. Mutinda (Quality Assurance Engineer)


---
