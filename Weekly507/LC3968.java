package Weekly507;

public class LC3968 {
    class Solution {
        public int maxDistance(String moves) {
            int val1=0;
            int val2=0;
            int val3=0;
            int val4=0;
            for(int i=0;i<moves.length();i++){
                char c=moves.charAt(i);
                if(c=='U'){
                    val1++;
                }
                if(c=='D'){
                    val2++;
                }
                if(c=='R'){
                    val3++;
                }
                if(c=='L'){
                    val4++;
                }
            }
            int blank=moves.length()-(val1+val2+val3+val4);
            int diff1=Math.abs(val1-val2);
            int diff2=Math.abs(val3-val4);
            return diff1+diff2+blank;




        }
    }
}
