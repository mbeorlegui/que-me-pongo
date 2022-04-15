class Atuendo {
  List<Prenda> atuendo;
}

abstract class Prenda {
  String tipo;
  String material;
  String colorPrincipal;
  String colorSecundario;
  Categoria categoria;

  constructor (String tipo, String material, String colorPrincipal, String colorSecundario, Categoria categoria){
    if(tipo.notNull() && material.notNull() && colorPrincipal.notNull() && 
      categoria.notNull() && categoria.correspondeTipo(tipo)) {
      this.tipo = tipo;
      this.material = material;
      this.colorPrincipal = colorPrincipal;
      this.colorSecundario = colorSecundario;
      this.categoria= categoria;
    } else {
      throw new Exception(message = "Inicializacion invalida");
    }
  }
}

class Categoria {
  List<String> tiposValidos;

  Bool correspondeTipo(String tipo) {
    return (this.tiposValidos.contains(tipo))
  }
}

Categoria parteSuperior = new Categoria (["remera", "camisa", ...]);
Categoria parteInferior = new Categoria (["short", "jean", ...]);
Categoria accesorios = new Categoria (["lentes de sol", "anillo", ...]);
Categoria calzado = new Categoria (["zapatillas", "ojotas", ...]);
