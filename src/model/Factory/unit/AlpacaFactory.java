package model.Factory.unit;

import model.units.Alpaca;

/**
 * Alpaca creator with standard characteristics
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public class AlpacaFactory implements UnitFactory {
    /**
     * @return desired Alpaca
     */
    @Override
    public Alpaca createUnit() {
        return new Alpaca(50, 2, null);
    }
}
