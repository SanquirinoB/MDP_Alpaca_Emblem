package model.Factory.Item;

import model.items.Bow;

public class BowFactory implements ItemFactory {
    @Override
    public Bow createI() {
        return new Bow("Bow", 15, 1, 3);
    }
}
