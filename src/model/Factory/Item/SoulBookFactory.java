package model.Factory.Item;

import model.items.SoulBook;

public class SoulBookFactory implements ItemFactory {
    @Override
    public SoulBook createI() {
        return new SoulBook("SoulBook", 15, 2, 4);
    }
}
