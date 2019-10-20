package model.Factory.Item;

import model.items.LightBook;

public class LightBookFactory implements ItemFactory {

    @Override
    public LightBook createI() {
        return new LightBook("Light Book", 20, 1, 3);
    }
}
