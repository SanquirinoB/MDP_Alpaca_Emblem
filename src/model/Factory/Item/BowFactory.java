package model.Factory.Item;

import model.items.Bow;

/**
 * Bow creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class BowFactory implements ItemFactory {
    /**
     * @return desired bow
     */
    @Override
    public Bow createI() {
        return new Bow("Bow", 15, 1, 3);
    }
}
