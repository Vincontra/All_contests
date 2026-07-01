package Weekly508;
import java.util.*;
public class Q3977 {
   static class Solution {
        public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int src, int tar) {
            long ans[]={-1,-1};
            ArrayList<ArrayList<Edge>>adj=new ArrayList<>();
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<>());
            }
            for(int edge[]:edges){
                adj.get(edge[0]).add(new Edge(edge[1],edge[2]));
            }
            long dist[][]=new long[n][power+1];
            for(int i=0;i<n;i++){
                for(int j=0;j<=power;j++){
                    dist[i][j]=Long.MAX_VALUE;
                }
            }
            PriorityQueue<Pair>pq=new PriorityQueue<>();
            dist[src][power]=0;
            pq.add(new Pair(src,0L,power));

            while(!pq.isEmpty()){
                Pair curr=pq.remove();
                int node=curr.node;
                long time=curr.time;
                int cpower=curr.power;
                int currP=cpower-cost[node];

                if(time!=dist[node][cpower]||node==tar||cpower<cost[node]){
                    continue;
                }

                for(Edge e:adj.get(node)){
                    if(time+e.time<dist[e.to][currP]){
                        dist[e.to][currP]=time+e.time;
                        pq.add(new Pair(e.to,time+e.time,currP));
                    }
                }
            }
            long bT=Long.MAX_VALUE;
            int bP=-1;
            for(int i=0;i<=power;i++){
                if(bT>dist[tar][i]){
                    bT=dist[tar][i];
                    bP=i;
                }else if(bT==dist[tar][i]){
                    if(i>bP){
                        bP=i;
                    }
                }
            }

            if(bT==Long.MAX_VALUE){
                return ans;
            }
            ans[0]=bT;
            ans[1]=bP;
            return ans;

        }
        static class Pair implements Comparable<Pair>{
            int node;
            long time;
            int power;
            Pair(int node, long time, int power) {
                this.node = node;
                this.time = time;
                this.power = power;
            }
            public int compareTo(Pair o){
                if(this.time!=o.time){
                    return Long.compare(this.time,o.time);
                }
                return Integer.compare(o.power,this.power);
            }

        }
        static class Edge{
            int to;
            int time;
            Edge(int to,int time){
                this.to=to;
                this.time=time;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
    }
}
