# Backend

Please read the CONTRIBUTING.md first.

## Development

1. Update the project to the latest version: `git pull`
2. Select an issue from the `issues` tab. Let everyone know you are working on this issue, add a comment to the issue and you will be assigned.
3. Switch to develop branch.: `git checkout develop`
4. Create a new branch for this issue: `git checkout -b <issue-id>-<short-description>`
5. After you are done with the task, send the changes:

```bash
git add .
git commit -m "<issue-id>: <A short meaningful description>"
git push -u origin <branch-name>
```

After successfully pushed the changes to the issue branch, open a pull request from the new branch to `develop` branch. Optionally add a reviewer(for now @Mert18). That's it.

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