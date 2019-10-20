package model.Factory.unit;

import model.units.SwordMaster;

public class SwordMasterFactory implements UnitFactory {
    @Override
    public SwordMaster createUnit() {
        return new SwordMaster(70, 1, null);
    }
}
