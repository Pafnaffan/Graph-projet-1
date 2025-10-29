package m1graphs2025;

import java.util.Objects;

public class Edge implements Comparable<Edge> {

    private Node from;
    private Node to;
    private Integer weight;

    /**
     * Constructor that create this Edge
     * @param from the starting Node of this Edge
     * @param to the ending Node of this Edge
     */
    public Edge(Node from, Node to){
        if(from == null){
            throw new IllegalArgumentException("Node from must be non-null");
        } else if (to == null) {
            throw new IllegalArgumentException("Node to must be non-null");
        } else if (from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Nodes from and to must be from the same Graph");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor that create this Edge with a weight
     * @param from the starting Node of this Edge
     * @param to the ending Node of this Edge
     * @param weight the weight of this Edge
     */
    public Edge(Node from, Node to, Integer weight){
        if(from == null){
            throw new IllegalArgumentException("Node from must be non-null");
        } else if (to == null) {
            throw new IllegalArgumentException("Node to must be non-null");
        } else if (from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Nodes from and to must be from the same Graph");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Constructor that create this Edge with the ids of the Nodes
     * @param from id of the starting Node of this Edge
     * @param to id of the ending Node of this Edge
     * @param g the Graph of the Nodes
     */
    public Edge(int from, int to, Graph g){
        Node nfrom = g.getNode(from);
        Node nto = g.getNode(to);
        if(nfrom == null){
            throw new IllegalArgumentException("Node from must be non-null");
        } else if (nto == null) {
            throw new IllegalArgumentException("Node to must be non-null");
        }
        this.from = nfrom;
        this.to = nto;
    }

    /**
     * Constructor that create this Edge with the ids of the Nodes and with a weight
     * @param from id of the starting Node of this Edge
     * @param to id of the ending Node of this Edge
     * @param weight the weight of this Edge
     * @param g the Graph of the Nodes
     */
    public Edge(int from, int to, Integer weight, Graph g){
        Node nfrom = g.getNode(from);
        Node nto = g.getNode(to);
        if(nfrom == null){
            throw new IllegalArgumentException("Node from must be non-null");
        } else if (nto == null) {
            throw new IllegalArgumentException("Node to must be non-null");
        }
        this.from = nfrom;
        this.to = nto;
        this.weight = weight;
    }

    /**
     * Method that return the Node from of this Edge
     * @return the Node from of this Edge
     */
    public Node from(){
        return from;
    }

    /**
     * Method that return the Node to of this Edge
     * @return the Node to of this Edge
     */
    public Node to(){
        return to;
    }

    /**
     * Method that return the symmetric of this Edge as a new Edge instance
     * @return the symmetric of this Edge
     */
    public Edge getSymmetric(){
        return new Edge(this.to,this.from,this.weight);
    }

    /**
     * Method that return true if this Edge is a self-loop, otherwise false
     * @return true if this Edge is a self-loop, otherwise false
     */
    public boolean isSelfLoop(){
        return this.from == this.to;
    }

    /**
     * Method that return true if this Edge is a multi-edge, otherwise false
     * @return true if this Edge is a multi-edge, otherwise false
     */
    public boolean isMultiEdge(){
        return from.getGraph().isMultiEdge(this);
    }

    /**
     * Method that return true if this Edge is weighted, otherwise false
     * @return true if this Edge is weighted, otherwise false
     */
    public boolean isWeighted(){
        return weight != null;
    }

    /**
     * Method that return the weight of this Edge
     * @return the weight of this Edge
     */
    public Integer getWeight(){
        return weight;
    }


    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        Edge e = (Edge)o;
        if(this.from == e.from()){
            if(this.to == e.to()){
                return Objects.equals(this.weight, e.weight);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Edge o) {
        if(this.from.compareTo(o.from()) < 0){
            return -1;
        }
        else if(this.from.compareTo(o.from()) > 0){
            return 1;
        }
        else {
            if(this.to.compareTo(o.to()) < 0){
                return -1;
            }
            else if(this.to.compareTo(o.to()) > 0){
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return from+"->"+to;
    }
}
