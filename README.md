👋 Hi, I'm [Mike](https://github.com/mikechristianson).

## Intro

I considered three different approaches, and worked on each of them. All use Kotlin.

1. CSV processing 
2. DataFrame
3. SQL

The code for these can be found in the commit history of this repository, with the SQL-backed approach having a minimally usable API. 

## How To

Requirements to run this application:
1. Java 21. If using [SDKMAN](https://sdkman.io), run `sdk env` to install automatically.
2. Docker and Docker Compose 

Run this application:
1. Build. `./gradlew build -x test`
2. Start db. `docker compose -f src/main/resources/docker-compose.yml up -d`
3. Run. `./gradlew bootRun` or `java -jar build/libs/backend-0.0.1-SNAPSHOT.jar`

Run tests: `./gradlew test`

Use the API:
- Single data type: `curl "http://localhost:8080/loan-stats/loan_amnt"`
- Multiple data types: `curl "http://localhost:8080/loan-stats/types?types=loan_amnt,int_rate,grade"`
- Summary: `curl "http://localhost:8080/loan-stats/"`

### CSV processing

My first approach started with OpenCSV. I quickly realized it was row-based, and I could not find a way to work with columns, directly. As such, my thinking was that I would have to put everything in memory and write a bunch of aggregating code, looping for each column. But, I decided early on that I wanted to avoid loading everything into memory, which lead me to remember my brief time with Spark.

### DataFrame

This approach began with [Kotlin DataFrame](https://kotlin.github.io/dataframe/overview.html#main-features-and-concepts). Of course, it would need an HTTP API in front of it. With some work, it could probably do the job fairly well. Then I hit a learning curve using the tool and observed that it's still a pretty [nascent project](https://kotlin.github.io/dataframe/columnstatistics.html), over all. Remembering the "2-3 hours" guideline, I realized that time was insufficient. This lead me to ask myself "What could I use that was expedient for me in creating an API?" Spring Boot. And, "What could I use that was expedient for me in aggregating data?" MySQL.

### SQL

My approach here evolved as I discovered the tools I'm quickest with are not well suited to the task of producing, say, the min, max, and mean for some number of arbitrary columns of the 100+ columns of loan data.

SQL-based approaches used:
1. JPA
2. `JdbcTemplate`
3. SQL string concatenation

So, it turns out that it is impossible to write a JPA query for arbitrary/dynamic columns. It can't be done with Spring's `JdbcTemplate`, either. Actually, JPA and Spring are incidental. It simply cannot be done in JDBC with proper parameterization: _nothing in the select part can be parameterized_.  

These row-based tools are not made for working with columns in a dynamic way. 

Ultimately, I ended up resorting to string concatenation to construct the SQL query. But, of course, that's susceptible to SQL injection.

## Concluding Thoughts

If I were to do this all over again, I would focus on the CSV processing approach. If I had copious amounts of time, I'd play with Kotlin DataFrame and see how far I could get.

The decision to absolutely avoid loading the entire file into memory did not serve me well. At the least, I could have spent some time going row-by-row building up a map of the data, with columns as collections stored in a map by data type key. I suspect it may be possible to calculate and aggregate for each data type as it goes along.

Thanks for reading!