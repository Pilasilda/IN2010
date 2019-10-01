import java.util.*;

//klasse for tasks som inneholder info om en task.
class Task{
  //Variable for konstruktor
  int id, time, staff;
  int posisjon;
  String name;
  int earliestStart, latestStart;
  ArrayList<Task> outEdges;
  ArrayList<Task> edges;
  int cntPredecessors;

  //Konstruktor
  Task(){
    this.id = 0;
    this. time = 0;
    this.staff = 0;
    this.name = null;
    this.earliestStart = 0;
    this.latestStart = 0;
    this.outEdges = new ArrayList<Task>();
    this.edges = new ArrayList<Task>();
  }

  public void addId(int id){
    this.id = id;
  }

  public void addTime(int time){
    this.time = time;
  }

  public void addStaff(int staff){
    this.staff = staff;
  }

  public void addName(String name){
    this.name = name;
  }

  /*
  //Metode for å sjekke om det er nok manpower til å fullføre en task
  public boolean sjekkManpower(){
    if(staff > totaltS){
      System.out.println("Ikke nok manpower til fullføre denne tasken" + id);
    }
    if(FStaff >= staff){
      FStaff -= staff;
      return true;
    }
    return false;
  }*/

  public String toString(){
    String linje = "\nTask ID: " + this.id + "\nName: " + this.name +
    "\nTime: " + this.time + "\nManpower: " + this.staff + "\nSlack: " +
    (this.latestStart - this.earliestStart) +
    "\nEarliest: " + this.earliestStart +
    "\nLastest: " + this.latestStart + "\nOut-edges: ";

    if(this.outEdges.isEmpty()){
      linje += "\nNONE";
    }else{
      for(Task t : outEdges){
        linje += t.id + "";
      }
    }
    return linje;
  }
}
