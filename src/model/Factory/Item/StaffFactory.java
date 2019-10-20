package model.Factory.Item;

import model.items.Staff;

public class StaffFactory implements ItemFactory {
    @Override
    public Staff createI() {
        return new Staff("Staff", 20, 1, 1);
    }
}
