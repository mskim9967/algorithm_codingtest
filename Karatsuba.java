import java.io.*;
import java.util.*;

public class Karatsuba{
    int[] a, b;
    
    Karatsuba(long ai, long bi) {
        a = convert_arr(ai);
        b = convert_arr(bi);
    }
 
    int[] karatsuba(int[] a, int[] b) {
        if(isBigger(b, a)) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        
        if(a.length + b.length < 5)
            return multiple(a, b);
            
        int mid = a.length / 2;
        
        int[] a0, a1, b0, b1;
        a0 = new int[mid];
        a1 = new int[a.length - mid];
        b0 = new int[mid < b.length ? mid : b.length];
        b1 = new int[mid < b.length ? b.length - mid : 0];
        
        System.arraycopy(a, 0, a0, 0, a0.length);
        System.arraycopy(a, mid, a1, 0, a1.length);
        System.arraycopy(b, 0, b0, 0, b0.length);
        System.arraycopy(b, mid, b1, 0, b1.length);
        
         System.out.println("a0");
            for(int i = a0.length - 1; i >= 0; i--) 
        System.out.print(a0[i] + " ");
        System.out.println();
         System.out.println("a1");
            for(int i = a1.length - 1; i >= 0; i--) 
        System.out.print(a1[i] + " ");
                System.out.println();
                 System.out.println("b0");
            for(int i = b0.length - 1; i >= 0; i--) 
        System.out.print(b0[i] + " ");
                System.out.println();
                 System.out.println("b1");
            for(int i = b1.length - 1; i >= 0; i--) 
        System.out.print(b1[i] + " ");
                System.out.println();
        
        
        int[] z0 = karatsuba(a0, b0);
        int[] z2 = karatsuba(a1, b1);
        int[] z1 = minus(karatsuba(plus(a0, a1), plus(b0, b1)), plus(z0, z2));
        return plus(plus(shift(z2, mid * 2), shift(z1, mid)), z0);
    }
    
    void swap(int[] a, int[] b) {
        int[] temp = a;
        a = b;
        b = temp;
    }
    
    boolean isBigger(int[] a, int[] b) {
        if(a.length < b.length) return false;
        if(a.length > b.length) return true;
        for(int i = a.length - 1; i >= 0; i--)
            if(a[i] < b[i]) return false;
        return true;
    }
 
    int[] convert_arr(long integer) {
        int len = 0;
        for(long i = integer; i != 0; i /= 10)    len++;
        int ret[] = new int[len];
        long temp = integer;
        for(int i = 0; i < len; temp /= 10, i++)
            ret[i] = (int)(temp % 10);
        return ret;
    }
    
    int[] multiple(int[] a, int[] b) {
        long ai = 0, bi = 0;
        for(int i = 0; i < a.length; i++)
            ai += a[i] * Math.pow(10, i);
        for(int i = 0; i < b.length; i++)
            bi += b[i] * Math.pow(10, i);
        return convert_arr(ai * bi);
    }
    
    int[] plus(int[] a, int[] b) {
        if(isBigger(b, a)) {
            int[] temp = a;
            a = b;
            b = temp;
        }

        int[] ret = (int[]) a.clone();
        
        for(int i = 0; i < b.length; i++) 
            ret[i] += b[i];
        
        for(int i = 0; i < ret.length - 1; i++) {
            if(ret[i] > 9) {
                ret[i] -= 10;
                ret[i + 1] += 1;
            }
        }
                
        if(ret[ret.length - 1] > 9) {   //extend
            ret[ret.length - 1] -= 10;
            int[] temp = ret;
            ret = new int[temp.length + 1];
            System.arraycopy(temp, 0, ret, 0, temp.length);
            ret[ret.length - 1] = 1;
        }
        
        return ret;
    }
    
    int[] minus(int[] a, int[] b) {
        if(isBigger(b, a)) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        int[] ret = (int[]) a.clone();
        
        for(int i = 0; i < b.length; i++) 
            ret[i] -= b[i];
        
        for(int i = 0; i < ret.length; i++) {
            if(ret[i] < 0) {
                ret[i] += 10;
                ret[i + 1] -= 1;
            }
        }
        
        for(int i = ret.length - 1; i >= 0; i--) {
            if(ret[i] != 0) {
                int[] temp = ret;
                ret = new int[i + 1];
                System.arraycopy(temp, 0, ret, 0, ret.length);
                break;
            }   
        }
        return ret;
    }
    
    int[] shift(int[] a, int n) {
        if(a.length == 0)   return new int[0];
        
        int[] ret = new int[a.length + n];
        for(int i = 0; i < a.length; i++)   ret[i + n] = a[i];
        for(int i = 0; i < n; i++)   ret[i] = 0;
        return ret;
    }
    
    
    public static void main(String []args) throws Exception {
      Karatsuba k = new Karatsuba(12345, 987654);
      int[] ret = k.karatsuba(k.a, k.b);
      
      for(int i = ret.length - 1; i >= 0; i--) 
        System.out.print(ret[i] + " ");
    }
}
