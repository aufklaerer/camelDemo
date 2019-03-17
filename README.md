# camelDemo

Демонстрационное приложение, которое доступно на 8080 порту машины, на которой запущено. В корне доступна форма, которая формирует и отправляет для обработки данные.
В процессе обработки происходит валидация данных c JSON-схемой. Далее при успешной валидации, производится поиск по справочнику и обогощение данных найденными сведениями.

# Установка

Для сборки проекта требуется иметь установленные git (установка для Mac brew install mvn), java8 (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) и maven (установка для Mac brew install maven, страница установки brew - https://docs.brew.sh/Installation) и выполнить следующую последовательность команд:

git clone https://github.com/aufklaerer/camelDemo.git
cd camelDemo
mvn clean install
cd target
java -jar camel-0.1.jar
