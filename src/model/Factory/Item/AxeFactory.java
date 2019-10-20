package model.Factory.Item;


import model.items.Axe;

public class AxeFactory implements ItemFactory {
    @Override
    public Axe createI() {
        return new Axe("Axe", 20, 1, 2);
    }
}
