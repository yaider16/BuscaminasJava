import java.util.Random;

public class Buscaminas {

    public void imprimir(Casilla[][] matriz){

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

    public Casilla[][] tamaño(int dificultad){

        final int FACIL=1;
        final int MEDIA=2;
        final int DIFICIL=3;

        int cantidadMinas;

        Casilla[][] matriz = new Casilla[10][10];

        // Este for es para colocar una mina en una posicion random si no llega a haber otro en el mismo lugar 
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = new Casilla();
            }
        }

        switch (dificultad) {
            case FACIL:
                cantidadMinas=20;
                crearMinas(cantidadMinas, matriz);
                break;
            
            case MEDIA:
                cantidadMinas=40;
                crearMinas(cantidadMinas, matriz);
                break;

            case DIFICIL:
                cantidadMinas=60;
                crearMinas(cantidadMinas, matriz);
                break;

            default:
                break;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {

                if(matriz[i][j].getMina()){

                }else if(hayAlrededor(i,j, matriz)){

                    String cuantos = Integer.toString(cuantosAlrededor(i,j, matriz));
                    
                    matriz[i][j].setValor(cuantos);

                }else{
                    matriz[i][j].setValor("0");
                }
            }
        }
        return matriz;
    }


    public Casilla[][] crearMinas(int cantidadMinas, Casilla[][] casillas){

        Random random = new Random();

        for (int i = 0; i < cantidadMinas; i++) {
            
            int pos1= random.nextInt(10);
            int pos2= random.nextInt(10);



            casillas[pos1][pos2].setMina(true);
        }

        return casillas;
    }
    

    public int cuantosAlrededor(int fila, int columna,Casilla[][] matriz){

        int contador = 0;
        int i = fila;
        int j= columna;

        boolean esquinaSuperiorDerecha=false;
        boolean esquinaSuperiorIzquierda=false;
        boolean esquinaInferiorDerecha=false;
        boolean esquinaInferiorIzquierda=false;
        boolean lateralIzquierdo=false;
        boolean lateralDerecho=false;
        boolean limiteSuperior = false;
        boolean limiteInferior=false;
        boolean centro = false;


        if (i==0 && j==matriz.length-1) {
            esquinaSuperiorDerecha = matriz[i][j-1].getMina() || matriz[i+1][j].getMina() || matriz[i+1][j-1].getMina();            
        }

        else if( i==0  && j ==0){
            esquinaSuperiorIzquierda = matriz[i][j+1].getMina() || matriz[i+1][j].getMina() || matriz[i+1][j+1].getMina();
        }

        else if(i==matriz.length-1 && j==matriz.length-1){
            esquinaInferiorDerecha = matriz[i-1][j].getMina() || matriz[i][j-1].getMina() || matriz[i-1][j+1].getMina();
        }

        else if(i==matriz.length-1 && j==0){
            esquinaInferiorIzquierda = matriz[i-1][j].getMina() || matriz[i][j+1].getMina() || matriz[i-1][j-1].getMina();
        }

        else if(i>0 && i<matriz.length-1 && j==0){
            lateralIzquierdo = matriz[i-1][j].getMina() || matriz[i-1][j+1].getMina() || matriz[i][j+1].getMina() || matriz[i+1][j+1].getMina() || matriz[i+1][j].getMina();
        }
        
        else if(i>0 && i<matriz.length-1 && j==matriz.length-1){
            lateralDerecho  = matriz[i-1][j].getMina() || matriz[i-1][j-1].getMina() || matriz[i][j-1].getMina() || matriz[i-1][j-1].getMina() || matriz[i-1][j].getMina();
        }

        else if(i==0 && j>0 && j<matriz.length-1){
            limiteSuperior = matriz[i][j-1].getMina() || matriz[i+1][j-1].getMina() || matriz[i+1][j].getMina() || matriz[i+1][j+1].getMina() || matriz[i][j+1].getMina();
        }

        else if(i==matriz.length-1 && j>0 && j<matriz.length-1){
            limiteInferior = matriz[i][j-1].getMina() || matriz[i-1][j-1].getMina() || matriz[i-1][j].getMina() || matriz[i-1][j+1].getMina() || matriz[i][j+1].getMina();
        }
        else if(i>0 && i<matriz.length-1 && j>0 && j<matriz.length-1){
            centro = matriz[i-1][j-1].getMina() || matriz[i-1][j].getMina() || matriz[i-1][j+1].getMina() || matriz[i][j-1].getMina() || matriz[i][j+1].getMina() || matriz[i+1][j-1].getMina() || matriz[i+1][j+1].getMina() || matriz[i+1][j].getMina();
        }
        
        if (esquinaSuperiorDerecha && i==0 && j==matriz.length-1){

            for (int k = 0; k < 3; k++) {
                if (matriz[i][j-1].getMina()) {
                    contador++;
                }else if(matriz[i+1][j].getMina()){
                    contador++;
                }else if (matriz[i+1][j-1].getMina()) {
                    contador++;
                }                    
            }
        }
            
        else if(esquinaSuperiorIzquierda && i==0  && j ==0){
            for (int k = 0; k < 3; k++) {
                if (matriz[i][j+1].getMina()) {
                    contador++;
                }else if (matriz[i+1][j].getMina()) {
                    contador++;
                }else if(matriz[i+1][j+1].getMina()){
                    contador++;
                }                    
            }

        }

        else if(esquinaInferiorIzquierda && i==matriz.length-1 && j==0){
            for (int k = 0; k < 3; k++) {
                if (matriz[i-1][j].getMina()) {
                    contador++;
                }else if (matriz[i][j+1].getMina()) {
                    contador++;
                }else if(matriz[i-1][j-1].getMina()){
                    contador++;
                }                    
            }

        }

        else if(esquinaInferiorDerecha && i==matriz.length-1 && j==matriz.length-1){
            for (int k = 0; k < 3; k++) {
                if (matriz[i-1][j].getMina()) {
                    contador++;
                }else if (matriz[i][j-1].getMina()) {
                    contador++;
                }else if(matriz[i-1][j-1].getMina()){
                    contador++;
                }                    
            }

        }

        else if(lateralIzquierdo && i>0 && i<matriz.length-1 && j==0){
            for (int k = 0; k < 5; k++) {
                if (matriz[i-1][j].getMina()) {
                    contador++;
                }else if (matriz[i-1][j+1].getMina()) {
                    contador++;
                }else if(matriz[i][j+1].getMina()){
                    contador++;
                }else if(matriz[i+1][j+1].getMina()){
                    contador++;
                }else if(matriz[i+1][j].getMina()){
                    contador++;
                }                    
            }
        }

        else if(lateralDerecho && i>0 && i<matriz.length-1 && j==matriz.length-1){
            for (int k = 0; k < 5; k++) {
                if (matriz[i-1][j].getMina()) {
                    contador++;
                }else if (matriz[i-1][j-1].getMina()) {
                    contador++;
                }else if(matriz[i][j-1].getMina()){
                    contador++;
                }else if(matriz[i-1][j-1].getMina()){
                    contador++;
                }else if(matriz[i-1][j].getMina()){
                    contador++;
                }                    
            }
        }

        else if(limiteSuperior && i==0 && j>0 && j<matriz.length-1){
            for (int k = 0; k < 5; k++) {
                if (matriz[i][j-1].getMina()) {
                    contador++;
                }else if (matriz[i+1][j-1].getMina()) {
                    contador++;
                }else if(matriz[i+1][j].getMina()){
                    contador++;
                }else if(matriz[i+1][j+1].getMina()){
                    contador++;
                }else if(matriz[i][j+1].getMina()){
                    contador++;
                }                    
            }
        }

        else if(limiteInferior && i==matriz.length-1 && j>0 && j<matriz.length-1){
            for (int k = 0; k < 5; k++) {
                if (matriz[i][j-1].getMina()) {
                    contador++;
                }else if (matriz[i-1][j-1].getMina()) {
                    contador++;
                }else if(matriz[i-1][j].getMina()){
                    contador++;
                }else if(matriz[i-1][j+1].getMina()){
                    contador++;
                }else if(matriz[i][j+1].getMina()){
                    contador++;
                }    
            }
        }

        else if(centro && i>0 && i<matriz.length-1 && j>0 && j<matriz.length-1){
            for (int k = 0; k < 8; k++) {
                if (matriz[i-1][j-1].getMina()) {
                    contador++;
                }else if (matriz[i-1][j].getMina()) {
                    contador++;
                }else if(matriz[i-1][j+1].getMina()){
                    contador++;
                }else if(matriz[i][j-1].getMina()){
                    contador++;
                }else if(matriz[i][j+1].getMina()){
                    contador++;
                }else if(matriz[i+1][j-1].getMina()){
                    contador++;
                }else if(matriz[i+1][j+1].getMina()){
                    contador++;
                }else if(matriz[i+1][j].getMina()){
                    contador++;
                }                    
            }
        }

            return contador;
        }

    public boolean hayAlrededor(int fila, int columna, Casilla[][] matriz){

        int contador = cuantosAlrededor(fila, columna, matriz);

        if (contador >0) {
            return true;
        }else{
            return false;
        } 
    }
}
