# Дипломный проект по профессии «Тестировщик».

[Описание задания](https://github.com/netology-code/qa-diploma).

1. [План тестирования](https://github.com/valeit98/Project_Diploma/blob/main/Plan.md);
2. [Отчёт о проведённом тестировании](https://github.com/valeit98/Project_Diploma/blob/main/Report.md);
3. [Отчёт о проведённой автоматизации](https://github.com/valeit98/Project_Diploma/blob/main/Summary.md).

## Запуск проекта:
## Требования к рабочей станции:
1. Установлена операционная система, не ниже `OS Windows 10` `версии 22H2`, оперативная память не менее `16Гб`;
2. Установлен `Веб-браузер` `Google Chrome` версии `130.0.6723.59`;
3. Установлен `Веб-браузер` `Mozila Firefox` версии `131.0.3`;
4. Установлена среда разработки `IntelliJ IDEA 2024.1.4 (Community Edition)`;
5. Установлена платформа контейнеризации `Docker Desktop 4.34.3`. В неё с `DockerHub` загружены образы `node-app:1.0`, `mysql:8.0`, `postgres:12.11-alpine`;
6. Сохранён на компьютер с Github [репозиторий проекта](https://github.com/valeit98/Project_Diploma).

## Запуск тестов
1. Запустить MySQL, PostgreSQL, NodeJS через терминал в первой вкладке командой:
   `docker compose up` ;
![]()

2. Во второй вкладке терминала запустить тестируемое приложение:
   * Для MySQL: 
   `Java -jar artifacts/aqa-shop.jar --spring.profiles.active=msql` ;
   * Для PostgreSQL: `Java -jar artifacts/aqa-shop.jar --spring.profiles.active=psql` ;
![]()

3. Проверить работоспособность системы. Приложение должно быть доступно по ссылке:`http://localhost:8080/` ;
4. В третьей вкладке терминала запустить тесты:
   * Для MySQL: `./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/msql -D schemas=msql` ;
   * Для PostgreSQL: `./gradlew clean test -D db.url=jdbc:postgresql://localhost:5432/psql -D schemas=public` .
![]()

## Перезапуск тестов и приложения:
Для остановки приложения в каждой вкладке терминала необходимо ввести команду `Ctrl+С`, подтвердить остановку работы и повторить необходимые действия из предыдущих пунктов с другой БД.

## Формирование отчёта о тестировании:
Для формирования отчёта Allure после прохождения тестов каждой БД вводим команду: `./gradlew allureServe` .


![]()
![]()