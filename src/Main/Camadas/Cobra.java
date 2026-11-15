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

    public static double getVelocidade() {
        return VELOCIDADE;
    }

    public void mover(double deltaTempo) {

        construtor.atualizarPosicao(deltaTempo);
        construtor.construirCobra();
    }

    public void setPosicaoInicial() {

        int bloco = Conversor.posicaoParaBitboard(construtor.linhaAtual, construtor.colunaAtual).bloco();
        long mascara = Conversor.posicaoParaBitboard(construtor.linhaAtual, construtor.colunaAtual).mascara();
        bitboard[bloco] |= mascara;
    }

    public long[] getBitboard() {
        return bitboard.clone();
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
        private int linhaAtual = 8;
        private int colunaAtual = 8;
        private double linhaPrecisa = 8 + CENTRO_DA_CASA;
        private double colunaPrecisa = 8 + CENTRO_DA_CASA;

        public Construtor() {
        }

        private void construirCobra() {

            int bloco = Conversor.posicaoParaBitboard(this.linhaAtual, this.colunaAtual).bloco();
            long mascara = Conversor.posicaoParaBitboard(this.linhaAtual, this.colunaAtual).mascara();

            bitboard[bloco] |= mascara;
        }

        private void atualizarPosicao(double deltaTempo) {

            int codigoDirecao = (((controle.getDirecaoAtual()[0] ? 1 : 0) << 1) + (controle.getDirecaoAtual()[1] ? 1 : 0));
            double deltaVelocidade = VELOCIDADE * deltaTempo;

            switch (codigoDirecao) {

                case 0 -> this.linhaPrecisa -= deltaVelocidade; //cima
                case 1 -> this.colunaPrecisa += deltaVelocidade; //direita
                case 2 -> this.colunaPrecisa -= deltaVelocidade; //esquerda
                case 3 -> this.linhaPrecisa += deltaVelocidade; //baixo
            }

            this.linhaPrecisa = ciclarBorda(this.linhaPrecisa);
            this.colunaPrecisa = ciclarBorda(this.colunaPrecisa);

            int linhaDestino = (int) Math.floor(this.linhaPrecisa);
            int colunaDestino = (int) Math.floor(this.colunaPrecisa);

            boolean mudouDeCasa = (linhaDestino != this.linhaAtual) || (colunaDestino != this.colunaAtual);

            if (mudouDeCasa) {
                if (colisaoComCobra(linhaDestino, colunaDestino)) {
                    colidiu = true;
                } else {
                    this.linhaAtual = linhaDestino;
                    this.colunaAtual = colunaDestino;
                }
            }
        }

        private boolean colisaoComCobra(int linha, int coluna) {

            int indice = Conversor.posicaoParaIndice(linha, coluna);
            return Conversor.ocupaCasa(indice, bitboard);
        }
    }
}