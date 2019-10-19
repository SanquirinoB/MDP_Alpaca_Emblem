package model.units;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected DarkBook darkBook;
  protected LightBook lightBook;
  protected SoulBook soulBook;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
    this.field.isConnected();
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 20, 1, 2);
    this.sword = new Sword("Sword", 20, 1, 2);
    this.spear = new Spear("Spear", 20, 1, 2);
    this.staff = new Staff("Staff", 20, 1, 2);
    this.bow = new Bow("Bow", 20, 2, 3);
    this.darkBook = new DarkBook("DarkBook", 20, 1, 3);
    this.lightBook = new LightBook("LightBook", 20, 1, 3);
    this.soulBook = new SoulBook("SoulBook", 20, 1, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  @Override
  public void equipDarkBook() { checkEquippedItem(getDarkBook());}

  @Override
  public DarkBook getDarkBook() { return darkBook; }

  @Override
  public void equipLightBook() {checkEquippedItem(getLightBook());}

  @Override
  public LightBook getLightBook() { return lightBook;  }

  @Override
  public void equipSoulBook() { checkEquippedItem(getSoulBook()); }

  @Override
  public SoulBook getSoulBook() { return soulBook; }

  @Override
  @Test
  public void equipBookTest() {}


  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }


  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Override
  public IUnit getFightExample() {
    IUnit ex = getTestUnit();
    ex.setLocation(field.getCell(2, 2));
    return ex;
  }

  @Override
  @Test
  public void testAttackViable() {
    IUnit other = getTestUnit();
    other.setEquippedItem(getAxe());
    assertFalse(other.attackViable(getFightExample()));
    assertFalse(targetAlpaca.attackViable(other));
  }

  @Override
  public abstract void testAttack();

  @Override
  public abstract void testEquipItem();

  @Override
  @Test
  public void testAddItem() {
    IUnit unit = getTestUnit();
    assertTrue(unit.getItems().isEmpty());
    unit.addItem(getSoulBook());
    assertEquals(getSoulBook(), unit.getItems().get(0));
  }

  @Override
  @Test
  public void testQuitItem() {
    IUnit unit = getTestUnit();
    assertTrue(unit.getItems().isEmpty());
    unit.addItem(getSoulBook());
    assertEquals(getSoulBook(), unit.getItems().get(0));
    unit.quitItem(0);
    assertTrue(unit.getItems().isEmpty());
  }

  @Override
  @Test
  public void testExchange() {
    // Giving: Everyone can give.
    IUnit giver = getTestUnit();
    IUnit receiver = new Alpaca(50, 2, field.getCell(1, 0));
    assertTrue(giver.getItems().isEmpty());
    giver.addItem(getSoulBook());
      giver.exchangeTo(receiver, getSoulBook());
    assertTrue(giver.getItems().isEmpty());
    assertEquals(getSoulBook(), receiver.getItems().get(0));
    // Receiving: Everyone can receive.
    giver = receiver;
    receiver = getTestUnit();
      giver.exchangeTo(receiver, getSoulBook());
    assertTrue(giver.getItems().isEmpty());
    assertEquals(getSoulBook(), receiver.getItems().get(0));
  }

  @Override
  @Test
  public void testHealing() {
    getTestUnit().setCurrentHitPoints(10);
    int health = getTestUnit().getCurrentHitPoints();
    IUnit healer = new Cleric(50, 2, field.getCell(0, 1));
    healer.addItem(staff);
    healer.equipItem(staff);
    healer.healing(getTestUnit());
    assertEquals(health + staff.getPower(), getTestUnit().getCurrentHitPoints());
    getTestUnit().setCurrentHitPoints(0);
    healer.healing(getTestUnit());
    assertEquals(0, getTestUnit().getCurrentHitPoints());
    getTestUnit().setCurrentHitPoints(50);
    healer.healing(getTestUnit());
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    }

}
