# API Automation Project

This project is designed to automate API testing using Java, Maven, and TestNG. It includes test cases for creating and retrieving user details, with test data separated into JSON files.

## Project Structure

```markdown
src
├── main
│   ├── java
│   │   └── com
│   │       └── api
│   │           └── automation
│   │               ├── client
│   │               │   └── ApiClient.java
│   │               └── utils
│   │                   └── FileReaderUtil.java
│   │                   └── JsonUtils.java
│   └── resources
├── test
│   ├── java
│   │   └── com
│   │       └── api
│   │           └── automation
│   │               ├── CreateUserTests.java
│   │               └── GetUserTests.java
│   └── resources
│       ├── validUserPayload.json
│       ├── userPayloadWithoutTitle.json
│       ├── userPayloadWithInvalidTitle.json
│       ├── userPayloadWithMissingFirstName.json
│       ├── userPayloadWithWrongLengthFirstName.json
│       ├── userPayloadWithMissingLastName.json
│       ├── userPayloadWithWrongLengthLastName.json
│       ├── userPayloadWithInvalidEmail.json
│       ├── userPayloadWithInvalidDateFormat.json
│       ├── userPayloadWithInvalidRating.json
│       ├── userPayloadWithZeroRating.json
│       ├── userPayloadWithOneRating.json
│       ├── userPayloadWithTenRating.json
│       └── userPayloadWithMissingPassword.json
└── pom.xml
```

## Prerequisites

- Java 8 or higher
- Maven
- IntelliJ IDEA or any other IDE

## Setup

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Open the project in IntelliJ IDEA or any preferred IDE.

3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Running Tests

To run the tests, use the following Maven command:
```sh
mvn test
```
## Project Components
### ApiClient
The `ApiClient` class handles the API requests and responses.  

### FileReaderUtil
The `FileReaderUti`l class reads JSON files from the `test/resources` directory.  

### JsonUtils
The `JsonUtils` class provides utility methods for parsing JSON responses.  

### Test Classes
`CreateUserTests`: Contains test cases for creating users.
`GetUserTests`: Contains test cases for retrieving user details.

### Test Data
Test data is stored in JSON files in the `test/resources` directory. 

