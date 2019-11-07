package model.Factory.unit;

import model.units.IUnit;

/**
 * Interface for the units factories
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public interface UnitFactory {
    /**
     * @return desired unit
     */
    IUnit createUnit();
}
