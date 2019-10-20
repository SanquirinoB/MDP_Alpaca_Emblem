package model.Factory.unit;

import model.units.Sorcerer;

public class SorcererFactory implements UnitFactory {
    @Override
    public Sorcerer createUnit() {
        return new Sorcerer(50, 2, null, 3);
    }
}
