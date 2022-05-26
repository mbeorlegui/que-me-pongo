# Qué me pongo

QuéMePongo es una empresa dedicada al armado de atuendos adecuados a las condiciones climáticas y preferencias de sus 
clientes. El servicio que provee se basa en tomar las prendas del guardarropas de une usuarie y generar diferentes 
combinaciones posibles que cubran las necesidades de les mismes en términos de distintos factores climáticos tales como
temperatura, viento, sol, lluvia, etc. Asimismo, se busca que estos atuendos se adecuen de la mejor forma a las 
sensibilidades particulares respecto de dichos factores de cada usuarie y a sus gustos en el aspecto estético.

## Primera Iteración

Comenzaremos definiendo que un atuendo es una combinación de prendas que tiene sentido usar juntas. Algunos ejemplos de 
atuendos podrían ser:
- Tus anteojos de sol favoritos, una remera de mangas cortas azul, el pantalón que te regaló tu mamá y unas zapatillas 
  Converse.
- Un pañuelo verde, una remera de mangas largas rayada, un pantalón de jean y un par de botas de cuero.
- Una musculosa de mickey, una pollera amarilla y unas crocs.

Como primer paso para generar los atuendos, les usuaries de QuéMePongo identificaron el siguiente requerimiento como 
principal:

> Como usuarie de QuéMePongo, quiero poder cargar prendas válidas para generar atuendos con ellas.

Y luego, al consultar más sobre este requerimiento general, logramos descomponerlo con mayor detalle en los siguientes:
- Como usuarie de QuéMePongo, quiero especificar qué tipo de prenda estoy cargando (zapatos, camisa de mangas cortas,
  pantalón, etc).
- Como usuarie de QuéMePongo, quiero identificar a qué categoría pertenece una prenda (parte superior, calzado, parte
  inferior, accesorios).
- Como usuarie de QuéMePongo, quiero poder indicar de qué tela o material está hecha una prenda
- Como usuarie de QuéMePongo, quiero poder indicar un color principal para mis prendas
- Como usuarie de QuéMePongo, quiero poder indicar, si existe, un color secundario para mis prendas.
- Como usuarie de QuéMePongo, quiero evitar que haya prendas sin tipo, tela, categoría o color primario`
- Como usuarie de QuéMePongo, quiero evitar que haya prendas cuya categoría no se condiga con su tipo.
  (Ej, una remera no puede ser calzado).

### Requerimientos

<u>Iteración 1:</u>
- Definir _atuendo_ como una combinación de prendas.
- Los usuarios deben poder cargar prendas validas.
- Cada prenda debe tener: 
  * Un tipo.
  * Una categoria a la que pertenece.
  * Un material del que está hecho.
  * Color principal.
  * Color secundario (si existe).
  * Evitar que a una prenda le falte el tipo, el material, la categoría o el color principal. -> Hay que meter estas 
  propiedades en el constructor para que sean necesarias a la hora de instanciar clases.
  * Evitar que a una prenda se le asigne una categoría que no se condiga con su tipo
    (ej: una remera no puede ser calzado)

<u>Iteración 2:</u>
- Quiero especificar qué trama tiene la tela de una prenda (lisa, rayada, con lunares, 
  a cuadros o un estampado).
- Quiero crear una prenda especificando primero de qué tipo es.
- Quiero crear una prenda especificando en segundo lugar los aspectos relacionados a su 
  material (colores, material, trama, etc) para evitar elegir materiales inconsistentes con el tipo de prenda.
- Quiero guardar un borrador de la la última prenda que empecé a cargar para continuar 
  después.
- Quiero poder no indicar ninguna trama para una tela, y que por defecto ésta sea lisa.
- Quiero poder guardar una prenda solamente si esta es válida.

__BONUS:__ Por otro lado, el equipo de producto está analizando potenciales futuras funcionalidades para la aplicación 
y, a fin de tener una estimación de su complejidad, nos pidió que esbocemos una solución a los siguientes 
requerimientos, orientados a integrar el software con colegios e instituciones privadas:
- Quiero poder recibir sugerencias de uniformes armados.
- Quiero que un uniforme siempre conste de una prenda superior, una inferior y un calzado
- Como administrador de QueMePongo, quiero poder configurar diferentes uniformes para distintas instituciones 
  (Ej: para el colegio San Juan debe ser una chomba verde de piqué, un pantalón de acetato gris y zapatillas blancas, 
  mientras que para el Instituto Johnson siempre será una camisa blanca, pantalón de vestir negro y zapatos negros)

<u>Iteración 3:</u>
- Quiero poder conocer las condiciones climáticas de Buenos Aires en un momento dado para obtener sugerencias acordes.
- Quiero poder recibir sugerencias de atuendos que tengan una prenda para cada categoría, aunque a futuro podrán tener más (Ej.: Una remera, un pantalón, zapatos y un gorro).
- Quiero que al generar una sugerencia las prendas sean acordes a la temperatura actual sabiendo que para cada prenda habrá una temperatura hasta la cual es adecuada. (Ej.: “Remera de mangas largas” no es apta a más de 20°C).
- Quiero poder configurar fácilmente diferentes servicios de obtención del clima para ajustarme a las cambiantes condiciones económicas.
- Quiero poder asegurar la calidad de mi aplicación sin incurrir en costos innecesarios. 




### Resolución

El pseudocodigo utilizado se puede encontrar [aquí](pseudocodigo.java), mientras que el diagrama de 
clases [aquí](out/diagrama/QMP2.png).

# Integrantes

- Alejo Goltzman
- Alejo Sandrini
- Agustin Vidaurreta
- Ignacio Ardanaz
- Matias Beorlegui