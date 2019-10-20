package model.Factory.unit;

import model.units.Fighter;

public class FighterFactory implements UnitFactory {
    @Override
    public Fighter createUnit() {
        return new Fighter(50, 2, null);
    }
}
