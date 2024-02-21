public class Item {

    private ItemType type;
    private int value = 0;

    public Item(ItemType type) {
        this.type = type;
    }

    public Item(ItemType type, int value) {
        this.type = type;
        this.value = value;
    }

    public ItemType Type() {
        return type;
    }

    public int Value() {
        return value;
    }

    // Static methods to construct different items

    public static Item Add() {
        return new Item(ItemType.ADD);
    }

    public static Item Sub() {
        return new Item(ItemType.SUB);
    }

    public static Item Mul() {
        return new Item(ItemType.MUL);
    }

    public static Item Div() {
        return new Item(ItemType.DIV);
    }

    public static Item Value(int value) {
        return new Item(ItemType.VALUE, value);
    }
}
