class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> uniqueComb = new ArrayList<>();
        permute(0, nums, uniqueComb);
        return uniqueComb;
    }

    /*
        This function genrates all the possible permutations of list of numbers
        @param int ind --- current ind
        @param int[] nums --- current permutations
        @param List<List<Integer>> uniqueComb --- contains all the unique combinations
        @return void -- updates uniquqeComb 
    */
    private void permute(int i, int[] nums, List<List<Integer>> uniqueComb) {
        int n = nums.length;

        // base case
        if (i == n) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums)
                temp.add(num);
            uniqueComb.add(temp);
            return;
        }

        // genrates all the possible pemutions from i t0 n
        for (int s = i; s < n; s++) {
            // check for duplicate
            if (isDuplicate(nums, i, s))
                continue;
            swap(nums, s, i);
            permute(i + 1, nums, uniqueComb);
            swap(nums, s, i);
        }
    }

    /*
        This fuctions checks for duplicate item for nums[ind] from start to ind-1
        @Param int[] nums 
        @Param int start --- starting index
        @Param int ind ---  original index
    */
    private boolean isDuplicate(int[] nums, int start, int ind) {
        for (int j = start; j < ind; j++) {
            if (nums[j] == nums[ind])
                return true;
        }
        return false;
    }

    /*
        This funcctions swaps two num
    */
    private void swap(int[] nums, int start, int i) {
        int t = nums[start];
        nums[start] = nums[i];
        nums[i] = t;
    }

}