package model.Factory.unit;

import model.units.Alpaca;

public class AlpacaFactory implements UnitFactory {
    @Override
    public Alpaca createUnit() {
        return new Alpaca(50, 2, null);
    }
}
