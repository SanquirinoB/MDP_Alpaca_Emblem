package model;


import model.units.IUnit;

import java.util.List;

public class Tactician {
    private String name;
    private List<IUnit> unitList;
    private IUnit currentUnit;

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

}
