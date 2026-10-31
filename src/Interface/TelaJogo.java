package Interface;


import Camadas.Cobra;
import Camadas.Conversor;
import Camadas.Maca;
import Camadas.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TelaJogo extends JPanel {

    private static final int X = 0;
    private static final int Y = 0;
    private static final Color VERDE_MAIS_CLARO = new Color(0x9a9e3f);
    private static final Color VERDE_CLARO = new Color(0x496b22);
    private static final Color VERDE_ESCURO = new Color(0x0e450b);
    private static final Color VERDE_MAIS_ESCURO = new Color(0x1b2a09);
    private final int telaPixeisLado;
    private final Cobra cobra;
    private final Maca maca;
    private final Tabuleiro tabuleiro;
    private final RenderizadorJogo renderizadorJogo;

    //private final Controle controle = new Controle(this, cobra);

    private final BufferedImage canvas;
    private final Graphics2D gCanvas;
    private final ActionListener atualizarFrame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            cobra.mover();
            desenharBorda();
            desenharJogo();
            gCanvas.dispose();
            repaint();
        }
    };
    private Rectangle area;

    public TelaJogo(Cobra cobra, Maca maca, Tabuleiro tabuleiro, RenderizadorJogo renderizadorJogo) {

        this.cobra = cobra;
        this.maca = maca;
        this.tabuleiro = tabuleiro;
        this.renderizadorJogo = renderizadorJogo;
        this.telaPixeisLado = tabuleiro.getTabuleiroTotalPixeisLado();
        this.canvas = new BufferedImage(telaPixeisLado, telaPixeisLado, BufferedImage.TYPE_INT_RGB);
        this.gCanvas = this.canvas.createGraphics();

        setPreferredSize(new Dimension(telaPixeisLado * 16, telaPixeisLado * 16));


        Timer timer = new Timer(1000, atualizarFrame);
        timer.start();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {

        super.setBounds(x, y, width, height);

        //Ajustar as dimensões da tela do jogo
        int jogoBordaTamanho = Math.min(width, height);

        //Onde a imagem do jogo é criada
        int jogoX = (width - jogoBordaTamanho) / 2;
        int jogoY = (height - jogoBordaTamanho) / 2;

        this.area = new Rectangle(jogoX, jogoY, jogoBordaTamanho, jogoBordaTamanho);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Objetos necessários
        Graphics2D g2d = (Graphics2D) g;

        //this.renderizadorJogo.renderizarJogo(this.gCanvas);
        //this.gCanvas.dispose();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (this.area != null) {

            g2d.drawImage(this.canvas, area.x, area.y, area.width, area.height, this);
        }
    }

    public int getTelaPixeisLado() {
        return telaPixeisLado;
    }

    private void desenharBorda() {

        this.gCanvas.setColor(VERDE_MAIS_CLARO);
        this.gCanvas.fillRect(X, Y, telaPixeisLado, telaPixeisLado);
    }

    /**
     * Recebe um índice de 0 a 255, que representa a posição de um tabuleiro 16 * 16, da esquerda para direita,
     * de cima para baixo. E um bitboard: {@code long[] bitboard = new long[4]}.
     * Checa se a posição {@code i} do bitboard está preenchida.
     *
     * @param indice
     * @param bitboard
     * @return {@code true} para posição preenchida (1) e {@code false} para posição vazia (0).
     */
    private boolean ocupaCasa(int indice, long[] bitboard) {

        int bloco = indice >>> 6;
        int bit = indice & 63;
        long mascara = 1L << bit;

        return (bitboard[bloco] & mascara) != 0;
    }

    private void desenharTabuleiro(int linha, int coluna) {

        if ((linha + coluna) % 2 == 0)
            this.gCanvas.setColor(VERDE_MAIS_CLARO);
        else
            this.gCanvas.setColor(VERDE_CLARO);
    }

    private void desenharCobra() {

        this.gCanvas.setColor(VERDE_ESCURO);
    }

    public Color getVerdeMaisEscuro() {
        return VERDE_MAIS_ESCURO;
    }

    private void desenharMaca() {
        this.gCanvas.setColor(VERDE_MAIS_ESCURO);
    }

    /**
     * Desenha toda a área ativa do jogo de uma só vez.
     */
    public void desenharJogo() {

        for (int i = 0; i < 64 * 4; i++) {

            int linha = Conversor.indiceParaLinhaColuna(i).linha();
            int coluna = Conversor.indiceParaLinhaColuna(i).coluna();

            int x = X + this.tabuleiro.getPixeisBorda() + (coluna * this.tabuleiro.getPixeisCasasLado());
            int y = Y + this.tabuleiro.getPixeisBorda() + (linha * this.tabuleiro.getPixeisCasasLado());
            int lado = this.tabuleiro.getPixeisCasasLado();

            if (ocupaCasa(i, this.renderizadorJogo.getTabuleiroBitboard()))
                desenharTabuleiro(linha, coluna);/*
            else if (ocupaCasa(i, this.cobra.getBitboard()))
                desenharCobra();
            else if (ocupaCasa(i, this.maca.getBitboard()))
                desenharMaca();*/

            this.gCanvas.fillRect(x, y, lado, lado);
        }
    }
}