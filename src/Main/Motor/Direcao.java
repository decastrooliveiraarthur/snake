package Main.Motor;

public enum Direcao {

    CIMA(new boolean[]{false, false}),
    DIREITA(new boolean[]{false, true}),
    ESQUERDA(new boolean[]{true, false}),
    BAIXO(new boolean[]{true, true});

    private final boolean[] direcao;

    Direcao(boolean[] direcao) {
        this.direcao = direcao;
    }

    public boolean[] get() {
        return direcao.clone();
    }
}
