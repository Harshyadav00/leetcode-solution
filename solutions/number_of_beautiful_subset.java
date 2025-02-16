import java.util.*;

class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        // O(2^n) solution
        return countBeautifulSubsets(nums, 0, k, new HashMap<>());
    }


    int countBeautifulSubsets(int[] nums, int index, int k, Map<Integer, Integer> subsetContent) {
        if (index == nums.length) {

            if (subsetContent.size() > 0) {
                return 1;
            }
            return 0;
        }

        int validSubsetCount = 0;

        // include the current number if it doesn't create invalid subset
        if (!subsetContent.containsKey(nums[index] + k) && !subsetContent.containsKey(nums[index] - k)) {
            subsetContent.put(nums[index], subsetContent.getOrDefault(nums[index], 0) + 1);
            validSubsetCount += countBeautifulSubsets(nums, index + 1, k, subsetContent);
            subsetContent.put(nums[index], subsetContent.get(nums[index]) - 1);
            if (subsetContent.get(nums[index]) == 0)
                subsetContent.remove(nums[index]);
        }

        // exclude the current number
        validSubsetCount += countBeautifulSubsets(nums, index + 1, k, subsetContent);

        return validSubsetCount;
    }
}