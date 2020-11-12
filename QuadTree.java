import java.io.*;
import java.util.*;

public class QuadTree{
    class Space {
        Space a, b, c, d;
        char color;
        
        Space(Space a, Space b, Space c, Space d) {
            this.a = a; this.b = b; this.c = c; this.d = d;
        }
        Space(char color) {
            this.color = color;
        }
        void print_vertical_flip() {
            if(color != 0)  System.out.print(color);
            else {
                System.out.print("x");
                c.print_vertical_flip(); d.print_vertical_flip();
                a.print_vertical_flip(); b.print_vertical_flip();
            }
        }
    }
    
    Space space;
    char[] str;
    
    QuadTree(Scanner scanner) {
        String line = scanner.nextLine();
        str = new char[line.length()];
        for(int i = 0; i < line.length(); i++)
            str[i] = line.charAt(i);
    }

    void makeSpace() {
        Stack<Space> stack = new Stack<Space>();
        for(int i = str.length - 1; i >= 0; i--) {
            if(str[i] != 'x')
                stack.push(new Space(str[i]));
            else {
    			Space temp = new Space(stack.pop(), stack.pop(), stack.pop(), stack.pop());
    			stack.push(temp);    
            }
        }
        space = stack.pop();
    }

     public static void main(String []args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int testCase = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < testCase; i++) {
            QuadTree qt = new QuadTree(scanner);
            qt.makeSpace();
            qt.space.print_vertical_flip();
            System.out.println();
        }
     }
}
