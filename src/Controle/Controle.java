package Controle;

import Camadas.Cobra;
import Interface.TelaJogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Controle {

    private final TelaJogo telaJogo;
    private final Cobra cobra;

    KeyStroke w = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
    KeyStroke d = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
    KeyStroke a = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
    KeyStroke s = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);

    KeyStroke up = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
    KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
    KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
    KeyStroke down = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
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

    public Controle(Cobra cobra, TelaJogo telaJogo) {

        this.telaJogo = telaJogo;
        this.cobra = cobra;

        movimentar(w, up, "actionUp", actionUp);
        movimentar(d, right, "actionRight", actionRight);
        movimentar(a, left, "actionLeft", actionLeft);
        movimentar(s, down, "actionDown", actionDown);
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