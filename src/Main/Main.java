package Main;

import Camadas.Cobra;
import Camadas.Maca;
import Camadas.Tabuleiro;
import Controle.Controle;
import Interface.Janela;
import Interface.RenderizadorJogo;
import Interface.TelaJogo;

import javax.swing.*;

public class Main {

    static void main(String[] args) {

        Cobra cobra = new Cobra();
        Maca maca = new Maca();
        Tabuleiro tabuleiro = new Tabuleiro();

        SwingUtilities.invokeLater(() -> {

            RenderizadorJogo renderizadorJogo = new RenderizadorJogo(cobra, maca);
            TelaJogo telaJogo = new TelaJogo(cobra, maca, tabuleiro, renderizadorJogo);
            new Controle(cobra, telaJogo);
            new Janela(telaJogo);
        });
    }
}