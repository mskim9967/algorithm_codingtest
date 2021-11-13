import java.io.*;
import java.util.*;

public class Karatsuba{
    int[] a, b;
    
    Karatsuba(String s1, String s2) {
        a = new int[s1.length()];
        for(int i = 0; i < s1.length(); i++) 
            a[i] = s1.charAt(s1.length() - 1 - i) - '0';
        b = new int[s2.length()];
        for(int i = 0; i < s2.length(); i++) 
            b[i] = s2.charAt(s2.length() - 1 - i) - '0';
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
        
       for(int i = 0; i < a0.length; i++) a0[i] = a[i];
       for(int i = 0; i < a1.length; i++) a1[i] = a[mid + i];
       for(int i = 0; i < b0.length; i++) b0[i] = b[i];
       for(int i = 0; i < b1.length; i++) b1[i] = b[mid + i];
        
        int[] z0 = karatsuba(a0, b0);
        int[] z2 = karatsuba(a1, b1);
        int[] z1 = minus(karatsuba(plus(a0, a1), plus(b0, b1)), plus(z0, z2));
        return plus(plus(shift(z2, mid * 2), shift(z1, mid)), z0);
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
        return refine(ret);
    }
    
    int[] minus(int[] a, int[] b) {
        int[] ret = (int[]) a.clone();
        for(int i = 0; i < b.length; i++) 
            ret[i] -= b[i];
        return refine(ret);
    }
    
    int[] refine(int[] a) {
        int[] ret = a.clone();
        if(a.length == 0) return ret;
        for(int i = 0; i < ret.length - 1; i++) {
            if(ret[i] > 9) {
                ret[i] -= 10;
                ret[i + 1] += 1;
            }
            else if(ret[i] < 0) {
                ret[i] += 10;
                ret[i + 1] -= 1;
            }
        }

        if(ret[ret.length - 1] > 9) {   //extend
            ret[ret.length - 1] -= 10;
            int[] temp = ret;
            ret = new int[temp.length + 1];
            for(int i = 0; i < temp.length; i++) ret[i] = temp[i];
            ret[ret.length - 1] = 1;
        }

        
        for(int i = ret.length - 1; i >= 0; i--) {
            if(ret[i] != 0) {
                int[] temp = ret;
                ret = new int[i + 1];
                for(int j = 0; j < ret.length; j++) ret[j] = temp[j];
                break;
            }
            if(i == 0 && ret[i] == 0) ret = new int[0];
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
      Karatsuba k = new Karatsuba("893724358493284", "238947328947329");
      int[] ret = k.karatsuba(k.a, k.b);
      
      for(int i = ret.length - 1; i >= 0; i--) 
        System.out.print(ret[i] + " ");
    }
}
//"893724358493284", "238947328947329", 2 1 3 5 5 3 0 4 8 2 7 7 1 3 5 3 2 0 5 5 2 2 3 6 2 3 8 4 3 6 
