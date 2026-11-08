package Main.Camadas;

import Main.Conversor;
import Main.Motor.Controle;
import Main.Tabuleiro;

public class Cobra {

    private static final double VELOCIDADE = 2;
    private final Controle controle;
    private final long[] bitboard = new long[4];
    private final Construtor construtor = new Construtor();
    private boolean colidiu = false;

    public Cobra(Controle controle) {
        this.controle = controle;
    }

    public static double getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void mover(double deltaTempo) {

        construtor.atualizarPosicao(deltaTempo);
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

    private double ciclarBorda(double deltaLinhaColuna) {
        if (deltaLinhaColuna >= Tabuleiro.TABULEIRO_CASAS_LADO.get())
            return deltaLinhaColuna - Tabuleiro.TABULEIRO_CASAS_LADO.get();
        if (deltaLinhaColuna < 0)
            return deltaLinhaColuna + Tabuleiro.TABULEIRO_CASAS_LADO.get();
        return deltaLinhaColuna;
    }

    public boolean isColidiu() {
        return colidiu;
    }

    private class Destruidor {
    }

    private class Construtor {

        private static final double CENTRO_DA_CASA = 0.5;
        private int linha = 8;
        private int coluna = 8;
        private double deltaLinha = 8 + CENTRO_DA_CASA;
        private double deltaColuna = 8 + CENTRO_DA_CASA;

        public Construtor() {
        }

        private void construirCobra() {

            int bloco = Conversor.posicaoParaBitboard(this.linha, this.coluna).bloco();
            long mascara = Conversor.posicaoParaBitboard(this.linha, this.coluna).mascara();

            bitboard[bloco] |= mascara;
        }

        private void atualizarPosicao(double deltaTempo) {

            int direcaoCase = (((controle.getDirecaoNova()[0] ? 1 : 0) << 1) + (controle.getDirecaoNova()[1] ? 1 : 0));
            double deltaVelocidade = VELOCIDADE * deltaTempo;

            switch (direcaoCase) {

                case 0:
                    //cima
                    this.deltaLinha -= deltaVelocidade;
                    break;

                case 1:
                    //direita
                    this.deltaColuna += deltaVelocidade;
                    break;

                case 2:
                    //esquerda
                    this.deltaColuna -= deltaVelocidade;
                    break;

                case 3:
                    //baixo
                    this.deltaLinha += deltaVelocidade;
                    break;
            }

            this.deltaLinha = ciclarBorda(this.deltaLinha);
            this.deltaColuna = ciclarBorda(this.deltaColuna);

            int novaLinha = (int) Math.floor(this.deltaLinha);
            int novaColuna = (int) Math.floor(this.deltaColuna);

            boolean moveu = (novaLinha != this.linha) || (novaColuna != this.coluna);

            if (moveu) {
                if (colisaoComCobra(novaLinha, novaColuna)) {
                    colidiu = true;
                } else {
                    this.linha = novaLinha;
                    this.coluna = novaColuna;
                }
            }
        }

        private boolean colisaoComCobra(int linha, int coluna) {

            int indice = Conversor.posicaoParaIndice(linha, coluna);
            return Conversor.ocupaCasa(indice, bitboard);
        }
    }
}