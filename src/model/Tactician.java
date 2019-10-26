package model;

import model.Factory.Item.ItemFactory;
import model.Factory.unit.UnitFactory;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import javax.swing.plaf.basic.BasicListUI;
import javax.swing.plaf.basic.BasicListUI.PropertyChangeHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Tactician implements PropertyChangeListener {

    private final String name;
    private List<IUnit> units;
    private PropertyChangeSupport support;
    private int nUnits;
    private Field map;
    private boolean heroIsDead;
    private UnitFactory HeroFactory;
    private IUnit selectedUnit;

    public Tactician(String name) {
        this.name = name;
        units = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        heroIsDead = false;
        initializer();
    }

    public String getName() {
        return name;
    }

    public List<IUnit> getUnits() {
        return units;
    }

    public void addUnit(IUnit unit) {
        unit.addPropertyChangeListener(this);
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

    public void initializer() {
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

    public void giveMap(Field gameMap) {
        map = gameMap;
    }

    @Override
    public void propertyChange(PropertyChangeEvent PCE) {
        String event = PCE.getPropertyName();
        if (event.equals("newUnitInMap")) {
            support.firePropertyChange(PCE.getPropertyName(), PCE.getOldValue(), PCE.getNewValue());
        }
    }

    public void showUnits() {
        List<IUnit> units = this.getUnits();
        int n = units.size();
        for (int i = 0; i < n; i++) {
            System.out.println(units.get(i).getClass() + " | ");
        }
    }

    public void setTestNullUnits() {
        this.units = new ArrayList<>();
    }
}
