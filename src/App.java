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

        buscaminas.imprimir(casillas);


        sc.close();
    }
}
