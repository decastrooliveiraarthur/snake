package Interface;

import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {

    private static final String JANELA_TITULO = "Snake Game";

    public Janela(TelaJogo telaJogo) {

        super(JANELA_TITULO);

        int lado = Tabuleiro.getTabuleiroTotalPixeisLado() * 16;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(telaJogo, BorderLayout.CENTER);
        pack();
        setMinimumSize(new Dimension(
                lado + getInsets().left + getInsets().right,
                lado + getInsets().top + getInsets().bottom));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}