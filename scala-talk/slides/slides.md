#

---

About me
==================================
- Miguel Pastor
- Interested in distributed systems, scalability and PaaS.
- AspectJ and Scala enthusiast. Erlang aficionado.
- Phd student??
- Twitter: @miguelinlas3

---

Agenda
======

- Global overview of the language
- Language fundamentals
- A different approach to concurrency
- Where to go next?
- Q & A

---

What is Scala?
========================

- What the hell is __Scala__? Why should I use __Scala__?
- Mixin of functional and object oriented paradigms
- Extensible language
- Run on top of the __JVM__
- Crisis
    - Moore's law
    - Multicore trends

---

Fundamentals: classes and objects
=================================

- Class definition
- __vals__ and __vars__
- Inmutable arguments
- Semicolon inference

---

Fundamentals: singleton objects
=================================

- No __static__ methods
- __Companion__ class and __companion__ objects
- We can not instantiate them
- Implemented using __synthetic classes__
- __Static__ semantic

---

Fundamentals: functional objects
================================

- Parámetros de clase
- Sobrescritura
- Precondiciones
- Atributos y métodos
- Métodos privados
- Operadores

---

Fundamentals: closures and functions
====================================

- High order functions
- Literal and value functions
- Closures: free variables
- Changes visibility

---

Fundamentals: tail recursion
====================================

- Not all the recursive functions are tail recursive
- __JVM bytecode__ restrictions/limitations
- Indirect recursions does not work

---

Fundamentals: currying
====================================

- Functional paradigm technique
- Multiple argument lists
- Subsequent invocation of functions

---

Fundamentals: traits (I)
========================

- Attributes and methods
- __Mixin__ within multiple classes
- Using __extend__ or __with__
- Define types by themselves
- More advanced than __interfaces__
    - Class parameters are not allowed
    - __super__ calls are linked dynamically

---

Fundamentals: traits (II)
=========================

- Enrich existing interfaces
- __Stackable__ modifications
    - Modify the method of a class
    - Stackable modifications
- __Abstract override__

---

Fundamentals: traits (III)
===========================

Should I use __traits__?
-------------------------

- No reuse --> __class__
- Reuse on multiple unrelated classes --> __trait__
- Do I need to extend from a __Java__ class? --> __class__
- Efficiency --> __class__
- I am not sure . . . --> start with __traits__

---

Fundamentals: Pattern matching
==============================

Alternative selector
--------------------
- __selector match { alternatives }__

Case classes
------------

- __factory-method__ is included
- Parameter list: implicit __val__
- Instintive implementation __ToString__, __equals__ y __hashCode__
- We can use them in pattern matching

---

Fundamentals: Pattern matching
==============================

Kinds of patterns
-----------------

- Wildcard
- Constant
- Variables
- Constructor
- Sequence
- Type