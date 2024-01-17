package com.example.battlenavalserver;

import com.example.battlenavalserver.model.Case;
import com.example.battlenavalserver.model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void constructorShouldInitializeGridWithCases() {
        Grid testGrid = new Grid();
        Case[][] cases = testGrid.getGrid();

        assertNotNull(cases);
        assertEquals(10, cases.length);
        assertEquals(10, cases[0].length);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertNotNull(cases[i][j]);
                assertEquals(i, cases[i][j].getLign());
                assertEquals(j, cases[i][j].getColumn());
            }
        }
    }

    @Test
    void getGridAndSetGrid() {
        Grid testGrid = new Grid();
        Case[][] newCases = new Case[10][10];
        testGrid.setGrid(newCases);

        assertEquals(newCases, testGrid.getGrid());
    }
}
