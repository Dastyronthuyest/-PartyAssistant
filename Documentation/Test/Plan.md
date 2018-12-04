* [Вернуться на главную](https://github.com/Dastyronthuyest/PartyAssistant/blob/master/README.md)

### Содержание
  1. [Введение](#1)
  2. [Объект тестирования](#2)
  3. [Риски](#4)
  4. [Аспекты тестирования](#5)<br>
5. [Подходы к тестированию](#6)
6. [Представление результатов](#7)
7. [Выводы](#8)


<a name="1"></a>
### 1. Введение
Содержание данного документа описывает план тестирования приложения [Party Assistant](https://github.com/Dastyronthuyest/PartyAssistant/blob/master/Documentation/SRS.md).  
Цель тестирования - проверка работоспособности приложения, а также соответствия всем требованиям, составленым на этапе разработки требований.

<a name="2"></a>
### 2. Объект тестирования

1. Функциональность:
	+ функциональная полнота: приложение должно выполнять все заявленные на этапе разработки функции
	+ функциональная корректность: приложение должно выполнять все заявленные на этапе разработки функции корректно
	+ функциональная целесообразность: отсутствие незаявленных функций, а также функций, препятствующих реализации изначальных требований
2.  Удобство использования:
	+ простота пользовательского интерфейса: интерфейс должен быть интуитивно понятен всем группам пользователей.

Данные атрибуты были взяты с учётом специфики приложения.

<a name="3"></a>
### 3. Риски
Скорость получения данных из баз данных может разниться в зависимости от скорости интернета. Иных рисков при работе с базами данных удалось избежать ведением политики READ ONLY при доступе к данным, что исключает возможности грязного чтения, фантомного чтения и т. д.

<a name="4"></a>
### 4. Аспекты тестирования

#### Аспекты, подвергаемые тестированию:

В ходе тестирования планируется проверить реализацию основных функций приложения, провести позитивные и негативные тесты, а также проверить нефункциональные требования.
К основным функциям приложения Party Assistant можно отнести следующие пункты:
* возможность выбрать глобальную категорию рецепта (закуска, дессерт...);
* возможность выбрать локальную категорию рецепта (для закуски: бутерброд, канапе...);
* возможность указать имеющиеся в наличии ингредиенты для текущей локальной категории;
* возможность просмотреть список доступных по указанным критериям рецептов;

<a name="5"></a>
### 5. Подходы к тестированию
Для тестирования приложения необходимо вручную проверить каждый аспект тестирования.

<a name="6"></a>
### 6. Представление результатов

Результаты тестирования представлены в документе ["Представление результатов"](https://github.com/Dastyronthuyest/PartyAssistant/blob/master/Documentation/Test/Results.md).

<a name="7"></a>
### 7. Выводы
Данный тестовый план позволяет протестировать основной функционал приложения. Успешное прохождение всех тестов не гарантирует полной работоспособности на всех платформах и архитектурах, однако позволяет полагать, что данное программное обеспечение работает корректно.