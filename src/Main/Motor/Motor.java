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
    private boolean posicaoInicializada = true;
    private long tempoAnterior = System.nanoTime();

    public Motor(Cobra cobra, Controle controle, RenderizadorJogo renderizadorJogo, TelaJogo telaJogo) {

        this.cobra = cobra;
        this.controle = controle;
        this.telaJogo = telaJogo;

        ActionListener atualizarFrame = e -> {

            long tempoAtual = System.nanoTime();
            double deltaTempo = Math.min(1.0 / Cobra.getVelocidade(), (tempoAtual - tempoAnterior) / 1_000_000_000.0);
            tempoAnterior = tempoAtual;

            if (cobra.isColidiu()) {
                System.out.println("Jogo acabou!");
                ((Timer) e.getSource()).stop();
            } else {
                atualizarEstadoJogo(deltaTempo);
                renderizadorJogo.desenharJogo();
                telaJogo.repaint();
            }
        };

        Timer timer = new Timer(1_000 / 60, atualizarFrame);

        movimentar();

        timer.start();
    }

    public void atualizarEstadoJogo(double deltaTempo) {

        if (posicaoInicializada) {
            cobra.setPosicaoInicial();
            posicaoInicializada = false;
        } else
            cobra.mover(deltaTempo);
        controle.atualizarDirecaoVelha();
    }

    private void moverDirecao(KeyStroke keyStroke0, KeyStroke keyStroke1, String actionMapKey, Action action) {

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