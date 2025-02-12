# books-store-training

## book store training

This project's purpose is to familiarise the interns with the use of version control tools (GitHub) and the methodology behind managing and working on a project.
The project has been developed using Java.

This project vontains the following elements:

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

## Libraries
Libraries are published in Maven Central:
- The only library used so far is JUnit

You may need to add that repository explicitly in your build, if you haven't done it before.

repositories { mavenCentral() }


Then add the libraries in the usual way.

// In Gradle Kotlin 
dependencies {
  testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

### Local Development

To build and test the project locally, you can use the following commands:

./gradlew build
./gradlew test

