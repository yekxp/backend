# Backend

Please read the CONTRIBUTING.md first.

## Running for frontend development (no postgres installation required):
1. Download the code.
2. Ensure that "docker" and "docker compose" is installed and you get meaningful response when you type `docker compose version` on terminal.
3. Run `docker compose up` in the root folder.
4. That's it! You will be able to access the application from `http://localhost:8080`.

## Development
1. Fork the project. While forking, disable "Copy only main branch" option.
2. Ensure that you configured your ssh keys with github: https://docs.github.com/en/authentication/connecting-to-github-with-ssh
3. Download the forked project with ssh option.
4. Go to `develop` branch: `git checkout develop`. And run `npm install` in the root folder of the project.
5. Update the project to the latest version: `git pull`
6. Select an issue from the `issues` tab. Let everyone know you are working on this issue, add a comment to the issue and you will be assigned.
7. Create a new branch for this issue: `git checkout -b <issue-id>-<short-description>`
8. After you are done with the task, send the changes:

```bash
git add .
git commit -m "<issue-id>: <A short meaningful description>"
git push -u origin <branch-name>
```

After successfully pushed the changes to the issue branch, open a pull request from forked repo's new branch to this repo's `develop` branch(You can do it via github interface). Add a reviewer(for now @Mert18). That's it.

Example application.properties:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/hire-a-senior
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html

jwt.secret=eyJhbGciOiJIUzI1NiyJ8zAeld3LfFTgPU3cAVxpKm7EnBVZxTWOVlCVI5kGg
# 1 day
jwt.accessExpirationDateInMs=86400000

# 3 days
jwt.refreshExpirationDateInMs= 259200000
sendgrid.key= <sendgrid-key>

email.from=mertplayschess@outlook.com

application.url=http://localhost:8080
``` 
