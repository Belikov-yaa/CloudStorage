Как организовать клиент-серверное взаимодействие?
- Сервер будет ожидать подключения клиентов на определенном порту, После поступления запроса на соединение открывать сокет/канал и проводить аутентификацию пользователя, при успешной аутентификации отправлять пользователю перечень содержимого его корневой папки и ждать дальнейших команд.
	
Как и в каком виде передавать файлы?
- Именафайлов и катологов для отображения в панели приложения клиенской части можно передавать в виде сериализованных объектов класса Path 
Содержимое файлов передавать в виде потока байтов.
	
Как пересылать большие файлы?
- наверное имеет смысл делить их на части и пересылать в несколько потоков, но подозреваю что самостоятельно такое реализовать довольно проблематично. Возможно у Netty есть для этого функционал

Как пересылать служебные команды?
Создать для этого специальный класс. По умолчанию считать что при поступлении входящего сообщения от клиента в его начале будет содержаться команда.
	
Что хранить в базе данных?
- В БД хранить пользователей (ID, логин, почту, пароль либо хэш пароля)
	
Как передавать структуру каталогов/файлов?
- Я бы попробовал передавать текущую папку с перечнем файлов/папок в виде сериализованного ArrayList<Path> либо ArrayList<FileInfo>, где FileInfo - собственный класс содержаший тип, имя, размер, дату создания файла.
	
Какую библиотеку использовать для сетевого взаимодействия: java.io, java.nio, Netty?
- Ну тут у нас видимо выбора нет :) будем использовать Netty