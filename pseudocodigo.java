import java.util.List;
import java.util.Map;

import Color.Categoria;

class Prenda {
  TipoPrenda tipo;
  Material material;
  Color colorPrincipal;
  Color colorSecundario;
  Trama trama;
  EstadoPrenda estado;
  Integer usos;
  Integer temperaturaMaximaAdecuada;

  constructor (TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario, Trama trama, Integer temperaturaMaximaAdecuada){
    this.tipo = tipo;
    this.material = material;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;
    this.trama = trama;
    this.estado = new Limpia();
    this.usos = 1;
    this.temperaturaMaximaAdecuada = temperaturaMaximaAdecuada;
  }

  Integer getTemperaturaMaximaAdecuada(){
    return temperaturaMaximaAdecuada;
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
    if (!estado.puedeSerSugerida()) {
      throw new Exception(
       message = (prenda.TipoDePrenda.name() + " no puede ser sugerida"));
    }
  }

  void validarTemperaturaAdecuada(Integer temperatura){
    if(temperaturaMaximaAdecuada != NULL && temperaturam > temperaturaMaximaAdecuada){
      throw new Exception(
       message = (prenda.TipoDePrenda.name() + " no puede ser sugerida porque la temperatura supera su temperatura maxima adecuada"));
    }
  }

  void validarParaSugerencia(Categoria categoria, Integer temperatura) {
    this.validarCategoria(categoria);
    this.validarUsos();
    this.validarTemperaturaAdecuada(temperatura);
  }
}

class Borrador {
  TipoPrenda tipo;
  Material material;
  Color colorPrincipal;
  Color colorSecundario = NULL;
  Categoria categoria;
  Trama trama = Trama.LISA;
  Integer temperaturaMaximaAdecuada = NULL;

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

  void cargarTemperaturaMaximaAdecuada(temperaturaMaximaAdecuada) {
    this.temperaturaMaximaAdecuada = temperaturaMaximaAdecuada;
  }
  // Si no especificamos la temperatura maxima adecuada, por defecto es null

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
        this.trama,
        this.temperaturaMaximaAdecuada);
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

/* Otra posibilidad: Guardar las temperaturas en una class Temperaturas y que el sistema
  las actualice cada X tiempo, asi controlamos la cantidad de llamados a la API
  Y ademas para tener actualizada la lista cuando la llaman, cada una hora deberia ir 
  eliminando el primer item de la lista para hacer get(0) y sea la "hora actual"
class Temperaturas {
  List<Map<String, Object>> condicionesClimaticas;

  constructor (List<Map<String, Object>> condicionesClimaticas){
    AccuWeatherAPI apiClima = new AccuWeatherAPI();
    List<Map<String, Object>> nuevasCondicionesClimaticas = apiClima.getWeather(“Buenos Aires, Argentina”);
    //this.temperaturaActual = condicionesClimaticas.get(0).get("Temperature").get("Value");

    condicionesClimaticas = nuevasCondicionesClimaticas;
  }

  void actualizarTemperaturas(){

  }
}*/

class Sugerencia {
  List<Prenda> torso;
  List<Prenda> piernas;
  List<Prenda> pies;
  List<Prenda> accesorios;
  Integer temperaturaActual;

constructor(Prenda torso, Prenda piernas, Prenda pies, Prenda accesorio){
  AccuWeatherAPI apiClima = new AccuWeatherAPI();
  List<Map<String, Object>> condicionesClimaticas = apiClima.getWeather(“Buenos Aires, Argentina”);
  this.temperaturaActual = condicionesClimaticas.get(0).get("Temperature").get("Value");
  validarSugerencia(torso, piernas, pies, accesorio);
  this.agregarTorso(torso);
  this.agregarPiernas(piernas);
  this.agregarPies(pies);
  this.agregarAccesorio(accesorio);
}

  void agregarAccesorio(Prenda unAccesorio) {
    accesorio.validarParaSugerencia(Categoria.ACCESORIO, temperaturaActual);
    accesorios.add(unAccesorio);
  }

  void agregarTorso(Prenda unaPrendaTorso) {
    torso.validarParaSugerencia(Categoria.PARTE_SUPERIOR, temperaturaActual);
    torso.add(unaPrendaTorso);
  }

  void agregarPiernas(Prenda unaPrendaPiernas) {
    piernas.validarParaSugerencia(Categoria.PARTE_INFERIOR, temperaturaActual);
    piernas.add(unaPrendaPiernas);
  }

  void agregarPies(Prenda unaPrendaPies) {
    pies.validarParaSugerencia(Categoria.CALZADO, temperaturaActual);
    pies.add(unaPrendaPies);
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

public final class AccuWeatherAPI {

  public final List<Map<String, Object>> getWeather(String ciudad) {
  return Arrays.asList(new HashMap<String, Object>(){{
    put("DateTime", "2019-05-03T01:00:00-03:00");
    put("EpochDateTime", 1556856000);
    put("WeatherIcon", 33);
    put("IconPhrase", "Clear");
    put("IsDaylight", false);
    put("PrecipitationProbability", 0);
    put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
    put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
    put("Temperature", new HashMap<String, Object>(){{
      put("Value", 57);
      put("Unit", "F");
      put("UnitType", 18);
    }});
  }});
}
}