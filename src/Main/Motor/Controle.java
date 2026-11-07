package Main.Motor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Controle {

    private static final boolean[] DIRECAO_CIMA = new boolean[]{false, false};
    private static final boolean[] DIRECAO_DIREITA = new boolean[]{false, true};
    private static final boolean[] DIRECAO_ESQUERDA = new boolean[]{true, false};
    private static final boolean[] DIRECAO_BAIXO = new boolean[]{true, true};

    private final boolean[] direcaoVelha = DIRECAO_DIREITA.clone();
    private final Action actionUp = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoNova = setDirecaoNova(DIRECAO_CIMA);
        }
    };
    private final Action actionRight = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoNova = setDirecaoNova(DIRECAO_DIREITA);
        }
    };
    private final Action actionLeft = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoNova = setDirecaoNova(DIRECAO_ESQUERDA);
        }
    };
    private final Action actionDown = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoNova = setDirecaoNova(DIRECAO_BAIXO);
        }
    };
    private boolean[] direcaoNova = DIRECAO_DIREITA.clone();

    public Controle() {
    }

    public boolean[] getDirecaoNova() {
        return this.direcaoNova;
    }

    public boolean[] setDirecaoNova(boolean[] direcaoNova) {

        boolean[] inversoDirecaoVelha = new boolean[]{!direcaoVelha[0], !direcaoVelha[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            return new boolean[]{direcaoNova[0], direcaoNova[1]};
        }
        return this.direcaoNova;
    }

    public void atualizarDirecaoVelha() {

        this.direcaoVelha[0] = this.direcaoNova[0];
        this.direcaoVelha[1] = this.direcaoNova[1];
    }

    public Action getActionUp() {
        return actionUp;
    }

    public Action getActionRight() {
        return actionRight;
    }

    public Action getActionLeft() {
        return actionLeft;
    }

    public Action getActionDown() {
        return actionDown;
    }
}