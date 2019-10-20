package model.Factory.unit;

import model.units.Archer;

public class ArcherFactory implements UnitFactory {
    @Override
    public Archer createUnit() {
        return new Archer(50, 2, null);
    }
}
