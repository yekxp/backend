# Sosyal App

## Development
1. Çözeceğin "issue"yi seç.
2. "develop" branch'ine git: `git checkout develop`
3. Branch'i güncelle: `git pull`
4. Çözeceğin "issue" için 'develop' branch'inden yeni bir branch aç:  `git checkout -b <issue-id>-<description>`
5. Geliştirmelerini yaptıktan sonra yeni branch'e "push"la.
```bash
git add .
git commit -m "<issue-id>: <A short meaningful description>"
git push -u origin <branch-name>
```
Bu komutları girdikten sonra terminalde çıkan linki takip ederek ya da Github repo arayüzü üzerinden "pull request" aç.
Pull request'ini yeni branch'ten -> `develop` branch'ine merge olacak şekilde ayarlamalısın.
'Reviewer' olarak birini ekle (Şu an için @Mert18). Kodun review olmadan merge edilmemeli.
Review kabul edildikten sonra merge etmeyi unutma.

Kod içerisindeki değişken isimleri, fonksiyon isimleri ve yorumlar İngilizce dilinde olmalı.

Örnek src/main/resources/application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/sosyalapp
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
