package Interface;

import Camadas.Cobra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaJogo extends JPanel {

    private final RenderizadorJogo renderizadorJogo;

    private Rectangle area;

    public TelaJogo(Cobra cobra, RenderizadorJogo renderizadorJogo) {

        this.renderizadorJogo = renderizadorJogo;
        int lado = Tabuleiro.getTabuleiroTotalPixeisLado();

        ActionListener atualizarFrame = _ -> {

            cobra.mover();
            renderizadorJogo.atualizarGCanvas();
            renderizadorJogo.desenharBorda();
            renderizadorJogo.desenharJogo();
            renderizadorJogo.getGCanvas().dispose();
            repaint();
        };

        setPreferredSize(new Dimension(lado * 16, lado * 16));
        setBackground(Cores.VERDE_MAIS_ESCURO.get());

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

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (this.area != null) {

            g2d.drawImage(renderizadorJogo.getCanvas(), area.x, area.y, area.width, area.height, this);
        }
    }
}