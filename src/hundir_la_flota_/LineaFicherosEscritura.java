package hundir_la_flota_;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LineaFicherosEscritura {

    //DECLARACIÓN ATRIBUTOS
    //declaración atributo de clase constante entero que representa el código
    //de caracter '#'
    private static final int SEPARADOR = (int) '#';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control RETURN
    private static final int RETURN = (int) '\r';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control SALTO DE LINEA
    private static final int SALTO_LINEA = (int) '\n';
    //declaración atributo de objeto BufferedWriter que posibilite el enlace
    //con el fichero de texto a nivel de escritura
    private BufferedWriter fichero = null;

    //MÉTODOS
    //MÉTODO CONSTRUCTOR
    public LineaFicherosEscritura(String nombreFichero) throws Exception {
        //establecimiento enlace BufferedWriter con fichero de texto identificado
        //a través del parámetro String nombreFichero dado
        fichero = new BufferedWriter(new FileWriter(nombreFichero));
    }

    //CONSTRUCTOR PARA AÑADIR SIN  ELIMINAR FICHERO SI EXISTE PREVIAMENTE
    public LineaFicherosEscritura(String nombreFichero, boolean adicion) throws Exception {
        //establecimiento enlace BufferedWriter con fichero de texto identificado
        //a través del parámetro String nombreFichero dado
        fichero = new BufferedWriter(new FileWriter(nombreFichero, adicion));
    }

    //MÉTODOS FUNCIONALES
    //MÉTODO escritura QUE LLEVA A CABO LA ESCRITURA DE UNA SECUENCIA EN EL
    public void escritura(Linea line) throws Exception {
        //escritura de los caracteres de la secuencia dada, caracter a caracter en
        //el fichero
        for (int indice = 0; indice < line.getNumeroCaracteres(); indice++) {
            //escritura en el fichero del caracter indice-ésimo de la palabra
            //dada
            fichero.write(line.getCaracter(indice));
        }
    }
    //método que realiza la escritura directa de los Strings en el fichero

    public void escrituraString(String nombre) throws Exception {
        fichero.write(nombre);
    }

    //MÉTODO escrituraEspacio QUE LLEVA A CABO LA ESCRITURA DEL CÓDIGO
    //DE CARACTER '/' EN EL FICHERO
    public void escrituraSeparador() throws Exception {
        //escritura del código del espacio en blanco en el fichero
        fichero.write(SEPARADOR);
    }

    //MÉTODO saltoLinea QUE LLEVA A CABO LA CREACIÓN DE UNA NUEVA LINEA
    public void saltoLinea() throws Exception {
        //escritura caracter de control RETURN en el fichero
        fichero.write(RETURN);
        //escritura caracter de control SALTO_LINEA en el fichero
        fichero.write(SALTO_LINEA);
    }

    //MÉTODO cerrarEnlaceFichero QUE LLEVA A CABO EL CIERRE DEL ENLACE BufferedWriter
    //con el fichero 
    public void cerrarEnlaceFichero() throws Exception {
        if (fichero != null) {
            fichero.close();
        }
    }
}
