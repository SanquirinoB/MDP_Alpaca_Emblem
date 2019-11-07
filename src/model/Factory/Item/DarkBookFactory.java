package model.Factory.Item;

import model.items.DarkBook;

/**
 * Dark Book creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class DarkBookFactory implements ItemFactory {

    /**
     * @return desired dark book
     */
    @Override
    public DarkBook createI() {
        return new DarkBook("Dark Book", 25, 1, 2);
    }
}
