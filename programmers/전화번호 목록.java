import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> hash = new HashMap<>();
        
        for(int i = 0; i < phone_book.length; i++) 
            hash.put(phone_book[i], i);
        
        for(String str : phone_book)
            for(int i = 1; i < str.length(); i++)
                if(hash.get(str.substring(0, i)) != null) return false;
        
        return true;
    }
}