# Отправка данных в TOPIC и QUEUE

```
curl -X GET http://localhost:8080/add
```

# Тестовая работа с глобальной транзакцией

- [https://www.javaworld.com/article/2077963/distributed-transactions-in-spring--with-and-without-xa.html](https://www.javaworld.com/article/2077963/distributed-transactions-in-spring--with-and-without-xa.html)
- [https://medium.com/preplaced/distributed-transaction-management-for-multiple-databases-with-springboot-jpa-and-hibernate-cde4e1b298e4](https://medium.com/preplaced/distributed-transaction-management-for-multiple-databases-with-springboot-jpa-and-hibernate-cde4e1b298e4)
- [https://gist.github.com/ezhov-da/418c44cf417e1723e0666f6e4e45866f](https://gist.github.com/ezhov-da/418c44cf417e1723e0666f6e4e45866f)
- [https://medium.com/@kirill.sereda/%D1%82%D1%80%D0%B0%D0%BD%D0%B7%D0%B0%D0%BA%D1%86%D0%B8%D0%B8-%D0%B2-spring-framework-a7ec509df6d2](https://medium.com/@kirill.sereda/%D1%82%D1%80%D0%B0%D0%BD%D0%B7%D0%B0%D0%BA%D1%86%D0%B8%D0%B8-%D0%B2-spring-framework-a7ec509df6d2)

Пример транзакционного класса [ru.ezhov.springjms.TestService](src/main/java/ru/ezhov/springjms/TestService.java)

Удачное выполнение сценария:
1. Начинается транзакция
1. Первая отправка в TOPIC
1. Сохранение в БД
1. Вторая отправка в TOPIC
1. Отправка в QUEUE

```
// Все успешно
curl -X GET http://localhost:8080/tran?what=g
// Ошибка после первой отправки в TOPIC 
curl -X GET http://localhost:8080/tran?what=f
// Ошибка после сохранения в БД 
curl -X GET http://localhost:8080/tran?what=j
// Ошибка после второй отправки в TOPIC
curl -X GET http://localhost:8080/tran?what=s
// Ошибка после отправки в QUEUE
curl -X GET http://localhost:8080/tran?what=q
```
