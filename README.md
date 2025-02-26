# books-store-training

## book store training

This project's purpose is to familiarise the interns with the use of version control tools (GitHub) and the methodology behind managing and working on a project.
The project has been developed using Java.

This project contains the following elements:

Models:
- Book
- User

Service:
- Bookstore

## Features

1.Book Management:

- Add books with title, author
- List available books
- Search for books by title or author

2.User Management:

- Register students with name and registration number
- List registered students

3.Loans and Returns:

- Allow students to request a book
- Register the loan date and return the book
- Display borrowed books

4.Data Persistence:

- Initially in memory
- Save information in SQLite
- Load data when the program starts.

5.Tests

- Checks if the project runs properly


## Run

### Docker

Creation of a file named [docker-compose.yml](docker-compose.yml) for the database.

### Flyway

Creation of flyway (src/main/java/com/xebia/migrations) for the migrations

```
public class FlywayMigration {
  public static void main(String[] args) {}

  public static void migrate(String url, String username, String password) {
    Flyway flyway =
        Flyway.configure()
            .dataSource(url, username, password)
            .locations("classpath:db/migrations")
            .load();

    flyway.migrate();
  }
}
```


## Libraries
Libraries are published in Maven Central:
- JUnit for memory tests
- Flyway for migrations
- postgres for the database
- Testcontainers for database test

You may need to add that repository explicitly in your build, if you haven't done it before.

```
repositories { mavenCentral() }
```
Then add the libraries in the usual way.

```

[libraries]
junit-jupiter-bom = { module = "org.junit:junit-bom", version.ref = "junit" }
flyway = { module = "org.flywaydb:flyway-core", version.ref = "flyway" }
postgresql= {module = "org.postgresql:postgresql", version.ref = "postgre"}
testcontatiners = {module = "org.testcontainers:postgresql", version.ref = "testcontainers"}

```

Then add the respective dependencies
```
dependencies {
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(libs.flyway)
    implementation(libs.postgresql)
    testImplementation(libs.testcontatiners)
}
```

### Local Development

To build and test the project locally, you can use the following commands:

- ./gradlew build
- ./gradlew test

To properly execute the app you must use the following command:

- docker compse up

