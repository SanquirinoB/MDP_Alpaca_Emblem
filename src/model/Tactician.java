package model;

import model.Factory.Item.ItemFactory;
import model.Factory.unit.HeroFactory;
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

/**
 * Player of the game.
 * The Tactician represent and execute the player's actions.
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */
public class Tactician implements PropertyChangeListener {

    private final String name;
    private List<IUnit> units;
    private PropertyChangeSupport support;
    private int nUnits;
    private Field map;
    private boolean heroIsDead;
    private UnitFactory HeroFactory;
    private IUnit selectedUnit;

    /**
     * Who called for create the player is connected to the tactician as listener
     * and we set the Tactician as a future listener of her units.
     *
     * @param name We will set the name as "Player X", where X is a number
     */
    public Tactician(String name) {
        this.name = name;
        units = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        heroIsDead = false;
        HeroFactory = new HeroFactory();
        support = new PropertyChangeSupport(this);
    }

    /**
     * @return The String which represents the name of the Tactician
     */
    public String getName() {
        return name;
    }

    /**
     * @return Return the list of units of the Tactician
     */
    public List<IUnit> getUnits() {
        return units;
    }

    /**
     * It add to the list of units this new unit
     *
     * @param unit
     */
    public void addUnit(IUnit unit) {
        unit.addPropertyChangeListener(this);
        units.add(unit);
    }

    /**
     * This method delete all of the existence of the tactician if he lost and analise
     * why he lost
     */
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

    /**
     * @param x row
     * @param y column
     * @return the unit in the location (x,y)
     */
    public IUnit getUnitIn(int x, int y) {
        for (int i = 0; i < getUnits().size(); i++) {
            IUnit unit = getUnits().get(i);
            if (unit.getLocation().equals(new Location(x, y))) {
                return unit;
            }
        }
        return null;
    }

    /**
     * @param pcl The observable, in this situation, an Unit
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * @param pcl The observable who is going to be ignored
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * It notice to the Controller that this Tactician lost all of his units
     */
    public void noUnitsLeft() {
        support.firePropertyChange("nUnits", this.nUnits, this);
        this.nUnits = 0;
    }

    /**
     * It notice to the Controller that the Hero of the Tactician is dead
     */
    public void heroIsDead() {
        support.firePropertyChange("heroIsDead", this.heroIsDead, this);
        this.heroIsDead = true;
    }

    /**
     * @param typeOfItem The factory which can develop the desired item
     * @return the desired item with standard characteristics
     */
    public IEquipableItem createItem(ItemFactory typeOfItem) {
        return typeOfItem.createI();
    }

    /**
     * @param typeOfUnit The factory which can develop the desired unit
     * @return the desired unit with standard characteristics
     */
    public IUnit createUnit(UnitFactory typeOfUnit) {
        return typeOfUnit.createUnit();
    }

    /**
     * After the Controller call to the constructor of the Tactician, it give them the map of the game
     * with this method
     * @param gameMap current map of the game
     */
    public void giveMap(Field gameMap) {
        map = gameMap;
    }

    /**
     * When a unit died and notice us, the Tactician remove it from the unit's list and notice
     * to the Controller that the dead unit's location is free now
     * @param unitDead
     */
    public void quitUnit(IUnit unitDead){
        Location loc = unitDead.getLocation();
        this.getUnits().remove(unitDead);
        nUnits--;
        support.firePropertyChange("quitUnit", unitDead, loc);
    }

    /**
     * Here we analise the event shared to us from the units and what to do in which case
     * @param PCE the event we catch
     */
    @Override
    public void propertyChange(PropertyChangeEvent PCE) {
        String event = PCE.getPropertyName();
        if (event.equals("newUnitInMap")) {
            support.firePropertyChange(PCE.getPropertyName(), PCE.getOldValue(), PCE.getNewValue());
        }
        else if(event.equals("unitDied")){
            removePropertyChangeListener(this);
            quitUnit((IUnit) PCE.getOldValue());
        }
        else if (event.equals("selected")){
            selectedUnit = (IUnit) PCE.getNewValue();
        }
    }

    /**
     * Developed only for testing
     */
    public void setTestNullUnits() {
        this.units = new ArrayList<>();
    }

    /**
     * Developed only for visual testing
     */
    public void showUnits() {
        List<IUnit> units = this.getUnits();
        int n = units.size();
        for (int i = 0; i < n; i++) {
            System.out.println(units.get(i).getClass() + " | ");
        }
    }
}
