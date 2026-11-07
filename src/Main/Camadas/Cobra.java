package Main.Camadas;

import Main.Conversor;
import Main.Motor.Controle;
import Main.Tabuleiro;

public class Cobra {

    private final Controle controle;

    private final long[] bitboard = new long[4];
    private final Construtor construtor = new Construtor();
    private boolean colidiu = false;

    public Cobra(Controle controle) {
        this.controle = controle;
    }

    public void mover() {

        construtor.atualizarPosicao();
        construtor.construirCobra();
    }

    public void setPosicaoInicial() {

        int bloco = Conversor.posicaoParaBitboard(construtor.linha, construtor.coluna).bloco();
        long mascara = Conversor.posicaoParaBitboard(construtor.linha, construtor.coluna).mascara();
        bitboard[bloco] |= mascara;
    }

    public long[] getBitboard() {
        return bitboard;
    }

    private int ciclarBorda(int linhaColuna) {
        if (linhaColuna >= Tabuleiro.TABULEIRO_CASAS_LADO.get())
            return linhaColuna - Tabuleiro.TABULEIRO_CASAS_LADO.get();
        if (linhaColuna < 0)
            return linhaColuna + Tabuleiro.TABULEIRO_CASAS_LADO.get();
        return linhaColuna;
    }

    public boolean isColidiu() {
        return colidiu;
    }

    private class Destruidor {
    }

    private class Construtor {

        private int linha = 8;
        private int coluna = 8;

        public Construtor() {
        }

        private void construirCobra() {

            int bloco = Conversor.posicaoParaBitboard(this.linha, this.coluna).bloco();
            long mascara = Conversor.posicaoParaBitboard(this.linha, this.coluna).mascara();

            bitboard[bloco] |= mascara;
        }

        private void atualizarPosicao() {

            int direcaoCase = (((controle.getDirecaoNova()[0] ? 1 : 0) << 1) + (controle.getDirecaoNova()[1] ? 1 : 0));

            switch (direcaoCase) {

                case 0:
                    //cima
                    if (colisaoComCobra(this.linha - 1, this.coluna)) {
                        colidiu = true;
                    } else
                        this.linha--;
                    break;

                case 1:
                    //direita
                    if (colisaoComCobra(this.linha, this.coluna + 1)) {
                        colidiu = true;
                    } else
                        this.coluna++;
                    break;

                case 2:
                    //esquerda
                    if (colisaoComCobra(this.linha, this.coluna - 1)) {
                        colidiu = true;
                    } else
                        this.coluna--;
                    break;

                case 3:
                    //baixo
                    if (colisaoComCobra(this.linha + 1, this.coluna)) {
                        colidiu = true;
                    } else
                        this.linha++;
                    break;
            }

            this.linha = ciclarBorda(this.linha);
            this.coluna = ciclarBorda(this.coluna);
        }

        private boolean colisaoComCobra(int linha, int coluna) {

            int indice = Conversor.posicaoParaIndice(ciclarBorda(linha), ciclarBorda(coluna));
            return Conversor.ocupaCasa(indice, bitboard);
        }
    }
}