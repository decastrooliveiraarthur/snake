package Main.Motor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Controle {

    private final boolean[] direcaoAnterior = Direcao.DIREITA.get();
    private final Action actionUp = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoAtual(Direcao.CIMA.get());
        }
    };
    private final Action actionRight = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoAtual(Direcao.DIREITA.get());
        }
    };
    private final Action actionLeft = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoAtual(Direcao.ESQUERDA.get());
        }
    };
    private final Action actionDown = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setDirecaoAtual(Direcao.BAIXO.get());
        }
    };
    private boolean podeMudarDirecao = true;
    private boolean[] direcaoPorCasa = Direcao.DIREITA.get();
    private boolean[] direcaoAtual = Direcao.DIREITA.get();
    private int codigoDirecao = (((direcaoAtual[0] ? 1 : 0) << 1) + (direcaoAtual[1] ? 1 : 0));

    public Controle() {
    }

    public boolean[] getDirecaoPorCasa() {
        return direcaoPorCasa;
    }

    public int getCodigoDirecao() {
        return codigoDirecao;
    }

    public boolean[] getDirecaoAtual() {
        return this.direcaoAtual.clone();
    }

    public void setDirecaoAtual(boolean[] direcaoNova) {

        boolean[] inversoDirecaoVelha = new boolean[]{!direcaoAnterior[0], !direcaoAnterior[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            this.direcaoAtual = new boolean[]{direcaoNova[0], direcaoNova[1]};
        }
    }

    public void atualizarDirecaoVelha() {

        this.direcaoAnterior[0] = this.direcaoAtual[0];
        this.direcaoAnterior[1] = this.direcaoAtual[1];
    }

    public boolean mudouDeDirecao() {
        return !Arrays.equals(this.direcaoAnterior, this.direcaoAtual);
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

    public boolean isPodeMudarDirecao() {
        return podeMudarDirecao;
    }

    public void setPodeMudarDirecao(boolean podeMudarDirecao) {
        this.podeMudarDirecao = podeMudarDirecao;
    }

    public void mudarDirecao() {

        codigoDirecao = ((direcaoAtual[0] ? 1 : 0) << 1) + (direcaoAtual[1] ? 1 : 0);
        direcaoPorCasa = (Arrays.copyOf(direcaoAtual, direcaoAtual.length));
    }
}