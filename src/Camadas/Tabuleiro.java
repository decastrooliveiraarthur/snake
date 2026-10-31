//Classe ajustada

package Camadas;

public class Tabuleiro {

    private static final int PIXEIS_BORDA = 1;
    private static final int PIXEIS_CASAS_LADO = 2;
    private static final int TABULEIRO_CASAS_LADO = 16;

    public Tabuleiro() {
    }

    public int getTabuleiroTotalPixeisLado() {

        return (TABULEIRO_CASAS_LADO * PIXEIS_CASAS_LADO) + (PIXEIS_BORDA * 2);
    }

    public int getPixeisCasasLado() {
        return PIXEIS_CASAS_LADO;
    }

    public int getPixeisBorda() {
        return PIXEIS_BORDA;
    }

}