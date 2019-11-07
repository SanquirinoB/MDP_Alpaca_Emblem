# Proyecto Semestral: Alpaca Emblem

El proyecto a realizar será programar un juego de estrategia por turnos donde participarán dos jugadores y
se tendrán distintos tipos de unidades con caracterı́sticas particulares. El objetivo del juego es derrotar a
una unidad especial de su oponente (a partir de ahora, se referirá a esta unidad como Héroe).

AE se encuentra en su primera etapa de desarrollo, la implementación de los modelos necesarios para generar la logística del juego.

## Aclaraciones Iniciales

El proyecto se desarrolla sobre la plataforma IntelliJ IDEA Ultimate, por lo que la mayoría de las explicaciones, tanto de implementación como ejecución, se basan en las herramientas que el software posee.

## Ejecución

Dado que el proyecto se encuentra en un estado de construcción logística, solo es posible visualizar su ejecución por medio de los test.

Hasta el penúltimo commit realizado el 7 de Octubre 2019, estos test tienen un 95% de cobertura, tanto en los métodos de los distintos packages como líneas de código posee, de tal forma los test vislumbran con un amplio rango el comportamiento del proyecto.

## Javadoc

Todos los métodos de las interfaces se hayan documentados bajo JavaDoc, y los métodos de las clases abstractas que no son heredados de la interfaz también, i.e. no se documenta el mismo método más de una vez por lo bajo.

De igual forma, todas las distintas clases y sus constructores se encuentran documentados. Así, si llegace a nacer una duda sobre cómo trabaja determinado método, guíese por el diagrama UML adjunto en el archivo [Resumen](https://github.com/SanquirinoB/MDP_Alpaca_Emblem/blob/master/Resumen_UML.pdf) para encontrar la información deseada.

##  Implementación

### Patrón de Diseño

Se utilizan los siguientes patrones de diseño:

1. Observer: Como el controlador debe estar al tanto de los jugadores, como los jugadores de sus unidades, se implementa una interfaz predeterminada para la relación Observador-Observado, ya que se requieren tomar decisiones en virtud de cambios externos en las distintas clases.
2. Factory: El jugador solo solicita obtener un item o una unidad declarando solo el tipo de la fábrica, no interactuando directamente con el paquete de Unidades.
3. Proxy: El controlador y los jugadores son el intermediario entre el cliente real y la implementación real del juego. Donde estos desempeñan la logística.

### Prácticas utilizadas

La interacción entre objetos se caracteriza por el uso del **Double Dispatch**. La razón de ello, es que la dinámica del juego consta de que los jugadores son unidades, las cuales poseen items caracterizados por su poder y debilidad ante otros items, de igual forma estos pueden ser objetos mágicos o no, ser agresivos o sanadores, entonces hay un claro mecanismo que se rige por reglas de interacción.

Por ejemplo, necesitamos diferenciar el hecho de que una unidad ataque con un hacha a otra que posee una espada, ya que el hacha es débil contra la espada, generando que el ataque de esta se modifique. Entonces, la forma para abordar esto es que existan métodos que permitan a los objetos actuar a partir de su clase, definiendo así su comportamiento en virtud de saber cómo actuar ante otra clase de objeto.

### Unidades, características y supuestos de restricción

1. **Alpaca**: No posee límite de items a cargar, pero no puede equipar items, por lo que (lamentablemente) puede recibir ataques y no contra atacar (Ojalá implementar un escupitajo de respuesta) pero también puede ser sanada por el Cleric con su Staff (item curador).

> A continuación, las siguientes unidades poseen un límite de 3 items para llevar consigo y poder equipar solo uno de ellos. Entiéndase por equipar un item como la acción de seleccionar un item con el que se pueden realizar acciones. Se asume que una unidad no puede atacar bajo las siguientes condiciones: cuando se encuentra fuera de rango de su item, no posee item equipado, hitPoints iguales a 0 (se da por muerto) o bien no es una unidad con la capacidad de atacar (Alpaca/Cleric). Hasta ahora los hitPoints máximos que se pueden tener son 50. Todas las fortalezas y debilidades se declaran en el [enunciado](https://github.com/SanquirinoB/MDP_Alpaca_Emblem/blob/master/Protecto_Parte_1.pdf) ajdunto.

2. **Archer**: Item equipable arco (Bow), puede atacar, contra atacar y ser sanado.

3. **Cleric**: Item equipable Staff, item que solo tiene la capacidad de curar a otra unidad, por lo cual el clerigo solo puede recibir ataques y sanar a otros.

4. **Fighter**: Item equipable hacha (Axe), puede atacar, contra atacar y ser sanado.

5. **Hero**: Item equipable lanza (Spear), puede atacar, contra atacar y ser sanado.

6. **Sorcerer**: Item equipable libros de mágia (DarkBook/LightBook/SoulBook), puede atacar, contra atacar y ser sanado, **no tiene límite de items a cargar**.

7. **SwordMaster**: Item equipable espada (Sword), puede atacar, contra atacar y ser sanado.

### Tactician (ETAPA II)

El Tactician es la implementación destinada a representar a un jugador, el cual posee una cantidad de unidades para combate, se caracterizan por nombres bajo enumeración (i.e. Player 1)

### Controller (ETAPA II)

Es lo más cercano que tenemos hasta ahora de la logística del juego, más para representar la dinamica del mismo solo es viable vía test.

### Factories (ETAPA II)

Creamos tanto items como unidades con valores, hasta el minuto, estandar. De esta forma los jugadores solo solicitan al objeto, no declaran sus propiedades.
