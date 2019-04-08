import java.util.*;

public class Test{
  public static void main(String[] args) {
    //Variable
    final int max = 300000;
    long max_length = 100000;

    //Objekt
    Sort s = new Sort();
    Insertion is = new Insertion();
    Quicksort qs = new Quicksort();
    Bucketsort bs = new Bucketsort();

    //Array av stigende og fallende
    int[] sortert = {1,2,3,4,5,6,7,10,12,15};
    int[] usortert = {15,12,10,8,6,5,4,3,2,1};

    //Oppgave4: Test for stigende og fallende ved 10 elementer
    //Test av alle sorts for for stigende rekkefolge
    System.out.println("Stigende for selection sort: ");
    //Selection sort
    s.selectionSort(sortert);
    s.printArray(sortert);
    System.out.println();

    //Insertionsort
    System.out.println("Stigende for Insertionsort: ");
    is.insertionArray(sortert);
    is.printArray(sortert);
    System.out.println();

    //Quicksort
    System.out.println("Stigende for quickSort: ");
    int tall = sortert.length;
    qs.quick(sortert,0, tall-1);
    qs.printArray(sortert);
    System.out.println();

    //bucketSort
    System.out.println("Stigende for bucketsort: ");
    bs.bucketSort(sortert, max);
    bs.printArray(sortert);
    System.out.println();


    //Test av alle sorts for fallende rekkefolge
    //SelectionSort
    System.out.println("Fallende for selection sort: ");
    s.selectionSort(usortert);
    s.printArray(usortert);
    System.out.println();

    //Insertion
    System.out.println("Fallende for Insertionsort: ");
    is.insertionArray(usortert);
    is.printArray(usortert);
    System.out.println();

    //quicksort
    System.out.println("Fallende for quickSort: ");
    //Test for quickSort
    int tall2 = usortert.length;
    qs.quick(usortert,0, tall-1);
    qs.printArray(sortert);
    System.out.println();

    //Bucketsort
    System.out.println("Fallende for bucketsort: ");
    int max2 = 50;
    bs.bucketSort(usortert, max2);
    bs.printArray(sortert);
    System.out.println();

    //Tilfeldig sortering
    //Selection
    System.out.println("Tilfelding selection");
    int[] test1 = s.generate(10);
    s.selectionSort(test1);
    s.printArray(test1);
    System.out.println();
    //Insertion
    System.out.println("Tilfeldig insertion");
    int[] test2 = s.generate(10);
    is.insertionArray(test2);
    is.printArray(test2);
    System.out.println();
    //Quick
    System.out.println("Tilfeldig quick");
    int[] test3 = qs.generate(10);
    int ns = test3.length;
    qs.quick(test3,0, ns-1);
    qs.printArray(test3);
    System.out.println();
    //Bucketsort
    System.out.println("Tilfeldig bucket");
    int max3 = 3000000;
    int[] test4 = bs.generate(10);
    bs.bucketSort(usortert, max3);
    bs.printArray(test4);


    //Oppgave5: test for 1000+ elementer
    for(int i = 0, n = 1000; n <= max; n *= (i&1) ==0 ? 5: 2, i++){
      //Test for tilfeldig sortering ved bruk av random
      System.out.println("Elementer:  " + n);
      //selection(n);
      //insertion(n);
      //quick(n);
      //bucket(n);
      //arraySort(n);
      /*
      //Test for usortert ved selection sort
      long start = System.nanoTime();
      int[] test1 = s.generate(n);
      reverse(test1);
      s.selectionSort(test1);
      s.printArray(test1);
      long end = System.nanoTime();
      long output = end-start;
      System.out.println("Elapsed time for usortert selectionSort: " + output / 1000000.0);

      //Test for usortert ved insertion sort
      long start1 = System.nanoTime();
      int[] test2 = s.generate(n);
      reverse(test2);
      is.insertionArray(test2);
      long end1 = System.nanoTime();
      long output1 = end1-start1;
      System.out.println("Elapsed time for usortert Insertionsort: " + output1 / 1000000.0);

      //Test usortert ved bucketsort
      long s3 = System.nanoTime();
      int max3 = 3000000;
      int[] test4 = bs.generate(n);
      reverse(test4);
      bs.bucketSort(usortert, max3);
      System.out.println(Arrays.toString(usortert));
      long e3 = System.nanoTime();
      //Elapsed time
      long o3 = e3-s3;
      System.out.println("Elapsed time for usortert bucketsort: " + o3 / 10000000.0);


      //Test usortert ved quick sort
      long start3 = System.nanoTime(); //nanosek
      int[] test3 = qs.generate(n);
      int ns = test3.length;
      System.out.println("Quicksort: ");
      reverse(test3);
      qs.quick(test3,0, ns-1);
      long end3 = System.nanoTime();
      long output2 = end3-start3;
      System.out.println("Elapsed time for usortert quicksort: " + output2 / 10000000.0);*/

      //Arraysort
      long start6 = System.nanoTime(); //nanosek
      Arraysort arrayen = new Arraysort();
      final int max5 = 300000;
      int[] test6 = arrayen.generate(n);
      System.out.println("Arraysort: ");
      reverse(test6);
      arrayen.arraysort(test6);
      long end6 = System.nanoTime();
      long output6 = end6 - start6;
      System.out.println("Elapsed time: " + output6 /10000000.0);
    }
}
    //Test av alle sorts for tilfeldig sortering
    //nextint generer en random integer fra 0(inkludert) til bound value 9(inkludert)
    //Selection
    public static void selection(int len){
      Sort s = new Sort();
      long start = System.nanoTime(); //nanosek
      int[] test = s.generate(len);
      System.out.println("Selectionsort: ");
      s.selectionSort(test);
      //s.printArray(test);
      //Time end
      long end = System.nanoTime();
      //Elapsed time
      long output = end-start;
      System.out.println("Elapsed time: " + output / 1000000.0);
    }


    //Insertionsort
    public static void insertion(int len){
      Insertion i = new Insertion();
      long start1 = System.nanoTime(); //nanosek
      int[] test1 = i.generate(len);
      System.out.println("Insertionsort: ");
      i.insertionArray(test1);
      //i.printArray(test1);
      //Time end
      long end1 = System.nanoTime();
      //Elapsed time
      long output1 = end1-start1;
      System.out.println("Elapsed time: " + output1 / 10000000.0);
    }


    //Quicksort
    public static void quick(int len){
      Quicksort q = new Quicksort();
      long start2 = System.nanoTime(); //nanosek
      int[] test2 = q.generate(len);
      int n = test2.length;
      System.out.println("Quicksort: ");
      q.quick(test2,0, n-1);
      //q.printArray(test2);
      //Time end
      long end2 = System.nanoTime();
      //Elapsed time
      long output2 = end2-start2;
      System.out.println("Elapsed time: " + output2 / 10000000.0);
    }


    //Bucketsort
    public static void bucket(int len){
      final int m = 300000;
      Bucketsort b = new Bucketsort();
      long starTime, endTime;
      int[] tester = b.generate(len);
      starTime = System.nanoTime();
      b.bucketSort(tester, m);
      System.out.println("Bucketsort: ");
      //Time end
      endTime = System.nanoTime();
      //Elapsed time
      long output3 = endTime-starTime;
      System.out.println("Elapsed time: " + output3 / 10000000.0);
    }

    //Kjoretid for java.util.arrays.sort
    public static void arraySort(int len){
      Arraysort arrayen = new Arraysort();
      long start5 = System.nanoTime();
      final int max = 300000;
      int[] test5 = arrayen.generate(len);
      System.out.println("Arraysort: ");
      arrayen.arraysort(test5);
      long end5 = System.nanoTime();
      long output5 = end5 - start5;
      System.out.println("Elapsed time: " + output5 /10000000.0);
    }

    //Reverse array
    private static void reverse(int[] a) {
      int temp;
      for(int i = 0; i < a.length; i++){
        for(int j  = i+1; j < a.length; j++){
          if(a[i] < a[j]){
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
        }
      }
  }
}
