class Solution {

    /**
     * Computes the sum of squares of numbers `i` (1 ≤ i ≤ n) that satisfy the special partition condition.
     * A number `i` is valid if the decimal representation of `i * i` can be split into contiguous 
     * substrings such that the sum of those substrings equals `i`.
     * 
     * @param n The upper limit of numbers to check.
     * @return The sum of squares of numbers that satisfy the condition.
     */
    public int punishmentNumber(int n) {
        int ans = 0;

        // Iterate through all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            String squareStr = String.valueOf(square);

            // If a valid partition exists, add the square to the answer
            if (canPartition(squareStr, 0, 0, i)) {
                ans += square;
            }
        }

        return ans;
    }

    /**
     * Recursively checks whether the square of a number can be partitioned into contiguous
     * substrings such that their sum equals the original number.
     * 
     * @param squareStr  The string representation of `i * i`.
     * @param index      The current index in `squareStr` being processed.
     * @param sum        The sum of numbers formed by previous partitions.
     * @param originalNum The original number `i` (whose square is `squareStr`).
     * @return `true` if a valid partition exists, otherwise `false`.
     */
    private static boolean canPartition(String squareStr, int index, int sum, int originalNum) {
        // Base case: If we have used all digits, check if sum matches the original number
        if (index == squareStr.length()) {
            return sum == originalNum;
        }

        int num = 0;

        // Try forming different substrings starting from the current index
        for (int j = index; j < squareStr.length(); j++) {
            num = num * 10 + (squareStr.charAt(j) - '0'); // Convert substring to an integer

            // Recursively check if adding this partition results in a valid sum
            if (canPartition(squareStr, j + 1, sum + num, originalNum)) {
                return true;
            }
        }

        return false;
    }
}
