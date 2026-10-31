//Classe ajustada

package Camadas;

public final class Conversor {

    private static final int JOGO_CASAS_LADO = 16;

    private Conversor() {
    }

    public static Posicao indiceParaLinhaColuna(int indice) {

        int linha = indice / JOGO_CASAS_LADO;
        int coluna = indice % JOGO_CASAS_LADO;

        return new Posicao(linha, coluna);
    }

    public static int linhaColunaParaIndice(Posicao posicao) {

        return posicao.linha * JOGO_CASAS_LADO + posicao.coluna;
    }

    public record Posicao(int linha, int coluna) {
    }
}
