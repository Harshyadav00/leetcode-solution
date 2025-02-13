import java.util.*;

class Solution {

    // DP table to store intermediate results for memoization
    int[][] dp;

    // Modulo to prevent overflow in large results (10^9 + 7)
    private final int MOD = 1_000_000_007;

    /**
     * Finds the total number of special permutations in the given array.
     * A permutation is special if for every adjacent pair (nums[i], nums[i+1]),
     * either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
     * 
     * @param nums An array of distinct positive integers.
     * @return The total count of special permutations, modulo 10^9 + 7.
     */
    public int specialPerm(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][1 << n]; // DP table to store results for each (prevInd, mask) state
        
        // Initialize DP table with -1 to indicate uncomputed states
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Start permutation generation with no previous index and an empty bitmask
        return permute(nums, -1, 0, 0);
    }

    /**
     * Generates all possible special permutations using backtracking and memoization.
     * Uses bitmasking to track selected numbers efficiently.
     * 
     * @param nums    The input array of distinct integers.
     * @param prevInd The index of the previously selected number (-1 if none).
     * @param currInd The count of numbers selected in the current permutation.
     * @param mask    A bitmask representing which numbers are already used.
     * @return The number of valid special permutations.
     */
    private int permute(int[] nums, int prevInd, int currInd, int mask) {
        int n = nums.length;
        
        // Base case: If all numbers are selected, return 1 valid permutation
        if (currInd == n) {
            return 1;
        }

        // If this state (prevInd, mask) has been computed before, return stored value
        if (dp[prevInd + 1][mask] != -1) {
            return dp[prevInd + 1][mask];
        }

        int sum = 0;

        // Try placing each unused number in the next position
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0)  // Skip if nums[i] is already used
                continue;

            // Check if nums[i] satisfies the divisibility condition with the previous number
            if (prevInd == -1 || nums[prevInd] % nums[i] == 0 || nums[i] % nums[prevInd] == 0) {
                sum = (sum + permute(nums, i, currInd + 1, mask | (1 << i))) % MOD;
            }
        }

        // Store computed result for current state in DP table
        return dp[prevInd + 1][mask] = sum;
    }
}
