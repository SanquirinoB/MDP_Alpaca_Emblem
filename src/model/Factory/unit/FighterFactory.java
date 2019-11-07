package model.Factory.unit;

import model.units.Fighter;

/**
 * Fighter creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class FighterFactory implements UnitFactory {
    /**
     * @return desired Fighter
     */
    @Override
    public Fighter createUnit() {
        return new Fighter(50, 2, null);
    }
}
