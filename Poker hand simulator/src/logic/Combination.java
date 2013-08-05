package logic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class containing method for selecting n elements from a list.
 * 
 * @author Sebastian Björkqvist
 */
public class Combination {

    /**
     * Creates all possible combinations with k elements from the
     * given collection.
     * 
     * @param <T> Given object
     * @param list Given collection
     * @param k How many elements to put in a combination
     * @return A set of all the combinations. Each combination is represented
     * by a collection.
     * @throws IllegalArgumentException if n is negative or larger than the
     * size of the list.
     * @throws IllegalArgumentException if the list is null.
     */
    public static <T> Collection<Collection<T>> takeKFromList(Collection<T> list, int k) {
        if (list == null) {
            throw new IllegalArgumentException("List can't be null");
        }
        if (k < 0 || k > list.size()) {
            throw new IllegalArgumentException("n can't be negative or larger than the size of the list");
        }
        
        Collection<Collection<T>> allCombinations = new ArrayList<Collection<T>>();
        
        return allCombinations;
    }
    
    /**
     * Calculates how many unique combinations of size k can be made
     * from a collection of n elements.
     * 
     * @param n Size of collection
     * @param k Size of subcollection
     * @return Number of possible combinations
     */
    public static int countNumberOfCombinations(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            return 0;
        }
        
        long numerator = 1;
        
        for (int i = n; i > n - k; i--) {
            numerator *= i;        
        }
        
        long denominator = 1;
        for (int i = 1; i <= k; i++) {
            denominator *= i;
        }
        return (int) (numerator / denominator);
    }
}
