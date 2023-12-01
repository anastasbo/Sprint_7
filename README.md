# Sprint_7

Финальный проект 7 спринта. Тестирование сервиса Яндекс.Самокат.

# Технологии

| Наименование | Версия |
|--------------|--------|
| Java         |    11    |
| JUnit        |    4.13.1    |
| RestAssured  |    4.4.0    |
| Allure       |    2.15.0    |

## Документация

[Ссылка](https://qa-scooter.praktikum-services.ru/docs/) документация Яндекс.Самокат.

[Ссылка](http://qa-scooter.praktikum-services.ru) приложение Яндекс.Самокат.

### Запуск автотестов

Для запуска автотестов необходимо:

1. Скачать код

 ```sh
   git clone https://github.com/anastasbo/Sprint_7.git
   ```

2. Запустить команду в проекте

```sh
mvn clean test
```

3. Для создания отчета в Allure ввести команду

```sh
mvn allure:report
```