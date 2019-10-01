public class Test{
  public static void main(String[] args) throws Exception{
    BSTree bt = new BSTree();

    //For aa teste BESTEFORELDER
    BSTree.Node n = bt.new Node(7);
    //System.out.println(test);

    //Array for aa teste addAll-metode
    int[] array = {10,14,13};


    //Tester for add-metoden
    bt.add(2);
    bt.add(7);
    bt.add(2);
    bt.add(6);
    bt.add(5);
    bt.add(11);
    bt.add(5);
    bt.add(9);
    bt.add(7);
    //bt.add(4);

    //Sjekk for inorder-metoden
    System.out.println("INORDER");
    int[] array1 = bt.sortedArray();
    for(int i : array1){
      System.out.println(i);
    }

    System.out.println(" ");

    //ADDALL
    System.out.println("ADDALL");
    //bt.addAll(array); //Funker
    //System.out.println("FIND");
    //bt.find(5); //funker
    System.out.println(" ");
    System.out.println("SIZE");
    System.out.println(bt.size()); //Funker
    System.out.println(" ");
    System.out.println("FINDNEARESTSMALLER");
    System.out.println(bt.findNearestSmallerThan(9));
    System.out.println();
    System.out.println(" ");
    System.out.println("FINDINRANGE");
    int[] a = bt.findInRange(2, 11);
    for(int i = 0; i < a.length; i++){
      System.out.println(a[i]);
    }
    System.out.println(" ");
    System.out.println("Test 2 for FINDINRANGE:");
    a = (bt.findInRange(5, 9));

    for(int i = 0; i < a.length; i++){
      System.out.println(a[i]);
    }

    System.out.println(" ");
    System.out.println("REMOVE");
    System.out.println(bt.remove(9)); //Funker
    System.out.println(" ");
    System.out.println("EXIST");
    System.out.println(bt.existsInTree(7));
    System.out.println(" ");
    System.out.println("FORELDER ");
    System.out.println(bt.findParent(n).value); //Funker
    System.out.println(" ");
    System.out.println("BESTEFORELDER");
    System.out.println (bt.findGrandparent(n).value);
  }

}
