package hundir_la_flota_;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Partida {

    //DECLARACIÓN DE ATRIBUTOS DE LA CLASE
    private String fecha;
    //atributo de objeto Jugador para almacenar la informacion del jugador
    private Jugador jugador;
    //atributo de objeto String que contiene el tamaño del tablero de la partida
    private String tamanoTablero;
    //atributo de objeto String que contiene al distribucion de los barcos de la partida
    private String distribucionBarcos;
    //atributo de objeto boolean para identificar si la partida ha sido ganada o perdida
    private boolean haGanado;
    //atributo de objeto String que contiene la modalidad de juego 
    private String modoJuego;

    //método cobstructor utilizando un parametro para almacenar el nombre dej jugador
    public Partida(String modo, String tamano, String distribucion, Linea nombre) {
        //inicializacion del objeto Jugador
        jugador = new Jugador(nombre);
        //la partida empieza como derrota (aun no ha ganado)
        haGanado = false;
        //inicializacion de la fecha 
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        //inicialiazcion de los atributos dados por parametro
        tamanoTablero = tamano;
        distribucionBarcos = distribucion;
        modoJuego = modo;

    }
    
    //METODO constructor para la visualizacion de los registros
    public Partida(String fecha, Jugador jugador, String tamanoTablero, String distribucionBarcos, boolean haGanado, String modoJuego) {
        this.fecha = fecha;
        this.jugador = jugador;
        this.tamanoTablero = tamanoTablero;
        this.distribucionBarcos = distribucionBarcos;
        this.haGanado = haGanado;
        this.modoJuego = modoJuego;
    }

    //método para obtener la fecha
    public String getFecha() {
        return fecha;
    }

    //método para obtener el modo de juego
    public String getModoJuego() {
        return modoJuego;
    }

    //método para obtener el tamaño del tablero
    public String getTamano() {
        return tamanoTablero;
    }

    //método para obtener la distribucion de los barcos
    public String getDistribucionBarcos() {
        return distribucionBarcos;
    }

    //método obtener para obtener el jugador
    public Jugador getJugador() {
        return jugador;
    }

    //método para establecer el resultado
    public void setResultado(boolean estado) {
        haGanado = estado;
    }

    public boolean getResultado() {
        return haGanado;
    }

    //METODO AumentarDisparosAgua() para aumentar en 1 el numero de disparos al agua
    public int getDisparosAgua() {
        return jugador.getDisparosAgua();
    }

    //METODO AumentarDisparosBarcosTocados() para aumentar en 1 el numero de disparos a barcos tocados
    public int getDisparosBarcosTocados() {
        return jugador.getDisparosBarcosTocados();
    }

    //METODO AumentarDisparosBarcosHundidos() para aumentar en 1 el numero de disparos a barcos hundidos
    public int getDisparosBarcosHundidos() {
        return jugador.getDisparosBarcosHundidos();
    }

    //METODO AumentarDisparosRecibidos() para aumentar en 1 el numero de disparos recibidos
    public int getDisparosRecibidos() {
        return jugador.getDisparosRecibidos();
    }

    //método para establecer la hora y fechar de la partida
    /*public void setFechaHora(){
        fechaHoraActual= LocalDateTime.now();
    }*/

    public String toString() {
        String conversion = "";

        String mensaje;
        if (haGanado) {
            mensaje = "VICTORIA";
        } else {
            mensaje = "DERROTA";
        }

        conversion = conversion + "FECHA: " + fecha + "\n"
                + "MODO DE JUEGO: " + modoJuego + "\n"
                + jugador.toString() + "\n"
                + "RESULTADO: " + mensaje + "\n";
        return conversion;
    }

}
