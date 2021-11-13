import java.io.*;
import java.util.*;

class Fence{
  
    int[] height;
  
    Fence(Scanner scanner) {
        height = new int[scanner.nextInt()];
        
        for(int i = 0 ; i < height.length; i++)
            height[i] = scanner.nextInt();
    }
    
    int max(int a, int b) { return a > b ? a : b; }
    int min(int a, int b) { return a < b ? a : b; }
    
    int calc_maxArea(int left, int right) {
        int maxArea, center = (left + right) / 2;
        
        if(left == right)
            return height[center];
            
        maxArea = max(calc_maxArea(left, center), calc_maxArea(center + 1, right));
        
        int lm = center, rm = center + 1, minHeight = min(height[rm], height[lm]);
        while(true) {
            int area = minHeight * (rm - lm + 1);
            maxArea = max(area, maxArea);
            
            if(rm == right && lm == left)
                break;
                
            if(rm == right) 
                minHeight = min(minHeight, height[--lm]);
            else if(lm == left)
                minHeight = min(minHeight, height[++rm]);
            else if(height[lm - 1] > height[rm + 1]) 
                minHeight = min(minHeight, height[--lm]);
            else
                minHeight = min(minHeight, height[++rm]);
        }
        
        return maxArea;
    }

   
     public static void main(String []args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        for(int i = 0; i < testCase; i++) {
            Fence fence = new Fence(scanner);
            System.out.println(fence.calc_maxArea(0,fence.height.length - 1));
        }
     }
}
