package Interface;

import Camadas.Cobra;
import Camadas.Maca;

/**
 * Recebe as informações (bitboard) das classes Cobra e Maca, sobrepõe os dois bitboards para criar um só,
 * e retorna para que a classe TelaJogo possa desenhá-lo.
 *
 * @author Arthur de Castro Oliveira
 * @version 2.0
 */
public class RenderizadorJogo {

    private final Maca maca;
    private final Cobra cobra;

    public RenderizadorJogo(Cobra cobra, Maca maca) {

        this.maca = maca;
        this.cobra = cobra;
    }

    public long[] getTabuleiroBitboard() {

        long[] tabuleiroBitboard = new long[4];

        for (int i = 0; i < tabuleiroBitboard.length; i++) {
            tabuleiroBitboard[0] = ~(cobra.getBitboard()[0] | maca.getBitboard()[0]);
        }
        return tabuleiroBitboard;
    }
}