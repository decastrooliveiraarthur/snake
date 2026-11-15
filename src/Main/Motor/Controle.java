package Main.Motor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Controle {

    private final boolean[] direcaoAnterior = Direcao.DIREITA.get();
    private final Action actionUp = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoAtual = setDirecaoNova(Direcao.CIMA.get());
        }
    };
    private final Action actionRight = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoAtual = setDirecaoNova(Direcao.DIREITA.get());
        }
    };
    private final Action actionLeft = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoAtual = setDirecaoNova(Direcao.ESQUERDA.get());
        }
    };
    private final Action actionDown = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            direcaoAtual = setDirecaoNova(Direcao.BAIXO.get());
        }
    };
    private boolean[] direcaoAtual = Direcao.DIREITA.get();

    public Controle() {
    }

    public boolean[] getDirecaoAtual() {
        return this.direcaoAtual.clone();
    }

    public boolean[] setDirecaoNova(boolean[] direcaoNova) {

        boolean[] inversoDirecaoVelha = new boolean[]{!direcaoAnterior[0], !direcaoAnterior[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            return new boolean[]{direcaoNova[0], direcaoNova[1]};
        }
        return this.direcaoAtual;
    }

    public void atualizarDirecaoVelha() {

        this.direcaoAnterior[0] = this.direcaoAtual[0];
        this.direcaoAnterior[1] = this.direcaoAtual[1];
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