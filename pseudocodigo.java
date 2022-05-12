class Prenda {
  TipoPrenda tipo;
  Material material;
  Color colorPrincipal;
  Color colorSecundario;
  Trama trama;
  EstadoPrenda estado;
  Integer usos;

  constructor (TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario, Trama trama){
    this.tipo = tipo;
    this.material = material;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;
    this.trama = trama;
    this.estado = new Limpia();
    this.usos = 1;
  }

  Categoria getCategoria() {
    return tipo.categoria();
  }

  Boolean esDeCategoria(Categoria unaCategoria) {
    return this.getCategoria().equals(unaCategoria);
  }

  void setEstado(EstadoPrenda unEstado) {
    this.estado = unEstado;
  }

  void validarCategoria(Categoria unaCategoria) {
    if (!this.esDeCategoria(unaCategoria)) {
      throw new Exception(
          message = (prenda.TipoDePrenda.name() + " no pertenece a la categoria " + categoria.name()));
    }
  }

  void usar() {
    usos++;
    estado.usar(this);
  }

  void validarUsos() {
    if (estado.puedeSerSugerida()) {
      throw new Exception(
          message = (prenda.TipoDePrenda.name() + " no puede ser sugerida"));
    }
  }
}

class Borrador {
  TipoPrenda tipo;
  Material material;
  Color colorPrincipal;
  Color colorSecundario;
  Categoria categoria;
  Trama trama = Trama.LISA;

  constructor(tipo){
    // Ponemos el tipo en el constructor para asegurarnos
    // de que se cargue primero
    this.tipo = requireNonNull(tipo, "El tipo de prenda es obligatorio");
  }

  void cargarMaterial(material){
    this.validarMaterial(material);
    this.material = requireNonNull(material, "El material de prenda es obligatorio");
  }

  void cargarColorPrincipal(colorPrincipal){
    this.colorPrincipal = requireNonNull(colorPrincipal, "El color principal de prenda es obligatorio");
  }

  void cargarColorSecundario(colorSecundario){
    this.colorSecundario = colorSecundario;
  }
  // Si no especificamos el color secundario, por defecto es null

  void cargarTrama(trama){
    this.trama = trama;
  }
  // Si no especificamos la trama, por defecto es LISA

  void validarMaterial() {
    // Falta especificar validaciones en la consigna.
    // Seguramente cuando se agreguen requerimientos
    // tengamos que refactorizar
  }

  void validarPrenda() {
    requireNonNull(material, "Imposible instanciar la prenda: El material de prenda es obligatorio");
    requireNonNull(colorPrincipal, "Imposible instanciar la prenda: El color principal de la prenda es obligatorio");
    requireNonNull(trama, "Imposible instanciar la prenda: La trama de la prenda es obligatoria");
  }

  Prenda crearPrenda() {
    this.validarPrenda();
    return new Prenda(
        this.tipo,
        this.material,
        this.colorPrincipal,
        this.colorSecundario,
        this.trama);
  }
  // La unica forma de instanciar una Prenda deberia ser a traves del borrador
}

class Color { // Modelo el color a partir de RGB
  int rojo, verde, azul

  constructor (rojo, verde, azul){
    this.rojo = rojo;
    this.verde = verde;
    this.azul = azul;
  }
}

enum Categoria {
  CALZADO,
  PARTE_SUPERIOR,
  PARTE_INFERIOR,
  ACCESORIO,
}

enum Material {
  ALGODON,
  LINO,
  LANA,
  POLIESTER,
  SEDA,
}

class TipoDePrenda {
  Categoria categoria

  constructor(Categoria categoria) {
    this.categoria = categoria;
  }

  Categoria getCategoria() {
    return this.categoria;
  }

  const ZAPATO = new TipoDePrenda(CALZADO);
  const REMERA=new TipoDePrenda(PARTE_SUPERIOR);
  const PANTALON=new TipoDePrenda(PARTE_INFERIOR);
  // Asi establecemos la relacion entre los Tipos y
  // las Categorias
}

class Uniforme {
  Prenda parteSuperior;
  Prenda parteInferior;
  Prenda calzado;

  constructor(Prenda parteSuperior, Prenda parteInferior, Prenda calzado){
    parteSuperior.validarCategoria(Categoria.PARTE_SUPERIOR);
    this.parteSuperior = parteSuperior;
    parteInferior.validarCategoria(Categoria.PARTE_INFERIOR);
    this.parteInferior = parteInferior;
    calzado.validarCategoria(Categoria.CALZADO); 
    this.calzado = calzado;
  }
}

  // Por ahora en lugar de crear una clase Colegio solo con el atributo de
  // Uniforme, decidimos mostrar como
  // instanciar un uniforme para el proximo uso
Borrador chombaVerde = new Borrador(...);
Borrador pantalonGris = new Borrador(...);
Borrador zapatillasBlancas = new Borrador(...);
//...
Uniforme sanJuan = new Uniforme(chombaVerde.crearPrenda(), pantalonGris.crearPrenda(), zapatillasBlancas.crearPrenda());

class Sugerencia {
  Prenda torso;
  Prenda piernas;
  Prenda pies;
  List<Prenda> accesorios;

constructor(Prenda torso, Prenda piernas, Prenda pies){
  validarSugerencia(torso, piernas, pies);
  this.torso = torso;
  this.piernas = piernas;
  this.pies = pies;
}

  void agregarAccesorio(Prenda unAccesorio) {
    accesorios.add(unAccesorio);
  }

  void validarSugerencia(Prenda torso, Prenda piernas, Prenda pies) {
    torso.validarCategoria(Categoria.PARTE_SUPERIOR);
    torso.validarUsos();
    piernas.validarCategoria(Categoria.PARTE_INFERIOR);
    piernas.validarUsos();
    pies.validarCategoria(Categoria.CALZADO);
    pies.validarUsos();
  }
}

abstract class EstadoPrenda {
  Boolean puedeSerSugerida();

  void usar(Prenda unaPrenda);
}

class Sucia extends EstadoPrenda {
  void lavar(Prenda unaPrenda) {
    unaPrenda.setUsos(0);
    unaPrenda.setEstado(new Lavando());
  }

  Boolean puedeSerSugerida() {
    return true;
  }

  void usar(Prenda unaPrenda) {
    if (unaPrenda.usos > 5) {
      unaPrenda.setEstado(new Percudida());
    }
  }
}

class Percudida extends EstadoPrenda {
  Boolean puedeSerSugerida() {
    return false;
  }

  void usar(Prenda unaPrenda) {

  }
}

class Limpia extends EstadoPrenda {
  Boolean puedeSerSugerida() {
    return true;
  }

  void usar(Prenda unaPrenda) {
    if (unaPrenda.usos >= 2) {
      unaPrenda.setEstado(new Sucia());
    }
  }
}

class Lavando extends EstadoPrenda {
  Boolean puedeSerSugerida(){
    return false;
  }
  void usar(Prenda unaPrenda) {
    unaPrenda.setEstado(new Limpia());
  }
}