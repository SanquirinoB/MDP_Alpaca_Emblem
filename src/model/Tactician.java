package model;

import model.Factory.Item.ItemFactory;
import model.Factory.unit.HeroFactory;
import model.Factory.unit.UnitFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Tactician {

    private final String name;
    private List<IUnit> units;
    private PropertyChangeSupport support;
    private int nUnits;
    private boolean heroIsDead;
    private UnitFactory HeroFactory;

    public Tactician(String name) {
        this.name = name;
        units = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        heroIsDead = false;
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
            if (unit.getClass().equals(createUnit(HeroFactory))) {
                heroIsDead();
            }
            unit.setLocation(null);
            nUnits--;
        }
        if (nUnits == 0) {
            noUnitsLeft();
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

    public void PCLNewsAgency() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void noUnitsLeft() {
        support.firePropertyChange("nUnits", this.nUnits, this);
        this.nUnits = 0;
    }

    public void heroIsDead() {
        support.firePropertyChange("heroIsDead", this.heroIsDead, this);
        this.heroIsDead = true;
    }

    public IEquipableItem createItem(ItemFactory typeOfItem) {
        return typeOfItem.createI();
    }

    public IUnit createUnit(UnitFactory typeOfUnit) {
        return typeOfUnit.createUnit();
    }


}
