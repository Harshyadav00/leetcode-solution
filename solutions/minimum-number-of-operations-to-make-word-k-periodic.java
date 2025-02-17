import java.util.*;

class Solution {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();

        // stores the unique string
        Map<String, Integer> uniqueString = new HashMap<>();
        int maxFreq = 0;
        int ind = 0;
        while (ind < n) {
            String tempString = word.substring(ind, ind + k);
            int val = uniqueString.getOrDefault(tempString, 0) + 1;
            uniqueString.put(tempString, val);
            maxFreq = Math.max(maxFreq, val);
            ind += k;
        }

        // subtract maxFreq from total no. of substring of size k 
        return (n / k) - maxFreq;
    }

}