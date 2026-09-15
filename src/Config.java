import java.awt.Color;

public final class Config {

    private Config() {}

    //Janela
    public static final String JANELA_TITULO = "Snake Game";

    //Tabuleiro
    public static final int TABULEIRO_CASAS_TAMANHO = 2;
    public static final int TABULEIRO_CASAS_QUANTIDADE_LADO = 16;
    public static final int TABULEIRO_BORDA_ESPESSURA = 1;
    public static final int TABULEIRO_LADO = (TABULEIRO_CASAS_QUANTIDADE_LADO * TABULEIRO_CASAS_TAMANHO) + (TABULEIRO_BORDA_ESPESSURA * 2);

    //Cores
    public static final Color VERDE_MAIS_CLARO = new Color(0x9a9e3f);
    public static final Color VERDE_CLARO = new Color(0x496b22);
    public static final Color VERDE_ESCURO = new Color(0x0e450b);
    public static final Color VERDE_MAIS_ESCURO = new Color(0x1b2a09);

}