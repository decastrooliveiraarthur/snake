package Main;

import Camadas.Cobra;
import Camadas.Tabuleiro;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro();
        Cobra cobra = new Cobra(tabuleiro);

        //cobra.atualizarPosicao();

        SwingUtilities.invokeLater(() -> {

            RenderizadorJogo renderizadorJogo = new RenderizadorJogo(tabuleiro, cobra);
            TelaJogo telaJogo = new TelaJogo(renderizadorJogo);
            Controle controle = new Controle(telaJogo, cobra);
            Janela janela = new Janela(telaJogo);
            janela.setVisible(true);
        });
    }
}