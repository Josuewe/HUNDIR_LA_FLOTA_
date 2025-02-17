/*
CLASE MENU QUE REALIZA LA CONSTRUCCION DEL MENU DEL JUEGO
 */
package hundir_la_flota_;

public class Menu {

    public void Menu() throws Exception {
        char opcion;
        boolean continuar = true; // Variable de control para el menú principal

        //visualizacion del menu
        while (continuar) {
            System.out.println("*****************************************************************"+
            "\nMENU HUNDIR LA FLOTA"+
            "\n*****************************************************************"+
            "\nSELECCIONE UNA OPCION: "+
            "\n|1| JUGAR\n|2| REGISTRO\n|s| SALIR"+
            "\n*****************************************************************"+
            "\nOPCION ELEGIDA: ");
            //lectura de la opcion seleccionada
            opcion = LT.readChar();
            //limpieza del buffer
            LT.skipLine();

            //inicializacion del menu elegido segun la opcion insertada por teclado
            switch (opcion) {
                case '1':
                    menuJugar();
                    break;
                case '2':
                    menuRegistros();
                    break;
                case 's':
                    System.out.println("¡GRACIAS POR JUGAR!");
                    continuar = false; // Cambia la variable de control para salir del menú
                    break;
                default:
                    System.out.println("OPCION INCORRECTA. INTENTE NUEVAMENTE.");
            }
        }
    }

    private void menuJugar() throws Exception {
        char opcionJugar;
        boolean continuar = true; // Variable de control para el menú de jugar

        //visualiacion menu
        while (continuar) {
            System.out.println("*****************************************************************"+
            "\nMENU JUGAR"+
            "\n*****************************************************************"+
            "\n|1| JUGAR SOLO\n|2| JUGADOR CONTRA JUGADOR\n|3| JUGAR CONTRA LA MAQUINA (NO IMPLEMENTADO)\n|s| SALIR"+
            "\nOPCION ELEGIDA: ");
            opcionJugar = LT.readChar();
            LT.skipLine();

            //inicializacion del menu elegido segun la opcion insertada por teclado
            switch (opcionJugar) {
                case '1':
                    Jugar.jugarSolo();
                    break;
                case '2':
                    Jugar.jugadorContraJugador();
                    break;
                case '3':
                    System.out.println("ESTA FUNCION NO HA SIDO IMPLEMENTADA");
                    break;
                case 's':
                    continuar = false; // Cambia la variable de control para salir del menú de jugar
                    break;
                default:
                    System.out.println("OPCION INCORRECTA. INTENTE NUEVAMENTE.");
            }
        }
    }

    private void menuRegistros() throws Exception {
        char opcionRegistros;
        boolean continuar = true; // Variable de control para el menú de registros

        //visualiacion menu
        while (continuar) {
            System.out.println("*****************************************************************"+
            "\nMENU REGISTROS"+
            "\n*****************************************************************"+
            "\n|1| MOSTRAR DETALLES DE PARTIDAS\n|2| MOSTRAR ESTADISTICAS DE UN JUGADOR(NO IMPLEMENTADO)\n|s| SALIR"+
            "\nOPCION ELEGIDA: ");
            opcionRegistros = LT.readChar();
            LT.skipLine();

            //inicializacion del menu elegido segun la opcion insertada por teclado
            switch (opcionRegistros) {
                case '1':
                    //lectura de las diferentes Partidas
                    PartidaFicherosLectura lector = new PartidaFicherosLectura("DetallesPartidas.txt");

                    while (lector.quedanPartidas()) {
                        Partida partida = lector.lectura();
                        System.out.println(partida);
                    }
                    //cerrar el fichero de texto
                    lector.cierre();
                    break;
                case '2':
                    System.out.println("FUNCION NO IMPLEMENTADA");
                    break;
                case 's':
                    continuar = false; // Cambia la variable de control para salir del menú de registros
                    break;
                default:
                    System.out.println("OPCION INCORRECTA. INTENTE NUEVAMENTE.");
            }
        }
    }
}
