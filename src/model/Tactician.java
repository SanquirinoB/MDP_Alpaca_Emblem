package model;

import model.Factory.Item.ItemFactory;
import model.Factory.unit.UnitFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

public class Tactician {

    private final String name;
    private List<IUnit> units;

    public Tactician(String name) {
        this.name = name;
        units = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<IUnit> getUnits() {
        return units;
    }

    public void addUnit(IUnit unit) {
        units.add(unit);
    }

    public void remove() {
        for (int i = 0; i < getUnits().size(); i++) {
            IUnit unit = getUnits().get(i);
            unit.setLocation(null);
        }
    }

    public IUnit getUnitIn(int x, int y) {
        for (int i = 0; i < getUnits().size(); i++) {
            IUnit unit = getUnits().get(i);
            if (unit.getLocation().equals(new Location(x, y))) {
                return unit;
            }
        }
        return null;
    }

    public IEquipableItem createItem(ItemFactory typeOfItem) {
        return typeOfItem.createI();
    }

    public IUnit createUnit(UnitFactory typeOfUnit) {
        return typeOfUnit.createUnit();
    }


}
