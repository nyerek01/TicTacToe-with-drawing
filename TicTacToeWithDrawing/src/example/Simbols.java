package example;

enum Simbols {

    Z((byte) 0), X((byte) 1), O((byte) 2);

    private final byte value;//Colort ide kene tenni?

    Simbols(byte v) {
        value = v;
    }

    byte getValue() {
        return value;
    }
}
