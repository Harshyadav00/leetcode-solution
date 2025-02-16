import java.util.*;

class Solution {
    public int[] constructDistancedSequence(int n) {
        int ans_size = 2 * n - 1;
        int[] ans_arr = new int[ans_size];
        int[] marked = new int[n + 1];
        Arrays.fill(ans_arr, -1);
        Arrays.fill(marked, -1);
        constructSequence(0, ans_arr, marked, n);
        return ans_arr;

    }

    boolean constructSequence(int ind, int[] ans_arr, int[] marked, int n) {
        if (ind >= 2 * n - 1) {
            return true;
        }
        if (ans_arr[ind] != -1) {
            return constructSequence(ind + 1, ans_arr, marked, n);
        }

        for (int i = n; i > 0; i--) {
            if (marked[i] != -1)
                continue;

            if (i == 1) {
                ans_arr[ind] = 1;
            } else if (ind + i >= n * 2 - 1 || ans_arr[ind + i] != -1) {
                continue;
            } else {
                ans_arr[ind] = i;
                ans_arr[ind + i] = i;
            }
            marked[i] = 1;

            if (constructSequence(ind + 1, ans_arr, marked, n))
                return true;

            if (i == 1) {
                ans_arr[ind] = -1;
            } else {
                ans_arr[ind] = -1;
                ans_arr[ind + i] = -1;
            }
            marked[i] = -1;

        }
        return false;
    }

}