# newNRG
Этот проект решает проблемы автоматизации технического обслуживания и ремонтов электротехнического оборудования электростанций.

касательно технологического стека:
В этом проекте реализован сервер с использованием Spring Boot, Spring Data JPA, Spring Security, JSON Web Token (JWT) с СУБД PostgreSQL. 

Будут реализованы решения:
1. покрытие тестами по мере реализации клиентов.
2. деление на микросервисы с минимум двумя модулями. Один из сервисов будет отвечать за авторизацию, регистрацию и работу с пользователями, второй будет составлять основу приложения. В будущем появится сервис регистратора действий пользователя.
3. добавление API Gateway для обращения к микросервисам.
4. создание клиента на JAvaFX отдельным проектом https://github.com/bREDMACHINE/NewNRG-client-javafx (на данный момент создан графический интерфейс клиента с использованием Swing и Spring Boot с применением паттерна MVC)


Касательно функционала проекта:
На данный момент в проекте реализованы функции пользователей, модератора и администратора, добавлены функции создания характеристик оборудования в зависимости от типа, создание типа оборудования с прикреплением заводских документов, создание самого оборудования, а также проекта и событий в соответствии с жизненным циклом оборудования в эксплуатации. 

Также добавлена возможность загрузки файлов, создавать документы с помощью PDFBox(пока простой текст, в планах сделать формы в соответствии с приказом Минэнерго). Будет реализована возможность отправки на печать.

В среднесрочных планах добавить функционал для поиска (пока приложение просто сравнивает введенное значение с базой) и сортировки оборудования, регистратор действий для пользователей, валидацию введенных значений.

В далеких планах добавить фозможность формирования план-графика технического обслуживания, отслеживание фактического выполнения по загруженным документам, формирование итоговых документов по результатам мероприятий, загрузка оборудования по схеме соединений оборудования.
