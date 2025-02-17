package hundir_la_flota_;

public class PartidaFicherosEscritura {

    //DECLARACIONES ATRIBUTOS
    private LineaFicherosEscritura fichero = null;

    //MÉTODOS
    //Métodos Constructores
    public PartidaFicherosEscritura(String nombre) throws Exception {
        fichero = new LineaFicherosEscritura(nombre);
    }

    public PartidaFicherosEscritura(String nombre, boolean adicion) throws Exception {
        fichero = new LineaFicherosEscritura(nombre, adicion);
    }

    //Métodos Funcionales
    //método que lleva a cabo la escritura de un objeto Partida en un fichero de texto
    public void escritura(Partida partida) throws Exception {

        //ESCRITURA OBJETO Partida EN FICHERO
        //escribir fecha
        fichero.escrituraString(partida.getFecha());
        //escritura separador
        fichero.escrituraSeparador();
        //escritura nombre del Jugador
        fichero.escrituraString(partida.getJugador().getNombre());
        //escritura separador
        fichero.escrituraSeparador();
        //modo de juego
        fichero.escrituraString(partida.getModoJuego());
        //escritura separador
        fichero.escrituraSeparador();
        //escritura de la distribucion y tipos de barcos
        fichero.escrituraString(partida.getDistribucionBarcos());
        //escritura del separador
        fichero.escrituraSeparador();
        //tamaño del tablero
        fichero.escrituraString(partida.getTamano());
        //escritura del separador
        fichero.escrituraSeparador();
        //escritura DisparoAgua
        fichero.escrituraString(enteroToString(partida.getDisparosAgua()));
        //escritura del separador
        fichero.escrituraSeparador();
        //escribir NumerosDeDisparos a barcos hunddos
        fichero.escrituraString(enteroToString(partida.getDisparosBarcosHundidos()));
        //escritura del separador
        fichero.escrituraSeparador();
        //escribir NumerosDeDisparos a barcos tocados
        fichero.escrituraString(enteroToString(partida.getDisparosBarcosTocados()));
        //escritura del separador
        fichero.escrituraSeparador();
        //escribir NumerosDeDisparos recibidos
        fichero.escrituraString(enteroToString(partida.getDisparosRecibidos()));
        //escritura separador
        fichero.escrituraSeparador();
        //escritura del resultado de la partida
        if (partida.getResultado()) {
            fichero.escrituraString("true");
        } else {
            fichero.escrituraString("false");
        }
        //escritura separador
        fichero.escrituraSeparador();
        //escritura salto de linea
        fichero.saltoLinea();

    }

    //método que lleva a cabo el cierre del enlace lógico con el fichero físico
    public void cierre() throws Exception {
        fichero.cerrarEnlaceFichero();
    }

    //método para pasar un entero a String
    public String enteroToString(int numero) {
        // Convertir el número a String utilizando concatenación
        return "" + numero;
    }

}
