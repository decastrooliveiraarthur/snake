# 🐍 Snake com Bitboards

Jogo da cobrinha em **Java puro (Swing)**, com o estado do jogo representado por **bitboards**. Projeto pessoal para
estudar Java e operações bit a bit, feito no ritmo que dá, de forma descontraída.

## Por que bitboards?

Em vez de uma matriz de objetos, cada camada do jogo (cobra, maçã) é guardada como uma sequência de bits: `1` para casa
ocupada, `0` para vazia. Escolhi essa abordagem para praticar manipulação de bits e ver como um estado de jogo pode ser
representado de forma compacta.

O tabuleiro tem 16 × 16 = 256 casas, então cada camada usa um `long[4]` (4 blocos de 64 bits):

```java
int indice  = linha * 16 + coluna;
int bloco   = indice >>> 6;           // qual dos 4 longs
long mascara = 1L << (indice & 63);   // qual bit dentro dele

bitboard[bloco] |= mascara;           // ocupar a casa
(bitboard[bloco] & mascara) != 0      // consultar a casa
```

A colisão da cobra com o próprio corpo é um simples `&` no bitboard, sem percorrer lista nenhuma. O renderizador
percorre as 256 casas e consulta os bitboards para decidir a cor de cada uma.

## O que já funciona

- Tabuleiro 16 × 16 em janela redimensionável, mantendo a proporção
- Movimento automático da cobra (a cada 500 ms)
- Controle por **WASD** ou **setas**, com bloqueio de retorno de 180°
- Bordas "cíclicas": a cobra sai por um lado e entra pelo outro
- Detecção de colisão com o próprio corpo

## Próximos passos

- [ ] Apagar a cauda ao mover
- [ ] Gerar e comer maçãs
- [ ] Crescer ao comer
- [ ] Pontuação e tela de fim de jogo
- [ ] Reiniciar a partida

## O que estou praticando

- **Java:** classes internas, enums, records, interfaces funcionais
- **Swing/AWT:** `JFrame`, `JPanel`, `Timer`, `Action` e `InputMap` para o teclado
- **Bitboards:** operações `<<`, `>>>`, `&`, `|` e conversão entre índice, posição e bit
- **Organização:** separação em camadas (estado, motor, interface)

## Como rodar

Requer **JDK 16+** (o código usa `record`).

A partir da pasta que contém `Main/`:

```bash
javac -d out $(find Main -name '*.java')
java -cp out Main.Main
```

Ou abra no IntelliJ IDEA e execute a classe `Main.Main`.

## Controles

| Ação     | Teclas     |
|----------|------------|
| Cima     | `W` ou `↑` |
| Esquerda | `A` ou `←` |
| Baixo    | `S` ou `↓` |
| Direita  | `D` ou `→` |

## Estrutura

```
Main/
├── Main.java                 # ponto de entrada
├── Tabuleiro.java            # constantes (tamanho do tabuleiro, pixels)
├── Conversor.java            # conversões índice ⇄ posição ⇄ bitboard
├── Camadas/
│   ├── Cobra.java            # bitboard e movimento da cobra
│   └── Maca.java             # bitboard da maçã
├── Motor/
│   ├── Motor.java            # loop do jogo (Timer do Swing)
│   ├── Controle.java         # leitura do teclado e direção
│   └── Teclas.java           # teclas usadas
└── Interface/
    ├── Janela.java           # JFrame
    ├── TelaJogo.java         # JPanel que exibe a imagem do jogo
    ├── RenderizadorJogo.java # desenha as camadas em um BufferedImage
    └── Cores.java            # paleta de verdes
```

## Autor

**Arthur de Castro Oliveira**
[GitHub](https://github.com/decastrooliveiraarthur)
