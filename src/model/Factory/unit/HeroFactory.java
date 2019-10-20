package model.Factory.unit;

import model.units.Hero;

public class HeroFactory implements UnitFactory {
    @Override
    public Hero createUnit() {
        return new Hero(60, 1, null);
    }
}
