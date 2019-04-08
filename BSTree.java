import java.util.*;

public class BSTree implements BSToper{
//Konstruktorer for BSTree
  //Rot node
  public Node rot;

  public BSTree(){
    this.rot = null;
  }

  public class Node{
    Node left, right;
    int value;
    //verdier i venstre subtre er < verdien i noden selv
    //verdier i hoyre subtre er > verdien i noden selv

    //konstruktor for node, programmer disse
    Node(int value){
      this.value = value;
      this.left = null;
      this.right = null;
    }
    Node(){
    }
  }

    public Node parentRekursiv(Node n, int value){
      if(value == n.value || rot == null){
        return rot;
      }
      //Basistilfellet
      if((n.right != null && n.right.value == value) ||
        n.left != null && n.left.value == value){
          return n;
      }
      if(n.left != null){
        if(n.value > value){
          return parentRekursiv(n.left, value);
        }
      }

      if(n.right != null){
        if(n.value < value){
          return parentRekursiv(n.right, value);
        }
      }
      return n;
    }


    public Node findParent(Node n){
      return parentRekursiv(rot, n.value);
    }

    //Parent av parent til en child skal gi findGrandparents
    public Node findGrandparent(Node n){
      return (findParent(findParent(n)));
    }


    public Node rekurFind(Node n, int value){
      if(n == null){
        return null;
      }
      else if(n.value > value){
        return rekurFind(n.left, value);
      }
      else if(n.value < value){
        return rekurFind(n.right, value);
      }
      //System.out.println(n.value);
      return n;
    }

    //Kaller rekursiv metode, med rot og value som input
    public Node find(int value){
      return rekurFind(rot, value);
    }

    //Metoder fra BStoper
    //Rekursivmetode for aa gaa gjennom treet, sjekke om verdier allerede eksisterer eller ikke
    public Node addRekursiv(Node n, int verdi){
      if(n == null){
        rot = new Node(verdi); //Basistilfellet; hvis plassen er tom legges til ny node
        return rot;
      }
      if(verdi < n.value){
        n.left = addRekursiv(n.left, verdi); //Gaar rekursivt sjekker venstre gren av treet, hvis tom legger til ny node
        //hvis det allerede eksisterer node return node
        //System.out.println("left");
      }
      else if(verdi > n.value){
        n.right = addRekursiv(n.right, verdi); //Det samme som over, bare sjekker hoyre gren av treet
      //  System.out.println("hoyre");
      }
      else{
        //Hvis verdien eksisterer allerede
        return n;
      }
      return n; //Returner til slutt noden
    }


    public void add(int v){
      //teller++;
      rot = addRekursiv(rot, v);
    }

    public Node removeRek(Node n, int data){
      //Node som sendes inn finnes ikke i treet
      if(n == null){
        return null;
      }
      //Finner node som skal slettes: data
      if(data < n.value){
        n.left = removeRek(n.left, data);
      }else if(data > n.value){
        n.right = removeRek(n.right, data);
      }

      //Hvis node som skal slettes er funnet
      else{
        //Senario1: hvis node ikke har barn
        if(n.left == null && n.right == null){
          removeRek(n.left, data);
          n = null;
        }
        //Senario2: hvis node har ett barn
        else if(n.left == null){
          Node temp1 = n; //Lagrer current node
          n = n.right;
          removeRek(n, data);
        }
        else if(n.right == null){
          Node temp2 = n;
          n = n.left;
          removeRek(n, data);
        }
        //Senario3: node har 2 barn
        else{ //Kaller findmin som finner minste verdi
          Node temp = findMin(n.left);
          n.value = temp.value;
          n.right = removeRek(n.right, temp.value);
        }
      }
      return n;
    }


    public boolean remove(int value){
      if(existsInTree(value) == true){
        removeRek(rot, value);
        return true;
      }
      return false;
    }

    //Finne node med minste verdi
      public Node findMin(Node n){
        if(n == null) {
          return null;
        }
        if(n.left != null){
          return findMin(n.left);
        }
        return n;
      }

    //existsInTree rekursivhjelp
    public boolean existsInTreeRekursiv(Node n, int verdi){
      if(n == null){
        return false;
      }
      if(n.value == verdi){
        return true;
      }
      else if(n.value > verdi){
        return existsInTreeRekursiv(n.left, verdi);
      }
      if(n.value < verdi){
        return existsInTreeRekursiv(n.right, verdi);
      }else{
        return false;
      }
    }

    public boolean existsInTree(int value){
      return existsInTreeRekursiv(rot, value);
    }

    public int findNearestSmallerThan(int value){
      Node n = find(value);
      if(n.left != null){
        n = n.left;
        while(n != null){
          if(n.right == null){
             break;
          }
          n = n.right;
        }
      }
      if(n.value == value){
        System.out.println("Mindre tall finnes ikke");
      }
      return n.value;
    }

    //legger alle elementene i integers inn i det binaere soketreet
    public void addAll(int[] integers){
      for(int i = 0; i < integers.length; i++){
        add(integers[i]);
        System.out.println(integers[i]);
      }
    }

    //size hjelpemetode
    public int sizeHjelper(Node n){
      if(n == null){
        return 0;
      }else{
         return(sizeHjelper(n.left) + 1 + sizeHjelper(n.right));
      }
    }

    //size av noder
    public int size(){
      return sizeHjelper(rot);
    }

    //Inorder
    int teller = 0;
    public void sortedHjelper(Node n, int[] array){
      if(n == null){
        return;
      }

      sortedHjelper(n.left, array);
      array[teller] = n.value;
      teller++;
      sortedHjelper(n.right, array);
    }


    public int[] sortedArray(){
      int[] array = new int[size()];
      sortedHjelper(rot, array) ;
      return array;
    }


    //finner alle verdier mellom low og high i treet
    public int[] findInRange(int low, int high){
      t = 0;
      int[] array = new int[size()];

      findInRangeRek(rot, array, low , high);

      int[] a = new int[cou];

      a = Arrays.copyOf(array, cou);
      cou = 0;

      return a;
    }

    //Variable for findInRange
    int t = 0;
    int cou = 0;

    public void findInRangeRek(Node n, int[] array, int low, int high){
      if(n == null){
        return;
      }

      if(n.value >= low && n.value <= high){
        array[t] = n.value;
        t++;
        cou++;
      }

      if(n.left != null && n.value > low){
        findInRangeRek(n.left, array, low, high);
      }

      if(n.right != null && n.value < high){
        findInRangeRek(n.right, array, low, high);
      }
    }
  }
