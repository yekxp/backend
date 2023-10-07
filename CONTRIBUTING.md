# Contributing

You are welcome.

## Coding Standards
There are a few things to consider if you want to contribute to the project.

1. Naming Variables: Use "camelCase" format when naming variables.
2. Do not leave unnecessary spaces before lines.
3. Write comment if you think others might not understand the code part easily.
4. Clear all "System.out.println"s before opening a pull request. "Sout"s only should be used when debugging.
5. Use "LOGGER" when in important places. (E.g entity saved to database, mail send etc.)  
6. If you are adding a new method, create a "Request" and "Response" dtos if needed. Do not use "model" classes directly in controllers.


## Setting Up The Development Environment

1. Install `maven` and ensure its installed. `mvn --version` should return a meaningful response in the terminal.
2. Install Postgres(and pgAdmin), set a password for your "postgres" user, and change the credentials at `src/main/resources/application.properties` accordingly.
3. Since the development is in progress, switch to "develop" branch: `git checkout develop`.
4. And run the application: `mvn spring-boot:run`
5. You can try out endpoints at: `/swagger-ui/index.html`
