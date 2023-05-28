import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el tamaño de la matriz: ");
        int size = sc.nextInt();

        String[][] tamaño = tamaño(size);

        imprimir(tamaño);

        sc.close();
    }



    public static void imprimir(String[][] matriz){

        for (int j = 0; j < matriz.length; j++) {
            for (int j2 = 0; j2 < matriz.length; j2++) {
                
                if (j2==matriz.length-1) {
                    System.out.print(matriz[j][j2]+"\n");
                }else{
                    System.out.print(matriz[j][j2]+" ");
                }

            }
        }
    }

    public static String[][] tamaño(int tamañoMatriz){
        String[][] matriz = new String[tamañoMatriz][tamañoMatriz];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = "0";
            }
        }
        return matriz;
    }
}
