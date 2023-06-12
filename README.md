# Spring Boot Many-to-Many Relationship with Extra Column

This is a sample project that demonstrates how to implement a many-to-many relationship with an extra column in a Spring Boot application.

## Overview

In certain scenarios, a many-to-many relationship between two entities requires additional information to be stored in an intermediate table. This extra column can hold additional attributes or properties specific to the relationship. This project provides an example of implementing such a relationship using Spring Boot and JPA.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- Spring Boot

## Getting Started

Follow the steps below to set up and run the project on your local machine.

1. Clone the repository:

git clone https://github.com/your-username/your-repository.git

2. Navigate to the project directory:


3. Build the project using Maven:


4. Run the application:


## Project Structure

The project follows a standard Maven project structure:

├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.labelvie.lablecious.backend
│ │ │ ├── controllers # REST controllers
│ │ │ ├── models # Entity models
│ │ │ ├── repositories # JPA repositories
│ │ │ └── services # Business logic services
│ │ └── resources
│ │ ├── application.properties # Application configuration
│ │ ├── data.sql # Sample data script
│ │ └── schema.sql # Database schema script
│ └── test
│ └── java
│ └── com.labelvie.lablecious.backend
│ └── services # Unit tests
├── pom.xml # Project dependencies and configuration
└── README.md # Project documentation

## Implementation

The implementation of the many-to-many relationship with an extra column involves the following steps:

1. Define the entity classes (`Menu`, `Plate`, `MenuPlates`) to represent the database tables and relationships.

2. Use JPA annotations (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`, etc.) to define the entity relationships and mappings.

3. Create repository interfaces (`MenuRepository`, `PlateRepository`, `MenuPlatesRepository`) that extend the `JpaRepository` interface to perform CRUD operations on the entities.

4. Implement service classes (`MenuService`, `PlateService`, `MenuPlatesService`) to encapsulate the business logic and provide methods for interacting with the repositories.

5. Create REST controllers (`MenuController`, `PlateController`, `MenuPlatesController`) to handle incoming requests and invoke the corresponding service methods.

## Usage

The project exposes RESTful APIs for managing menus, plates, and the relationship between them.

### Menu APIs

- `GET /menus`: Get all menus.
- `GET /menus/{id}`: Get a menu by ID.
- `POST /menus`: Create a new menu.
- `PUT /menus/{id}`: Update an existing menu.
- `DELETE /menus/{id}`: Delete a menu.

### Plate APIs

- `GET /plates`: Get all plates.
- `GET /plates/{id}`: Get

