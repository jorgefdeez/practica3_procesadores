package practica_2;

public class TestTraductorPostfijo {

    public static void main(String[] args) {
        //Traduccion
        String expresion = "(25 * (2 + 2)) / 2 * 3";

        TraductorExpresionPostfijo expr = new TraductorExpresionPostfijo(new Lexico(expresion));

        System.out.println("La expresioÌn " + expresion
                + " en notacioÌn postfija es " + expr.postfijo()
                + " y su valor es " + expr.valor());

        //Analisis Lexico

		ComponenteLexico etiquetaLexica;

		String programa ="(25*(2+2))/2*3";

		Lexico lexico = new Lexico(programa);


		System.out.println("Test lexico basico \t" + programa + "\n");

		do {

			etiquetaLexica = lexico.getComponenteLexico();

			System.out.println(etiquetaLexica.toString());

		} while (!etiquetaLexica.getEtiqueta().equals("end_program"));


    }

}