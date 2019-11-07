package model.Factory.Item;

import model.items.Spear;

/**
 * Spear creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class SpearFactory implements ItemFactory {
    /**
     * @return desired spear
     */
    @Override
    public Spear createI() {
        return new Spear("Spear", 20, 1, 3);
    }
}
