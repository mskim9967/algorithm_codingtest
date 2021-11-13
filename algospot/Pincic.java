import java.io.*;
import java.util.*;

class Picnic {
  class Pair {
    int stud1,stud2;
	  Pair(int a, int b){
	   stud1 = a;
	   stud2 = b;
	  }
  }
	
  int studCnt, pairCnt, possiblePairCnt;
	Vector<Pair> pairVec;
	boolean[] hasPair;
	
	public void init(Scanner sc){
	  pairVec = new Vector<Pair>();
	  studCnt = sc.nextInt();
	  pairCnt = sc.nextInt();
	  possiblePairCnt = 0;
	  hasPair = new boolean[studCnt];
	  for(int i = 0; i < pairCnt; i++)
		  pairVec.add(new Pair(sc.nextInt(), sc.nextInt()));
	}
	
	public void find_possible(Vector<Pair> pairVec, boolean[] hasPair){
    for(int i = 0; i < hasPair.length; i++){
		  if(!hasPair[i])	break;
		  else if(i == hasPair.length - 1) {
		    possiblePairCnt++;
		    return ;
		  }
	  }
	  
	  while(pairVec.size() != 0) {
		  Pair pop = pairVec.get(pairVec.size() - 1);
		  pairVec.remove(pairVec.size() - 1);
		
	  	Vector<Pair> pairVecCp = (Vector)pairVec.clone();
	  	boolean[] hasPairCp = new boolean[studCnt];
	  	for(int i = 0; i < studCnt; i++)
		    hasPairCp[i] = hasPair[i];
		
	   	if(!hasPair[pop.stud1] && !hasPair[pop.stud2]) {
		    hasPairCp[pop.stud1] = hasPairCp[pop.stud2] = true;
		    find_possible(pairVecCp, hasPairCp);
		  }
	  }
	}
	
	public static void main(String[] args) {
	  Picnic pic;
	  int testCase;
    
	  Scanner sc = new Scanner(System.in);
	  testCase = sc.nextInt();
	  for(int i = 0; i < testCase; i++) {
		  pic = new Picnic();
		  pic.init(sc);
		  pic.find_possible(pic.pairVec, pic.hasPair);
		  System.out.println(pic.possiblePairCnt);
	  }
	}
 }
