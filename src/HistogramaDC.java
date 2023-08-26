import java.io.*;
import java.text.Normalizer;

public class HistogramaDC {
    public static void main(String[] args) throws IOException {
        String archivo = "src\\divina_comedia_sp.txt";
        BufferedReader dCLector = new BufferedReader(new FileReader(archivo));

        int[] contador = new int[11]; // Contador de palabras por longitud.

        String renglon;
        while ((renglon = dCLector.readLine()) != null) {

            String[] separacionPalabra = renglon.split("\\s+"); // Divide al renglon en palabras.

            for (String palabra : separacionPalabra) {
                int longitud = limpieza(palabra).length();
                if (longitud >= 2 && longitud <= 10) {
                    contador[longitud]++; // Incrementar contador en la posición correspondiente.
                }
            }

        }

        dCLector.close();

        int conteoMax = 0; // Encontrar el máximo valor para ajustar la escala del histograma.
        for (int i = 2; i <= 10; i++) {
            if (contador[i] > conteoMax) {
                conteoMax = contador[i];
            }
        }

        // Imprimir histograma de palabras por longitud.
        for (int i = 2; i <= 10; i++) {
            System.out.print("Palabras de longitud " + i + ": " + contador[i] + " ");
            int barras = (int) ((contador[i] / (double) conteoMax) * 30); // Escala a 40 barras
            for (int j = 0; j < barras; j++) {
                System.out.print("||");
            }
            System.out.println();
        }

        System.out.println("\nEn este caso, cada | representa apróximadamente 500 palabras.");
    }

    public static String limpieza(String divCom){
        divCom = Normalizer.normalize(divCom, Normalizer.Form.NFD); // Normalización utilizando descomposición canónica NFD de Unicode.
        divCom = divCom.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); // Elimina diacríticos y acentuación.
        divCom = divCom.replaceAll("\\d+",""); // Elimina los digitos del 0-9.
        divCom = divCom.replaceAll("[,.\\-_'!¿?¡:;]",""); // Elimina todos esos símbolos especiales dentro de los corchetes.
        divCom = divCom.replaceAll("\\t",""); // Elimina las tabulaciones.
        divCom = divCom.replaceAll("[\\[\\]\"']",""); // Elimina corchetes, comillas dobles y comillas simples.
        return divCom; // Regresa divCom pero modificado, limpio y listo para leer.
    }
}