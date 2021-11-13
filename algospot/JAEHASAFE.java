/*
    Prob
    https://algospot.com/judge/problem/read/JAEHASAFE

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
import java.util.*;
import java.io.*;

public class Main{
    int n;
    String[] str;

    Main() {
        str = new String[51];
    }

    void init(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i <= n; i++) str[i] = br.readLine();
    }

    void clear() {
        Arrays.fill(str, null);
    }

    // Time Complexity: O(str.len + sub.len)
    int subStrPos(String str, String sub) {
        int[] p = new int[sub.length()];
        // init p[] (kmp)
        int match = 0;
        for(int i = 1; i < sub.length(); i++) {
            while(match != 0 && sub.charAt(i) != sub.charAt(match))
                match = p[match - 1];
            if(sub.charAt(i) == sub.charAt(match))
                p[i] = ++match;
        }

        // find subStr's start pos (kmp)
        match = 0;
        for(int i = 1; i < str.length(); i++) {
            while(match != 0 && str.charAt(i) != sub.charAt(match))
                match = p[match - 1];
            if(str.charAt(i) == sub.charAt(match)) 
                if(++match == sub.length()) return i - match + 1;
        }
        return -1;
    }

    // Time Complexity: O(n*(str.len + sub.len))
    int ans() {
        int ret = 0;
        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) // clockwise
                ret += subStrPos(str[i + 1] + str[i + 1],  str[i]);
            else // counterclockwise
                ret += subStrPos(str[i] + str[i], str[i + 1]);
        }
        return ret;
    }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(ans() + "\n");
        clear();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main c = new Main();
        for(int i = 0; i < testCase; i++)
            c.solve(br, bw);
        bw.flush(); bw.close(); br.close();
    }
}