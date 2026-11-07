package Main;

public enum Tabuleiro {

    ORIGEM_X(0),
    ORIGEM_Y(0),
    PIXEIS_BORDA(1),
    PIXEIS_CASAS_LADO(2),
    TABULEIRO_CASAS_LADO(16);

    private final int valor;

    Tabuleiro(int valor) {
        this.valor = valor;
    }

    public static int getTabuleiroTotalPixeisLado() {
        return (TABULEIRO_CASAS_LADO.get() * PIXEIS_CASAS_LADO.get()) + (PIXEIS_BORDA.get() * 2);
    }

    public int get() {
        return valor;
    }
}
