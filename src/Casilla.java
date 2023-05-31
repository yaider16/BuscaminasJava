import java.util.Random;

public class Casilla {
    /*
     * Hay tres variable principales, estas son: 
     * 1. Mina, este será un booleano que me dirá si en esa posicion hay una mina
     * 2. Valor, aqui le establezco el valor que tendra la posicion, esto quiere decir su cantidad de minas aledañas
     * 3. MinaValor, aunque parezca innecesaria esta se encarga de darle el valor de la mina, esto quiere decir el simbolo que se usará
     * 
     * Cada uno de estos cuenta con un set y un get para establecer y pedir sus valores
     * 
     * 
     */
    private boolean mina;
    private String valor;
    private String minaValor;
    // Bandera es para saber si tiene bandera y descubierto para saber si ya se abrio esa posicion
    private boolean bandera;
    public boolean descubierto;

    // valor falso es el valor inicial que tiene antes de ser descubierto
    public String valorFalso="■";

    public String getValorFalso(){
        return valorFalso;
    }
    // En este set pregunto si se esta colocando o quitando una bandera para hacer el set correspondiente
    // En terminos generales este set cambia el valor del cubierto a descubierto
    public String setValorFalso(boolean estaColocandoBandera, boolean estaQuitandoBandera){
        if (this.getMina() ) {
            if (estaColocandoBandera) {
                valorFalso="¶";
            }else if(estaQuitandoBandera){
                valorFalso="■";
            }else{
                valorFalso = minaValor;    
            }
        }else{
            if (estaColocandoBandera) {
                valorFalso="¶";
            }else if(estaQuitandoBandera){
                valorFalso="■";
            }else{
                valorFalso = valor;
            }
        }
        return valorFalso;
    }

    public boolean getMina(){
        return mina;
    }
    public void setMina(boolean mina) {
        this.mina = mina;
        this.minaValor = "*";
        setValor(minaValor);
    }

    public void setValor(String valor){
        this.valor=valor;
    }
    public String getValor(){
        return this.valor;
    }  


    public void setBandera(boolean es){
        this.bandera = es;
    }
    public boolean getBandera(){
        return this.bandera;
    }
    
    
    // Esta funcion crea las posiciones de las minas con dos numeros aleatorios desde 0 hasta 10
    public static Casilla[][] crearMinas(int cantidadMinas, Casilla[][] casillas){
        Random random = new Random();
        for (int i = 0; i < cantidadMinas; i++) {  

            int pos1= random.nextInt(10);
            int pos2= random.nextInt(10);

            if (!casillas[pos1][pos2].getMina()) {
                casillas[pos1][pos2].setMina(true);
            }else{
                // Aqui repite el ciclo si llega a repetirse la posicion de una mina
                i-=1;
            }
        }
        return casillas;
    }

    // Esta funcion se encarga de revelar con las coordenadas las posicion deseada, ya sea mina u otra cosa
    public Casilla[][] revelar(Casilla[][] matriz, int fila, int columna){
        // Cambio el valor falso a el valor real y coloco falso en ambos argumentos porque no estoy ni colocando ni quitando bandera
        matriz[fila-1][columna-1].setValorFalso(false,false);

        // Si hay una mina en esta posicion imprime todo descubierto
        if (this.getMina()) {
            this.imprimirFinal(matriz);
        }
        // Setea el descubierto a true y devuelve la matriz
        descubierto =true;

        return matriz;
    }

    // Imprime el valor real de toda la matriz
    public void imprimirFinal(Casilla[][] matriz){
        // Aqui se obtiene el valor real de la posicion ya sea mina o no, y lo imprime
        // Esto lo uso principalmente para cuando hay Game Over y quiero que el usuario vea en donde falló
        for (int j = 0; j < matriz.length; j++) {
            for (int j2 = 0; j2 < matriz.length; j2++) {
                if (j2==matriz.length-1) {
                    System.out.print(matriz[j][j2].getValor()+"\n");
                }else{
                    System.out.print(matriz[j][j2].getValor()+" ");
                }
            }
        }
    }

    // En esta funcion me encargo de colocar o quitar la bandera en una posicion que esté cubierta
    public Casilla[][] bandera(int fila, int columna, Casilla[][] matriz){
        // Si ya está descubierta pues no dejará al usuario colocar la bandera en esa posicion
        if (descubierto) {
            System.out.println("Está descubierto, no puede hacer eso");
        }else{
            // Preguntará si ya hay una bandera ahí, si es así, quitará la bandera y lo convertirá a false
            if(bandera){
                matriz[fila][columna].setValorFalso(false,true);
                bandera=false;
            }else{
                matriz[fila][columna].setValorFalso(true,false);
                bandera=true;
            }

        }
        return matriz;
    }
}
