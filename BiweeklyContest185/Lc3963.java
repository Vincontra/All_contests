package BiweeklyContest185;

public class Lc3963 {
    class Solution {
        public String[] createGrid(int m, int n) {
            String ans[][]=new String[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    ans[i][j]="#";
                }
            }
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(i==0||j==n-1){
                        ans[i][j]=".";
                    }
                }
            }
            String arr[]=new String[m];
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    sb.append(ans[i][j]);
                }
                arr[i]=sb.toString();
                sb=new StringBuilder();
            }
            return arr;
        }
    }
}
