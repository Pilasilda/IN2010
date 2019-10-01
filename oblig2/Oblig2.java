import java.util.*;
import java.io.*;

class Oblig2{
  public static void main(String[] args) {
    try{
      PrintStream test = new PrintStream(System.out);
      File fil = new File(args[0]);
      Scanner scan = new Scanner(fil);
      int count = Integer.parseInt(scan.nextLine());
      LesFil builddrail = new LesFil(count);
      builddrail.lesFil(fil);

      for(Task file : builddrail.sjekk()){
        builddrail.sjekkCycle(file);
      }

      //array for test av metoder
      ArrayList<Task> sorted = builddrail.sort();
      //ArrayList<Task> sort = new ArrayList<Task>();

      builddrail.countTime(sorted);
      System.out.println("\nAlle prosjekter i filen:  ");
      System.out.println(" ");
      for(Task t : builddrail.antallTasks){
        System.out.println(t);
      }

      System.out.println(" ");
      //Totalt 7
      System.out.println("Sjekk av slack og critical for tasks: ");
      builddrail.finishTime(sorted, sorted.get(7));
      //builddrail.finishTime(sorted, sorted.get(5));
      //builddrail.finishTime(sorted, sorted.get(1));
      //builddrail.finishTime(sorted, sorted.get(4));
      System.out.println(" ");
      builddrail.timeSchedule(sorted);

      System.setOut(test);
    }catch(FileNotFoundException f){
      System.out.println("Not Found");
    }
  }
}
