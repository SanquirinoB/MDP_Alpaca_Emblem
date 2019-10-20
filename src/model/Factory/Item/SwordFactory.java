package model.Factory.Item;

import model.items.Sword;

public class SwordFactory implements ItemFactory {
    @Override
    public Sword createI() {
        return new Sword("Sword", 25, 1, 1);
    }
}
