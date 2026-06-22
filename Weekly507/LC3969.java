package Weekly507;

public class LC3969 {
    class Solution {
        public int countValidSubarrays(int[] nums, int x) {
            long pfx[]=new long[nums.length];
            pfx[0]=nums[0];
            for(int i=1;i<nums.length;i++){
                pfx[i]=pfx[i-1]+nums[i];
            }
            int ans=0;
            StringBuilder sb=new StringBuilder(); // har baar naya object banega to memory limit i guess same object ko point krte just diff val
            for(int i=0;i<nums.length;i++){
                for(int j=i;j<nums.length;j++){
                    long sum=pfx[j];
                    if(i>0){
                        sum-=pfx[i-1];
                    }
                    sb.append(sum);
                    if(sb.charAt(0)==x+'0'&&sb.charAt(sb.length()-1)==x+'0'){
                        ans++;
                    }
                    sb.setLength(0);
                }
            }
            return ans;

        }
    }
}
