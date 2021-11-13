import java.util.*;
import java.io.*;

public class Main{
    String line;
    Stack<Character> stack;
    final String ob = "([{";
    final String cb = ")]}";

    Main() {
       stack = new Stack<Character>();
    }

    void init(BufferedReader br) throws Exception {
        line = br.readLine(); 
    }

    void clear() {
        stack.clear();
    }

    boolean ans() {
        stack.push(line.charAt(0));
        for(int i = 1; i < line.length(); i++) {
            if(ob.indexOf(line.charAt(i)) != -1) stack.push(line.charAt(i));
            else if(!stack.empty() && cb.indexOf(line.charAt(i)) == ob.indexOf(stack.peek())) stack.pop();
            else return false;
        }
        return stack.empty();
}

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write((ans() ? "YES" : "NO") + "\n");
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