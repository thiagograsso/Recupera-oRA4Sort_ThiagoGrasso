public class QuickSortIterativo {
    private static int trocas;
    private static int iteracoes;

    public static void main(String[] args) {
        // Configurar datasets
        int[][] datasets = {
                {1, 100, 2, 99, 3, 98, 4, 97, 5, 96, 6, 95, 7, 94, 8, 93, 9, 92, 10, 91, 11, 90, 12, 89, 13, 88, 14, 87, 15, 86, 16, 85, 17, 84, 18, 83, 19, 82, 20, 81, 21, 80, 22, 79, 23, 78, 24, 77, 25, 76},
                {1, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52},
                {50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
        };

        // Armazenamento das métricas
        int[] temposExecucao = new int[3];
        int[] trocasTotais = new int[3];
        int[] iteracoesTotais = new int[3];

        // Processar cada dataset
        for (int i = 0; i < datasets.length; i++) {
            trocas = 0;
            iteracoes = 0;

            int[] array = datasets[i].clone(); // Evitar modificar o dataset original
            long inicio = System.nanoTime(); // Para medir o tempo de execução
            quickSort(array);
            long fim = System.nanoTime(); // Para medir o tempo de execução

            // Salvar as métricas nos arrays
            temposExecucao[i] = (int) (fim - inicio);
            trocasTotais[i] = trocas;
            iteracoesTotais[i] = iteracoes;

            System.out.println("Dataset " + (i + 1) + " ordenado:");
            imprimirArray(array);
        }

        // Exibir métricas
        for (int i = 0; i < datasets.length; i++) {
            System.out.println("Dataset " + (i + 1) + ":");
            System.out.println("Tempo de execução (ns): " + temposExecucao[i]);
            System.out.println("Número de trocas: " + trocasTotais[i]);
            System.out.println("Número de iterações: " + iteracoesTotais[i]);
            System.out.println();
        }
    }

    // Implementação do QuickSort Iterativo
    public static void quickSort(int[] array) {
        int tamanho = array.length;
        int[] stack = new int[tamanho];
        int topo = -1;

        // Empilhar os limites iniciais do array
        stack[++topo] = 0;
        stack[++topo] = tamanho - 1;

        // Enquanto houver elementos para particionar
        while (topo >= 0) {
            iteracoes++;
            int end = stack[topo--];
            int start = stack[topo--];

            int pivoIndex = particionar(array, start, end);

            // Empilhar as novas partições
            if (pivoIndex - 1 > start) {
                stack[++topo] = start;
                stack[++topo] = pivoIndex - 1;
            }
            if (pivoIndex + 1 < end) {
                stack[++topo] = pivoIndex + 1;
                stack[++topo] = end;
            }
        }
    }

    // Função de particionamento
    public static int particionar(int[] array, int start, int end) {
        int pivo = array[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            iteracoes++;
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }
        trocar(array, i + 1, end);
        return i + 1;
    }

    // Função para trocar os elementos
    public static void trocar(int[] array, int i, int j) {
        trocas++;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Função para imprimir o array
    public static void imprimirArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
