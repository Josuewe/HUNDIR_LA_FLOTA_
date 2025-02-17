
package hundir_la_flota_;

public class PartidaFicherosLectura {

    //DECLARACIONES
    //declaración atributo de objeto que posibilite el enlace
    //con el fichero de texto a nivel de lectura
    private LineaFicherosLectura lector = null;
    // Nombre del archivo donde se almacenarán los registros de las partidas
    //private static final String NOMBRE_ARCHIVO = "DetallesPartida.txt";

    //MÉTODOS
    //Métodos Constructores
    public PartidaFicherosLectura(String nombre) throws Exception {
        //establecimiento del enlace lógico con el fichero físico
        lector = new LineaFicherosLectura(nombre);
    }

    public boolean quedanPartidas() throws Exception {
        return lector.quedanSecuencias();
    }

    // Método para mostrar los detalles de las partidas registradas
    public Partida lectura() throws Exception {
        //PalabraFicherosLectura lector = new PalabraFicherosLectura(NOMBRE_ARCHIVO);

        // Leer y mostrar cada línea del archivo de registros
        Linea fechaHora = lector.lectura();
        Linea nombreJugador = lector.lectura();
        Linea modoJuego = lector.lectura();
        Linea tamanoTablero = lector.lectura();
        Linea distribucionBarcos = lector.lectura();
        Linea disparosAgua = lector.lectura();
        Linea disparosHundidos = lector.lectura();
        Linea disparosTocados = lector.lectura();
        Linea disparosRecibidos = lector.lectura();
        Linea victoria = lector.lectura();

        //declaracion de objeto jugador para poder tener todos los datos de los disparos y nombre del jugadro
        Jugador jugador = new Jugador(nombreJugador, disparosAgua.toInt(), disparosTocados.toInt(), disparosHundidos.toInt(), disparosRecibidos.toInt());
        //devolucion de nueva partida creada por los parámetros leídos en el fichero
        return new Partida(fechaHora.toString(), jugador, tamanoTablero.toString(), distribucionBarcos.toString(), victoria.esTrue(), modoJuego.toString());
    }

    public void cierre() throws Exception {
        lector.cerrarEnlaceFichero();
    }

}
