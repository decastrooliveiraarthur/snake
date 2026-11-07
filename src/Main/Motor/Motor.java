package Main.Motor;

import Main.Camadas.Cobra;
import Main.Interface.RenderizadorJogo;
import Main.Interface.TelaJogo;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Motor {

    private final Cobra cobra;
    private final Controle controle;
    private final TelaJogo telaJogo;
    private boolean primeiroMovimento = true;

    public Motor(Cobra cobra, Controle controle, RenderizadorJogo renderizadorJogo, TelaJogo telajogo) {

        this.cobra = cobra;
        this.controle = controle;
        this.telaJogo = telajogo;

        ActionListener atualizarFrame = e -> {

            atualizarEstadoJogo();
            renderizadorJogo.desenharJogo();
            telajogo.repaint();

            if (cobra.isColidiu()) {
                System.out.println("Jogo acabou!");
                ((Timer) e.getSource()).stop();
            }
        };

        Timer timer = new Timer(500, atualizarFrame);

        movimentar();

        timer.start();
    }

    public void atualizarEstadoJogo() {

        if (primeiroMovimento) {
            cobra.setPosicaoInicial();
            primeiroMovimento = false;
        } else
            cobra.mover();
        controle.atualizarDirecaoVelha();
    }


    private void moverDirecao(

            KeyStroke keyStroke0,
            KeyStroke keyStroke1,
            String actionMapKey,
            Action action) {

        this.telaJogo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke0, actionMapKey);
        this.telaJogo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke1, actionMapKey);
        this.telaJogo.getActionMap().put(actionMapKey, action);
    }

    private void movimentar() {
        moverDirecao(Teclas.W.get(), Teclas.UP.get(), "actionUp", controle.getActionUp());
        moverDirecao(Teclas.D.get(), Teclas.RIGHT.get(), "actionRight", controle.getActionRight());
        moverDirecao(Teclas.A.get(), Teclas.LEFT.get(), "actionLeft", controle.getActionLeft());
        moverDirecao(Teclas.S.get(), Teclas.DOWN.get(), "actionDown", controle.getActionDown());
    }
}
