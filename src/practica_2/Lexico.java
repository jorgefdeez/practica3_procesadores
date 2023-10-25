package practica_2;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Lexico {
    private PalabrasReservadas palabrasReservadas;
    private int posicion;
    private int lineas;
    private char caracter;
    private String programa;

    public Lexico(String programa) {
        this.posicion = 0;
        this.lineas = 1;
        this.palabrasReservadas = new PalabrasReservadas("Lexico.txt");
        this.programa = programa + (char) 0;
    }
    public Lexico(File ficheroEntrada, Charset utf8) {
        try {
            this.posicion = 0;
            this.lineas = 1;
            this.palabrasReservadas = new PalabrasReservadas("Lexico.txt");
            this.programa = "";

            Scanner scanner = new Scanner(ficheroEntrada, utf8);
            while (scanner.hasNextLine()) {
                this.programa += scanner.nextLine() + "\n";
            }
            this.programa += (char) 0;
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char extraeCaracter() {
        return this.programa.charAt(this.posicion++);
    }

    private void devuelveCaracter() {
        this.posicion--;
    }

    private boolean extraeCaracter(char c) {
        if (this.posicion < this.programa.length() - 1) {
            this.caracter = extraeCaracter();
            if (c == this.caracter) {
                return true;
            } else {
                devuelveCaracter();
                return false;
            }
        } else {
            return false;
        }
    }

    public int getLineas() {
        return this.lineas;
    }

    public ComponenteLexico getComponenteLexico() {
        while (true) {
            this.caracter = extraeCaracter();
            if (this.caracter == 0) {
                return new ComponenteLexico("end_program");
            } else if (this.caracter == ' ' || (int) this.caracter == 9 || (int) this.caracter == 13) {
                continue;
            } else if ((int) this.caracter == 10) {
                this.lineas++;
            } else {
                break;
            }
        }

        if (Character.isDigit(this.caracter)) {
            String numero = "";
            do {
                numero = numero + this.caracter;
                this.caracter = extraeCaracter();
            } while (Character.isDigit(this.caracter));
            if (this.caracter != '.') {
                devuelveCaracter();
                return new ComponenteLexico("int", numero);
            }

            do {
                numero = numero + this.caracter;
                this.caracter = extraeCaracter();
            } while (Character.isDigit(this.caracter));
            devuelveCaracter();
            return new ComponenteLexico("float", numero);
        }

        if (Character.isLetter(this.caracter)) {
            String lexema = "";
            do {
                lexema = lexema + this.caracter;
                this.caracter = extraeCaracter();
            } while (Character.isLetterOrDigit(this.caracter));

            devuelveCaracter();
            if (this.palabrasReservadas.containsKey(lexema)) {
                return new ComponenteLexico(this.palabrasReservadas.getEtiqueta(lexema));
            } else {
                return new ComponenteLexico("id", lexema);
            }

        }

        switch (this.caracter) {
            case '=':
                return new ComponenteLexico("assignment");
            case '<':
                return new ComponenteLexico("less_than");
            case '>':
                return new ComponenteLexico("greater_than");
            case '+':
                return new ComponenteLexico("add");
            case '-':
                return new ComponenteLexico("subtract");
            case '*':
                return new ComponenteLexico("multiply");
            case '/':
                return new ComponenteLexico("divide");
            case '%':
                return new ComponenteLexico("remainder");
            case ';':
                return new ComponenteLexico("semicolon");
            case '(':
                return new ComponenteLexico("open_parenthesis");
            case ')':
                return new ComponenteLexico("closed_parenthesis");
            default:
                return new ComponenteLexico("invalid_char");
        }
    }
}
