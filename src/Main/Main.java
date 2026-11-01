package Main;

import Main.Camadas.Cobra;
import Main.Camadas.Maca;
import Main.Interface.Janela;
import Main.Interface.RenderizadorJogo;
import Main.Interface.TelaJogo;
import Main.Motor.Controle;
import Main.Motor.Motor;

import javax.swing.*;

public class Main {

    static void main(@SuppressWarnings("unused") String[] args) {

        Maca maca = new Maca();

        SwingUtilities.invokeLater(() -> {

            Controle controle = new Controle();
            Cobra cobra = new Cobra(controle);
            RenderizadorJogo renderizadorJogo = new RenderizadorJogo(cobra, maca);
            TelaJogo telaJogo = new TelaJogo(renderizadorJogo);
            new Motor(cobra, controle, renderizadorJogo, telaJogo);
            new Janela(telaJogo);
        });
    }
}