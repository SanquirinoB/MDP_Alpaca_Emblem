package model.Factory.unit;

import model.units.Sorcerer;

/**
 * Sorcerer creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class SorcererFactory implements UnitFactory {
    /**
     * @return desired Sorcerer
     */
    @Override
    public Sorcerer createUnit() {
        return new Sorcerer(50, 2, null, 3);
    }
}
