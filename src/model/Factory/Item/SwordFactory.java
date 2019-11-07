package model.Factory.Item;

import model.items.Sword;

/**
 * Sword creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class SwordFactory implements ItemFactory {
    /**
     * @return desired sword
     */
    @Override
    public Sword createI() {
        return new Sword("Sword", 25, 1, 1);
    }
}
