package Const;

public enum Product {
    HOTELS("hotels"),
    FLIGHTS("flights"),
    TOURS("tours"),
    TRANSFER("transfer"),
    VISA("visa");

    public final String name;

    Product(String name) {
        this.name = name;
    }
}
