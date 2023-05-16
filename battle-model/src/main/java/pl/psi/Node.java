package pl.psi;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Node extends Point implements Comparable<Node>{
    private int weight;
    private int costToReach;
    private int heuristic;
    private int cost;
    private Node parent;



    public Node(int aX, int aY)
    {
        super(aX, aY);
        this.weight = 0;
        this.costToReach = 0;
        this.cost = 0;
        this.parent = null;
        this.heuristic = 0;
    }

    public Node(final int aX, final int aY, final int weight )
    {
        super(aX, aY);
        this.weight = weight;
        this.costToReach = 0;
        this.cost = 0;
        this.parent = null;
        this.heuristic = 0;
    }
    @Override
    public int compareTo(Node other){
        return Integer.compare(this.getCost(), other.getCost());
    }

}

