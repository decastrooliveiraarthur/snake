package Main.Camadas;

import Main.Conversor;
import Main.Motor.Controle;
import Main.Tabuleiro;

public class Cobra {

    private static final double VELOCIDADE = 2;
    private final long[] bitboard = new long[4];
    private final Construtor construtor;
    private final int linhaInicial = 8;
    private final int colunaInicial = 8;

    public Cobra(Controle controle) {
        this.construtor = new Construtor(controle, this.linhaInicial, this.colunaInicial, this.bitboard);
    }

    public static double getVelocidade() {
        return VELOCIDADE;
    }

    public static double ciclarBorda(double linhaColunaPrecisa) {
        if (linhaColunaPrecisa >= Tabuleiro.TABULEIRO_CASAS_LADO.get())
            return linhaColunaPrecisa - Tabuleiro.TABULEIRO_CASAS_LADO.get();
        if (linhaColunaPrecisa < 0)
            return linhaColunaPrecisa + Tabuleiro.TABULEIRO_CASAS_LADO.get();
        return linhaColunaPrecisa;
    }

    public void mover(double deltaTempo) {

        construtor.atualizarBitboard(deltaTempo);
        construtor.construirCobra();
    }

    public void setPosicaoInicial() {

        int bloco = Conversor.posicaoParaBitboard(linhaInicial, colunaInicial).bloco();
        long mascara = Conversor.posicaoParaBitboard(linhaInicial, colunaInicial).mascara();
        bitboard[bloco] |= mascara;
    }

    public long[] getBitboard() {
        return bitboard.clone();
    }

    public boolean isColidiu() {
        return construtor.isColidiu();
    }

    private class Destruidor {
    }

}