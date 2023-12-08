
## Постановка задачи

1. Создать классы `Дерево` и `Узел дерева` в соответствии с предыдущим домашним заданием.
2. Создать два варианта соединения с БД (`PostgreSQL` и `H2`) через *JDBC*. Использовать константы Java или файл свойств для хранения настроек подключения (URL, user, password).
3. Создать Java-интерфейс или абстрактный класс для соединения с БД, затем имплементировать его в двух классах, соответствующих PostgreSQL и H2, с учетом особенностей каждой СУБД.
4. Реализовать класс и метод, который через JDBC прочитает содержимое таблицы TREES и построит список деревьев на основе прочитанных данных.
5. Создать функцию, которая для списка деревьев найдет суммарное количество листьев во всех деревьях.
6. В `public static void main`: установить соединение с БД (H2 по умолчанию), затем создать список деревьев, после чего вызвать функцию для нахождения суммарного количества листьев во всех деревьях и записать результат в файл `output.csv`.

## Технологии и инструменты
<div align="center">
<img alt="Java"  src="https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/1200px-Java_programming_language_logo.svg.png" width="40"/>
<img alt="Maven" height="50" src="https://cdn.fs.teachablecdn.com/L2rtxPaRxa4am1VtNegg"/>
<img alt="Postgres" height="50" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/993px-Postgresql_elephant.svg.png"/>
<img alt="H2" height="50" src="https://dbdb.io/media/logos/h2-logo.svg"/>
</div>

* <code>SDK 19.0.1, Java 17</code>
* <code>Maven</code> — инструмент автоматизации сборки.
* Соединение с <code>PostgreSQL</code> и <code>H2</code> осуществляется при помощи 
<code>JBDC</code> и соответствующих драйверов.

Драйверы для PostgreSQL и H2 находятся в папке <code>drivers</code>.
 
## Properties-файлы
__Properties-файлы, содержащие данные для подключений к БД, находятся в <code>src/main/resources</code>__:
- <code>h2.properties</code> - данные для подключения к H2.
- <code>postgres.properties</code> - данные для подключения к Postgres.

Файлы содержат данные в следующем формате (_пример для H2_):
```
URL = jdbc:h2:~/treeDB 
USER = userTree  
PASSWORD = pass
```
Считывание properties-файлов происходит в классах-обработчиках соответствующей БД.

##  Компиляция проекта в JAR из терминала
Необходимо перейти в папку проекта и использовать команду
```bash
mvn clean install
```
JAR-файл можно найти в папке <code>target</code> внутри проекта.
