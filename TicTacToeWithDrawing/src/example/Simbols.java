package example;

enum Simbols {

    Z((byte) 0), X((byte) 1), O((byte) 2);

    private final byte value;//Colort is lehetne ide rakni(?)

    Simbols(byte v) {
        value = v;
    }

    byte getValue() {
        return value;
    }
}
