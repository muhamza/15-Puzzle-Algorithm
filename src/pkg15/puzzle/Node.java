package pkg15.puzzle;


import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    char[][] arr = new char[4][4];
    int row;
    int col;
    int depth;
    int f;
    Node parent;
    List<Node> children = new ArrayList<>();
    boolean isVisited = false;
    
    public Node(char[][] arr,int row,int col,int depth){
        this.arr = arr;
        this.row = row;
        this.col = col;
        this.depth = depth;
    }
    
    public void addChild(Node n){
        children.add(n);
        n.parent = this;
    }
    
    public String toString(char[][] arr){
        String val = "";
        for(int i=0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                val = val + arr[i][j];
            }
        }
        return val;
    }
    
    @Override
    public int compareTo(Node n) {
        if(this.f < n.f){
            return -1;
        }else if(this.f > n.f){
            return 1;
        }else
            return 0;
    }
}
