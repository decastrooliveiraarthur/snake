package Main.Camadas;


import Main.Conversor;
import Main.Motor.Controle;
import Main.Motor.Direcao;

import java.util.Arrays;

public class Construtor {

    private static final double CENTRO_DA_CASA = 0.5;
    private final Controle controle;
    private final long[] bitboard;
    private boolean colidiu = false;
    private int linhaAtual;
    private int colunaAtual;
    private double linhaPrecisa;
    private double colunaPrecisa;

    public Construtor(Controle controle, int linhaInicial, int colunaInicial, long[] bitboard) {

        this.linhaAtual = linhaInicial;
        this.colunaAtual = colunaInicial;
        this.linhaPrecisa = linhaInicial + CENTRO_DA_CASA;
        this.colunaPrecisa = colunaInicial + CENTRO_DA_CASA;
        this.controle = controle;
        this.bitboard = bitboard;
    }

    boolean isColidiu() {
        return colidiu;
    }

    void construirCobra() {

        Conversor.Bitboard bitboard = Conversor.posicaoParaBitboard(linhaAtual, colunaAtual);
        int bloco = bitboard.bloco();
        long mascara = bitboard.mascara();

        this.bitboard[bloco] |= mascara;
    }


    void atualizarPosicaoPrecisa(double deltaTempo) {

        double deltaVelocidade = Cobra.getVelocidade() * deltaTempo;

        switch (controle.getCodigoDirecao()) {

            case 0 -> linhaPrecisa -= deltaVelocidade; //cima
            case 1 -> colunaPrecisa += deltaVelocidade; //direita
            case 2 -> colunaPrecisa -= deltaVelocidade; //esquerda
            case 3 -> linhaPrecisa += deltaVelocidade; //baixo
        }
    }

    private void alinharPosicaoPrecisa() {

        boolean horizontal =
                Arrays.equals(controle.getDirecaoAtual(), Direcao.DIREITA.get()) ||
                        Arrays.equals(controle.getDirecaoAtual(), Direcao.ESQUERDA.get());

        if (horizontal) {
            this.linhaPrecisa = Math.floor(this.linhaPrecisa) + CENTRO_DA_CASA;
        } else {
            this.colunaPrecisa = Math.floor(this.colunaPrecisa) + CENTRO_DA_CASA;
        }
    }

    private boolean colisaoComCobra(int linha, int coluna) {

        int indice = Conversor.posicaoParaIndice(linha, coluna);
        return Conversor.ocupaCasa(indice, bitboard);
    }

    void atualizarBitboard(double deltaTempo) {

        if (controle.mudouDeDirecao() && controle.isPodeMudarDirecao()) {

            alinharPosicaoPrecisa();
            controle.mudarDirecao();
            controle.setPodeMudarDirecao(false);
        }
        atualizarPosicaoPrecisa(deltaTempo);
        atualizarPosicao();
    }

    void atualizarPosicao() {

        linhaPrecisa = Cobra.ciclarBorda(linhaPrecisa);
        colunaPrecisa = Cobra.ciclarBorda(colunaPrecisa);

        int linhaDestino = (int) Math.floor(linhaPrecisa);
        int colunaDestino = (int) Math.floor(colunaPrecisa);

        boolean mudouDeCasa = (linhaDestino != linhaAtual) || (colunaDestino != colunaAtual);

        if (mudouDeCasa) {
            if (colisaoComCobra(linhaDestino, colunaDestino)) {
                colidiu = true;
            } else {
                linhaAtual = linhaDestino;
                colunaAtual = colunaDestino;
                controle.atualizarDirecaoVelha();
                controle.setDirecaoAtual(controle.getDirecaoPorCasa());
                controle.setPodeMudarDirecao(true);
            }
        }
    }
}