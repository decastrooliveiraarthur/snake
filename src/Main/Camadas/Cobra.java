package Main.Camadas;

import Main.Conversor;
import Main.Motor.Controle;
import Main.Tabuleiro;

public class Cobra {

    private final Controle controle;

    private final long[] bitboard = new long[4];
    private int construtorLinha = 8;
    private int construtorColuna = 8;
    private int destruidor;

    public Cobra(Controle controle) {
        this.controle = controle;
    }

    public void mover() {

        atualizarPosicaoConstrutor();
        construirCobra();
    }

    public long[] getBitboard() {
        return bitboard;
    }

    private int ciclarBorda(int linhaColuna) {
        if (linhaColuna >= Tabuleiro.TABULEIRO_CASAS_LADO.get())
            return 0;
        if (linhaColuna < 0)
            return Tabuleiro.TABULEIRO_CASAS_LADO.get() - 1;
        return linhaColuna;
    }

    private void construirCobra() {

        int construtorIndice = Conversor.linhaColunaParaIndice(
                new Conversor.Posicao(this.construtorLinha, this.construtorColuna));

        bitboard[Conversor.indiceParaBitboard(construtorIndice).bloco()] |=
                Conversor.indiceParaBitboard(construtorIndice).mascara();
    }

    private void atualizarPosicaoConstrutor() {

        int direcaoCase = (
                ((controle.getDirecaoNova()[0] ? 1 : 0) << 1) + (controle.getDirecaoNova()[1] ? 1 : 0));

        switch (direcaoCase) {

            case 0:
                //cima
                this.construtorLinha--;
                break;

            case 1:
                //direita
                this.construtorColuna++;
                break;

            case 2:
                //esquerda
                this.construtorColuna--;
                break;

            case 3:
                //baixo
                this.construtorLinha++;
                break;
        }

        this.construtorLinha = ciclarBorda(this.construtorLinha);
        this.construtorColuna = ciclarBorda(this.construtorColuna);
    }
}