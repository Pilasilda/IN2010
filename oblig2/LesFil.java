import java.util.*;
import java.io.*;

//Klasse for aa lese filene som er gitt
class LesFil{
  Task[] antallTasks;
  PrintStream print, stream;
  int tall = Integer.MAX_VALUE;
  ArrayList<Task> stack;
  ArrayList<Task> visited;

  LesFil(int count){
    this.visited = new ArrayList<Task>();
    this.antallTasks = new Task[count];
    this.stack = new ArrayList<Task>();

    try{
      this.stream = new PrintStream(System.out);
      this.print = new PrintStream(new FileOutputStream("output.txt"));
      System.setOut(print);
    }catch(FileNotFoundException f){
      System.out.println("File not found ");
    }
  }

  //Lesfil metode, metoden skal lese fil som sendes inn
  public void lesFil(File file)throws NumberFormatException{
    try{
      //Oppretter en scanner sender med fil som er file som skal leses
      Scanner scan = new Scanner(file);
      int count = Integer.parseInt(scan.nextLine());
      scan.nextLine();
      //scan.nextLine();

      for(int i =0; i < count; i++){
        Task t =  new Task();
        String[] linje = scan.nextLine().replaceAll("\\s+", " ").split(" ");
        /*
        if(linje == ""){
          scan.nextLine();
        }*/

        t.addId(Integer.parseInt(linje[0]));
        t.addName(linje[1]);
        t.addTime(Integer.parseInt(linje[2]));
        t.addStaff(Integer.parseInt(linje[3]));
        this.antallTasks[i] = t;
      }

      Scanner scan2 = new Scanner(file);
      int count2 = Integer.parseInt(scan2.nextLine());
      scan2.nextLine();
      for(int i = 0; i != count2; i++){
        String[] linje2 = scan2.nextLine().replaceAll("\\s+", " ").split(" ");
        for(int j = 4; Integer.parseInt(linje2[j]) != 0; j++){
          antallTasks[Integer.parseInt(linje2[j])-1].outEdges.add(antallTasks[i]);
          antallTasks[i].edges.add(antallTasks[Integer.parseInt(linje2[j])-1]);
          //Node sin posisjon
          antallTasks[i].posisjon++;
        }
      }
    }catch(FileNotFoundException f){
        System.out.println("File not found");
        System.exit(0);
    }
  }

  //Posisjon av node, finner node som er startnode
  public ArrayList<Task> sjekk(){
    ArrayList<Task> array = new ArrayList<Task>();
    for(Task t : antallTasks){
      if(t.posisjon == 0){
        array.add(t);
      }
    }
    return array;
  }

  public ArrayList<Task> sort(){
    ArrayList<Task> test = new ArrayList<Task>();
    for(Task t : antallTasks){
      test.add(t);
    }

    ArrayList<Task> sorted = new ArrayList<Task>();
    //0 slack
    Task d = null;
    for(Task t : test){
      if(t.posisjon == 0){
        d = t;
      }
    }

    while(d != null){
      for(Task t : d.outEdges){
        t.posisjon--;
      }
      sorted.add(d);
      test.remove(d);

      if(test.isEmpty()){
        d = null;
      }else{
        for(Task t : test){
          if(t.posisjon == 0){
            d = t;
          }
        }
      }
    }
    return sorted;
  }

  //Metode som sjekker om det er en cycle i grafen
  public void sjekkCycle(Task t){
    visited.add(t);

    if(t.outEdges.isEmpty()){
      visited.remove(t);
      stack.add(t);
    }else{
      for(Task task : t.outEdges){
        if(visited.contains(task)){
          System.out.println("Prosjektet er ikke realizable. ");
          String s = "CYCLE: ";
          for(int i = visited.indexOf(task); i< visited.size(); i++){
            s += visited.get(i).name + ": ";
          }

          s += " : " + task.name;
          System.out.println(s);
          visited = new ArrayList<Task>();
          System.setOut(stream);
          System.exit(0);
        }
        sjekkCycle(task);
      }
      visited.remove(t);
      stack.add(t);
    }
  }


  //Regner ut earliestStart av en task
  public void countTime(ArrayList<Task> liste){
    //earliestStart
    ArrayList<Task> sorted = liste;
    for(Task t : sorted){
      for(Task l : antallTasks){
        if(l == t){
          if(l.edges.isEmpty()){
            continue;
          }else{
            int counted = 0;
            for(Task times : l.edges){
              if((times.earliestStart+times.time) > counted){
                counted = times.earliestStart+times.time;
              }
            }
            l.earliestStart = counted;
          }
        }
      }
    }

    //regner ut LatestStart av en task
    for(int i = (sorted.size() - 1); i >= 0; i--){
      Task task1 = sorted.get(i);
      if(task1.outEdges.isEmpty()){
        if(task1 == sorted.get(sorted.size()- 1)){
          task1.latestStart = task1.earliestStart;
        }else{
          task1.latestStart = sorted.get(sorted.size() -1).latestStart - task1.time;
        }
      }else if(task1.outEdges.size() == 1){
        task1.latestStart = (task1.outEdges.get(0).latestStart - task1.time);
      }else{
        for(Task taskene : task1.outEdges){
          if(taskene.latestStart < tall){
            tall = taskene.latestStart;
          }
        }
        task1.latestStart = tall - task1.time;
      }
    }
  }

  //Find slack, critical og latestfinishtime
  public void finishTime(ArrayList<Task> sorted, Task task2){
    ArrayList<Task> critical = new ArrayList<Task>();
    //Critical
    for(Task t : sorted){
      if(t.earliestStart == t.latestStart){
        critical.add(t);
      }
    }
    System.out.println("\nTask name: " + task2.name);
    //Latestfinish time
    System.out.println("latest time to finish (uten delay): " + (task2.latestStart+task2.time));
    if(critical.contains(task2)){
      System.out.println("Har ikke slack og er critical ");
    }else{
      System.out.println("Task er ikke critical og har max delay start ved : " + (task2.latestStart-task2.earliestStart));
    }
  }

  //find the optimal time schedule
  public void timeSchedule(ArrayList<Task> sorted){
    System.out.println("\nOptimal schedule time: ");
    System.out.println(" ");
    System.out.println("ID for tasks som går samtidig: ");

    Set<Task> add = new HashSet<Task>();
    Set<Task> per =  new HashSet<Task>();

    for(Task t : sorted){
      if(!add.contains(t)){
        for(Task task : sorted){
          if(t == task){
            continue;
          }
          else if(t.earliestStart == task.earliestStart){
            per.add(t);
            per.add(task);
            add.add(t);
            add.add(task);
          }
        }
        if(!per.isEmpty()){
          System.out.println(" ");
          System.out.println("Tasker som gaar samtidig: ");
          for(Task task4 : per){
            System.out.println("ID for task: " + task4.id);
          }
          per.clear();
        }
      }
    }

    System.out.println(" ");
    System.out.println("Print tasks som kan kjores samtidig:   ");
    System.out.println(" ");
    for(Task t3 : sorted){
      System.out.println("\nID: " + t3.id + " ");
      System.out.println("\nName: " + t3.name + " ");
      System.out.println("\nRequired Manpower: " + t3.staff + " ");
      System.out.println("\nTime: " + t3.time + " ");
      System.out.println("\nEarlieststart: " + t3.earliestStart +  " ");
      System.out.println("\nEarliestfinish: " + (t3.earliestStart+t3.time + " "));
    }
  }
}
