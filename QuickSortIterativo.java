public class QuickSortIterativo {
    public static void main(String[] args) {

        int[] tempos = new int[3];
        int[] trocas = new int[3];
        int[] iteracoes = new int[3];


        int[] dataset1 = {1, 100, 2, 99, 3, 98, 4, 97, 5, 96, 6, 95, 7, 94, 8, 93, 9, 92, 10, 91, 11, 90, 12, 89, 13, 88, 14, 87, 15, 86, 16, 85, 17, 84, 18, 83, 19, 82, 20, 81, 21, 80, 22, 79, 23, 78, 24, 77, 25, 76};
        int[] dataset2 = {1, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52};
        int[] dataset3 = {50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};


        int tamanhoDataset1 = 50;
        int tamanhoDataset2 = 50;
        int tamanhoDataset3 = 50;


        processarQuickSort(dataset1, tamanhoDataset1, tempos, trocas, iteracoes, 0);
        processarQuickSort(dataset2, tamanhoDataset2, tempos, trocas, iteracoes, 1);
        processarQuickSort(dataset3, tamanhoDataset3, tempos, trocas, iteracoes, 2);


        for (int i = 0; i < 3; i++) {
            System.out.println("Dataset " + (i + 1) + ":");
            System.out.println("Tempo de execução (ns): " + tempos[i]);
            System.out.println("Número de trocas: " + trocas[i]);
            System.out.println("Número de iterações: " + iteracoes[i]);
            System.out.println();
        }
    }

    public static void processarQuickSort(int[] array, int tamanho, int[] tempos, int[] trocas, int[] iteracoes, int index) {
        long startTime = System.nanoTime();
        int[] metrics = quickSort(array, tamanho);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        tempos[index] = (int) duration;
        trocas[index] = metrics[0];
        iteracoes[index] = metrics[1];
    }

    public static int[] quickSort(int[] array, int tamanho) {
        int[] stack = new int[tamanho * 2];
        int top = -1;
        int numTrocas = 0;
        int numIteracoes = 0;

        stack[++top] = 0;
        stack[++top] = tamanho - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            int p = partition(array, low, high);
            numIteracoes++;

            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;
            }

            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
            }

            numTrocas++;
        }

        return new int[]{numTrocas, numIteracoes};
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
