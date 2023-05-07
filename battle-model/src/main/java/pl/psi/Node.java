package pl.psi;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node extends Point{
    private List<Node> neighbourList = new ArrayList<>();
    private int weight = 0;
    private int cost = 0;
    private Node parent = null;
    private int heuristic = 0;


    public Node(int aX, int aY) {
        super(aX, aY);
    }

    public Node(final int aX, final int aY, final int weight )
    {
        super(aX, aY);
        this.weight = weight;
    }

    public boolean compareTo(Node other){
        return this.getCost() < other.getCost();
    }

}

