import java.awt.Graphics2D;

public class RenderizadorJogo {

    private final Tabuleiro tabuleiro;
    private final Maca maca = new Maca();
    private final Cobra cobra;

    public RenderizadorJogo(Tabuleiro tabuleiro, Cobra cobra) {

        this.tabuleiro = tabuleiro;
        this.cobra = cobra;
    }

    public void renderizarJogo(Graphics2D gCanvas) {

        this.tabuleiro.desenharBorda(gCanvas);

        for (int i = 0; i < (this.tabuleiro.getCasasQuantidadeTotal()); i++) {

            //casas do tabuleiro
            if (!this.cobra.ocupaCasa(i)) {

                this.tabuleiro.desenharCasas(gCanvas, i);
            } else {

                
            }
        }
    }


}