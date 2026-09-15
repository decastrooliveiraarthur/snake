import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Janela extends JFrame {

    public Janela(TelaJogo telaJogo) {

        super(Config.JANELA_TITULO);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Config.VERDE_MAIS_ESCURO);
        add(telaJogo, BorderLayout.CENTER);
        pack();
        setMinimumSize(new Dimension(
                Config.TABULEIRO_LADO + getInsets().left + getInsets().right,
                Config.TABULEIRO_LADO + getInsets().top + getInsets().bottom));
        setLocationRelativeTo(null);
    }
}