package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class TelaJogo extends JComponent {

    public final Color verdeMaisClaro = new Color(0x9a9e3f);
    public final Color verdeClaro = new Color(0x496b22);
    public final Color verdeEscuro = new Color(0x0e450b);
    public final Color verdeMaisEscuro = new Color(0x1b2a09);

    private final RenderizadorJogo renderizadorJogo;
    private final BufferedImage canvas = new BufferedImage(
            Config.TABULEIRO_LADO,
            Config.TABULEIRO_LADO,
            BufferedImage.TYPE_INT_RGB);
    private final Graphics2D gCanvas = canvas.createGraphics();
    private Rectangle area;

    public TelaJogo(RenderizadorJogo renderizadorJogo) {

        this.renderizadorJogo = renderizadorJogo;
        setPreferredSize(new Dimension(Config.TABULEIRO_LADO * 16, Config.TABULEIRO_LADO * 16));
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

        this.renderizadorJogo.renderizarJogo(this.gCanvas);
        this.gCanvas.dispose();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (this.area != null) {

            g2d.drawImage(this.canvas, area.x, area.y, area.width, area.height, this);
        }
    }
}