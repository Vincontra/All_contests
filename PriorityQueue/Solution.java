package PriorityQueue;
import java.util.*;
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String>ans=new ArrayList<>();
        HashMap<String,Integer>hm=new HashMap<>();
        for(int i=0;i<words.length;i++){
            hm.put(words[i],hm.getOrDefault(words[i],0)+1);
        }
        PriorityQueue<String>pq=new PriorityQueue<>(
                (a,b)->{
                    if(hm.get(a).equals(hm.get(b))){
                        return b.compareTo(a);
                    }
                    return hm.get(a)-hm.get(b);
                }
        );
        for(String w:hm.keySet()){
            pq.add(w);
            if(pq.size()>k){
                pq.remove();
            }
        }
        while(!pq.isEmpty()){
            ans.add(pq.remove());
        }
        Collections.reverse(ans);
        return ans;
    }
}
// IMPORTANT:
// hm.get(a) and hm.get(b) return Integer objects,
// not primitive int values.

// Using == on objects compares their references (memory addresses),
// not their actual values.

// Example:
//
// Integer x = new Integer(200);
// Integer y = new Integer(200);
//
// x == y        -> false  (different objects)
// x.equals(y)  -> true   (same value)
//

// Java caches Integer objects in the range -128 to 127.
//
// Example:
//
// Integer x = 100;
// Integer y = 100;
//
// x == y -> true
//
// because both references point to the same cached Integer object.
//
// But:
//
// Integer x = 200;
// Integer y = 200;
//
// x == y -> false
//
// because values outside the cache range create different objects.
//

// Therefore, using:
//
// hm.get(a) == hm.get(b)
//
// may appear to work for small frequencies but can fail when
// frequencies become greater than 127.

// This is why one testcase failed in the Top K Frequent Words problem.
// Most frequencies were likely inside the cache range, but one frequency
// exceeded 127, causing == to compare different references.

// Correct ways to compare Integer values:

// 1) Using equals()  (Recommended)
//
// hm.get(a).equals(hm.get(b))
//
// Compares actual values stored inside the Integer objects.

// 2) By unboxing to primitive int
//
// hm.get(a).intValue() == hm.get(b).intValue()
//
// After unboxing, == compares primitive values.

// 3) Using Objects.equals()
//
// Objects.equals(hm.get(a), hm.get(b))
//
// Safe even if one value is null.


// RULE OF THUMB:
//
// Primitive types (int, long, char, boolean)
// -> use ==
//
// Wrapper classes (Integer, Long, Double, etc.)
// -> use equals() or unbox first
//
// Strings
// -> use equals()
// -> never use == for content comparison
//
// Custom Objects
// -> override and use equals()
//
// Use == on objects only when you want to check whether
// both references point to the exact same object in memory.