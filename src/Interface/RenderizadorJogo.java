package Interface;

import Camadas.Cobra;
import Camadas.Maca;
import Camadas.Tabuleiro;

import java.awt.*;

public class RenderizadorJogo {

    private final Tabuleiro tabuleiro;
    private final Maca maca;
    private final Cobra cobra;

    private final long[] tabuleiroBitboard = new long[4];
    private final int tabuleiroLado;

    public final Color verdeMaisClaro = new Color(0x9a9e3f);
    public final Color verdeClaro = new Color(0x496b22);
    public final Color verdeEscuro = new Color(0x0e450b);
    public final Color verdeMaisEscuro = new Color(0x1b2a09);

    public RenderizadorJogo(Tabuleiro tabuleiro, Maca maca, Cobra cobra) {

        this.tabuleiro = tabuleiro;
        this.maca = maca;
        this.cobra = cobra;

        this.tabuleiroLado = tabuleiro.getTabuleiroLado();
    }

    public void renderizarJogo(Graphics2D gCanvas) {

        int tabuleiroArea = this.tabuleiroLado * this.tabuleiroLado;

        this.tabuleiro.desenharBorda(gCanvas, this.verdeMaisClaro);

        for (int i = 0; i < tabuleiroArea; i++) {

            //casas do tabuleiro
            if (!this.cobra.ocupaCasa(i)) {

                this.tabuleiro.desenharCasas(gCanvas, i, this.verdeMaisClaro, this.verdeClaro);
            } else {

                
            }
        }
    }
}