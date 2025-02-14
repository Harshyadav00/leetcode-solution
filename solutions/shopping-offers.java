import java.util.*;

class Solution {

    // stores all visited states of needs
    Map<List<Integer>, Integer> dp;

    /*
     * Main function call the function with the given variables
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        dp = new HashMap<>();
        return helper(needs, price, special);
    }

    /*
     * List<Integer> needs ---> stores the required value of each item
     * List<Integer> price ---> Strores the base price of each item
     * List<List<Integer>> specials ---> stores the special offer for purchasing of
     * specials[i][j] amount of each product
     */
    int helper(List<Integer> needs, List<Integer> price, List<List<Integer>> specials) {
        int n = needs.size();

        // Base case
        for (int i = 0; i < n; i++) {
            if (needs.get(i) != 0)
                break;
            if (i == n - 1)
                return 0;
        }

        // Return the already stored value ;
        if (dp.containsKey(needs))
            return dp.get(needs);

        int min = Integer.MAX_VALUE;
        // check for all the possible specail offer that can be applied
        for (List<Integer> special : specials) {
            boolean isValidOffer = true;

            // check if this offer can be applied or not
            for (int i = 0; i < n; i++) {
                if (needs.get(i) < special.get(i)) {
                    isValidOffer = false;
                    break;
                }
            }

            // apply the offer and calculate the minimum
            if (isValidOffer) {
                for (int i = 0; i < n; i++) {
                    needs.set(i, needs.get(i) - special.get(i));
                }
                min = Math.min(min, helper(needs, price, specials) + special.get(n));
                for (int i = 0; i < n; i++) {
                    needs.set(i, needs.get(i) + special.get(i));
                }
            }
        }

        // calculate value if bought the item based on base value
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += needs.get(i) * price.get(i);
        }
        min = Math.min(min, sum);

        dp.put(needs, min);
        return min;

    }
}