package practica_2;
import java.nio.charset.StandardCharsets;
import java.io.File;

public class TestLexico {
    public static void main(String[] args) {
        File ficheroEntrada = new File("Programa.txt");
        ComponenteLexico componenteLexico;

        Lexico lexico = new Lexico(ficheroEntrada,
                StandardCharsets.UTF_8);
        int c = 0;
        do {
            componenteLexico = lexico.getComponenteLexico();
            System.out.println("<" + componenteLexico.toString() + ">");
            c++;
        } while (!componenteLexico.getEtiqueta().equals("end_program"));
        System.out.println("\nComponentes léxicos: " + c +
                ", lineas: " + lexico.getLineas() + "\n\n");
    }
}