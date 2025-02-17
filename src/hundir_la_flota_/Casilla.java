
package hundir_la_flota_;

public class Casilla {

    //ATRIBUTOS
    //atributos objeto para almacenar el valor de la fila de un objeto casilla
    private int fila;
    //atributos objeto para almacenar el valor de la columna de un objeto casilla
    private int columna;
    //atributos objeto para almacenar el valor del contenido de un objeto casilla
/*
  POSIBLES CONTENIDOS:
  -Casilla vacia('-')
  -Barco no tocado[solo punto de vista propio]('v')
  -disparo recibido:agua('a')
  -disparo recibido:tocado('t')
  -disparo recibido:hundido('x')  
     */
    private char contenido;
    //atributos objeto para almacenar el valor del estado de un objeto casilla (para identificar si la casilla ha sido seleccionada o no)
    private boolean estado;

    //METODOS CONSTRUCTORES
    //metodo constructor Casilla() inicializado con parametros para dar valor al los atributos fila,columna y contenido, además de instanciar el estado a FALSE
    public Casilla(int dato1, int dato2, char dato3) {
        fila = dato1;
        columna = dato2;
        contenido = dato3;
        estado = false;
    }

    //METODOS FUNCIONALES
    //metodo getContenido() para acceder al contenido del objeto casilla
    public char getContenido() {
        return contenido;
    }

    //metodo setContenido() para modificar el contenido del objeto casilla
    public void setContenido(char dato1) {
        contenido = dato1;
    }

    //metodo getEstado() para acceder al estado del objeto casilla
    public boolean getEstado() {
        return estado;
    }

    //metodo setEstado() para modificar el estado del objeto casilla
    public void setEstado(boolean dato1) {
        estado = dato1;
    }

    //metodo seleccionado() para modificar el valor del atributo estado a TRUE
    public void seleccionado() {
        estado = true;
    }

}
