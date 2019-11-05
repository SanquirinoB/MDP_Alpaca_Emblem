package model.itemFactory;

import model.Factory.unit.*;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitFactoryTest {
    UnitFactory alpacaFact = new AlpacaFactory();
    UnitFactory archerFact = new ArcherFactory();
    UnitFactory clericFact = new ClericFactory();
    UnitFactory fighterFact = new FighterFactory();
    UnitFactory heroFact = new HeroFactory();
    UnitFactory sorcererFact = new SorcererFactory();
    UnitFactory swordMasterFact = new SwordMasterFactory();

    @Test
    void alpacaMakeAlpacas(){
        IUnit alpaca = new Alpaca(50, 1, null);
        IUnit desiredAlpaca = alpacaFact.createUnit();
        assertEquals(alpaca.getClass(), desiredAlpaca.getClass());
    }

    @Test
    void archerMakeArchers(){
        IUnit archer = new Archer(50, 1, null);
        IUnit desiredArcher = archerFact.createUnit();
        assertEquals(archer.getClass(), desiredArcher.getClass());
    }

    @Test
    void clericMakeCleric(){
        IUnit cleric = new Cleric(50, 1, null);
        IUnit desiredCleric = clericFact.createUnit();
        assertEquals(cleric.getClass(), desiredCleric.getClass());
    }

    @Test
    void fighterMakeFighters(){
        IUnit fighter = new Fighter(50, 1, null);
        IUnit desiredFighter = fighterFact.createUnit();
        assertEquals(fighter.getClass(), desiredFighter.getClass());
    }

    @Test
    void heroMakeHeroes(){
        IUnit hero = new Hero(50, 1, null);
        IUnit desiredHero = heroFact.createUnit();
        assertEquals(hero.getClass(), desiredHero.getClass());
    }

    @Test
    void sorcererMakeSorcerers(){
        IUnit sorcerer = new Sorcerer(50, 1, null, 3);
        IUnit desiredSorcerer = sorcererFact.createUnit();
        assertEquals(sorcerer.getClass(), desiredSorcerer.getClass());
    }

    @Test
    void swordMasterMakeSwordMasters(){
        IUnit swordMaster = new SwordMaster(50, 1, null);
        IUnit desiredSwordMaster = swordMasterFact.createUnit();
        assertEquals(swordMaster.getClass(), desiredSwordMaster.getClass());
    }

}
