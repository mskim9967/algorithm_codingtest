import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville) pq.add(s);
        
        while(pq.size() != 0) {
            int pop = pq.poll();
            if(pop >= K) return ans;
            if(pq.size() == 0) break;
            pq.add(pop + pq.poll() * 2);
            ans++;
        }
        return -1;
    }
}
