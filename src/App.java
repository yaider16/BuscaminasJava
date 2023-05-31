import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Bienvenido a el buscaminas de Yaider");
        System.out.print("Ingrese la dificultad del buscaminas(1 a 3): ");
        int dificultad = sc.nextInt();

        Buscaminas buscaminas = new Buscaminas();
        boolean sigue = true;
        
        Casilla[][] casillas = buscaminas.tamaño(dificultad);

        while (sigue) {
            
            Buscaminas.imprimir(casillas);

            System.out.println("1 para colocar/quitar bandera y 2 para revelar posicion 3 para salir");
            int seleccion = sc.nextInt();

            switch (seleccion) {
                case 1:
                    System.out.println("Donde desea colocar/quitar la bandera: ");
                    int fila2 = sc.nextInt();
                    int columna2 = sc.nextInt();
                    casillas[fila2-1][columna2-1].bandera(fila2,columna2,casillas);
                    buscaminas.ganador(casillas, dificultad);
                    // Buscaminas.imprimir2(casillas);
                    break;
    
                case 2:
                    System.out.println("Que posición desea revelar: ");
                    int fila = sc.nextInt();
                    int columna = sc.nextInt();
        
                    if (casillas[fila-1][columna-1].getMina()) {
        
                        casillas[fila-1][columna-1].revelar(casillas, fila, columna);
                        System.out.println("Lo siento perdiste <3");
                        sigue = false;
                    
                    }else{
                        casillas[fila-1][columna-1].revelar(casillas, fila, columna);
                    }
                    break;


                case 3:
                    System.out.println("Muchas gracias por jugar");
                    sigue = false;
                    break;
                default:
                    break;
            }



        }


        sc.close();
    }
}
