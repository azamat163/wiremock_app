# wiremock_app
Приложение под андроид для передачи смс с определенного получателя  на сервер

### Настройка wiremock - сервера
* Скачать wiremock сервер по [ссылке](http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.1.7/wiremock-standalone-2.1.7.jar)
* Для запуска wiremick сервера выполнить следующую команду:
```
java -jar wiremock-standalone-2.1.7.jar
```
Если хотите запустить с конкретным портом, то нужно выполнить:
```
java -jar wiremock-standalone-2.1.7.jar --port 9999
```
* Для проверки успешного запуска сервера нужно выполнить команду: 
```
http://localhost:8080/__admin
```

### Описание основных полей в приложении wiremock_app:

* host port - это ip-машины
* server port - это port под которым запустили wiremock сервер
* PATH - это путь куда будет приходить данные, например можно задать ```/test```, то соответственно  url будет следующим:
```
http://localhost:8080/test
```
* receiver - это отправитель, по которому будет считываться смс

Спасибо, что дочитали до конца :+1:
