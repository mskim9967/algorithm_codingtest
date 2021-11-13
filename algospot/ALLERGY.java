import java.util.*;
import java.io.*;

public class Main{
    class Person implements Comparable<Person>{
        String name;
        ArrayList<Integer> edibleFood;
        Person(String name) { 
            this.name = new String(name);
            edibleFood = new ArrayList<Integer>();
        }
        public int compareTo(Person o) {
            return this.edibleFood.size() - o.edibleFood.size();
        }
    }
    int n, m, answer;
    Person[] person;
    ArrayList<Integer>[] ediblePerson;

    Main(BufferedReader br) throws Exception {
        answer = Integer.MAX_VALUE;

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        person = new Person[n];
        ediblePerson = new ArrayList[m];

        token = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) 
            person[i] = new Person(token.nextToken());

        for(int i = 0; i < m; i++) { 
            String[] parse = br.readLine().split(" ");
            int k = Integer.parseInt(parse[0]);
            ediblePerson[i] = new ArrayList<Integer>();
            for(int j = 1; j <= k; j++) {
                int l = 0;
                while(!parse[j].equals(person[l].name)) l++;
                person[l].edibleFood.add(i);
            }
        }
        Arrays.sort(person);

        for(int i = 0; i < n; i++) {
            for(int food : person[i].edibleFood)
                ediblePerson[food].add(i);
            Collections.sort(person[i].edibleFood, new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return ediblePerson[b].size() - ediblePerson[a].size();
                }
            });
        }
    }

    void ans(int idx, boolean[] canPersonEat, int pickedFoodNum) {
        if(pickedFoodNum >= answer) return;

        while(idx < n && canPersonEat[idx]) idx++;
        if(idx == n)  {
            answer = pickedFoodNum;
            return;
        }
        for(int food : person[idx].edibleFood) {
            boolean[] cp = canPersonEat.clone();
            for(int p : ediblePerson[food]) cp[p] = true;
            ans(idx + 1, cp, pickedFoodNum + 1);
        }
    }

    void write_answer(BufferedWriter bw) throws Exception {
        ans(0, new boolean[n], 0);
        bw.write(answer + "\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main l = new Main(br);
            l.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}