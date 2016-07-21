# wiremock_app
wiremock_app - это андроид приложение для передачи SMS сообщений с определенного получателя  на сервер wiremock.

### Настройка wiremock - сервера
* Скачать wiremock - сервер по [ссылке](http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.1.7/wiremock-standalone-2.1.7.jar)
* Для запуска wiremock сервера выполнить следующую команду:
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
Оффициальный сайт wiremock: [ссылка](http://wiremock.org/docs/) 

### Описание основных полей в приложении wiremock_app:

* host port - это ip - адрес, на котором запущен wiremock - сервер, например  ```192.168.0.1.```
* server port - это порт, под которым запустили wiremock сервер, например ```9999```
* PATH - это путь куда будет приходить данные, например можно задать ```/test```, то соответственно  url будет следующим:
```
http://localhost:8080/test
```
* receiver - это отправитель, по которому будет считываться смс, например ```github```

Спасибо, что дочитали до конца :+1:

