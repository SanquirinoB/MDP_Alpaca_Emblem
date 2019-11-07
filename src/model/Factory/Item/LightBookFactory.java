package model.Factory.Item;

import model.items.LightBook;

/**
 * Light Book creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class LightBookFactory implements ItemFactory {

    /**
     * @return desired light book
     */
    @Override
    public LightBook createI() {
        return new LightBook("Light Book", 20, 1, 3);
    }
}
