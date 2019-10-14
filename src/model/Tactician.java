package model;


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
}
