package model;


import model.map.Location;
import model.units.IUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tactician {
    private String name;
    private List<IUnit> unitList;
    private IUnit currentUnit;
    private Map<String, IUnit> unitPositions = new HashMap<>();

    public Tactician(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }

    /**
     * Add a new unit to the list
     *
     * @param unit
     */
    public void addUnit(IUnit unit) {
        unitList.add(unit);
    }

    /**
     * Remove the unit of the list
     *
     * @param unit
     */
    public void removeUnit(IUnit unit) {
        unitList.remove(unit);
    }

    /**
     * Delete all the reference to the objects associated with the player,
     * the Garbage Collector will delete the objects after this call.
     */
    public void disappear() {
        unitList = null;
        currentUnit = null;
        unitPositions = null;
    }

    public IUnit getCurrentUnit() {
        return currentUnit;
    }


    public IUnit unitIn(int x, int y) {
        return unitPositions.get(String.valueOf(x) + String.valueOf(y));
    }

    public void setCurrentUnit(IUnit unit) {
        currentUnit = unit;
    }
}
