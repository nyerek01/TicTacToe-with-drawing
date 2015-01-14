package example;

enum MinSteps {

    TicTacToeMinSteps((byte) 4), GomokuMinSteps((byte) 8);

    private final byte value;

    MinSteps(byte s) {
        value = s;
    }

    byte getValue() {
        return value;
    }
}
