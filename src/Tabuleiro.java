import java.awt.Color;
import java.awt.Graphics2D;

public class Tabuleiro {

    int X = 0;
    int Y = 0;
    Color cor0 = Config.VERDE_MAIS_CLARO;
    Color cor1 = Config.VERDE_CLARO;
    int CasasQuantidadeTotal = Config.TABULEIRO_CASAS_QUANTIDADE_LADO * Config.TABULEIRO_CASAS_QUANTIDADE_LADO;

    public Tabuleiro() {}

    public int getCasasQuantidadeTotal() {

        return this.CasasQuantidadeTotal;
    }

    public void desenharBorda(Graphics2D gCanvas) {

        //borda do tabuleiro
        gCanvas.setColor(this.cor0);
        gCanvas.fillRect(this.X, this.Y, Config.TABULEIRO_LADO, Config.TABULEIRO_LADO);
    }

    public byte[] conversorLinhaColuna(int indice) {

        return new byte[] {
                (byte) (indice / Config.TABULEIRO_CASAS_QUANTIDADE_LADO),
                (byte) (indice % Config.TABULEIRO_CASAS_QUANTIDADE_LADO)};
    }

    public short conversorLinhaColuna(int linha, int coluna) {

        return (short) (linha * Config.TABULEIRO_CASAS_QUANTIDADE_LADO + coluna);
    }

    public void desenharCasas(Graphics2D gCanvas, int indice) {

        int linha = conversorLinhaColuna(indice)[0];
        int coluna = conversorLinhaColuna(indice)[1];

        if ((linha + coluna) % 2 == 0) {

            gCanvas.setColor(this.cor0);
        } else {

            gCanvas.setColor(this.cor1);
        }

        gCanvas.fillRect(
                this.X + Config.TABULEIRO_BORDA_ESPESSURA + (linha * Config.TABULEIRO_CASAS_TAMANHO),
                this.Y + Config.TABULEIRO_BORDA_ESPESSURA + (coluna * Config.TABULEIRO_CASAS_TAMANHO),
                Config.TABULEIRO_CASAS_TAMANHO,
                Config.TABULEIRO_CASAS_TAMANHO);
    }
}
