
# Music Metadata Service

This project is a backend service designed to manage music metadata for a streaming platform. It provides capabilities to add tracks, edit artist names, fetch an artist's tracks, and determine the "Artist of the Day." The application is implemented using **Scala** with **Spring WebFlux**, offering a reactive and efficient solution for handling metadata operations.

## Features

1. **Add a New Track:** Endpoint to add a track to an artist's catalog, capturing details such as title, genre, and length.

2. **Edit Artist Name:** Endpoint to update an artist's name to accommodate aliases.

3. **Fetch Artist Tracks:** Retrieve all tracks associated with a specific artist.

4. **Artist of the Day:** Feature to rotate through artists daily to ensure fair visibility.

## Technology Stack

- **Programming Language:** Scala
- **Framework:** Spring Boot
- **Build Tool:** Gradle
- **Database:** In-memory H2 Database (for local development and testing)
- **Testing:** ScalaTest

## Installation and Setup

### Prerequisites

- Java 11+
- Gradle 7+

### Steps to Run

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/music-metadata-service.git
   cd music-metadata-service
   ```

2. Build the project:
   ```
   gradle clean build
   ```

3. Run the application:
   ```
   gradle bootRun
   ```

4. Access the API at `http://localhost:8080/api/v1`.

## Why MongoDB Was Considered

Although an in-memory H2 database is used for local development, the ideal choice for production would be **MongoDB**, for the following reasons:

1. **Scalability:** MongoDB offers horizontal scaling capabilities suitable for large datasets with frequent read and write operations.
2. **Schema Flexibility:** The document-based nature of MongoDB allows for flexible data structures that can evolve over time.
3. **Performance:** Optimized read and write performance, especially for metadata-heavy applications.
4. **High Availability:** Built-in replication and sharding capabilities ensure high availability and fault tolerance.
5. **Rich Querying:** Support for complex queries, aggregations, and indexing features.
