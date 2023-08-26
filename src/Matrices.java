import java.io.*;

public class Matrices {

    public static void main(String[] args) throws IOException {
        // Archivos de entrada.
        String archivoA = "src\\a.mat";
        String archivoB = "src\\b.mat";

        // Abrir archivos de entrada.
        FileInputStream fileInputStreamA = new FileInputStream(archivoA);
        FileInputStream fileInputStreamB = new FileInputStream(archivoB);

        // Leer los datos de los archivos de entrada.
        DataInputStream dataInputStreamA = new DataInputStream(fileInputStreamA);
        DataInputStream dataInputStreamB = new DataInputStream(fileInputStreamB);

        // Leer número de rengolnes y columnas.
        int renglonesA = dataInputStreamA.read();
        int columnasA = dataInputStreamA.read();

        int renglonesB = dataInputStreamB.read();
        int columnasB = dataInputStreamB.read();

        // Crear matrices para almacenar A y B.
        double[][] matrizA = new double[renglonesA][columnasA];
        double[][] matrizB = new double[renglonesB][columnasB];

        // Llenar las matrices A y B con los datos del archivo de entrada.
        for (int i = 0; i < renglonesA; i++) {
            for (int j = 0; j < columnasA; j++) {
                matrizA[i][j] = dataInputStreamA.readDouble();
            }
        }

        for (int i = 0; i < renglonesB; i++) {
            for (int j = 0; j < columnasB; j++) {
                matrizB[i][j] = dataInputStreamB.readDouble();
            }
        }

        // Cerrar.
        dataInputStreamA.close();
        dataInputStreamB.close();

        // Imprimir matrizA para mejor visualización.
        System.out.println("Matriz A");
        for (int i = 0; i < renglonesA; i++) {
            for (int j = 0; j < columnasA; j++) {
                System.out.print(matrizA[i][j] + "\t");
            }
            System.out.println();
        }
            System.out.println();

        // Imprimir matrizB para mejor visualización.
        System.out.println("Matriz B");
        for (int i = 0; i < renglonesB; i++) {
            for (int j = 0; j < columnasB; j++) {
                System.out.print(matrizB[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();

        // Calcular el producto de las matrices.
        double[][] matrizC = new double[renglonesA][columnasB];
        for (int i = 0; i < renglonesA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }

        // Imprimir matriz producto ó matriz C.
        System.out.println("Matriz C (Producto)");
        for (int i = 0; i < renglonesA; i++) {
            for (int j = 0; j < columnasB; j++) {
                System.out.print(matrizC[i][j] + "\t");
            }
            System.out.println();
        }

        String archivoC = "src\\c.mat"; // Archivo de salida.

        // Abrir un FileOutputStream y DataOutputStream para escribir en el archivo de salida.
        FileOutputStream fileOutputStreamC = new FileOutputStream(archivoC);
        DataOutputStream dataOutputStreamC = new DataOutputStream(fileOutputStreamC);

        // Escribir el número de renglones y columnas en el archivo de salida.
        dataOutputStreamC.write(renglonesA);
        dataOutputStreamC.write(columnasB);

        // Escribir los datos de la matrizC en el archivo de salida.
        for (int i = 0; i < renglonesA; i++) {
            for (int j = 0; j < columnasB; j++) {
                dataOutputStreamC.writeDouble(matrizC[i][j]);
            }
        }
        // Cerrar.
        dataOutputStreamC.close();
    }
}
