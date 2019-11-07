package model.Factory.unit;

import model.units.Hero;

/**
 * Hero creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class HeroFactory implements UnitFactory {
    /**
     * @return desired HERO ORU MIGHTOOOOOOOO
     */
    @Override
    public Hero createUnit() {
        return new Hero(60, 1, null);
    }
}
