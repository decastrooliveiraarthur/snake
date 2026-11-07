package Main.Interface;

import java.awt.*;

enum Cores {

    VERDE_MAIS_CLARO(new Color(0x9a9e3f)),
    VERDE_CLARO(new Color(0x496b22)),
    VERDE_ESCURO(new Color(0x0e450b)),
    VERDE_MAIS_ESCURO(new Color(0x1b2a09));

    private final Color cor;

    Cores(Color cor) {
        this.cor = cor;
    }

    public Color get() {
        return cor;
    }
}
