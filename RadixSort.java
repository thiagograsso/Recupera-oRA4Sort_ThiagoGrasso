public class RadixSort {
    public static void main(String[] args) {
        // Resultados
        int[] tempos = new int[3];
        int[] trocas = new int[3];
        int[] iteracoes = new int[3];

        // Dataset 1
        int[] dataset1 = {1, 100, 2, 99, 3, 98, 4, 97, 5, 96, 6, 95, 7, 94, 8, 93, 9, 92, 10, 91, 11, 90, 12, 89, 13, 88, 14, 87, 15, 86, 16, 85, 17, 84, 18, 83, 19, 82, 20, 81, 21, 80, 22, 79, 23, 78, 24, 77, 25, 76};

        // Dataset 2
        int[] dataset2 = {1, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52};

        // Dataset 3
        int[] dataset3 = {50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // Processamento
        processarRadixSort(dataset1, tempos, trocas, iteracoes, 0);
        processarRadixSort(dataset2, tempos, trocas, iteracoes, 1);
        processarRadixSort(dataset3, tempos, trocas, iteracoes, 2);

        // Exibindo os resultados
        for (int i = 0; i < 3; i++) {
            System.out.println("Dataset " + (i + 1) + ":");
            System.out.println("Tempo de execução (ns): " + tempos[i]);
            System.out.println("Número de trocas: " + trocas[i]);
            System.out.println("Número de iterações: " + iteracoes[i]);
            System.out.println();
        }
    }

    public static void processarRadixSort(int[] array, int[] tempos, int[] trocas, int[] iteracoes, int index) {
        long startTime = System.nanoTime();
        int[] metrics = radixSort(array);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        tempos[index] = (int) duration;
        trocas[index] = metrics[0];
        iteracoes[index] = metrics[1];
    }

    public static int[] radixSort(int[] array) {
        int tamanho = array.length;
        int max = findMax(array, tamanho);
        int numTrocas = 0;
        int numIteracoes = 0;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            int[] result = countingSort(array, exp, tamanho);
            numTrocas += result[0];
            numIteracoes += result[1];
        }

        return new int[] { numTrocas, numIteracoes };
    }

    public static int[] countingSort(int[] array, int exp, int tamanho) {
        int[] output = new int[tamanho];
        int[] count = new int[10];
        int numTrocas = 0;
        int numIteracoes = 0;

        for (int i = 0; i < tamanho; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
            numIteracoes++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = tamanho - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
            numTrocas++;
        }

        for (int i = 0; i < tamanho; i++) {
            array[i] = output[i];
        }

        return new int[] { numTrocas, numIteracoes };
    }

    public static int findMax(int[] array, int tamanho) {
        int max = array[0];
        for (int i = 1; i < tamanho; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
