package Main;

public class Conversor {

    Conversor() {
    }

    public static Posicao indiceParaPosicao(int indice) {

        int linha = indice / Tabuleiro.TABULEIRO_CASAS_LADO.get();
        int coluna = indice % Tabuleiro.TABULEIRO_CASAS_LADO.get();

        return new Posicao(linha, coluna);
    }

    public static int posicaoParaIndice(int linha, int coluna) {

        return linha * Tabuleiro.TABULEIRO_CASAS_LADO.get() + coluna;
    }

    public static Bitboard indiceParaBitboard(int indice) {

        int bloco = indice >>> 6;
        int bit = indice & 63;

        long mascara = 1L << bit;

        return new Bitboard(bloco, mascara);
    }

    public static Bitboard posicaoParaBitboard(int linha, int coluna) {

        int indice = posicaoParaIndice(linha, coluna);

        return indiceParaBitboard(indice);
    }

    /**
     * Checa se a posição {@code i} do bitboard está preenchida.
     *
     * @param indice   inteiro de 0 a 255, que representa a posição de um tabuleiro 16 * 16,
     *                 da esquerda para direita, de cima para baixo.
     * @param bitboard bitboard das posições a serem conferidas: {@code long[] bitboard = new long[4]}.
     * @return {@code true} para posição preenchida (1) e {@code false} para posição vazia (0).
     */
    public static boolean ocupaCasa(int indice, long[] bitboard) {

        return (bitboard[Conversor.indiceParaBitboard(indice).bloco()] &
                Conversor.indiceParaBitboard(indice).mascara()) != 0;
    }

    public record Posicao(int linha, int coluna) {
    }

    public record Bitboard(int bloco, long mascara) {
    }
}