
package hundir_la_flota_;

public class Barco {

    //ATRIBUTOS
    //atributo de objeto int para el numero de casillas del barco que han sido tocadas
    private int casillasTocadas;
    //atributo de objeto int para el numero total de casillas del barco
    private int numeroCasillas;
    //astributo de objeto char para el numero asignado al barco 
    private char codi;

    //METODO CONSTRUCTOR CON PARAMETROS para indicar el numero de casillas que componen al objeto barco y el numero que se le asigna
    public Barco(char numero, int casillas) {
        //inicializacion de las variables
        casillasTocadas = 0;
        numeroCasillas = casillas;
        codi = numero;
    }

    //METODO BarcoAtacado() para aumentar el numero de casillas del barco que han sido atacadas
    public void barcoAtacado() {
        casillasTocadas++;
    }

    //METODO BarcoHundido() que comprueba si todas las casillas del barco han sido tocadas
    public boolean barcoHundido() {
        if (casillasTocadas == numeroCasillas) {
            return true;
        }
        return false;
    }

    //METODO getBarco() para obtener el numero asignado al barco
    public char getBarco() {
        return codi;
    }

    //METODO getTocados() para obtener las casillas tocadas del barco
    public int getTocados() {
        return casillasTocadas;
    }

    //METODO getnumeroCasillas() para obtener el numero total de piezas que forman al barco
    public int getCasillas() {
        return numeroCasillas;
    }

}
