import java.util.*;

public class PonteDosZumbis {
    
    static class Estado {
        int[] pessoasPos; // Posição das pessoas (0: lado inicial, 1: lado final)
        int tempo; // Tempo necessário para chegar neste estado
        String direcao; // Direção em que a lanterna está (left: lado inicial, right: lado final)

        public Estado(int[] pessoasPos, int tempo, String direcao) {
            this.pessoasPos = pessoasPos;
            this.tempo = tempo;
            this.direcao = direcao;
        }
    }

    public static int travessiaPonteZumbis(int[] pessoas, int maxPessoas) {
        // Criação do estado inicial
        int[] estadoInicial = Arrays.copyOf(pessoas, pessoas.length);
        int tempoInicial = 0;
        String direcaoInicial = "left";
        Estado estadoFinal = new Estado(new int[pessoas.length], 0, "right");

        // Criação do grafo
        Queue<Estado> fila = new LinkedList<>();
        Set<String> visitados = new HashSet<>();

        Estado estadoAtual = new Estado(estadoInicial, tempoInicial, direcaoInicial);
        fila.add(estadoAtual);
        visitados.add(Arrays.toString(estadoInicial) + "_" + direcaoInicial);

        while (!fila.isEmpty()) {
            estadoAtual = fila.poll();

            if (Arrays.equals(estadoAtual.pessoasPos, estadoFinal.pessoasPos) && estadoAtual.direcao.equals(estadoFinal.direcao))
                return estadoAtual.tempo;

            // Gera os sucessores
            for (int i = 1; i <= maxPessoas; i++) {
                List<List<Integer>> combinacoes = generateCombinations(pessoas.length, i);

                for (List<Integer> comb : combinacoes) {
                    int[] novasPessoasPos = Arrays.copyOf(estadoAtual.pessoasPos, estadoAtual.pessoasPos.length);
                    for (int p : comb) {
                        novasPessoasPos[p] = 1 - novasPessoasPos[p]; // Move as pessoas para a outra posição
                    }

                    String novaDirecao = estadoAtual.direcao.equals("left") ? "right" : "left";
                    Estado novoEstado = new Estado(novasPessoasPos, estadoAtual.tempo + maxTime(comb, pessoas), novaDirecao);

                    if (!visitados.contains(Arrays.toString(novasPessoasPos) + "_" + novaDirecao) && novoEstado.tempo <= 17) {
                        fila.add(novoEstado);
                        visitados.add(Arrays.toString(novasPessoasPos) + "_" + novaDirecao);
                    }
                }
            }
        }

        return -1; // Não foi possível encontrar uma solução
    }

    // Função para gerar combinações de tamanho k
    public static List<List<Integer>> generateCombinations(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinationsHelper(n, k, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(int n, int k, int start, List<Integer> currentComb, List<List<Integer>> combinations) {
        if (k == 0) {
            combinations.add(new ArrayList<>(currentComb));
            return;
        }

        for (int i = start; i < n; i++) {
            currentComb.add(i);
            generateCombinationsHelper(n, k - 1, i + 1, currentComb, combinations);
            currentComb.remove(currentComb.size() - 1);
        }
    }

    // Função para calcular o tempo máximo considerando as pessoas selecionadas
    public static int maxTime(List<Integer> comb, int[] pessoas) {
        int maxTime = 0;
        for (int p : comb) {
            maxTime = Math.max(maxTime, pessoas[p]);
        }
        return maxTime;
    }

    public static void main(String[] args) {
        int[] pessoas = {1, 2, 5, 10}; // Tempos de travessia das pessoas
        int maxPessoas = 2; // Número máximo de pessoas que podem atravessar simultaneamente

        int tempoMinimo = travessiaPonteZumbis(pessoas, maxPessoas);
        if (tempoMinimo != -1) {
            System.out.println("Tempo mínimo necessário: " + tempoMinimo);
        } else {
            System.out.println("Não foi possível encontrar uma solução.");
        }
    }
}
