/*
CLASE LineaFicherosLectura
 */
package hundir_la_flota_;

import java.io.BufferedReader;
import java.io.FileReader;

public class LineaFicherosLectura {

    //DECLARACIONES ATRIBUTOS DE LA CLASES
    //Declaración atributo de clase constante entero que representa el final de
    //un fichero
    private static final int FIN_FICHERO = -1;
    //declaración atributo de clase constante entero que representa el código
    //de caracter '#'
    private static final int SEPARADOR = (int) '#';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control RETURN
    private static final int RETURN = (int) '\r';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control SALTO DE LINEA
    private static final int SALTO_LINEA = (int) '\n';
    //declaración atributo de objeto variable entero que represente el código
    //de caracter leido desde el fichero
    private int codigo = SEPARADOR;
    //declaración atributo de objeto BufferedReader que posibilite el enlace
    //con el fichero de texto a nivel de lectura
    private BufferedReader fichero = null;

    //MÉTODOS
    //MÉTODO CONSTRUCTOR
    public LineaFicherosLectura(String nombre) throws Exception {
        //establecimiento enlace BufferedReader con fichero de texto identificado
        //a través del parámetro String nombreFichero dado
        fichero = new BufferedReader(new FileReader(nombre));
    }

    //MÉTODOS FUNCIONALES
    //MÉTODO hayPalabras QUE VERIFICA SI HAY ALGUNA SECUENCIA EN EL FICHERO REPRESENTADO
    //A TRAVÉS DEL OBJETO BufferedReader CORRESPONDIENTE
    public boolean quedanSecuencias() throws Exception {
        buscarSecuencia();
        return (codigo != FIN_FICHERO);
    }

    //método que realiza la búsqueda de Secuencias o Lineas leyendo el siguiente caracter mientras sea diferente
    //de los caracteres de control o el SEPARADOR
    private void buscarSecuencia() throws Exception {
        //lectura desde el fichero mientras el código de caracter leído
        //sea igual al espacio en blanco
        while ((codigo == SEPARADOR) || (codigo == RETURN)
                || (codigo == SALTO_LINEA)) {
            //lectura siguiente código de caracter desde el fichero
            codigo = fichero.read();
        }
    }

    //MÉTODO lectura LLEVAR A CABO LA LECTURA DE UNA SECUENCIA DESDE EL FICHERO
    public Linea lectura() throws Exception {
        //DECLARACIONES
        //declaración objeto Linea que reporesentara la palabra leida desde
        //el fichero
        Linea line = new Linea();

        //ACCIONES
        //lectura y almacenamiento de los caracteres de la palabra correspondientes
        //a los códigos de caracteres leidos desde el fichero
        while ((codigo != SEPARADOR) && (codigo != RETURN)
                && (codigo != SALTO_LINEA) && (codigo != FIN_FICHERO)) {
            //almacenar en el objeto Palabra palabra el caracter correspondiente
            //al código de caracter leido desde el fichero
            line.putCaracter((char) codigo);
            //lectura siguiente código de caracter desde el fichero
            codigo = fichero.read();
        }

        while ((codigo == RETURN) || (codigo == SALTO_LINEA) || (codigo == SEPARADOR)) {
            codigo = fichero.read();
        }

        //Devolver el objeto Palabra
        return line;
    }

    //MÉTODO cerrarEnlaceFichero QUE LLEVA A CABO EL CIERRE DEL ENLACE BufferedReader
    //con el fichero 
    public void cerrarEnlaceFichero() throws Exception {
        if (fichero != null) {
            fichero.close();
        }
    }

}
