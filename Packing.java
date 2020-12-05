import java.util.*;
import java.io.*;

public class Main{
    class Item{
        String name;
        int vol, deg;
        Item(String name, int vol, int deg) {
            this.name = name; this.vol = vol; this.deg = deg;
        }
    }
    
    int itemCnt, bagVol;
    Item[] item;
    int[][] cache;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        itemCnt = Integer.parseInt(parse[0]);
        bagVol = Integer.parseInt(parse[1]);
        
        item = new Item[itemCnt]; 
        for(int i = 0; i < itemCnt; i++) {
            parse = br.readLine().split(" ");
            item[i] = new Item(parse[0], Integer.parseInt(parse[1]), Integer.parseInt(parse[2]));
        }
        
        cache = new int[bagVol + 1][itemCnt];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[i].length; j++) 
                cache[i][j] = -1;
    }

    int maxDeg(int leftVol, int idx) {
        if(idx == itemCnt)  return 0;
        if(cache[leftVol][idx] != -1)   return cache[leftVol][idx];
        
        int ret = maxDeg(leftVol, idx + 1);
        if(item[idx].vol <= leftVol)
            ret = Math.max(ret, item[idx].deg + maxDeg(leftVol - item[idx].vol, idx + 1));
        
        return cache[leftVol][idx] = ret;
    }
    
    void find_takenItems(Vector<String> takenItem, int leftVol, int idx) {
        if(idx == itemCnt)  return ;
        
        if(maxDeg(leftVol, idx) != maxDeg(leftVol, idx + 1)) {
            takenItem.add(item[idx].name);
            leftVol -= item[idx].vol;
        }
        find_takenItems(takenItem, leftVol, idx + 1);
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        Vector<String> takenItem = new Vector<String>();
        
        bw.write(maxDeg(bagVol, 0) + " ");
        find_takenItems(takenItem, bagVol, 0);
        bw.write(takenItem.size() + "\n");
        for(int i = 0; i < takenItem.size(); i++)
            bw.write(takenItem.get(i) + "\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main p = new Main(br);
            p.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
