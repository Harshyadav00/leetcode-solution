
class Solution {
    private int K;
    private char[] CHAR_ARRAY = new char[] { 'a', 'b', 'c' };

    public String getHappyString(int n, int k) {
        
        // O((2^n)*k) solution
        // K = k;
        // StringBuilder str = new StringBuilder();
        // kthHappyString(0, n, str, ' ');
        // return str.toString();
        
        // O(n) solution
        if (k > 3 * (1 << (n - 1)))
            return "";
            StringBuilder str = new StringBuilder();
        int prevChar = -1;
        for (int i = 1; i <= n; i++) {
            int chr;
            if (prevChar == 0)
                chr = 1;
            else
                chr = 0;
            while (k > (1 << (n - i))) {
                if (chr + 1 == prevChar) {
                    chr += 2;
                } else {
                    chr++;
                }
                k -= (1 << (n - i));
            }
            prevChar = chr;
            str.append(CHAR_ARRAY[chr]);
        }

        return str.toString();

    }

    private void kthHappyString(int ind, int n, StringBuilder str, char prevChar) {
        if (n == ind) {
            K--;
            return;
        }

        for (char ch : CHAR_ARRAY) {
            if (ch == prevChar)
                continue;

            kthHappyString(ind + 1, n, str, ch);
            if (K == 0) {
                str.insert(0, ch);
                break;
            }
        }
    }
}