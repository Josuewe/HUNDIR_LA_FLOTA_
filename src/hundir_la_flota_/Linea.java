package hundir_la_flota_;

public class Linea {

    //DECLARACIONES DE LOS ATRIBUTOS DE LA CLASE
    //declaración atributo de clase constante char que representa el 
    //del caracter de control SALTO DE LINEA
    private static final char SALTO_LINEA = '\n';
    //declaración atributo de clase para representar el máximo número de
    //caracteres de un objeto Palabra
    private static final int MAXIMO_NUMERO_CARACTERES = 30;
    //declaración atributo de objeto para almacenar los caracteres de un
    //objeto Palabra
    private char[] caracteres = new char[MAXIMO_NUMERO_CARACTERES];
    //declaración atributo de objeto para almacenar el número de caracteres
    //de un objeto Palabra
    private int numeroCaracteres;
    //declaración atributo de clase char para almacenar, de uno en uno, los caracteres
    //leídos desde el teclado
    private char caracter;

    //MÉTODOS
    //MÉTODOS CONSTRUCTORES
    //método constructor que posibilita la instanciación de un objeto Secuencia
    //vacio
    public Linea() {
        numeroCaracteres = 0;
    }

    //constructor por parámetros
    public Linea(char[] dato) {
        numeroCaracteres = dato.length;
        for (int indice = 0; indice < dato.length; indice++) {
            caracteres[indice] = dato[indice];
        }

    }

    //método para mirar si quedan Secuencias/Lineas por ser leídas
    public boolean quedanSecuencias() {
        caracter = LT.readChar();
        return (caracter != SALTO_LINEA);
    }

    //método que realiza la lectura de la Linea/Secuencia
    public void lectura() {
        numeroCaracteres = 0;
        while (caracter != SALTO_LINEA) {
            caracteres[numeroCaracteres] = caracter;
            numeroCaracteres++;
            caracter = LT.readChar();
        }
    }

    //método toString
    public String toString() {
        //DECLARACIONES
        //declaración una variable String para almacenar el resultado de la
        //conversión a String del objeto Secuencia que llamaal metodo toString
        String conversion = "";

        //ACCIONES
        //bucle de concatenación de los caracteres, de uno en uno, del objeto
        //Palabra al String conversion
        for (int indice = 0; indice < numeroCaracteres; indice++) {
            //concatenación del caracter indice-ésimo del objeto Palabra en el
            //String coonversion
            conversion = conversion + caracteres[indice];
        }
        //devolución String conversión donde estan todos los caracteres del objeto
        //Secuencia
        return conversion;
    }

    //método getNumeroCaracteres
    public int getNumeroCaracteres() {
        return numeroCaracteres;
    }

    //método getCaracter 
    public char getCaracter(int posicion) {
        return caracteres[posicion];
    }

    //método putCaracter
    public void putCaracter(char dato) {
        caracteres[numeroCaracteres] = dato;
        numeroCaracteres++;
    }

    //método que comprueva que dos lineas son iguales
    public static boolean iguales(Linea line1, Linea line2) {
        if (line1.numeroCaracteres == line2.numeroCaracteres) {
            for (int indice = 0; indice < line1.numeroCaracteres; indice++) {
                if (line1.caracteres[indice] != line2.caracteres[indice]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //método que nos permite convertir una Linea a un numero entero
    public int toInt() {
        int resultado = 0;
        int potencia = 1;
        for (int i = 0; i < this.numeroCaracteres; i++) {
            resultado += ((int) (caracteres[numeroCaracteres - i - 1] - '0')) * potencia;
            potencia *= 10;
        }
        return resultado;
    }

    //método que nos permite sasber si una Linea es True o no
    public boolean esTrue() throws Exception {
        if (this.numeroCaracteres != 4) {
            return false;
        }

        return this.caracteres[0] == 't'
                && this.caracteres[1] == 'r'
                && this.caracteres[2] == 'u'
                && this.caracteres[3] == 'e';
    }

}
