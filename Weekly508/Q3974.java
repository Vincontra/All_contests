package Weekly508;
import java.util.*;
public class Q3974 {
   static class Solution {
        public long maxSum(int[] nums, int k, int mul) {
            Arrays.sort(nums);
            long ans=0;
            int n=nums.length;
            for(int i=n-1;i>=0;i--){
                if(k>0){
                    if(mul>0){
                        // multiply krunga
                        ans+=1L*mul*nums[i];
                        mul--;
                        k--;
                    }else{
                        // add
                        ans+=nums[i];
                        k--;
                        mul--;
                    }
                }else{
                    break;
                }
            }
            return ans;

        }
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
    }
}
