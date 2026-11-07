package Main.Interface;

import Main.Camadas.Cobra;
import Main.Camadas.Maca;
import Main.Conversor;
import Main.Tabuleiro;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Usa as informações das classes {@link Cobra} e {@link Maca} para desenhar o jogo em um {@link BufferedImage}.
 *
 * @author Arthur de Castro Oliveira
 * @version 3.0
 */
public class RenderizadorJogo {

    private final BufferedImage canvas;
    private final Maca maca;
    private final Cobra cobra;
    private final int origemX = Tabuleiro.ORIGEM_X.get();
    private final int origemY = Tabuleiro.ORIGEM_Y.get();
    private final int lado = Tabuleiro.getTabuleiroTotalPixeisLado();
    private Graphics2D gCanvas;

    public RenderizadorJogo(Cobra cobra, Maca maca) {

        this.maca = maca;
        this.cobra = cobra;
        this.canvas = new BufferedImage(lado, lado, BufferedImage.TYPE_INT_RGB);
    }

    private void desenharBorda() {

        this.gCanvas.setColor(Cores.VERDE_MAIS_CLARO.get());
        this.gCanvas.fillRect(origemX, origemY, lado, lado);
    }

    private void desenharCobra() {

        this.gCanvas.setColor(Cores.VERDE_ESCURO.get());
    }

    private void desenharMaca() {
        this.gCanvas.setColor(Cores.VERDE_MAIS_ESCURO.get());
    }

    private void desenharTabuleiro(int linha, int coluna) {

        if ((linha + coluna) % 2 == 0)
            this.gCanvas.setColor(Cores.VERDE_MAIS_CLARO.get());
        else
            this.gCanvas.setColor(Cores.VERDE_CLARO.get());
    }

    /**
     * Desenha toda a área ativa do jogo de uma só vez.
     */
    private void desenharCamadas() {

        for (int i = 0; i < 64 * 4; i++) {

            int linha = Conversor.indiceParaPosicao(i).linha();
            int coluna = Conversor.indiceParaPosicao(i).coluna();

            int lado = Tabuleiro.PIXEIS_CASAS_LADO.get();
            int x = origemX + Tabuleiro.PIXEIS_BORDA.get() + (coluna * lado);
            int y = origemY + Tabuleiro.PIXEIS_BORDA.get() + (linha * lado);

            if (Conversor.ocupaCasa(i, this.maca.getBitboard()))
                desenharMaca();
            else if (Conversor.ocupaCasa(i, this.cobra.getBitboard()))
                desenharCobra();
            else
                desenharTabuleiro(linha, coluna);

            this.gCanvas.fillRect(x, y, lado, lado);
        }
    }

    public BufferedImage getCanvas() {
        return canvas;
    }

    private void atualizarGCanvas() {
        gCanvas = canvas.createGraphics();
    }

    public void desenharJogo() {

        atualizarGCanvas();
        desenharBorda();
        desenharCamadas();
        gCanvas.dispose();
    }
}