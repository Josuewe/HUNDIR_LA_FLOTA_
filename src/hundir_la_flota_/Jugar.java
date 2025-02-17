
package hundir_la_flota_;

public class Jugar {

    // ATRIBUTOS
    private static int fila, columna; // Coordenadas actuales del ataque
    private static char fila_char; // Representa la fila como carácter (A-J)

    // MÉTODO jugarSolo() que lleva a cabo la partida en modo solitario
    public static void jugarSolo() throws Exception {

        // Pedir el nombre del jugador
        System.out.println("Introduce tu nombre: ");
        Linea nombreJugador = new Linea();//lectura del nombre del jugador
        nombreJugador.lectura();

        // Crear el objeto jugador y la partida
        Partida Solitario = new Partida("Solitario", "10x10", "5-4-3-3-2", nombreJugador);

        // Inicializar el tablero
        Tablero tablero = new Tablero();
        tablero.distribucionRandom(); // Generar distribución aleatoria de barcos
        System.out.println(tablero.toStringTableroRival());

        // Iniciar partida
        while (!Solitario.getJugador().todosHundidos()) {
            Atacar(tablero);//se elige la casilla que se quiere atacar
            Solitario.getJugador().registrarDisparo(tablero.getContenido(fila, columna));//registro del disparo 
            tablero.acceder(fila, columna);
            clasificarContenidoTableroRival(tablero, Solitario.getJugador());//se modifica el tablero segun las casillas atacadas
            System.out.println(tablero.toStringTableroRival());//visualiacion del tablero 
        }

        // Mostrar mensaje de victoria
        System.out.println("¡HAS GANADO!");
        Solitario.setResultado(true);

        // Guardar los datos en el fichero de registros
        PartidaFicherosEscritura registro = new PartidaFicherosEscritura("DetallesPartidas.txt", true);
        registro.escritura(Solitario);
        registro.cierre();

        System.out.println("Partida registrada exitosamente en DetallesPartidas.txt");
    }

    // MÉTODO jugar1vs1() que lleva a cabo la partida en modo jugador contra jugador
    public static void jugadorContraJugador() throws Exception {
        Linea nombreJugador1 = new Linea();
        Linea nombreJugador2 = new Linea();

        // Pedir el nombre del jugador 1
        System.out.println("Jugador 1, introduce tu nombre (maximo 255 caracteres): ");
        nombreJugador1.lectura(); // Leer el nombre

        // Pedir el nombre del jugador 2
        System.out.println("Jugador 2, introduce tu nombre (maximo 255 caracteres): ");
        nombreJugador2.lectura(); // Leer el nombre

        // Mostrar los nombres de ambos jugadores
        System.out.println("Jugador 1: " + nombreJugador1.toString());
        System.out.println("Jugador 2: " + nombreJugador2.toString());

        // Inicializar los registros con ambos jugadores
        Partida PartidaJugador1 = new Partida("Jugador contra Jugador", "10x10", "5-4-3-3-2", nombreJugador1);
        Partida PartidaJugador2 = new Partida("Jugador contra Jugador", "10x10", "5-4-3-3-2", nombreJugador2);

        // Inicializar los tableros de ambos jugadores
        Tablero TabJugador1 = new Tablero();
        Tablero TabJugador2 = new Tablero();

        // Inicializar los tableros con barcos distribuidos aleatoriamente
        TabJugador1.distribucionRandom();
        TabJugador2.distribucionRandom();

        // Bucle principal del juego hasta que uno de los jugadores pierda
        while ((!PartidaJugador1.getJugador().todosHundidos()) && (!PartidaJugador2.getJugador().todosHundidos())) {
            // Turno del jugador 1
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nTURNO DEL JUGADOR 1");//visualiacion mensaje
            System.out.println(TabJugador1.toStringTablero1vs1(TabJugador2));//visualizacion de los tablero desde la perspectiva del jugador 1
            Atacar(TabJugador2);//atacar la casilla del jugador 2 seleccionada
            char contenidoAtacadoJugador2 = TabJugador2.getContenido(fila, columna);//obtener la casilla atacada
            TabJugador2.acceder(fila, columna);//acceso a la casilla del rival
            PartidaJugador1.getJugador().registrarDisparo(contenidoAtacadoJugador2);//registrar los disparos 
            PartidaJugador2.getJugador().registrarDisparoRecibido(contenidoAtacadoJugador2);// si el jugador 1 ha atacado un barco rival aumenta el numero de disparos recibidos del jugador 2
            clasificarContenidoTableroRivalJugadorContraJugador(TabJugador2, PartidaJugador2.getJugador(), PartidaJugador1.getJugador());//modificar el tablero segun el contenido de la casilla

            // Turno del jugador 2
            System.out.println("\n\n\n\n\n\n\n\n\n\n\nTURNO DEL JUGADOR 2");//visualiacion mensaje
            System.out.println(TabJugador2.toStringTablero1vs1(TabJugador1));//visualizacion de los tablero desde la perspectiva del jugador 2
            Atacar(TabJugador1);//atacar la casilla del jugador 1 seleccionada
            char contenidoAtacadoJugador1 = TabJugador1.getContenido(fila, columna);//obtener la casilla atacada
            TabJugador1.acceder(fila, columna);//acceso a la casilla del rival
            PartidaJugador2.getJugador().registrarDisparo(contenidoAtacadoJugador1);//registrar los disparos 
            PartidaJugador1.getJugador().registrarDisparoRecibido(contenidoAtacadoJugador1);// si el jugador 2 ha atacado un barco rival aumenta el numero de disparos recibidos del jugador 1
            clasificarContenidoTableroRivalJugadorContraJugador(TabJugador1, PartidaJugador1.getJugador(), PartidaJugador2.getJugador());//modificar el tablero segun el contenido de la casilla
        }

        //si gana el jugador 1 se visualiza el tablero y un mensaje de felicitacion
        if (PartidaJugador1.getJugador().todosHundidos()) {
            System.out.println(TabJugador2.toStringTablero1vs1(TabJugador1));
            System.out.println("GANA EL JUGADOR 2, " + nombreJugador2.toString());
            //gana el jugador 2 y pierde el jugador 1
            PartidaJugador2.setResultado(true);
            PartidaJugador1.setResultado(false);
        } //si gana el jugador 2 se visualiza el tablero y un mensaje de felicitacion
        else {
            System.out.println(TabJugador1.toStringTablero1vs1(TabJugador2));
            System.out.println("GANA EL JUGADOR 1, " + nombreJugador1.toString());
            //gana el jugador 1 y pierde el jugador 2
            PartidaJugador1.setResultado(true);
            PartidaJugador2.setResultado(false);
        }

        // Guardar los datos en el fichero de registros
        PartidaFicherosEscritura registro = new PartidaFicherosEscritura("DetallesPartidas.txt", true);
        registro.escritura(PartidaJugador1);
        registro.escritura(PartidaJugador2);
        registro.cierre();

        System.out.println("Partidas registradas exitosamente en DetallesPartidas.txt");
    }

    // MÉTODO clasificarContenidoTableroRival(): actualiza el estado del tablero según el ataque
    private static void clasificarContenidoTableroRival(Tablero Tablero, Jugador jugador) throws Exception {
        char contenido = Tablero.getContenido(fila, columna);

        switch (contenido) {
            case ('0') -> { // Si el disparo fue al barco 0
                (jugador.getBarco(0)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(0)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival
                    Tablero.BarcosHundidos((jugador.getBarco(0)).getBarco());
                    jugador.distribuirDisparosHundidos((jugador.getBarco(0)).getCasillas());
                }
            }
            case ('1') -> { // Si el disparo fue al barco 1
                (jugador.getBarco(1)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(1)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(1)).getBarco());
                    jugador.distribuirDisparosHundidos((jugador.getBarco(1)).getCasillas());
                }
            }
            case ('2') -> { // Si el disparo fue al barco 2
                (jugador.getBarco(2)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(2)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(2)).getBarco());
                    jugador.distribuirDisparosHundidos((jugador.getBarco(2)).getCasillas());
                }
            }
            case ('3') -> { // Si el disparo fue al barco 3
                (jugador.getBarco(3)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(3)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(3)).getBarco());
                    jugador.distribuirDisparosHundidos((jugador.getBarco(3)).getCasillas());
                }
            }
            case ('4') -> { // Si el disparo fue al barco 4
                (jugador.getBarco(4)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(4)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(4)).getBarco());
                    jugador.distribuirDisparosHundidos((jugador.getBarco(4)).getCasillas());
                }
            }
            case ('-') -> { // Si el disparo fue al agua
                Tablero.setContenidoAgua(fila, columna);
            }
            default ->
                System.out.println("Error: contenido desconocido.");
        }
    }

    // MÉTODO clasificarContenidoTableroRivalJugadorContraJugador(): actualiza el estado del tablero según el ataque en el modo Jugador Contra Jugador
    private static void clasificarContenidoTableroRivalJugadorContraJugador(Tablero Tablero, Jugador jugador, Jugador rival) throws Exception {
        char contenido = Tablero.getContenido(fila, columna);

        switch (contenido) {
            case ('0') -> { // Si el disparo fue al barco 0
                (jugador.getBarco(0)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(0)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival
                    Tablero.BarcosHundidos((jugador.getBarco(0)).getBarco());
                    rival.distribuirDisparosHundidos((jugador.getBarco(0)).getCasillas());
                }
            }
            case ('1') -> { // Si el disparo fue al barco 1
                (jugador.getBarco(1)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(1)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(1)).getBarco());
                    rival.distribuirDisparosHundidos((jugador.getBarco(1)).getCasillas());
                }
            }
            case ('2') -> { // Si el disparo fue al barco 2
                (jugador.getBarco(2)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(2)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(2)).getBarco());
                    rival.distribuirDisparosHundidos((jugador.getBarco(2)).getCasillas());
                }
            }
            case ('3') -> { // Si el disparo fue al barco 3
                (jugador.getBarco(3)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(3)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(3)).getBarco());
                    rival.distribuirDisparosHundidos((jugador.getBarco(3)).getCasillas());
                }
            }
            case ('4') -> { // Si el disparo fue al barco 4
                (jugador.getBarco(4)).barcoAtacado();//considerar el barco como atacado
                if ((jugador.getBarco(4)).barcoHundido()) {//si el barco ha sido hundido se muestran todas sus casillas como hundidas y se modifican los disparos del rival 
                    Tablero.BarcosHundidos((jugador.getBarco(4)).getBarco());
                    rival.distribuirDisparosHundidos((jugador.getBarco(4)).getCasillas());
                }
            }
            case ('-') -> { // Si el disparo fue al agua
                Tablero.setContenidoAgua(fila, columna);
            }
            default ->
                System.out.println("Error: contenido desconocido.");
        }
    }

    // MÉTODO Atacar(): gestiona el ataque de un jugador
    private static void Atacar(Tablero Tablero) throws Exception {
        //visualizacion mensaje al usuario
        System.out.println("INSERTE LA COORDENADA QUE QUIERE ATACAR(Por ejemplo: A1 o D10): ");
        //lectura caracter alfabetico correspondiente a las filas
        fila_char = LT.readChar();
        //lectura columna
        columna = LT.readInt();
        //conversion de la fila de char a int
        fila = fila_char - 'A' + 1;

        //si la casilla ya ha sido atacada se inserta otra coordenada
        while (Tablero.getEstado(fila, columna)) {
            //visualizacion mensaje al usuario
            System.out.println("ESTA COORDENADA YA HA SIDO ATACA, ELIJA OTRA");
            //visualizacion mensaje al usuario
            System.out.println("INSERTE LA COORDENADA QUE QUIERE ATACAR(EN CARACTERES ALFABETICOS MAYUSCULA): ");
            //lectura caracter alfabetico correspondiente a las filas
            fila_char = LT.readChar();
            //lectura columna
            columna = LT.readInt();
            //conversion de la fila de char a int
            fila = fila_char - 'A' + 1;
        }
    }
}
