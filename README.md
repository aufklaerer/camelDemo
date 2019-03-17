# camelDemo

Демонстрационное приложение, которое доступно на 8080 порту машины, на которой запущено. В корне доступна форма, которая формирует и отправляет для обработки данные. На форме перед отправкой производится предварительная валидация данных.
В процессе обработки на стороне бэка происходит валидация данных c JSON-схемой. Далее при успешной валидации, производится поиск по справочнику и обогощение данных найденными сведениями. Маршрутиризация данных происходит средствами Apache Camel, в форме файлов.

В файле abonents.csv содержатся данные для справочника абонентов, для обогощения отправляемых данных.
Веб-форма работает для Chrome и Safari.

# Установка

Для сборки проекта требуется иметь установленные git (установка для Mac brew install mvn), java8 (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) и maven (установка для Mac brew install maven, страница установки brew - https://docs.brew.sh/Installation) и выполнить следующую последовательность команд:

git clone https://github.com/aufklaerer/camelDemo.git

cd camelDemo

mvn clean install

cp abonents.csv target

cd target

java -jar camel-0.1.jar

Либо выполнить выгрузку репозитория в локальную директорию и скрипт, выполняющий вышеприведенные команды и запускающий приложение(скрипт для Mac).

git clone https://github.com/aufklaerer/camelDemo.git

cd camelDemo

sh start.sh
