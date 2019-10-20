package model.Factory.Item;

import model.items.DarkBook;

public class DarkBookFactory implements ItemFactory {
    @Override
    public DarkBook createI() {
        return new DarkBook("Dark Book", 25, 1, 2);
    }
}
