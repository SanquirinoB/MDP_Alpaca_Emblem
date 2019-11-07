package model.Factory.unit;

import model.units.Cleric;

/**
 * Cleric creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class ClericFactory implements UnitFactory {
    /**
     * @return desired Cleric
     */
    @Override
    public Cleric createUnit() {
        return new Cleric(50, 2, null);
    }
}
