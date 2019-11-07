package model.Factory.Item;

import model.items.SoulBook;

/**
 * Soul Book creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class SoulBookFactory implements ItemFactory {
    /**
     * @return desired soul book
     */
    @Override
    public SoulBook createI() {
        return new SoulBook("SoulBook", 15, 2, 4);
    }
}
