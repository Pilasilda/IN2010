import java.util.*;

public class Insertion{

public void insertionArray(int[] array){
  int tall = array.length;

  for(int i =1; i < tall; i++){
    int test = array[i];
    int j = i -1;

    //Flytter alle elementer av array som er storre enn test til en posisjon for deres nåværende posisjon
    while(j>=0 && array[j] > test){
      array[j+1] = array[j];
      j = j-1;
    }
    array[j+1] = test;
  }
}

public int[] generate(int tall){
  Random r = new Random();
  int[] nxt = new int[tall];

  for(int i = 0; i < nxt.length; i++){
    nxt[i] = r.nextInt(tall);
  }
  return nxt;
}

//Print av array
public void printArray(int array[]){
 int n = array.length;
 for(int i= 0; i < n; i++){
   System.out.print(array[i] + " ");
 }
 System.out.println();
}
}
