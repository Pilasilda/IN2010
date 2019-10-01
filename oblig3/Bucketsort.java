/*Idea of bucket sort: create n empty bucket, do it for every array element, insert array to bucket, sort individual bucket*/
import java.util.*;
class Bucketsort{
 void bucketSort(int array[], int n){
   int[] bucket = new int[n+1];

   for(int i=0; i< bucket.length; i++){
     bucket[i] = 0;
   }

   for(int i=0; i < array.length; i++){
     bucket[array[i]]++;
   }

   int posisjon = 0;
   for(int i = 0; i < bucket.length; i++){
     for(int j = 0; j< bucket[i]; j++){
       array[posisjon++] = i;
     }
   }

 }
  public void printArray(int array[]){
    int n = array.length;
    for(int i= 0; i < n; i++){
      System.out.print(array[i] + " ");
    }
      System.out.println();
   }

  public int[] generate(int tall){
    Random r = new Random();
    int[] nxt = new int[tall];
    for(int i = 0; i < nxt.length; i++){
      nxt[i] = r.nextInt(tall);
    }
      return nxt;
    }
}
