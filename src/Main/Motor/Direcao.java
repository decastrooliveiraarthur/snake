package Main.Motor;

enum Direcao {

    CIMA(new boolean[]{false, false}),
    DIREITA(new boolean[]{false, true}),
    ESQUERDA(new boolean[]{true, false}),
    BAIXO(new boolean[]{true, true});

    private final boolean[] direcao;

    Direcao(boolean[] direcao) {
        this.direcao = direcao;
    }

    boolean[] get() {
        return direcao.clone();
    }
}
