import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        // Inicio el programa y pido la dificultad que solo variará la cantidad de minas a colocar
        Scanner sc = new Scanner(System.in);
        System.out.print("Bienvenido a el buscaminas de Yaider");
        System.out.print("Ingrese la dificultad del buscaminas(1 a 3): ");
        int dificultad = sc.nextInt();

        // Creo el buscaminas con la funcion tamaño y le doy de parametro la dificultad
        Buscaminas buscaminas = new Buscaminas();
        boolean sigue = true;
        
        Casilla[][] casillas = buscaminas.tamaño(dificultad);

        // Establezco el bucle que continuará el juego
        while (sigue) {

            // Imprimo el buscaminas es su forma inicial 
            Buscaminas.imprimir(casillas);
            System.out.println("1 para colocar/quitar bandera y 2 para revelar posicion 3 para salir");
            int seleccion = sc.nextInt();

            /*
             * tengo el switch que tiene tres selecciones
             * 1. El lugar donde colocar o quitar la bandera
             * 2. Revelar alguna posicion
             * 3. Salir
             */
            switch (seleccion) {
                case 1:
                    System.out.println("Donde desea colocar/quitar la bandera: ");
                    int fila2 = sc.nextInt();
                    int columna2 = sc.nextInt();
                    casillas[fila2][columna2].bandera(fila2,columna2,casillas);
                    buscaminas.ganador(casillas, dificultad);
                    // Buscaminas.imprimir2(casillas);
                    break;
    
                case 2:
                    System.out.println("Que posición desea revelar: ");
                    int fila = sc.nextInt();
                    int columna = sc.nextInt();
        
                    if (casillas[fila][columna].getMina()) {
        
                        casillas[fila][columna].revelar(casillas, fila, columna);
                        System.out.println("Lo siento perdiste <3");
                        sigue = false;
                    
                    }else{
                        casillas[fila][columna].revelar(casillas, fila, columna);
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
