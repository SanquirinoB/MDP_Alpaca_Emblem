package model.Factory.unit;

import model.units.Archer;

/**
 * Archer creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class ArcherFactory implements UnitFactory {
    /**
     * @return desired archer
     */
    @Override
    public Archer createUnit() {
        return new Archer(50, 2, null);
    }
}
