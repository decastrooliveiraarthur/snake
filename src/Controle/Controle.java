package Controle;

import Camadas.Cobra;
import Interface.TelaJogo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controle {

    private final TelaJogo telaJogo;

    public Controle(Cobra cobra, TelaJogo telaJogo) {

        this.telaJogo = telaJogo;

        Action actionUp = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cobra.setDirecaoNova(false, false);
            }
        };

        Action actionRight = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cobra.setDirecaoNova(false, true);
            }
        };

        Action actionLeft = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cobra.setDirecaoNova(true, false);
            }
        };

        Action actionDown = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cobra.setDirecaoNova(true, true);
            }
        };

        movimentar(Teclas.W.get(), Teclas.UP.get(), "actionUp", actionUp);
        movimentar(Teclas.D.get(), Teclas.RIGHT.get(), "actionRight", actionRight);
        movimentar(Teclas.A.get(), Teclas.LEFT.get(), "actionLeft", actionLeft);
        movimentar(Teclas.S.get(), Teclas.DOWN.get(), "actionDown", actionDown);
    }

    public void movimentar(

            KeyStroke keyStroke0,
            KeyStroke keyStroke1,
            String actionMapKey,
            Action action) {

        this.telaJogo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke0, actionMapKey);
        this.telaJogo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke1, actionMapKey);
        this.telaJogo.getActionMap().put(actionMapKey, action);
    }
}