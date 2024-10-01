# AltenKata

## Development

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override
settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/AltenKata-0.0.1-SNAPSHOT.jar
```

