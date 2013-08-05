package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class CombinationTest {

    public CombinationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testCountNumberOfCombinationsNOrKNegative() {
        assertEquals(0, Combination.countNumberOfCombinations(-3, 3));
        assertEquals(0, Combination.countNumberOfCombinations(3, -3));
    }

    @Test
    public void testCountNumberOfCombinationsKLargerThanN() {
        assertEquals(0, Combination.countNumberOfCombinations(2, 3));
    }

    @Test
    public void testCountNumberOfCombinationsKEqualsN() {
        for (int i = 0; i < 10; i++) {
            int listSize = new Random().nextInt(21);
            assertEquals(1, Combination.countNumberOfCombinations(listSize, listSize));
        }
    }

    @Test
    public void testCountNumberOfCombinationsKZero() {
        for (int i = 0; i < 10; i++) {
            int listSize = new Random().nextInt(21);
            assertEquals(1, Combination.countNumberOfCombinations(listSize, 0));
        }
    }

    @Test
    public void testCountNumberOfCombinationsThreeFromFive() {
        assertEquals(10, Combination.countNumberOfCombinations(5, 3));
    }

    @Test
    public void testCountNumberOfCombinationsTwoFromFive() {
        assertEquals(10, Combination.countNumberOfCombinations(5, 2));
    }

    @Test
    public void testCountNumberOfCombinationsFiveFromSeven() {
        assertEquals(21, Combination.countNumberOfCombinations(7, 5));
    }

    @Test
    public void testCountNumberOfCombinationsFourFromNine() {
        assertEquals(126, Combination.countNumberOfCombinations(9, 4));
    }

    @Test
    public void testCountNumberOfCombinationsSymmetry() {
        int n = new Random().nextInt(11);
        int k = 0;
        while (k < n / 2) {
            assertEquals(Combination.countNumberOfCombinations(n, k), Combination.countNumberOfCombinations(n, n - k));
            k++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeKFromListNullList() {
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeKFromListKNegative() {
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(new ArrayList<Integer>(), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeKFromListKLargerThanList() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(integers, 3);
    }

    @Test
    public void testTakeNoneFromListOfTwo() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(integers, 0);
        assertNotNull(combinations);
        assertTrue(combinations.isEmpty());
    }

    @Test
    public void testTakeOneFromListOfTwo() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(integers, 1);
        Collection<Integer> expectedCombination1 = new ArrayList<Integer>();
        expectedCombination1.add(1);

        Collection<Integer> expectedCombination2 = new ArrayList<Integer>();
        expectedCombination2.add(2);

        assertTrue(combinations.contains(expectedCombination1));
        assertTrue(combinations.contains(expectedCombination2));
        assertEquals(2, combinations.size());
    }

    @Test
    public void testTakeTwoFromListOfTwo() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(integers, 2);
        Collection<Integer> expectedResult1 = new ArrayList<Integer>();
        expectedResult1.add(1);
        expectedResult1.add(2);

        assertTrue(combinations.contains(expectedResult1));
        assertEquals(1, combinations.size());
    }

    @Test
    public void testTakeThreeFromListOfFive() {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        Collection<Collection<Integer>> combinations = Combination.takeKFromList(integers, 3);

        Collection<Integer> expectedCombination1 = new ArrayList<Integer>();
        expectedCombination1.add(1);
        expectedCombination1.add(2);
        expectedCombination1.add(3);

        Collection<Integer> expectedCombination2 = new ArrayList<Integer>();
        expectedCombination2.add(1);
        expectedCombination2.add(2);
        expectedCombination2.add(4);

        Collection<Integer> expectedCombination3 = new ArrayList<Integer>();
        expectedCombination3.add(1);
        expectedCombination3.add(2);
        expectedCombination3.add(5);

        Collection<Integer> expectedCombination4 = new ArrayList<Integer>();
        expectedCombination4.add(1);
        expectedCombination4.add(3);
        expectedCombination4.add(4);

        Collection<Integer> expectedCombination5 = new ArrayList<Integer>();
        expectedCombination5.add(1);
        expectedCombination5.add(3);
        expectedCombination5.add(5);

        Collection<Integer> expectedCombination6 = new ArrayList<Integer>();
        expectedCombination6.add(1);
        expectedCombination6.add(4);
        expectedCombination6.add(5);

        Collection<Integer> expectedCombination7 = new ArrayList<Integer>();
        expectedCombination7.add(2);
        expectedCombination7.add(3);
        expectedCombination7.add(4);

        Collection<Integer> expectedCombination8 = new ArrayList<Integer>();
        expectedCombination8.add(2);
        expectedCombination8.add(3);
        expectedCombination8.add(5);

        Collection<Integer> expectedCombination9 = new ArrayList<Integer>();
        expectedCombination9.add(2);
        expectedCombination9.add(4);
        expectedCombination9.add(5);

        Collection<Integer> expectedCombination10 = new ArrayList<Integer>();
        expectedCombination10.add(3);
        expectedCombination10.add(4);
        expectedCombination10.add(5);

        assertTrue(combinations.contains(expectedCombination1));
        assertTrue(combinations.contains(expectedCombination2));
        assertTrue(combinations.contains(expectedCombination3));
        assertTrue(combinations.contains(expectedCombination4));
        assertTrue(combinations.contains(expectedCombination5));
        assertTrue(combinations.contains(expectedCombination6));
        assertTrue(combinations.contains(expectedCombination7));
        assertTrue(combinations.contains(expectedCombination8));
        assertTrue(combinations.contains(expectedCombination9));
        assertTrue(combinations.contains(expectedCombination10));
        assertEquals(10, combinations.size());
    }

    @Test
    public void takeKFromListRandom() {
        for (int i = 0; i < 10; i++) {
            List<Integer> integers = new ArrayList<Integer>();
            Random random = new Random();

            int listSize = random.nextInt(11);
            int k = random.nextInt(listSize + 1);

            for (int j = 1; j <= listSize; j++) {
                integers.add(j);
            }
            Collection<Collection<Integer>> result = Combination.takeKFromList(integers, k);

            // Checking that we have the right amount of combinations
            assertEquals("Wrong amount of combinations with n = " + listSize + " and k = " + k, Combination.countNumberOfCombinations(listSize, k), result.size());

            // Checking that all combinations are of the right size            
            for (Collection<Integer> combination : result) {
                assertEquals("Combination " + combination + " is of wrong size when n = "
                        + listSize + " and k = " + k, k, combination.size());
            }
        }
    }
}