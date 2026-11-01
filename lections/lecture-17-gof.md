---
theme: white
highlightTheme: atom-one-light
transition: slide
width: 1280
height: 800
margin: 0.06
slideNumber: true
---

# Шаблони проєктування GoF
### Породжувальні шаблони, частина 2

---

## План

1. Builder <!-- element class="fragment" -->
2. Factory Method <!-- element class="fragment" -->
3. Abstract Factory <!-- element class="fragment" -->
4. Як обрати породжувальний шаблон <!-- element class="fragment" -->

---

## Минулого разу

| Шаблон | Ключова ідея |
|---|---|
| Singleton | клас сам контролює єдиність екземпляра; на практиці — scope DI-контейнера |
| Prototype | копіювання готового еталона замість створення з нуля |
| Object Pool | перевикористання дорогих ресурсів |

<!-- element class="fragment" -->
Сьогодні: **як** створювати складні об'єкти і **хто** вирішує, який саме клас створити.

---

# Builder
## Будівельник

![[gof_builder_title.png]] <!-- element style="height:360px" -->

---

## Проблема: замовлення піци

Клас `Pizza`: розмір (обов'язковий), тісто, соус, сир, бекон, гриби, ананаси, халапеньо, подвійний сир…

**Телескопічні конструктори**: <!-- element class="fragment" -->
- `Pizza(size)`, `Pizza(size, cheese)`, `Pizza(size, cheese, bacon)`… <!-- element class="fragment" -->
- `new Pizza(30, true, false, true, false, true)` — що тут що? <!-- element class="fragment" -->
- переставили два `boolean` місцями — компілятор мовчить <!-- element class="fragment" -->

---

## Наївне рішення: сеттери

**JavaBeans**: порожній конструктор + `setCheese(true)`, `setBacon(true)`…

- між створенням і останнім сеттером об'єкт у **неконсистентному стані** <!-- element class="fragment" -->
- обов'язковий розмір можна забути — дізнаємось у рантаймі <!-- element class="fragment" -->
- об'єкт неможливо зробити **незмінним** <!-- element class="fragment" -->
- об'єкт передали в інший потік до завершення налаштування <!-- element class="fragment" -->

<!-- element class="fragment" -->
У Java немає **іменованих параметрів** і **значень за замовчуванням**, як у Kotlin, C# чи Python.

---

## Рішення: Builder

Породжувальний шаблон, що дає змогу створювати **складні об'єкти крок за кроком**. Один і той самий процес будування дає різні представлення об'єкта.

![[gof_builder.png]] <!-- element style="height:380px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як виглядає Builder?

![[gof_builder_uml.png]] <!-- element style="height:460px" -->

---

## Ролі

- **Builder** — інтерфейс кроків будування <!-- element class="fragment" -->
- **ConcreteBuilder** — реалізує кроки і повертає результат (`getResult()`) <!-- element class="fragment" -->
- **Director** — знає **порядок** кроків для типових конфігурацій <!-- element class="fragment" -->
- **Product** — результат; різні будівельники дають різні продукти <!-- element class="fragment" -->

<!-- element class="fragment" -->
Приклад: один Director "звіт за місяць" + `HtmlReportBuilder` / `PdfReportBuilder` → два різні документи одним процесом.

---

## Два варіанти Builder

| | Класичний GoF | Fluent Builder (Java-ідіома) |
|---|---|---|
| Мета | різні **представлення** одним процесом | зручне створення **одного** складного об'єкта |
| Director | є | зазвичай немає |
| Builder | інтерфейс + кілька реалізацій | `static` вкладений клас продукту |
| Виклик | `director.make(builder)` | ланцюжок методів + `build()` |

<!-- element class="fragment" -->
Effective Java (Item 2): "Розгляньте Builder, коли конструктор має багато параметрів".

---

## Fluent Builder: як це працює

1. обов'язкові параметри — у конструктор будівельника <!-- element class="fragment" -->
2. необов'язкові — методи, що повертають `this` <!-- element class="fragment" -->
3. `build()` перевіряє інваріанти і викликає **приватний** конструктор продукту <!-- element class="fragment" -->
4. продукт незмінний: усі поля `final` <!-- element class="fragment" -->

![[gof_meme_builder.png]] <!-- element style="height:260px" class="fragment" -->

---

## Builder у Java

- `StringBuilder` <!-- element class="fragment" -->
- `HttpClient.newBuilder()`, `HttpRequest.newBuilder()` (Java 11) <!-- element class="fragment" -->
- `Stream.builder()`, `Locale.Builder`, `ProcessBuilder` <!-- element class="fragment" -->
- **Lombok** `@Builder` генерує будівельник автоматично <!-- element class="fragment" -->
- Spring: `ResponseEntity.ok().header(...).body(...)`, `UriComponentsBuilder` <!-- element class="fragment" -->

---

## Варіації

- **Step Builder** — кожен крок повертає інший інтерфейс; обов'язкові кроки неможливо пропустити — помилка на етапі **компіляції** <!-- element class="fragment" -->
- **`toBuilder()`** — отримати будівельник з поточного об'єкта, змінити одне поле, побудувати нову копію <!-- element class="fragment" -->
- **`record` + Builder** — record для даних, будівельник для зручного створення <!-- element class="fragment" -->

---

## Типові помилки

- Builder для класу з 2–3 полями — зайвий код <!-- element class="fragment" -->
- `build()` без валідації — та сама проблема, що й із сеттерами <!-- element class="fragment" -->
- повторне використання одного будівельника — наступний продукт успадковує стан попереднього <!-- element class="fragment" -->
- продукт має публічні сеттери — незмінність втрачено <!-- element class="fragment" -->

---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Builder

| Переваги | Недоліки |
|---|---|
| покрокове створення, читабельні виклики | більше коду: окремий клас-будівельник |
| незмінні об'єкти без телескопічних конструкторів | для кожного продукту — свій ConcreteBuilder |
| валідація в одному місці (`build()`) | |
| ізолює конструювання від представлення | |

---

# Factory Method
## Фабричний метод

![[gof_meme_problem_factory.png]] <!-- element style="height:420px" -->

---

## Проблема: логістика

Застосунок логістики перших років працював лише з вантажівками.

- клас `Logistics` в методі `planDelivery()` робить `new Truck()` <!-- element class="fragment" -->
- компанія додає морські перевезення — `Ship` <!-- element class="fragment" -->
- `new Truck()` розкиданий по всьому коду, з'являються `if (type == SEA)` <!-- element class="fragment" -->
- наступного року — авіаперевезення. І знову скрізь `if` <!-- element class="fragment" -->

<!-- element class="fragment" -->
Код, що **використовує** транспорт, однаковий. Різниться лише те, **який** транспорт створити.

---

## Рішення: Factory Method

Породжувальний шаблон, який визначає **загальний інтерфейс для створення об'єктів** у суперкласі, дозволяючи **підкласам** змінювати тип створюваних об'єктів.

![[gof_factory_method.png]] <!-- element style="height:380px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як це вирішує проблему

- `Logistics` містить спільну логіку і викликає абстрактний `createTransport()` <!-- element class="fragment" -->
- `RoadLogistics` повертає `Truck`, `SeaLogistics` — `Ship` <!-- element class="fragment" -->
- `planDelivery()` працює з інтерфейсом `Transport` і не знає конкретного класу <!-- element class="fragment" -->
- авіаперевезення — нові класи `AirLogistics` і `Plane`; старий код не змінюється (**OCP**) <!-- element class="fragment" -->

---

## Як виглядає Factory Method?

![[gof_factory_method_uml.png]] <!-- element style="height:420px" -->

- `Creator` може мати реалізацію за замовчуванням — тоді він не абстрактний <!-- element class="fragment" -->
- головне в `Creator` — **бізнес-логіка**, а не створення <!-- element class="fragment" -->

---

## Factory Method у Java

- `Iterable.iterator()` — кожна колекція повертає свій `Iterator` <!-- element class="fragment" -->
- `Collection.stream()` <!-- element class="fragment" -->
- `ThreadFactory.newThread()` — `ThreadPoolExecutor` не знає, які потоки створюються <!-- element class="fragment" -->
- `URLStreamHandler.openConnection()` — `HttpURLConnection`, `JarURLConnection`… <!-- element class="fragment" -->

---

## Три "фабрики" — не плутати

| | Simple Factory | Factory Method (GoF) | Статичний фабричний метод |
|---|---|---|---|
| Що це | клас з `switch` за типом | метод, який перевизначають підкласи | `static` метод замість конструктора |
| Механізм | умова | наслідування | — |
| OCP | ні: новий тип — змінити `switch` | так | — |
| Приклад | `TransportFactory.create("sea")` | `Logistics.createTransport()` | `List.of()`, `LocalDate.of()` |

---

## Статичні фабричні методи

Effective Java (Item 1) — не GoF, але дуже поширена в Java ідіома:

- мають **імена**: `of`, `from`, `valueOf`, `getInstance`, `newInstance`, `create` <!-- element class="fragment" -->
- можуть повертати **кешований** об'єкт (`Integer.valueOf`, `Boolean.valueOf`) <!-- element class="fragment" -->
- можуть повертати **підтип**: `EnumSet.noneOf()` повертає `RegularEnumSet` або `JumboEnumSet` залежно від кількості елементів <!-- element class="fragment" -->
- клієнт залежить від інтерфейсу, а не від класу реалізації <!-- element class="fragment" -->

---

## Типові помилки

- фабрика для класу, який ніколи не матиме варіантів <!-- element class="fragment" -->
- Simple Factory з `switch`, що росте з кожним новим типом, — називають "Factory Method" <!-- element class="fragment" -->

---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Factory Method

| Переваги | Недоліки |
|---|---|
| позбавляє від прив'язки до конкретних продуктів | паралельні ієрархії: кожному продукту — свій творець |
| код створення в одному місці | |
| просте додавання нових продуктів | |
| реалізує OCP | |

---

# Abstract Factory
## Абстрактна фабрика

![[gof_meme_abstract_factory.png]] <!-- element style="height:420px" -->

---

## Проблема: меблевий магазин

Магазин продає крісла, дивани і столики у стилях **Модерн**, **Вікторіанський**, **Ар-деко**.

- покупець замовив вікторіанський диван — і отримав крісло в стилі модерн <!-- element class="fragment" -->
- код замовлення робить `new VictorianSofa()`, `new ModernChair()` — нічого не гарантує, що стиль збігається <!-- element class="fragment" -->
- новий стиль щосезону — правки по всьому коду <!-- element class="fragment" -->

![[gof_abstract_factory_comic.png]] <!-- element style="height:220px" class="fragment" -->

---

## Рішення: Abstract Factory

Породжувальний шаблон, що дає змогу створювати **сімейства пов'язаних об'єктів**, не прив'язуючись до конкретних класів.

![[gof_abstract_factory.png]] <!-- element style="height:380px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як це вирішує проблему

- інтерфейси продуктів: `Chair`, `Sofa`, `CoffeeTable` <!-- element class="fragment" -->
- інтерфейс фабрики `FurnitureFactory` з методами `createChair()`, `createSofa()`, `createCoffeeTable()` <!-- element class="fragment" -->
- `VictorianFurnitureFactory` створює **лише** вікторіанські меблі <!-- element class="fragment" -->
- клієнт отримує одну фабрику на старті і не знає жодного конкретного класу <!-- element class="fragment" -->

<!-- element class="fragment" -->
Змішати стилі **неможливо за побудовою**.

---

## Як виглядає Abstract Factory?

![[gof_abstract_factory_uml.png]] <!-- element style="height:480px" -->

---

## Abstract Factory у Java

- **JDBC**: `Connection` конкретного драйвера створює сумісні `Statement`, `PreparedStatement`, `CallableStatement` <!-- element class="fragment" -->
- **JAXP**: `DocumentBuilderFactory.newInstance()`, `TransformerFactory` — реалізація обирається під час виконання <!-- element class="fragment" -->
- **Swing**: `LookAndFeel` — родина UI для всіх компонентів <!-- element class="fragment" -->
- **Spring**: `@Profile` / `@Configuration` — набір бінів для `dev`, `test`, `prod` <!-- element class="fragment" -->

<!-- element class="fragment" -->
Реалізацію фабрики часто знаходять через `ServiceLoader`: підключили jar — з'явилась нова родина.

---

## Слабке місце

Додати новий **стиль** — легко: нова фабрика + нові продукти.

Додати новий **тип продукту** (шафа) — важко: <!-- element class="fragment" -->
- новий метод в інтерфейсі фабрики <!-- element class="fragment" -->
- реалізація в **кожній** фабриці <!-- element class="fragment" -->


---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Abstract Factory

| Переваги | Недоліки |
|---|---|
| гарантує сумісність продуктів | багато додаткових класів |
| клієнт не прив'язаний до конкретних класів | новий **тип** продукту — зміна всіх фабрик |
| код виробництва в одному місці | |
| реалізує OCP для нових родин | |

---

# Порівняння

---

## Factory Method vs Abstract Factory vs Builder

| | Factory Method | Abstract Factory | Builder |
|---|---|---|---|
| Створює | один продукт | родину продуктів | один складний продукт |
| Як | одним викликом | одним викликом на продукт | крок за кроком |
| Механізм | наслідування | композиція: об'єкт-фабрика | об'єкт-будівельник |
| Гарантує | — | сумісність продуктів | валідний стан продукту |

<!-- element class="fragment" -->
Часто еволюціонують: Factory Method → Abstract Factory або Builder, коли зростають вимоги.

---

## Як обрати породжувальний шаблон

| Ситуація | Шаблон |
|---|---|
| рівно один екземпляр | Singleton (краще — DI) |
| копія дешевша за створення | Prototype |
| дорогий ресурс, що перевикористовується | Object Pool |
| багато параметрів, складна валідація | Builder |
| тип продукту визначає підклас | Factory Method |
| родини сумісних об'єктів | Abstract Factory |

---

## Висновки

- **Builder** у Java особливо доречний: немає іменованих параметрів; fluent-варіант дає незмінні об'єкти <!-- element class="fragment" -->
- **Factory Method** переносить вибір класу продукту в підкласи; не плутати зі Simple Factory і статичними фабричними методами <!-- element class="fragment" -->
- **Abstract Factory** гарантує сумісність родини продуктів, але ускладнює додавання нових типів <!-- element class="fragment" -->
- на практиці багато що робить DI-контейнер: Spring сам є великою фабрикою <!-- element class="fragment" -->

---

# Питання?

![[gof_meme_thanks.png]] <!-- element style="height:400px" -->
