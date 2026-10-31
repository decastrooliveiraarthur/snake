package Camadas;

import java.util.Arrays;

public class Cobra {

    private final boolean[] direcaoVelha = new boolean[]{false, true};
    private final boolean[] direcaoNova = new boolean[]{false, true};
    private final long[] bitboard = new long[4];
    private int construtor = Conversor.linhaColunaParaIndice(new Conversor.Posicao(8, 8));
    private int destruidor;

    public Cobra() {
    }

    public void mover() {

        int construtorLinha = Conversor.indiceParaLinhaColuna(construtor).linha();
        int construtorColuna = Conversor.indiceParaLinhaColuna(construtor).coluna();

        int direcaoCase = (((direcaoNova[0] ? 1 : 0) << 1) + (direcaoNova[1] ? 1 : 0));

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

        construtor = Conversor.linhaColunaParaIndice(new Conversor.Posicao(construtorLinha, construtorColuna));

        int bloco = construtor >>> 6;
        int bit = construtor & 63;
        long mascara = 1L << bit;

        bitboard[bloco] = bitboard[bloco] | mascara;

        direcaoVelha[0] = direcaoNova[0];
        direcaoVelha[1] = direcaoNova[1];
    }

    public void setDirecaoNova(boolean direcao0, boolean direcao1) {

        boolean[] direcaoNova = new boolean[]{direcao0, direcao1};
        boolean[] inversoDirecaoVelha = new boolean[]{!direcaoVelha[0], !direcaoVelha[1]};

        if (!Arrays.equals(direcaoNova, inversoDirecaoVelha)) {

            this.direcaoNova[0] = direcaoNova[0];
            this.direcaoNova[1] = direcaoNova[1];
        }
    }

    public long[] getBitboard() {
        return bitboard;
    }
}