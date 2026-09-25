package Camadas;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tabuleiro {

    private final int x = 0;
    private final int y = 0;
    private final int bordaEspessura = 1;
    private final int casaLado = 2;
    private final int tabuleiroLado = 16;
    private final int ladoTotal = (tabuleiroLado * casaLado) + (bordaEspessura * 2);

    public int getTabuleiroLado() {

        return tabuleiroLado;
    }

    public Tabuleiro() {}

    public void desenharBorda(Graphics2D gCanvas, Color verdeMaisClaro) {

        //borda do tabuleiro
        gCanvas.setColor(verdeMaisClaro);
        gCanvas.fillRect(this.x, this.y, ladoTotal, ladoTotal);
    }

    public void desenharCasas(Graphics2D gCanvas, int indice, Color verdeMaisClaro, Color verdeClaro) {

        int linha = Conversor.converterIndiceParaLinhaColuna(indice)[0];
        int coluna = Conversor.converterIndiceParaLinhaColuna(indice)[1];

        if ((linha + coluna) % 2 == 0) {

            gCanvas.setColor(verdeMaisClaro);
        } else {

            gCanvas.setColor(verdeClaro);
        }

        gCanvas.fillRect(
                this.x + bordaEspessura + (linha * casaLado),
                this.y + bordaEspessura + (coluna * casaLado),
                casaLado,
                casaLado);
    }
}
