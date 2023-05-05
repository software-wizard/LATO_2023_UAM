package pl.psi;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node extends Point{
    private List<Node> neighbourList = new ArrayList<>();
    private int weight = 1;
    private int cost = 0;


    public Node(int aX, int aY) {
        super(aX, aY);
    }

    public Node(final int aX, final int aY, final int weight )
    {
        super(aX, aY);
        this.weight = weight;
        neighbourList = generateNeigboursList(aX, aY);
    }
    private List<Node> generateNeigboursList(int x, int y){
        List<Node> list = new ArrayList<>();
        if(y != 0){
            //UP
            list.add(new Node(x,y-1));
        }
        if(y != 9){
            //DOWN
            list.add(new Node(x,y+1));
        }
        if(x != 0){
            //LEFT
            list.add(new Node(x-1,y));
        }
        if(x != 14){
            //RIGHT
            list.add(new Node(x+1,y));
        }
        return list;
    }
}

