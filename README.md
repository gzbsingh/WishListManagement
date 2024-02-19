
# WishList Management System

The WishList Management System is a Java-based web application built with Spring Boot and Spring Data JPA that allows users to manage their wish lists. Users can add, edit, and delete items from their wish lists, as well as perform other administrative tasks.

## Features

- **User Authentication**: Secure user authentication and authorization using Spring Security.
- **CRUD Operations**: Allows users to perform CRUD (Create, Read, Update, Delete) operations on their wish list items.
- **Database Persistence**: Uses MySQL database for persistent storage of wish list items and user information.
- **RESTful API**: Provides a RESTful API for accessing wish list data programmatically.
- **Logging**: Uses logging to record application events and errors for debugging and monitoring purposes.
- **Testing**: Includes unit tests for repository, controller and service classes using Mockito and JUnit.

## Technologies Used

- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- Spring Security
- MySQL
- Mockito
- JUnit

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- MySQL Server
- Maven
## Database scheme

  ![db_digram](https://github.com/gzbsingh/WishListManagement/assets/39863817/027d616b-1736-4f56-abdc-4f4c0ec930c1)

### Configuration

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/WishList-Management.git
   Navigate to the project directory:
   cd WishList-Management
   Update the application.properties file with your MySQL database configuration:
 

   spring.datasource.url=jdbc:mysql://localhost:3306/WishList
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql: true

   logging.level.org.springframework.security=DEBUG
   logging.level.root=INFO

   logging.file.name==myapp.log
   logging.file.path==/var/log/myapp
   spring.jpa.properties.hibernate.globally_quoted_identifiers=true

2. Running the Application:

    Build the project using Maven:
     ``` bash
       mvn clean install

       Run the application:
       java -jar target/WishList_Management-0.0.1-SNAPSHOT.jar
3. Access the application in your web browser or PostMan(testing tools) at http://localhost:8080.


### Logging

The application uses logging to record events and errors. Log files are stored in the /var/log/myapp directory. You can configure the logging settings in the application.properties file.


Testing
To run the unit tests for the repository classes, use the following command:

      ```bash
      mvn test


# Contributing
Contributions are welcome! If you would like to contribute to this project, please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature/your-feature-name).

Commit your changes (git commit -am 'Add new feature').

Push to the branch (git push origin feature/your-feature-name).

Create a new Pull Request.

 # License

This project is licensed under the MIT License - see the LICENSE file for details.
