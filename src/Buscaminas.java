/*
 * Se podria decir que la clase buscaminas es la encargada de guardar la herramientas para usar en
 * el array de casillas y asi tener de forma mas organizado todo
 * Sé que el nombre no es demasiado intuitivo pero fue lo primero que se me ocurrio
 */

public class Buscaminas {

    // Este se podria llamar el constructor ya que se encarga de pedir la dificultad y crear toda la matriz ya 
    // Ya consus posiciones y sus valores de alrededor
    public Casilla[][] tamaño(int dificultad){

        // Establezco las dificultades en las cuales cambia la cantidad de minas a colocar en el mismo espacio
        // Empieza con la cantidad de 20 y va subiendo la misma cantidad hasta 60
        final int FACIL=1; 
        final int MEDIA=2;
        final int DIFICIL=3;

        int cantidadMinas;

        // Creo el array de casillas que será en donde se guardarán todos los datos
        Casilla[][] matriz = new Casilla[10][10];

        // Este for es para declarar y darle valor al array 
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = new Casilla();
            }
        }

        // Este switch en base a la dificultad crea las mina y las coloca en las posiciones random
        switch (dificultad) {
            case FACIL:
                cantidadMinas=20;
                Casilla.crearMinas(cantidadMinas, matriz);
                break;
            
            case MEDIA:
                cantidadMinas=40;
                Casilla.crearMinas(cantidadMinas, matriz);
                break;

            case DIFICIL:
                cantidadMinas=60;
                Casilla.crearMinas(cantidadMinas, matriz);
                break;

            default:
                break;
        }


        // Aqui hago el contador de cuantos hay alrededor de cada posicion que no tiene una mina
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if(matriz[i][j].getMina()){

                }else{
                    // Uso la funcion que me pregunta cuantos hay alrededor y en base a eso le coloca la cantidad que tendira este
                    String cuantos = Integer.toString(cuantosAlrededor(i,j, matriz));
                    matriz[i][j].setValor(cuantos);
                }
            }
        }
        // Retorno la matriz ya completa para su uso
        return matriz;
    }
    
    // Este imprimir imprime la parte inicial del array, esto quiere decir cuando aun no estan descubiertas
    public static Casilla[][] imprimir(Casilla[][] matriz){

        for (int j = 0; j < matriz.length; j++) {
            for (int j2 = 0; j2 < matriz.length; j2++) {
                // Se usa el valor falso porque es donde está guardado el "■" que será la forma encubierta de la casilla
                if (j2==matriz.length-1) {
                    System.out.print(matriz[j][j2].getValorFalso()+"\n");
                }else{
                    System.out.print(matriz[j][j2].getValorFalso()+" ");
                }
            }
        }

        return matriz;
    }

    // Esta funcion recibe la posicion y la matriz de donde se saca para evaluar cuantas mina tiene alrededor
    public int cuantosAlrededor(int i, int j,Casilla[][] matriz){

        // establezco el contador que me dira cuantas minas tiene alrededor iniciando este en 0
        int contador = 0;

        /* 
        En este for me encargo de recorrer cada uno de los espacios alrededor de el punto deseado
        Uso un if que determina si el espacio a evaluar está dentro de los limites
        Si el espacio está dentro de los limites entonces evaluo si tiene mina
        Si llega a tener mina al contador le sumo 1 
        */

        for (int j2 = i-1; j2 <= i+1; j2++) {
            for (int k = j-1; k <= j+1; k++) {
                // Verificar que el punto esté dentro de los límites del buscaminas
                if (j2 >= 0 && j2 < matriz.length && k >= 0 && k < matriz.length) {
                    if (matriz[j2][k].getMina()) {
                        contador++;
                    }
                }
            }
        }

        // Devuelvo la cantidad de minas encontradas (Si llega a ser cero anteriormente ya se ahabia evaluado de esa forma)
        return contador;
    }


    /*
     * Esta sera la funcion que se ejecute para cuando ya estan puestas las banderas en todas
     * y cada una de las minas.
     */
    public void ganador(Casilla[][] matriz, int dificultad){

        int contador =0;
        int comparador=0;

        // Tengo almacenadas las dificultades
        if (dificultad==1) {
            comparador=20;
        }else if(dificultad==2){
            comparador=40;
        }else if(dificultad==3){
            comparador=60;
        }

        // Comparo cada una de las minas y en caso de tener la bandera puesta sumo uno al contador
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].getMina() && matriz[i][j].getBandera()) {
                    contador++;
                }
            }
        }
        /*
         * Ya finalizando si el contador tiene la misma cantidad que el comparador 
         * significa que ya le puso la bandera a todas las minas y por ende habrá ganado 
         */
        if (contador == comparador) {
            System.out.println("Felicidades Has Ganado");
        }
    }
}
