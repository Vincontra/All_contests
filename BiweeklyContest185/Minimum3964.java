package BiweeklyContest185;

public class Minimum3964 {
    class Solution {
        public int minLights(int[] arr) {
            int n=arr.length;
            int d[]=new int[n+1];
            boolean check[]=new boolean[n];
            for(int i=0;i<n;i++){
                if(arr[i]>0){
                    int min=Math.max(0,i-arr[i]);
                    int max=Math.min(n-1,i+arr[i]);
                    d[min]++;
                    d[max+1]--;
                }
            }
            int val=0;
            for(int i=0;i<n;i++){
                val+=d[i];
                if(val>0){
                    check[i]=true;
                }
            }

            int ans=0;
            for(int i=0;i<n;){
                if(!check[i]){
                    ans++;
                    i+=3;
                }
                else{
                    i++;
                }
            }
            return ans;
        }
    }
}
