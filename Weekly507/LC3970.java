package Weekly507;
import java.util.*;
public class LC3970 {
    class Solution {
        public int shortestPath(int n, int[][] edges, String s, int k) {
            ArrayList<ArrayList<Pair>>adj=new ArrayList<>();
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<>());
            }
            for(int i=0;i<edges.length;i++){
                int u=edges[i][0];
                int v=edges[i][1];
                int wt=edges[i][2];
                adj.get(u).add(new Pair(v,wt));
            }
            long dis[][]=new long[n][k+1];
            for(int i=0;i<n;i++){
                for(int j=0;j<=k;j++){
                    dis[i][j]=Long.MAX_VALUE;
                }
            }
            PriorityQueue<Pair2>pq=new PriorityQueue<>();
            dis[0][1]=0;
            pq.add(new Pair2(0,0,1));
            while(!pq.isEmpty()){
                Pair2 curr=pq.remove();
                long node=curr.node;
                long wt=curr.wt;
                long cnt=curr.cnt;

                for(Pair i:adj.get((int)node)){
                    long v=i.v;
                    long w=i.w;
                    long nwcnt=0;
                    if(s.charAt((int)node)==s.charAt((int)v)){
                        nwcnt=cnt+1;
                    }else{
                        nwcnt=1;
                    }
                    if(nwcnt>k){
                        continue; // ya se aage badne ka no use
                    }
                    long nd=wt+w;
                    if(nd<dis[(int)v][(int)nwcnt]){
                        dis[(int)v][(int)nwcnt]=nd;
                        pq.add(new Pair2(v,nd,nwcnt));

                    }
                }
            }
            long ans=Long.MAX_VALUE;
            for(int i=1;i<=k;i++){
                ans=Math.min(ans,dis[n-1][i]);
            }

            if(ans==Long.MAX_VALUE){
                return -1;
            }
            return (int)ans;
        }
        public static class Pair{
            long v;
            long w;
            public Pair(long v,long w){
                this.v=v;
                this.w=w;
            }
        }
        static class Pair2 implements Comparable<Pair2>{
            long node;
            long wt;
            long cnt;
            public Pair2(long node,long wt,long cnt){
                this.node=node;
                this.wt=wt;
                this.cnt=cnt;
            }
            public int compareTo(Pair2 o){
                return Long.compare(this.wt,o.wt);
            }
        }
    }
}
