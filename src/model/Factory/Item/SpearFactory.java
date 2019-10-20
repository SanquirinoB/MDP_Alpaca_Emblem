package model.Factory.Item;

import model.items.Spear;

public class SpearFactory implements ItemFactory {
    @Override
    public Spear createI() {
        return new Spear("Spear", 20, 1, 3);
    }
}
