# MapEmployeeHW8
> Привет!
На связи домашнее задание урока 2.7 Коллекции: многообразие реализаций.

1. Перенести проект с EmployeeBook на Map в качестве хранилища сотрудников.
2. Продумать контракт для ключей, чтобы сотрудник с одним ФИО существовал только в одном экземпляре, корректно добавлялся и удалялся.
3. Избавиться от циклов в методах по поиску сотрудника, заменив на методы Map.
4. Переработать все методы по работе с хранилищем на работу с методами Map.
- **Критерии оценки**
    - Все сотрудники существуют только в одном экземпляре
    - Поиск сотрудников осуществляется с помощью метода
    - Все методы по работе с хранилищем заменены на мапы
    - Любого сотрудника можно удалить
    - Можно добавить нового сотрудника
    - Проект перенесен на map
============================================================
2.8. Stream API и Optional:
- В поле Employee  добавлены новые поля «Зарплата» и «Отдел»
- Переписаны контроллер и сервис, которые возвращают сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
- Переписаны контроллер и сервис, которые возвращают сотрудника с минимальной зарплатой на основе номера отдела.
- Переписаны контроллер и сервис, которые возвращают всех сотрудников по отделу
- Переписаны контроллер и сервис, которые возвращают всех сотрудников с разделением по отделам.
- Реализация проекта заменена на циклы на Stream API.
============================================================
2.10 Библиотеки:
- К проекту книги учета сотрудников подключена библиотека commons-lang3
- Библиотека подключена с помощью Maven
- С помощью класса StringUtils можно проверить все входящие текстовые данные от пользовался – имена сотрудников, фамилии сотрудников.
- Данные сотрудников записываются с большой буквы
- В переданной строке нет чисел и других запрещенных символов.
- Обработано исключение в случае, если данные не проходят проверку, возвращающее статус 400 Bad Request.
