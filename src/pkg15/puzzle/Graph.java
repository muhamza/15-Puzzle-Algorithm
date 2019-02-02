package pkg15.puzzle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Graph {
    int created = 0;
    Node root;
    int rfree, cfree;
    String stringOut = "";
    List<String> moved = new ArrayList<>();
    char[][] arr = new char[4][4];
    char[][] finalstate = {{'1','2','3','4'},
                           {'5','6','7','8'},
                           {'9','A','B','C'},
                           {'D','E','F',' '}};
    
    public void input(){
        Scanner in = new Scanner(System.in);
        System.out.print("Input should be in this form: ");
        System.out.println("\"51246A379 C8DEBF\" AStar 1");
        System.out.println("Please enter your Input: ");
        String read = in.nextLine();
        String[] input = read.split("\\s+");
        String test = input[0] + " " + input[1];
        stringToArray(test);
        root = new Node(arr,rfree,cfree,0);
        if(input[2].equals("BFS")){
            if(input[3].equals("1")){
                BFS1(root);
            }
            else if(input[3].equals("2")){
                BFS2(root);
            }
            else{
                System.out.println("Input is not Correct.");
            }
        }
        else if(input[2].equals("AStar")){
            if(input[3].equals("1")){
                AStar1(root);
            }
            else if(input[3].equals("2")){
                AStar2(root);
            }
            else{
                System.out.println("Input is not Correct.");
            }
        }
        else{
            System.out.println("Input is not Correct.");
        }
        
    }

    public void BFS1(Node n){
        int size, min=100, index=0;
        boolean istrue = isFound(n);
        if(arraysCompare(n.arr) || istrue){
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     "); 
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            if(arraysCompare(n.arr)){
                System.out.println("Found!");
            }
            else if(istrue){
                System.out.print("                     ");
                System.out.print("Depth = " + -1 + ",     "); 
                System.out.print("Created = " + created  + ",     ");
                System.out.println("Cost = " + -1);
            }
        }
        else{
            populateGraph(n);
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     ");
            created += n.children.size();
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            for(int i=0;i<n.children.size();i++){
               size = heuristic1(n.children.get(i).arr);
               n.children.get(i).f = size;
               if(size<min){
                   min = size;
                   index = i;
               }
            }
            BFS1(n.children.get(index));
        }
    }
    
    public void BFS2(Node n){
        int size, min=100, index=0;
        boolean istrue = isFound(n);
        if(arraysCompare(n.arr) || istrue){
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     "); 
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            if(arraysCompare(n.arr)){
                System.out.println("Found!");
            }
            else if(istrue){
                System.out.print("                     ");
                System.out.print("Depth = " + -1 + ",     "); 
                System.out.print("Created = " + created  + ",     ");
                System.out.println("Cost = " + -1);
            }
        }
        else{
            populateGraph(n);
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     ");
            created += n.children.size();
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            for(int i=0;i<n.children.size();i++){
               size = heuristic2(n.children.get(i).arr);
               n.children.get(i).f = size;
               if(size<min){
                   min = size;
                   index = i;
               }
            }
            BFS2(n.children.get(index));
        }
    }
    
    
    public void AStar1(Node n){
        int size; int min = 100; int index = 0;
        Node h,k;
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        boolean istrue = isFound(n);
        if(arraysCompare(n.arr) || n.isVisited ||istrue){
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     "); 
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            if(arraysCompare(n.arr)){
                System.out.println("Found!");
            }
            else if(istrue){
                System.out.print("                     ");
                System.out.print("Depth = " + -1 + ",     "); 
                System.out.print("Created = " + created  + ",      ");
                System.out.println("Cost = " + -1);
            }
        }
        else{
            populateGraph(n);
            closed.add(n);
            n.isVisited = true;
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     ");
            created += n.children.size();
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            
            
            for(int i=0;i<n.children.size();i++){
               size = heuristic1(n.children.get(i).arr) + n.children.get(i).depth;
               n.children.get(i).f = size;
               h = foundInList(n.children.get(i),closed);
               foundInList2(n.children.get(i),open);
               if(h!=null){
                   if(n.children.get(i).f<h.f){
                       open.add(n.children.get(i));
                   }
               }
               else{
                   open.add(n.children.get(i));
               }
               if(size<min){
                   min = size;
                   index = i;
               }
            }
            if(!open.isEmpty()){
                Node temp = findMin(open);
                if(temp.f < n.children.get(index).f){
                    open.remove(temp);
                    AStar1(temp);
                }
                else{
                    open.remove(n.children.get(index));
                    AStar1(n.children.get(index));
                }
            }
            else{
                open.remove(n.children.get(index));
                AStar1(n.children.get(index));
            }
        }
    }
    
    public void AStar2(Node n){
        int size; int min = 100; int index = 0;
        Node h;
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        boolean istrue = isFound(n);
        if(arraysCompare(n.arr) || n.isVisited ||istrue){
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     "); 
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            if(arraysCompare(n.arr)){
                System.out.println("Found!");
            }
            else if(istrue){
                System.out.print("                     ");
                System.out.print("Depth = " + -1 + ",     "); 
                System.out.print("Created = " + created  + ",     ");
                System.out.println("Cost = " + -1);
            }
        }
        else{
            populateGraph(n);
            closed.add(n);
            n.isVisited = true;
            stringOut = toString(n.arr);
            moved.add(stringOut);
            System.out.print(stringOut + ",     ");
            System.out.print("Depth = " + n.depth + ",     ");
            created += n.children.size();
            System.out.print("Created = " + created  + ",     ");
            System.out.println("Cost = " + n.f);
            
            
            for(int i=0;i<n.children.size();i++){
               size = heuristic2(n.children.get(i).arr) + n.children.get(i).depth;
               n.children.get(i).f = size;
               h = foundInList(n.children.get(i),closed);
               foundInList2(n.children.get(i),open);
               if(h!=null){
                   if(n.children.get(i).f<h.f){
                       open.add(n.children.get(i));
                   }
               }
               else{
                   open.add(n.children.get(i));
               }
               if(size<min){
                   min = size;
                   index = i;
               }
            }
            if(!open.isEmpty()){
                Node temp = findMin(open);
                if(temp.f < n.children.get(index).f){
                    open.remove(temp);
                    AStar2(temp);
                }
                else{
                    open.remove(n.children.get(index));
                    AStar2(n.children.get(index));
                }
            }
            else{
                open.remove(n.children.get(index));
                AStar2(n.children.get(index));
            }
        }
    }
    
    private void populateGraph(Node n){
        int r = n.row; int c = n.col; 
        char[][] tarr = cloneArray(n.arr);
        char temp; int newr, newc;
        //int k = c, t = r;
        
        if (r==0){
            temp = tarr[r+1][c];
            tarr[r][c] = temp;
            tarr[r+1][c] = ' ';
            newr = r+1;
            Node n2 = new Node(tarr,newr,c,n.depth+1);
            n.addChild(n2);
        }
        else if (r==3){
            temp = tarr[r-1][c];
            tarr[r][c] = temp;
            tarr[r-1][c] = ' ';
            newr = r-1;
            Node n2 = new Node(tarr,newr,c,n.depth+1);
            n.addChild(n2);
        }
        else{
            for(int i=r-1; i<=r+1; i++){
                if(i!=r){
                    temp = tarr[i][c];
                    tarr[i][c] = ' ';
                    tarr[r][c] = temp;
                    Node n2 = new Node(tarr,i,c,n.depth+1);
                    n.addChild(n2);
                }
                tarr = cloneArray(n.arr);
            }
        }
        
        tarr = cloneArray(n.arr);
        
        if (c==0){
            temp = tarr[r][c+1];
            tarr[r][c] = temp;
            tarr[r][c+1] = ' ';
            newc = c+1;
            Node n2 = new Node(tarr,r,newc,n.depth+1);
            n.addChild(n2);
        }
        else if (c==3){
            temp = tarr[r][c-1];
            tarr[r][c] = temp;
            tarr[r][c-1] = ' ';
            newc = c-1;
            Node n2 = new Node(tarr,r,newc,n.depth+1);
            n.addChild(n2);
        }
        else{
            for(int i=c-1; i<=c+1; i++){
                if(i!=c){
                    temp = tarr[r][i];
                    tarr[r][i] = ' ';
                    tarr[r][c] = temp;
                    Node n2 = new Node(tarr,r,i,n.depth+1);
                    n.addChild(n2);
                }
                tarr = cloneArray(n.arr);
            }
        }
    }
    
    private int heuristic1(char[][] arr){
        int wrongPosition = 0;
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j] != finalstate[i][j] && arr[i][j]!=' '){
                    wrongPosition++;
                }
            }
        }
        return wrongPosition;
    }
    
    private int heuristic2(char[][] arr){
        int pos = 0;
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j] != ' '){
                    pos = pos + findDistance(arr[i][j],i,j);
                }
            }
        }
        return pos;
    }
    
    private int findDistance(char val, int r, int c){
        int nr=0, nc=0, sum = 0;
        for(int i=0; i<finalstate.length; i++){
            for(int j=0; j<finalstate[0].length; j++){
                if(finalstate[i][j] == val){
                    nr = i;
                    nc = j;
                    break;
                }
            }
        }
        sum = Math.abs(r-nr) + Math.abs(c-nc);
        return sum;
    }
    
    private char[][] cloneArray(char[][] arr) {
        int length = arr.length;
        char[][] target = new char[length][arr[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(arr[i], 0, target[i], 0, arr[i].length);
        }
        return target;  
    }
    
    private void stringToArray(String str){
        int p=1;
        for(int i=0; i<arr.length;i++){
            for(int j=0; j<arr[0].length;j++){
                arr[i][j] = str.charAt(p);
                if(arr[i][j] == ' '){
                    rfree = i;
                    cfree = j;
                }
                p++;
            }
        }
    }
    
    public void print(char[][] arr){
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private String toString(char[][] arr){
        String val = "";
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                val = val + arr[i][j];
            }
        }
        return (val);
    }
    
    private boolean arraysCompare(char[][] arr){
        boolean check = true;
        for (int i = 0; check && i < arr.length; ++i) {
            check = Arrays.equals(arr[i], finalstate[i]);
        }
        return check;
    }
    
    private boolean arraysCompare2(char[][] arr, char[][] arr2){
        boolean check = true;
        for (int i = 0; check && i < arr.length; ++i) {
            check = Arrays.equals(arr[i], arr2[i]);
        }
        return check;
    }
    
    private boolean isFound(Node n){
        boolean found = false;
        String test = toString(n.arr);
        for(int i=0;i<moved.size();i++){
            if(test.equals(moved.get(i))){
                found = true;
            }
        }
        return found;
    }
    
    private Node foundInList(Node n, List<Node> list){
        boolean yes = false;
        int index=0;
        for(int j=0;j<list.size();j++){
            if(arraysCompare2(n.arr,list.get(j).arr)){
                index = j;
                yes = true;
                break;
            }
        }
        if(yes){
            return list.get(index);
        }
        else{
            return null;
        }
    }

    private void foundInList2(Node n, List<Node> list){
        for(int j=0;j<list.size();j++){
            if(arraysCompare2(n.arr,list.get(j).arr)){
                if(n.f<list.get(j).f){
                    list.remove(j);
                }
                break;
            }
        }
    }
    
    private Node findMin(List<Node> list){
        int min = 100;
        int index = 0;
        for(int j=0;j<list.size();j++){
            if(list.get(j).f < min){
                min = list.get(j).f;
                index = j;
            }
        }
        return list.get(index);
    }    
    
}
