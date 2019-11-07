package model.Factory.unit;

import model.units.SwordMaster;

/**
 * Sword Master creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class SwordMasterFactory implements UnitFactory {
    /**
     * @return desired Sword Master
     */
    @Override
    public SwordMaster createUnit() {
        return new SwordMaster(70, 1, null);
    }
}
