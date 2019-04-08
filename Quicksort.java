import java.util.*;

public class Quicksort{
public int[] quick(int array[], int start, int slutt){
  if(start < slutt){
    int index = partition(array,start, slutt);

    quick(array, slutt, index-1);
    quick(array,index+1, slutt);
  }
  return array;
 }

public int partition(int array[], int start, int slutt){
  int teller = array[slutt];
  int i = (start-1);

  for(int j = start; j< slutt; j++){
    if(array[j] <= teller){
      i++;

      int swap = array[i];
      array[i] = array[j];
      array[j] = swap;
    }
  }

  int swap = array[i+1];
  array[i+1] = array[slutt];
  array[slutt] = swap;
  return i+1;
}

public int[] generate(int len){
  Random r = new Random();
  int[] nxt = new int[len];

  for(int i = 0; i < nxt.length; i++){
    nxt[i] = r.nextInt(len);
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
