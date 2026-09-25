package Camadas;

final class Conversor {

    private static final int CASAS_LADO = 16;

    private Conversor() {}

    public static int[] converterIndiceParaLinhaColuna(int indice) {

        int linha = indice / CASAS_LADO;
        int coluna = indice % CASAS_LADO;

        return new int[] {linha, coluna};
    }

    public static int converterLinhaColunaParaIndice(int linha, int coluna) {

        return linha * CASAS_LADO + coluna;
    }
}
