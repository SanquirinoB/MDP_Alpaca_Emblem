package model.Factory.Item;


import model.items.Axe;

/**
 * Axe creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class AxeFactory implements ItemFactory {

    /**
     * @return desired axe
     */
    @Override
    public Axe createI() {
        return new Axe("Axe", 20, 1, 2);
    }
}
