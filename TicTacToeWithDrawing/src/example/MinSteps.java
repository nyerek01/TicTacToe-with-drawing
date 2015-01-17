package example;

enum MinSteps {

    TicTacToeMinSteps((byte) 4), GomokuMinSteps((byte) 8);//Azert 4 es 8 mert ahol vizsgalva vannak, nincs megengedve az egyenloseg, azt nezzuk hogy nagyobb-e

    private final byte value;

    MinSteps(byte s) {
        value = s;
    }

    byte getValue() {
        return value;
    }
}
