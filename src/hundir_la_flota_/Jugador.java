package hundir_la_flota_;

public class Jugador {
    //atributo de objeto Linea para almacenar el nombre del jugador

    private Linea nombre;
    //atributos de objeto Barco para los 5 tipos de barco que tiene cada jugador en su tablero
    private Barco Barco0;
    private Barco Barco1;
    private Barco Barco2;
    private Barco Barco3;
    private Barco Barco4;
    //atributos de objeto para almecenar el numero de disparos
    private int DisparosAgua;
    private int DisparosBarcosTocados;
    private int DisparosBarcosHundidos;
    private int DisparosRecibidos;

    //METODO constructor con parametros para el nombre
    public Jugador(Linea nombreJugador) {
        //inicializacion del nombre
        nombre = nombreJugador;
        //inicializacion de los objetos barco del jugador 
        Barco0 = new Barco(Tablero.Barco0, 5);
        Barco1 = new Barco(Tablero.Barco1, 4);
        Barco2 = new Barco(Tablero.Barco2, 3);
        Barco3 = new Barco(Tablero.Barco3, 3);
        Barco4 = new Barco(Tablero.Barco4, 2);
        //inicializacion en 0 de los disparos
        DisparosAgua = 0;
        DisparosBarcosTocados = 0;
        DisparosBarcosHundidos = 0;
        DisparosRecibidos = 0;
    }

    //METODO constructor para la visualizacion de los registros
    public Jugador(Linea nombre, int DisparosAgua, int DisparosBarcosTocados, int DisparosBarcosHundidos, int DisparosRecibidos) {
        //iniciailizacion de los atributos con los parametros indicados
        this.nombre = nombre;
        this.DisparosAgua = DisparosAgua;
        this.DisparosBarcosTocados = DisparosBarcosTocados;
        this.DisparosBarcosHundidos = DisparosBarcosHundidos;
        this.DisparosRecibidos = DisparosRecibidos;
    }

    //METODO getNombre para obtener un String con el nombre del jugador
    public String getNombre() {
        //devolucion del nombre como String
        return nombre.toString();
    }

    // MÉTODO TodosHundidos(): verifica si todos los barcos están hundidos
    public boolean todosHundidos() {
        final int TODOS_HUNDIDOS = 5 + 4 + 3 + 3 + 2; // Suma de las casillas de todos los barcos
        return (Barco0.getTocados() + Barco1.getTocados() + Barco2.getTocados() + Barco3.getTocados() + Barco4.getTocados()) == TODOS_HUNDIDOS;
    }

    //METODO getBarco() para obtener el objeto Barco indicado por parametro
    public Barco getBarco(int numero) throws Exception {
        //segun el numero del barco indicado en parametro se deuelve un barco
        if (numero == 0) {
            return Barco0;
        }
        if (numero == 1) {
            return Barco1;
        }
        if (numero == 2) {
            return Barco2;
        }
        if (numero == 3) {
            return Barco3;
        } else {
            return Barco4;
        }
    }

    //METODO toString para visualizar todo el contenido del objeto jugador
    public String toString() {
        String conversion;

        conversion = "NOMBRE DEL JUGADOR: " + nombre.toString() + "\n"
                + "DISPAROS AL AGUA: " + DisparosAgua + "\n"
                + "DISPAROS A BARCOS TOCADOS: " + DisparosBarcosTocados + "\n"
                + "DISPAROS A BARCOS HUNDIDOS: " + DisparosBarcosHundidos + "\n"
                + "DISPARTOS RECIBIDOS: " + DisparosRecibidos;
        return conversion;
    }

    // Método para registrar un disparo realizado por un jugador 
    public void registrarDisparo(char contenido) {
        if (contenido == '-') { // Si el disparo fue al agua aumentar los disparos al agua
            DisparosAgua++;
        } else { // si el disparo no fue al agua                          
            DisparosBarcosTocados++;// Incrementar disparos a barcos tocados     
        }
    }

    //METODO distribuirDisparosHundidos() para aumentar los disaparos a barcos hundidos cuando se ha hundido un barco
    public void distribuirDisparosHundidos(int barco) {
        DisparosBarcosHundidos += barco;//aumentar los disparos a barcos hundidos
        DisparosBarcosTocados -= barco;//disminuir los disparos a barcos tocados
    }

    // Método para registrar un disparo realizado por un jugador 
    public void registrarDisparoRecibido(char contenido) {
        if (contenido != '-') { // Si el disparo fue al agua aumentar los disparos al agua
            DisparosRecibidos++;
        }
    }

    //METODO getDisparosAgua() para obtener el numero de disparos al agua
    public int getDisparosAgua() {
        return DisparosAgua;
    }

    //METODO getDisparosBarcosTocados() para obtener el numero de disparos a barcos tocados
    public int getDisparosBarcosTocados() {
        return DisparosBarcosTocados;
    }

    //METODO getDisparosBarcosHundidos() para obtener el numero de disparos a barcos hundidos
    public int getDisparosBarcosHundidos() {
        return DisparosBarcosHundidos;
    }

    //METODO getDisparosRecibidos() para obtener el numero de disparos recibidos
    public int getDisparosRecibidos() {
        return DisparosRecibidos;
    }
}
