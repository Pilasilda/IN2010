import java.util.*;

class Arraysort{
  void arraysort(int[] arr){
    Arrays.sort(arr);
  }

  public int[] generate(int tall){
    Random r = new Random();
    int[] nxt = new int[tall];

    for(int i = 0; i < nxt.length; i++){
      nxt[i] = r.nextInt(tall);
    }
    return nxt;
  }

  public void printArray(int array[]){
    int n = array.length;
    for(int i= 0; i < n; i++){
      System.out.print(array[i] + " ");
    }
    System.out.println();
   }
}
