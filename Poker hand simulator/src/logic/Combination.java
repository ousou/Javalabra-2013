package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class containing method for selecting n elements from a list.
 * 
 * @author Sebastian Björkqvist
 */
public class Combination {

    /**
     * Creates all possible combinations with k elements from the
     * given list.
     * 
     * @param <T> Given object
     * @param list Given list
     * @param k How many elements to put in a combination
     * @return A list of all the combinations. Each combination is represented
     * by a list.
     * @throws IllegalArgumentException if n is negative or larger than the
     * size of the list.
     * @throws IllegalArgumentException if the list is null.
     */
    public static <T> List<List<T>> takeKFromList(List<T> list, int k) {
        if (list == null) {
            throw new IllegalArgumentException("List can't be null");
        }
        if (k < 0 || k > list.size()) {
            throw new IllegalArgumentException("n can't be negative or larger than the size of the list");
        }
        if (k == 0) {
            return new ArrayList<List<T>>();
        }
        
        /* The algorithm creates the combinations by picking them out in lexicographic order.
         * 
         * Example:
         * If we were to sort the two-combinations from four elements lexicograpically,
         * we'd get the following list (here each integer i represents the i:th elements
         * of the list, starting from 1):
         * 
         * 12, 13, 14, 23, 24, 34.
         */
        
        /* We create the combinations recursively, starting by creating lists of one element.
         * We then add elements to the combinations, one element at a time, until
         * we reach the desired listsize k.
         */
                
        List<List<T>> currentCombinations = new ArrayList<List<T>>();
        List<List<T>> previousCombinations;
        /* These maps contain information about the largest index
         * in each of the combinations. This is needed so we can keep
         * the lexicographic order, and thus avoid creating the smae
         * combination twice. 
         */
        Map<Integer, Integer> currentLargestIndexInThisCombination = new HashMap<Integer,Integer>();        
        Map<Integer, Integer> previousLargestIndexInThisCombination;
        
        /* Here we create the combinations containing one element.
         * We know we necessarily don't have to make a one-element
         * list of all the elements in the original list, since we
         * intend to keep the lexicographic order.
         */
        for (int i = 0; i < list.size() - k + 1; i++) {
            List<T> combination = new ArrayList<T>();
            combination.add(list.get(i));
            currentCombinations.add(combination);
            currentLargestIndexInThisCombination.put(i,i);
        }
        
        // Looping to add the necessary amount of elements to each list.
        int howManyAdded = 1;        
        while (howManyAdded < k) {
            previousCombinations = currentCombinations;
            previousLargestIndexInThisCombination = currentLargestIndexInThisCombination;
            currentCombinations = new ArrayList<List<T>>();            
            currentLargestIndexInThisCombination = new HashMap<Integer,Integer>();
            
            // We check every previous list and add remaining elements with larger index.  
            for (int i = 0; i < previousCombinations.size(); i++) {
                List<T> combination = previousCombinations.get(i);
                int nextIndex = previousLargestIndexInThisCombination.get(i) + 1;
                for (int j = nextIndex; j < list.size() + howManyAdded - k + 1; j++) {
                    List<T> newCombination = new ArrayList<T>(combination);
                    newCombination.add(list.get(j));
                    currentCombinations.add(newCombination);
                    currentLargestIndexInThisCombination.put(currentCombinations.size() - 1, j);
                }                
            }
            howManyAdded++;
        }        
        
        return currentCombinations;
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
