package model.Factory.unit;

import model.units.Cleric;

public class ClericFactory implements UnitFactory {
    @Override
    public Cleric createUnit() {
        return new Cleric(50, 2, null);
    }
}
