import java.util.*;
class Sort{

//(o(n^2))
public void selectionSort(int[] array){
  int i, j, value, index, teller = 0;
  for(i = 0; i < array.length; i++){
    value = array[i];
    index = i;
    for(j = i; j < array.length; j++){
    if(array[j] < value){
      value = array[j];
      index = j;
    }
  }
  //swap
  if(value < array[i]){
    teller = array[i];
    array[i] = array[index];
    array[index] = teller;
  }
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
