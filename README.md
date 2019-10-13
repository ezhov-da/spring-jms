# Отправка данных в TOPIC и QUEUE

```
curl -X GET http://localhost:8080/add
```

# Тестовая работа с глобальной транзакцией

- [https://www.javaworld.com/article/2077963/distributed-transactions-in-spring--with-and-without-xa.html](https://www.javaworld.com/article/2077963/distributed-transactions-in-spring--with-and-without-xa.html)
- [https://medium.com/preplaced/distributed-transaction-management-for-multiple-databases-with-springboot-jpa-and-hibernate-cde4e1b298e4](https://medium.com/preplaced/distributed-transaction-management-for-multiple-databases-with-springboot-jpa-and-hibernate-cde4e1b298e4)
- [https://gist.github.com/ezhov-da/418c44cf417e1723e0666f6e4e45866f](https://gist.github.com/ezhov-da/418c44cf417e1723e0666f6e4e45866f)

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