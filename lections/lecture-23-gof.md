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
### Поведінкові шаблони, частина 4

---

## План

1. Memento <!-- element class="fragment" -->
2. Visitor <!-- element class="fragment" -->
3. Iterator <!-- element class="fragment" -->
4. GoF у сучасній Java: підсумок <!-- element class="fragment" -->

---

## Минулого разу

| Шаблон | Ключова ідея |
|---|---|
| Strategy | замінний алгоритм, обирає клієнт |
| State | поведінка залежить від стану |
| Template Method | кістяк алгоритму в базовому класі |

<!-- element class="fragment" -->
Сьогодні: як **зберегти стан**, як **додати операцію** до чужих класів і як **обійти** колекцію, не знаючи її будови.

---

# Memento
## Знімок

![[gof_meme_memento.jpg]] <!-- element style="height:420px" -->

---

## Проблема: undo в редакторі

Графічний редактор: користувач натискає `Ctrl+Z`.

- перед кожною дією треба зберегти стан: фігури, шари, виділення, масштаб <!-- element class="fragment" -->
- поля редактора **приватні** <!-- element class="fragment" -->
- зробити їх публічними — будь-хто зможе змінити стан в обхід правил <!-- element class="fragment" -->
- копіювати стан ззовні — клас історії залежить від усіх полів редактора; додали поле — зламали undo <!-- element class="fragment" -->

<!-- element class="fragment" -->
Потрібно: знімок, який **може створити і прочитати лише сам редактор**.

---

## Рішення: Memento

Поведінковий шаблон, що дає змогу **зберігати та відновлювати** минулий стан об'єкта, не розкриваючи подробиць його реалізації.

![[gof_memento.png]] <!-- element style="height:340px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як це вирішує проблему

- **Originator** (редактор) створює знімок свого стану: `save()` <!-- element class="fragment" -->
- **Memento** — незмінний об'єкт зі станом; зовні його вміст недоступний <!-- element class="fragment" -->
- **Caretaker** (історія) зберігає знімки в стеку, але не бачить їхнього вмісту <!-- element class="fragment" -->
- `undo()`: історія віддає останній знімок, редактор відновлюється: `restore(memento)` <!-- element class="fragment" -->

---

## Як виглядає Memento?

![[gof_memento_uml.png]] <!-- element style="height:400px" -->

---

## Memento у Java

Java дає **справжню інкапсуляцію** знімка — на відміну від PHP, Python чи JavaScript:
- Memento — `static` вкладений клас з `private` полями і конструктором <!-- element class="fragment" -->
- зовнішній клас (Originator) має доступ до `private` членів вкладеного <!-- element class="fragment" -->
- Caretaker бачить лише непрозорий тип — без жодного методу доступу до стану <!-- element class="fragment" -->

---

## Java: деталі реалізації

- `record` — зручний незмінний знімок, але копія **поверхнева**: змінювані колекції копіюємо (`List.copyOf()`) <!-- element class="fragment" -->
- історія — `ArrayDeque` як стек; для redo — другий стек <!-- element class="fragment" -->
- `Serializable`-знімок — щоб зберегти стан на диск <!-- element class="fragment" -->
- приклади: undo в редакторах, збереження гри, чернетки форм, відкат транзакцій <!-- element class="fragment" -->

---

## Проблема пам'яті

1000 дій × повний знімок документа на 5 МБ = 5 ГБ.

- обмежити глибину історії: зберігати останні N знімків <!-- element class="fragment" -->
- **інкрементальні** знімки: лише змінена частина <!-- element class="fragment" -->
- поєднати з **Command**: команда зберігає лише те, що потрібно для її скасування <!-- element class="fragment" -->
- незмінні структури даних: знімок ділить незмінені частини з поточним станом <!-- element class="fragment" -->

---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Memento

| Переваги | Недоліки |
|---|---|
| не порушує інкапсуляцію Originator | багато пам'яті при частих знімках |
| Originator не зберігає історію сам | Caretaker має видаляти застарілі знімки |

---

# Visitor
## Відвідувач

![[gof_meme_visitor.jpg]] <!-- element style="height:420px" -->

---

## Проблема: експорт геометрії

Редактор має ієрархію фігур: `Circle`, `Rectangle`, `Polygon`, `Group`.

Нові вимоги щомісяця: <!-- element class="fragment" -->
- експорт у SVG <!-- element class="fragment" -->
- експорт у JSON <!-- element class="fragment" -->
- розрахунок площі <!-- element class="fragment" -->
- перевірка перетинів <!-- element class="fragment" -->

<!-- element class="fragment" -->
Додавати `toSvg()`, `toJson()`, `area()` у кожну фігуру? Класи фігур розростаються кодом, що до них не належить. А якщо фігури — в чужій бібліотеці?

---

## Чому не `instanceof`

Окремий клас `SvgExporter` з ланцюжком `if (shape instanceof Circle) … else if (shape instanceof Rectangle) …`:

- такий самий ланцюжок у кожній операції <!-- element class="fragment" -->
- додали `Ellipse` — забули оновити `JsonExporter` → помилка лише в рантаймі <!-- element class="fragment" -->

<!-- element class="fragment" -->
А перевантаження `export(Circle)`, `export(Rectangle)` не допоможе — чому?

---

## Single dispatch у Java

- перевантажений метод обирається **під час компіляції** за **статичним** типом аргументу <!-- element class="fragment" -->
- `exporter.export(shape)`, де `shape` має тип `Shape`, шукає `export(Shape)` — не знайде `export(Circle)` <!-- element class="fragment" -->
- динамічно (під час виконання) обирається лише метод за типом об'єкта, **на якому** його викликають <!-- element class="fragment" -->

<!-- element class="fragment" -->
Потрібно, щоб метод обирався за типами **двох** об'єктів — операції і фігури.

---

## Рішення: Visitor

Поведінковий шаблон, що дає змогу **додавати нові операції**, не змінюючи класи об'єктів, над якими ці операції виконуються.

![[gof_visitor.png]] <!-- element style="height:340px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як це вирішує проблему: double dispatch

1. кожна фігура має один метод `accept(Visitor v)` <!-- element class="fragment" -->
2. `Circle.accept(v)` викликає `v.visit(this)` — тут тип `this` вже відомий як `Circle` <!-- element class="fragment" -->
3. інтерфейс `Visitor` має `visit(Circle)`, `visit(Rectangle)`, `visit(Polygon)`… <!-- element class="fragment" -->
4. кожна операція — окремий відвідувач: `SvgExportVisitor`, `AreaVisitor` <!-- element class="fragment" -->

<!-- element class="fragment" -->
Перший вибір — за типом фігури (`accept`), другий — за типом відвідувача (`visit`).

---

## Як виглядає Visitor?

![[gof_visitor_uml.png]] <!-- element style="height:440px" -->

---

## Visitor у Java

- `Files.walkFileTree()` + `FileVisitor` / `SimpleFileVisitor` — обхід дерева каталогів <!-- element class="fragment" -->
- `javax.lang.model.element.ElementVisitor` — обробка анотацій <!-- element class="fragment" -->
- ASM `ClassVisitor` — аналіз і генерація байткоду <!-- element class="fragment" -->
- компілятори і лінтери: обхід AST <!-- element class="fragment" -->

---

## Сучасна Java: `sealed` + pattern matching

`sealed interface Shape permits Circle, Rectangle, Polygon` + `switch` з pattern matching (Java 21):

- без методів `accept()` у кожному класі <!-- element class="fragment" -->
- операція — звичайний метод зі `switch` за типом <!-- element class="fragment" -->
- компілятор вимагає обробити **всі** підтипи — новий тип одразу ламає компіляцію в усіх `switch` <!-- element class="fragment" -->

<!-- element class="fragment" -->
Те, що Visitor емулював, тепер вміє мова.

---

## Visitor vs `sealed` + `switch`

| | Visitor | `sealed` + `switch` |
|---|---|---|
| Java | будь-яка | 21+ |
| Зміни в класах елементів | метод `accept()` | лише `sealed ... permits` |
| Перевірка повноти | інтерфейсом `Visitor` | компілятором |
| Ієрархія з чужої бібліотеки | якщо там є `accept()` | якщо ієрархія `sealed` |
| Накопичення стану під час обходу | поля відвідувача | локальні змінні / параметри |

---

## Expression problem

| | Додати новий **тип** | Додати нову **операцію** |
|---|---|---|
| Звичайний поліморфізм | легко: новий клас | важко: метод у кожному класі |
| Visitor / `sealed` + `switch` | важко: метод у кожному відвідувачі | легко: новий відвідувач |

<!-- element class="fragment" -->
Visitor обирають, коли ієрархія **стабільна**, а операції **додаються часто**.

---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Visitor

| Переваги | Недоліки |
|---|---|
| просте додавання нових операцій | невиправданий, якщо ієрархія часто змінюється |
| споріднені операції в одному класі | може порушити інкапсуляцію елементів |
| накопичення стану під час обходу | у Java 21+ часто простіше `sealed` + `switch` |

---

# Iterator
## Ітератор

![[gof_meme_iterator.jpg]] <!-- element style="height:420px" -->

---

## Проблема: обхід соціального графа

Соцмережа зберігає друзів як граф.

- функції "друзі", "друзі друзів", "колеги" обходять граф по-різному: в ширину, в глибину, з фільтром <!-- element class="fragment" -->
- код обходу дублюється в кожному місці використання <!-- element class="fragment" -->
- клієнти знають, що всередині граф — змінити структуру на список суміжності вже неможливо <!-- element class="fragment" -->
- два обходи одночасно — кожен має пам'ятати, де він зупинився <!-- element class="fragment" -->

---

## Рішення: Iterator

Поведінковий шаблон, що дає змогу **послідовно обходити** елементи колекції, не розкриваючи її внутрішньої організації.

![[gof_iterator.png]] <!-- element style="height:340px" -->

<small>Ілюстрації взято з https://refactoring.guru/uk/design-patterns/</small>

---

## Як це вирішує проблему

- логіка обходу — в окремому об'єкті-ітераторі <!-- element class="fragment" -->
- ітератор зберігає **поточну позицію** — кілька обходів не заважають один одному <!-- element class="fragment" -->
- колекція видає різні ітератори: `bfsIterator()`, `dfsIterator()` <!-- element class="fragment" -->
- клієнт знає лише `hasNext()` і `next()` <!-- element class="fragment" -->

---

## Як виглядає Iterator?

![[gof_iterator_uml.png]] <!-- element style="height:420px" -->

---

## Iterator у Java

Шаблон вбудований у мову:
- `Iterable<T>` + `Iterator<T>` — `hasNext()`, `next()`, `remove()` <!-- element class="fragment" -->
- цикл **for-each** працює з будь-яким `Iterable` — реалізуйте його для своєї структури <!-- element class="fragment" -->
- `ListIterator` — обхід в обидва боки, вставка і заміна <!-- element class="fragment" -->
- `Spliterator` — розбиття колекції для паралельних стрімів <!-- element class="fragment" -->
- `Iterable.iterator()` — це **Factory Method** (лекція 17) <!-- element class="fragment" -->

---

## Fail-fast і fail-safe

| | Fail-fast | Weakly consistent / snapshot |
|---|---|---|
| Колекції | `ArrayList`, `HashMap` | `ConcurrentHashMap`, `CopyOnWriteArrayList` |
| Зміна під час обходу | `ConcurrentModificationException` | без винятку |
| Що бачить ітератор | — | частину змін або знімок на момент створення |

<!-- element class="fragment" -->
Видаляти під час обходу — через `iterator.remove()` або `removeIf()`, а не через `list.remove()`.

---

## Зовнішня і внутрішня ітерація

| | Зовнішня | Внутрішня |
|---|---|---|
| Хто керує обходом | клієнт (`for`, `Iterator`) | колекція |
| Приклад | for-each | `forEach()`, Stream API |
| Ліниві обчислення | вручну | стріми ліниві за замовчуванням |
| Паралелізм | вручну | `parallelStream()` |

---

## ENOUGH THEORY

![[gof_enough_theory.png]] <!-- element style="height:460px" -->

---

## Переваги і недоліки Iterator

| Переваги | Недоліки |
|---|---|
| спрощує класи колекцій | невиправданий, якщо досить простого циклу |
| різні способи обходу однієї структури | |
| кілька обходів одночасно | |

---

# Підсумок GoF

---

## Поведінкові шаблони

| Шаблон | Проблема | Ключова ідея |
|---|---|---|
| Chain of Responsibility | багато перевірок одного запиту | ланцюжок обробників |
| Command | дію треба зберегти, відкласти, скасувати | запит як об'єкт |
| Observer | багато реакцій на подію | підписка |
| Mediator | хаотичні зв'язки "кожен з кожним" | зв'язки через центр |
| Strategy | варіанти алгоритму | замінний об'єкт-алгоритм |
| State | `switch` за станом у кожному методі | стан як об'єкт |
| Template Method | однакові алгоритми з різними кроками | кістяк у базовому класі |
| Memento | undo без порушення інкапсуляції | непрозорий знімок |
| Visitor | нові операції над стабільною ієрархією | double dispatch |
| Iterator | обхід без знання структури | об'єкт-курсор |

---

## Що змінила мова

| Шаблон | Класична реалізація | Сучасна Java |
|---|---|---|
| Singleton | `getInstance()` | `enum`, Spring-бін |
| Prototype | `Cloneable` | конструктор копіювання, незмінні об'єкти |
| Builder | ручний клас | Lombok `@Builder` |
| Strategy, Command | ієрархія класів | лямбди, функціональні інтерфейси |
| Observer | `Observable` | `Flow`, `@EventListener`, Reactor |
| Iterator | ручний | for-each, Stream API |
| Visitor, State | double dispatch, класи станів | `sealed` + pattern matching |
| Template Method | наслідування | callback-и (`JdbcTemplate`) |
| Proxy, Decorator | ручні обгортки | `java.lang.reflect.Proxy`, Spring AOP |

---

## Висновки

- **Memento** — знімок стану без порушення інкапсуляції; в Java це забезпечують вкладені класи <!-- element class="fragment" -->
- **Visitor** — нові операції без зміни класів; у Java 21+ часто замінюється `sealed` + `switch` <!-- element class="fragment" -->
- **Iterator** — вбудований у мову; знати fail-fast поведінку і внутрішню ітерацію стрімів <!-- element class="fragment" -->
- GoF — не набір правил, а **словник рішень**: впізнавати шаблони в JDK і фреймворках, застосовувати, коли проблема вже є <!-- element class="fragment" -->

---

# Питання?

![[gof_meme_thanks.png]] <!-- element style="height:400px" -->
