class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder str = new StringBuilder();


        // O(N!) algorithm
        for (int i = 1; i <= 9; i++) {
            if (tryAllPatterns(0, pattern, i, (1 << i), str.append(i)))
                return str.toString();
            str.deleteCharAt(str.length() - 1);
        }

        return "" ;
    }

    private boolean tryAllPatterns(int ind, String pattern, int lastNum, int mask, StringBuilder str) {
        if (ind == pattern.length())
            return true;

        if (pattern.charAt(ind) == 'I') {
            for (int i = lastNum + 1; i <= 9; i++) {
                if ((mask & (1 << i)) == 0) {
                    if (tryAllPatterns(ind + 1, pattern, i, mask ^ (1 << i), str.append(i)))
                        return true;
                    str.deleteCharAt(str.length() - 1);
                }
            }
        } else {
            for (int i = lastNum - 1; i >= 1; i--) {
                if ((mask & (1 << i)) == 0) {
                    if (tryAllPatterns(ind + 1, pattern, i, mask ^ (1 << i), str.append(i)))
                        return true;
                    str.deleteCharAt(str.length() - 1);
                }
            }

        }

        return false;
    }
}