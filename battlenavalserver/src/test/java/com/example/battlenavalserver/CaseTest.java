package com.example.battlenavalserver;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Ship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    void constructorShouldSetInitialValues() {
        Case testCase = new Case(1, 2);

        assertEquals(1, testCase.getLign());
        assertEquals(2, testCase.getColumn());
        assertFalse(testCase.isHit());
        assertNull(testCase.getShip());
        assertEquals(Case.ShotResult.NONE, testCase.getShotResult());
    }

    @Test
    void setHitShouldUpdateHitAndShotResult() {
        Case testCase = new Case(1, 2);

        testCase.setHit(true);
        assertTrue(testCase.isHit());
        assertEquals(Case.ShotResult.MISS, testCase.getShotResult());

        Ship testShip = new Ship(Ship.ShipType.AIRCRAFT_CARRIER);
        testCase.setShip(testShip);
        testCase.setHit(true);
        assertTrue(testCase.isHit());
        assertEquals(Case.ShotResult.HIT, testCase.getShotResult());

        testShip.hit();
        testShip.hit();
        testShip.hit();
        testShip.hit();
        testCase.setHit(true);
        assertTrue(testCase.isHit());
        assertEquals(Case.ShotResult.SUNK, testCase.getShotResult());
    }

    @Test
    void setShipAndGetShip() {
        Case testCase = new Case(1, 2);
        assertNull(testCase.getShip());

        Ship testShip = new Ship(Ship.ShipType.AIRCRAFT_CARRIER);
        testCase.setShip(testShip);

        assertNotNull(testCase.getShip());
        assertEquals(testShip, testCase.getShip());
    }

    @Test
    void setAndGetShotResult() {
        Case testCase = new Case(1, 2);
        assertEquals(Case.ShotResult.NONE, testCase.getShotResult());

        testCase.setShotResult(Case.ShotResult.HIT);
        assertEquals(Case.ShotResult.HIT, testCase.getShotResult());
    }
}
