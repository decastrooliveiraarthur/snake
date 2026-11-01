package Main;

import Camadas.Cobra;
import Camadas.Maca;
import Controle.Controle;
import Interface.Janela;
import Interface.RenderizadorJogo;
import Interface.TelaJogo;

import javax.swing.*;

public class Main {

    static void main() {

        Cobra cobra = new Cobra();
        Maca maca = new Maca();

        SwingUtilities.invokeLater(() -> {

            RenderizadorJogo renderizadorJogo = new RenderizadorJogo(cobra, maca);
            TelaJogo telaJogo = new TelaJogo(cobra, renderizadorJogo);
            new Controle(cobra, telaJogo);
            new Janela(telaJogo);
        });
    }
}