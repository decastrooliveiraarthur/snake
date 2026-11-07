package Main.Motor;

import javax.swing.*;
import java.awt.event.KeyEvent;

enum Teclas {

    W(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0)),
    D(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0)),
    A(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0)),
    S(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0)),

    UP(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)),
    RIGHT(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)),
    LEFT(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)),
    DOWN(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));

    private final KeyStroke tecla;

    Teclas(KeyStroke tecla) {
        this.tecla = tecla;
    }

    public KeyStroke get() {
        return tecla;
    }
}
