package Main.Controle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Controle {

    private final boolean[] direcaoVelha = new boolean[]{false, true};
    private final boolean[] direcaoNova = new boolean[]{false, true};


    private final Action actionUp = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoNova(false, false);
        }
    };

    private final Action actionRight = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoNova(false, true);
        }
    };

    private final Action actionLeft = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoNova(true, false);
        }
    };

    private final Action actionDown = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoNova(true, true);
        }
    };

    public Controle() {
    }

    public void setDirecaoNova(boolean direcao0, boolean direcao1) {

        boolean[] direcaoNova = new boolean[]{direcao0, direcao1};
        boolean[] inversoDirecaoVelha = new boolean[]{!direcaoVelha[0], !direcaoVelha[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            this.direcaoNova[0] = direcaoNova[0];
            this.direcaoNova[1] = direcaoNova[1];
        }
    }

    public boolean[] getDirecaoNova() {
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