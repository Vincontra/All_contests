package BiweeklyContest185;
import java.util.*;
public class LC3965 {
    class Solution {
        public long finishTime(int n, int[][] edges, int[]arr) {
            ArrayList<ArrayList<Integer>>adj=new ArrayList<>();
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<>());
            }
            for(int i=0;i<edges.length;i++){
                int u=edges[i][0];
                int v=edges[i][1];
                adj.get(u).add(v); // bol sakte hai this is directional kind of thing
            }
            return dfs(adj,arr,0);

        }
        public long dfs(ArrayList<ArrayList<Integer>>adj,int arr[],int node){
            if(adj.get(node).size()==0){
                return arr[node];
            }
            long min=Long.MAX_VALUE;
            long max=Long.MIN_VALUE;
            for(int i:adj.get(node)){
                long val=dfs(adj,arr,i);
                min=Math.min(min,val);
                max=Math.max(max,val);
            }
            return 2*max-min+arr[node];
        }
    }
}
