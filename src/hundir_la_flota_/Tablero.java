
package hundir_la_flota_;

/*
CasillaNoConfirmada --->  BLANCO
BarcoNoTocado ---> VERDE
Agua ----> AZUL
Tocado ----> AMARILLO
Hundido ----> ROJO
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Tablero {
    //ATRIBUTOS
    //atributos de clase final int para indicar el color de la casilla segun el valor numerico asociado por la clase LT
    public static final int BLANCO=0,VERDE=3,AZUL=7,AMARILLO=4,ROJO=2;
    //atributos de clase final char para los tipos de contenidos que puede almacenar un objeto casilla
    public static final char CasillaNoConfirmada='-',BarcoNoTocado='v',Agua='a',Tocado='t',Hundido='x',Barco1='1',
       Barco2='2',Barco3='3',Barco4='4',Barco0='0';
    //atributo objeto int para el valor de las filas y las columnas de la matriz que representa el tablero
    private int fila,columna;
    //atributo objeto array bidimensional que representa el tablero con el valor de cada casilla
    private Casilla matriz [][];
    
    
    //METODOS CONSTRUCTORES 
    //METODO CONSTRUCTOR VACÍO
    public Tablero(){
        fila=10;
        columna=10;
        matriz=new Casilla[fila][columna];
        inicializar();
    }
    
    //METODO CONSTRUCTOR con dos parametros para asignar las dimensiones del tablero
    public Tablero(int dato1, int dato2){
        fila=dato1;
        columna=dato2;
        matriz=new Casilla[fila][columna];
        inicializar();
    }
    
    //METODO inicializar() para inicializar todas casillas del tablero como Agua
    private void inicializar(){
        //Bucle para aumentar el valor de la fila, luego de haber asignado los contenidos de cada una de sus columna pertenecientes
        for (int dimX=0;dimX<fila;dimX++){
            //bucle para aumentar el valor de las columnas de cada fila para asignarles un contenido
            for (int dimY=0;dimY<columna;dimY++){
                matriz[dimX][dimY]=new Casilla(dimX,dimY,CasillaNoConfirmada);
            }
        } 
    }
// METODO DistribucionRandom() para inicializar todas casillas del tablero, mediante una distrucion aleatoria de una tablero 10x10
public void distribucionRandom() throws Exception {
    // DECLARACIONES
    // Objeto BufferedReader para leer el fichero con la distribucion de los barcos
    BufferedReader fichero;
    // Objeto Random para generar un número aleatorio para elegir el fichero con la distribucion de los barcos
    Random generador = new Random();
    // int para almacenar el valor aleatorio generado por el objeto Random
    int aleatorio;
    //char para almacenar el contenido de cada casilla
    char contenido;
    //final int para el codigo del caracter espacio
    final int SEPARADOR=(int)' ';
    //final int para el codigo del caracter de control RETURN
    final int RETURN=(int) '\r';
    //final int para el codigo del caracter de control SALTO DE LINEA
    final int SALTO_LINEA=(int) '\n';
    //int codigo para la lectura de los codigos de caracteres el fichero
    int codigo=SEPARADOR;

    // ACCIONES
    // Generación del número aleatorio
    aleatorio = generador.nextInt(0,99);
    System.out.println("Archivo seleccionado: "+aleatorio+".txt"); 

    // Instanciación del componente PalabraFicherosLectura para enlazarlo con el fichero correspondiente
    fichero=new BufferedReader(new FileReader(aleatorio+".txt"));
    //lectura del primer codigo
    codigo=fichero.read();

    // Bucle para recorrer las filas del tablero
    for (int dimX = 0; dimX < fila; dimX++) {
        // Bucle para recorrer las columnas de cada fila
        for (int dimY = 0; dimY < columna; dimY++) {          
            //mientras el codigo no corresponda a un caracter alfabetico se lee el proximo codigo
             while((codigo==SEPARADOR)||(codigo==RETURN)||(codigo==SALTO_LINEA)){
            //lectura del siguiente codigo
            codigo=fichero.read();
            }           
            // Lectura del contenido de la casilla desde el fichero           
            contenido=(char)codigo;
            // Asignación del contenido a la casilla en la matriz
            matriz[dimX][dimY] = new Casilla(dimX, dimY, contenido);
            //lectura del siguiente codigo
            codigo=fichero.read();
           
        }
    }
    // Cerrar el fichero
    fichero.close();
}

    
    //METODO acceder() con parametros para visualiar el contenido de la casilla
    public void acceder(int fila, int columna){
        //Aplicar el metodo seleccionado() a la casilla seleccionada por parametros para que el estado de la casilla pase a ser TRUE y se visualize su contenido
        matriz[fila-1][columna-1].seleccionado();
    }
    
    
    //METODO filaGuion() ,dado por parametro, para visualizar una fila de guiones segun la dimension del tablero
    private String filaGuion(){
        //atributo String para obtener una fila de guiones
        String filaG="";
        //bucle para crear la primera fila de la matriz, con el numero de columnas de la matriz 
        for(int Finicial=0;Finicial<12;Finicial++){
            //visualizacion del numero de columnas del tablero
            filaG+="--";          
        }
        return filaG;
    }
    
    //METODO toStringTablerorIVAL() que visualiza el tablero del rival
    public String toStringTableroRival() throws Exception{
        //ATRIBUTOS
        //final int para el numero total de filas y columnas
        final int MAX=10;
        //string vacio para almacenar el string con la matriz
        String Tablero="";
        //int para representar el alfabeto mediente el codigo numerico
        int NumAlf=65;
        //int para contener el color del background segun la casilla seleccionada por el jugador
        int color=BLANCO;
        
        //ACCIONES
        //visualizacion de una fila que funciona como separador (se añade un espacio al final para que las columnas queden alineadas)
        Tablero+=filaGuion()+"\n ";
        
        //bucle para crear la primera fila de la matriz, con el numero de columnas de la matriz 
        for(int Finicial=0;Finicial<MAX;Finicial++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Finicial+1);          
        }
        
        //salto de línea
        Tablero+="\n";
        
        //bucle para visualiar los contenidos de las casillas con 2 columnas extras con las letras del alfabeto para usarlos de referencia\
        for(int fila=0;fila<MAX;fila++){
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna inicial 
            Tablero+=(char)NumAlf+"|";
            for(int columna=0;columna<MAX;columna++){
                //tratamiento de la casilla segun su contenido, modificacion del color del BG
                switch(matriz[fila][columna].getContenido()){
                    //si el contenido es Agua ---> AZUL
                    case Agua: color=AZUL;
                    break;
                    //si el contenido es una casilla Barco----> AMARILLO (se mantendra el contenido de la casilla pero se mostrara como un barco tocado)
                    case Barco0,Barco1,Barco2,Barco3,Barco4: color=AMARILLO; 
                    break;
                    //si el contenido es HUNDIDO ---> ROJO
                    case Hundido: color=ROJO;
                    break;
                }
                //si se ha accedido ya a la casilla se muestra su contenido
                if(matriz[fila][columna].getEstado()){
                    //en el caso de que la casilla acertada sea un barco se mostrará como una casilla hundida, pero se mantendra el contenido como un barco
                    if(matriz[fila][columna].getContenido()==Barco0||matriz[fila][columna].getContenido()==Barco1||matriz[fila][columna].getContenido()==Barco2||
                            matriz[fila][columna].getContenido()==Barco3||matriz[fila][columna].getContenido()==Barco4){
                        Tablero+=LT.colorBG(color)+Tocado+LT.colorBG(BLANCO)+" ";

                    }
                    else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(color)+matriz[fila][columna].getContenido()+LT.colorBG(BLANCO)+" ";
                    }
                }
                //si no se ha accedido previamente a la casilla se muestra la casilla como CasillaNoConfirmada
                else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(BLANCO)+CasillaNoConfirmada+" ";
                }
            }
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna final
            Tablero+="|"+(char)NumAlf+"\n";
            //aumento del codigo numerico de NumAlf para para obtener la letra de la siguiente fila
            NumAlf++;                  
        }
        
        //se añade el caracter espacio al String tablero para que las columnas de la fila final de numeros queden alineadas con las columnas de las casillas del tablero
        Tablero+=" ";
        //bucle para crear la ultima fila de la matriz, con el numero de columnas de la matriz 
        for(int Ffinal=0;Ffinal<MAX;Ffinal++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Ffinal+1);          
        }
        //visualizacion de una fila que funciona como separador
        Tablero+="\n"+filaGuion();

        return Tablero;
    }
    
    //METODO getEstado() con parametros para obtener el estado de la casilla seleccionada del tablero
    public boolean getEstado(int fila, int columna){
        //obtener el estado de la casilla seleccionada
        return matriz[fila-1][columna-1].getEstado();
    }
    
    //METODO getContenido() con parametros para obtener el contenido de la casilla seleccionada del tablero
    public char getContenido(int fila, int columna){
        //obtener el contenido de la casilla seleccionada
        return matriz[fila-1][columna-1].getContenido();
    }
    
    //METODO setContenidoTocado() con parametros para modificar el contenido de la casilla seleccionada del tablero y convertirlo en una casilla tocada
    public void setContenidoTocado(int fila, int columna){
        //convertir contenido de la casilla en 'Tocado'
        matriz[fila-1][columna-1].setContenido(Tocado);
    }
    
    //METODO setContenidoAgua() con parametros para modificar el contenido de la casilla seleccionada del tablero y convertirlo en una casilla de agua
    public void setContenidoAgua(int fila, int columna){
        //convertir contenido de la casilla en 'Tocado'
        matriz[fila-1][columna-1].setContenido(Agua);
    }
    
    //METODO setContenidoHundido() con parametros para modificar el contenido de la casilla seleccionada del tablero y convertirlo en una casilla hundida
    public void setContenidoHundido(int fila, int columna){
        //convertir contenido de la casilla en 'Tocado'
        matriz[fila-1][columna-1].setContenido(Hundido);
    }
    
    //METODO BarcosHundidos() con parametros para convertir todas las casillas del tipo de barco indicado en los parametros en casillas hundidas
    public void BarcosHundidos(char tipoBarco){
        //Bucle para aumentar el valor de la fila, luego de haber asignado los contenidos de cada una de sus columna pertenecientes
        for (int dimX=0;dimX<10;dimX++){
            //bucle para aumentar el valor de las columnas de cada fila para asignarles un contenido
            for (int dimY=0;dimY<10;dimY++){
                //si el contenido es como el que se ha indicado en los parametros la casilla se convierte a Hundido
                if(matriz[dimX][dimY].getContenido()==tipoBarco)
                matriz[dimX][dimY].setContenido(Hundido);
            }
        } 
    }
    
    //METODO toStringTablerorIVAL() que visualiza el tablero del rival
    public String toStringTablero1vs1(Tablero Rival) throws Exception{
        //ATRIBUTOS
        //final int para el numero total de filas y columnas
        final int MAX=10;
        //string vacio para almacenar el string con la matriz
        String Tablero="";
        //int para representar el alfabeto mediente el codigo numerico
        int NumAlf=65;
        //int para contener el color del background segun la casilla seleccionada por el jugador
        int color=BLANCO;
        
        //ACCIONES
        //visualizacion de una fila que funciona como separador (se añade un espacio al final para que las columnas queden alineadas)
        Tablero+=filaGuion()+"\n"+"     TABLERO PROPIO                     TABLERO RIVAL\n ";
        
        //bucle para crear la primera fila de la matriz, con el numero de columnas de la matriz del LOCAL
        for(int Finicial=0;Finicial<MAX;Finicial++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Finicial+1);          
        }
        //separacion entre los dos tableros
            Tablero+="             "; 
        //bucle para crear la primera fila de la matriz, con el numero de columnas de la matriz del ENEMIGO
        for(int Finicial=0;Finicial<MAX;Finicial++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Finicial+1);          
        }
        
        //salto de línea
        Tablero+="\n";
        
        //bucle para visualiar los contenidos de las casillas con 2 columnas extras con las letras del alfabeto para usarlos de referencia\
        for(int fila=0;fila<MAX;fila++){
            
            //TABLERO LOCAL(SE MUESTRAN TODOS LOS CONTENIDOS DE LAS CASILLAS)
        
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna inicial 
            Tablero+=(char)NumAlf+"|";
            for(int columna=0;columna<MAX;columna++){
                //tratamiento de la casilla segun su contenido, modificacion del color del BG
                switch(matriz[fila][columna].getContenido()){
                    //si el contenido es Agua ---> AZUL
                    case Agua: color=AZUL;
                    break;
                    //si el contenido es una casilla Barco----> VERDE
                    case Barco0,Barco1,Barco2,Barco3,Barco4: color=VERDE; 
                    break;
                    //si el contenido es HUNDIDO ---> ROJO
                    case Hundido: color=ROJO;
                    break;
                }
                //si se ha accedido ya a la casilla 
                if(matriz[fila][columna].getEstado()){
                    //en el caso de que la casilla acertada sea un barco se mostrará como una casilla hundida de color amarillo, pero se mantendra el contenido como un barco
                    if(matriz[fila][columna].getContenido()==Barco0||matriz[fila][columna].getContenido()==Barco1||matriz[fila][columna].getContenido()==Barco2||
                            matriz[fila][columna].getContenido()==Barco3||matriz[fila][columna].getContenido()==Barco4){
                        Tablero+=LT.colorBG(AMARILLO)+Tocado+LT.colorBG(BLANCO)+" ";

                    }
                    else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(color)+matriz[fila][columna].getContenido()+LT.colorBG(BLANCO)+" ";
                    }
                }
                //si no se ha accedido previamente a la casilla
                else{
                    //en el caso de que la casilla sea un barco se mostrara la casilla como BarcoNoTocado, de color verde
                    if(matriz[fila][columna].getContenido()==Barco0||matriz[fila][columna].getContenido()==Barco1||matriz[fila][columna].getContenido()==Barco2||
                            matriz[fila][columna].getContenido()==Barco3||matriz[fila][columna].getContenido()==Barco4){
                        Tablero+=LT.colorBG(color)+BarcoNoTocado+LT.colorBG(BLANCO)+" ";
                    }
                    //en el caso de que la casilla no sea un barco
                    else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(BLANCO)+CasillaNoConfirmada+" ";
                    }
                }
            }
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna final
            Tablero+="|"+(char)NumAlf;
            
            //separacion entre los dos tableros
            Tablero+="          ";
            
            
            
            //TABLERO ENEMIGO(se trata como si fuese el tablero enemigo del modo JUGAR SOLO)
            
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna inicial 
            Tablero+=(char)NumAlf+"|";
            //en este caso el bucle trata de la columna 1 hasta la 10, porque se trata con el objeto tablero y los metodos utilizados para obetener el estado y contenido 
            //de sus casillas ya hacen la converion del numero de fila y columna a la componente del array correspondiente (fila-1,columna-1)
            
            //en relacion a lo mencionado, cuando se usen estos metodos habra que sumar +1 a las filas, porque se esta reutilizando el bucle anterior usado para almecenar
            //el contenido del tablero del primer jugador
            for(int columna=1;columna<=MAX;columna++){
                //tratamiento de la casilla segun su contenido, modificacion del color del BG
                switch(Rival.getContenido(fila+1,columna)){
                    //si el contenido es Agua ---> AZUL
                    case Agua: color=AZUL;
                    break;
                    //si el contenido es una casilla Barco----> AMARILLO (se mantendra el contenido de la casilla pero se mostrara como un barco tocado)
                    case Barco0,Barco1,Barco2,Barco3,Barco4: color=AMARILLO; 
                    break;
                    //si el contenido es HUNDIDO ---> ROJO
                    case Hundido: color=ROJO;
                    break;
                }
                //si se ha accedido ya a la casilla se muestra su contenido
                if(Rival.getEstado(fila+1,columna)){
                    //en el caso de que la casilla acertada sea un barco se mostrará como una casilla hundida, pero se mantendra el contenido como un barco
                    if(Rival.getContenido(fila+1,columna)==Barco0||Rival.getContenido(fila+1,columna)==Barco1||Rival.getContenido(fila+1,columna)==Barco2||
                            Rival.getContenido(fila+1,columna)==Barco3||Rival.getContenido(fila+1,columna)==Barco4){
                        Tablero+=LT.colorBG(color)+Tocado+LT.colorBG(BLANCO)+" ";

                    }
                    else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(color)+Rival.getContenido(fila+1,columna)+LT.colorBG(BLANCO)+" ";
                    }
                }
                //si no se ha accedido previamente a la casilla se muestra la casilla como CasillaNoConfirmada
                else{
                    //visualzizacion del contenido de la casilla con su correspondiente color asignado
                    Tablero+=LT.colorBG(BLANCO)+CasillaNoConfirmada+" ";
                }
            }
            //visualizacion de la letra del alfabeto correspondiente segun la fila en la columna final
            Tablero+="|"+(char)NumAlf+"\n";
            
            
            
            
            //aumento del codigo numerico de NumAlf para para obtener la letra de la siguiente fila
            NumAlf++;                  
        }
        
        //se añade el caracter espacio al String tablero para que las columnas de la fila final de numeros queden alineadas con las columnas de las casillas del tablero
        Tablero+=" ";
        //bucle para crear la ultima fila de la matriz, con el numero de columnas de la matriz 
        for(int Ffinal=0;Ffinal<MAX;Ffinal++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Ffinal+1);          
        }
        //separacion entre los dos tableros
            Tablero+="            ";
        
        //se añade el caracter espacio al String tablero para que las columnas de la fila final de numeros queden alineadas con las columnas de las casillas del tablero
        Tablero+=" ";
        //bucle para crear la ultima fila de la matriz, con el numero de columnas de la matriz
        for(int Ffinal=0;Ffinal<MAX;Ffinal++){
            //visualizacion del numero de columnas del tablero
            Tablero+=" "+(Ffinal+1);          
        }
        
        //visualizacion de una fila que funciona como separador
        Tablero+="\n"+filaGuion();

        return Tablero;
    }
}
