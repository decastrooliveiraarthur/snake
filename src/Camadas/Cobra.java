package Camadas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Timer;

public class Cobra {

    private final Tabuleiro tabuleiro;

    private final boolean[] direcaoVelha = new boolean[]{false, true};
    private final boolean[] direcaoNova = new boolean[2];

    private final long[] bitboard = new long[4];

    private int construtor;
    private int destruidor;

    public Cobra(Tabuleiro tabuleiro) {

        this.tabuleiro = tabuleiro;
        construtor = tabuleiro.converterLinhaColunaParaIndice(8, 8);

        Timer timer = new Timer(1000, atualizarPosicao);
        timer.start();
    }

    public void setDirecaoNova(boolean direcao0, boolean direcao1) {

        boolean[] direcaoNova = new boolean[] {direcao0, direcao1};
        boolean[] inversoDirecaoVelha = new boolean[] {!direcaoVelha[0], !direcaoVelha[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            this.direcaoNova[0] = direcaoNova[0];
            this.direcaoNova[1] = direcaoNova[1];
        }
    }

    ActionListener atualizarPosicao = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            int construtorLinha = tabuleiro.converterIndiceParaLinhaColuna(construtor)[0];
            int construtorColuna = tabuleiro.converterIndiceParaLinhaColuna(construtor)[1];

            int direcaoCase = (int)
                    (((direcaoNova[0] ? 1 : 0) << 1) +
                            (direcaoNova[1] ? 1 : 0));

            switch (direcaoCase) {

                case 0:
                    construtorLinha--;
                    break;

                case 1:
                    construtorColuna++;
                    break;

                case 2:
                    construtorColuna--;
                    break;

                case 3:
                    construtorLinha++;
                    break;
            }

            construtor = tabuleiro.converterLinhaColunaParaIndice(construtorLinha, construtorColuna);

            int bloco = construtor >>> 6;
            int bit = construtor & 63;
            long mascara = 1L << bit;
            
            bitboard[bloco] = mascara;

            direcaoVelha[0] = direcaoNova[0];
            direcaoVelha[1] = direcaoNova[1];
        }
    };

    public boolean ocupaCasa(int indice) {

        int bloco = indice >>> 6;
        int bit = indice & 63;
        long mascara = 1L << bit;

        return (bitboard[bloco] & mascara) != 0;
    }
}