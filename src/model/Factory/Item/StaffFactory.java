package model.Factory.Item;

import model.items.Staff;

/**
 * Staff creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class StaffFactory implements ItemFactory {
    /**
     * @return desired staff
     */
    @Override
    public Staff createI() {
        return new Staff("Staff", 20, 1, 1);
    }
}
