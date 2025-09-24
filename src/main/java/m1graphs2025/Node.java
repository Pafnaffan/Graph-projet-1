package m1graphs2025;

import javax.naming.Name;
import java.util.List;

public class Node implements Comparable<Node> {

    private int id;
    private Graph graph;
    private String name;

    /**
     * Constructor that create this Node
     * @param id the id of this Node
     * @param graph the graph holder of this Node
     */
    public Node(int id,Graph graph){
        this.id = id;
        this.graph = graph;
    }

    /**
     * Constructor that create this Node with a name
     * @param id the id of this Node
     * @param graph the graph holder of this Node
     * @param name the name of this Node
     */
    public Node (int id,Graph graph,String name){
        this.id = id;
        this.graph = graph;
        this.name = name;
    }

    /**
     * Method that return the id of this Node
     * @return the id of this Node
     */
    public int getId(){
        return id;
    }

    /**
     * Method that return the graph holder of this Node
     * @return the graph holder of this Node
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Method that return the name of this Node
     * @return the name of this Node
     */
    public String getName() {
        return name;
    }

    /**
     * Method that return a list of this Node's neighbours without duplicates
     * @return list of this Node's neighbours
     */
    public List<Node> getSuccessors(){
        //todo
        return null;
    }

    /**
     * Method that return a list of this Node's neighbours with possible duplicates
     * @return list of this Node's neighbours
     */
    public List<Node> getSuccessorsMulti(){
        //todo
        return null;
    }

    /**
     * Method that return true if the Node u is adjacent to this Node
     * @param u the Node to test
     * @return true if the Node u is adjacent to this Node, otherwise false
     */
    public boolean adjacent(Node u){
        //todo
        return false;
    }

    /**
     * Method that return true if the Node with the id u_id is adjacent to this Node
     * @param u_id the id of the Node to test
     * @return true if the Node with the id u_id is adjacent to this Node, otherwise false
     */
    public boolean adjacent(int u_id){
        //todo
        return false;
    }

    /**
     * Method that return the in-degree of this Node
     * @return the in-degree of this Node
     */
    public int inDegree(){
        //todo
        return 0;
    }

    /**
     * Method that return the out-degree of this Node
     * @return the out-degree of this Node
     */
    public int outDegree(){
        //todo
        return 0;
    }

    /**
     * Method that return the degree of this Node
     * @return the degree of this Node
     */
    public int degree(){
        //todo
        return 0;
    }

    /**
     * Method that return the list of all edges leaving this Node
     * @return list of all edges leaving this Node
     */
    public List<Edge> getOutEdges(){
        //todo
        return null;
    }

    /**
     * Method that return the list of all edges entering this Node
     * @return list of all edges entering this Node
     */
    public List<Edge> getInEdges(){
        //todo
        return null;
    }

    /**
     * Method that return the list of all edges incident to this Node
     * @return list of all edges incident to this Node
     */
    public List<Edge> getIncidentEdges(){
        //todo
        return null;
    }

    /**
     * Method that return the list of all edges going from this Node to Node u
     * @param u the Node to test
     * @return list of all edges going from this Node to Node u
     */
    public List<Edge> getEdgesTo(Node u){
        //todo
        return null;
    }


    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        Node other = (Node)o;
        return this.id == other.getId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int compareTo(Node o) {
        if (this.id > o.getId()){
            return 1;
        }
        else if(this.id == o.getId()){
            return 0;
        }
        else{
            return -1;
        }
    }
}