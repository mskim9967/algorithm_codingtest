import java.util.*;

class Solution {    
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }
    
    int dfs(int[] nums, int sum, int i, int t) {
        if(i == nums.length) 
            return sum == t ? 1 : 0;
        return dfs(nums, sum + nums[i], i + 1, t) 
            + dfs(nums, sum - nums[i], i + 1, t);
    }
}