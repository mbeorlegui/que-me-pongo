abstract class Prenda {
  TipoPrenda tipo;
  Material material;
  Color colorPrincipal;
  Color colorSecundario;
  Categoria categoria;

  constructor (TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario){
    this.tipo = requireNonNull(tipo, "El tipo de prenda es obligatorio");
    this.material = requireNonNull(material, "El material es obligatorio");
    this.colorPrincipal = requireNonNull(colorPrincipal, "El color principal es obligatorio");
    this.colorSecundario = requireNonNull(colorSecundario, "El color secundario es obligatorio");
  }

  constructor (TipoPrenda tipo, Material material, Color colorPrincipal){
    this.tipo = requireNonNull(tipo, "El tipo de prenda es obligatorio");
    this.material = requireNonNull(material, "El material es obligatorio");
    this.colorPrincipal = requireNonNull(colorPrincipal, "El color principal es obligatorio");
  }

  Categoria getCategoria() {
    return tipo.categoria()
  }
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
  REMERA, 
  PANTALON, 
  POLLERA, 
  CAMISA_MANGA_CORTA, 
  BLUSA
}

enum Material {
  ALGODON,
  LINO,
  LANA,
  POLIESTER,
  SEDA
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
  const REMERA = new TipoDePrenda(PARTE_SUPERIOR);
  const PANTALON = new TipoDePrenda(PARTE_INFERIOR);
  // Asi establecemos la relacion entre los Tipos y 
  // las Categorias
}
