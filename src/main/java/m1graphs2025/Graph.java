package m1graphs2025;

import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Edge>> adjEdList;

    /**
     * Constructor that create this empty Graph
     */
    public Graph(){}

    /**
     * Constructor that create this Graph with a successor array to initialize
     * @param sa the successor array in unspecified number of integers
     */
    public Graph(int... sa){
        //todo
    }

    /**
     * Constructor that create this Graph with a successor array to initialize
     * @param sa the successor array in integers array
     */
    public Graph(List<Integer> sa){
        //todo
    }


    /**
     * Method that return the number of Nodes in this Graph
     * @return the number of Nodes in this Graph
     */
    public int nbNodes(){
        //todo
        return -1;
    }

    /**
     * Method that return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     * @param n the Node to test
     * @return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     */
    public boolean usesNode(Node n){
        //todo
        return false;
    }

    /**
     * Method that return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     * @param n the id of the Node to test
     * @return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     */
    public boolean usesNode(int n){
        //todo
        return false;
    }

    /**
     * Method that return true if the Node n is a Node of this Graph, otherwise false
     * @param n the Node to test
     * @return true if the Node n is a Node of this Graph, otherwise false
     */
    public boolean holdsNode(Node n){
        //todo
        return false;
    }

    /**
     * Method that return the Node held by this Graph with the id, otherwise null
     * @param id the id of the Node to get
     * @return the Node held by this Graph with the id, otherwise null
     */
    public Node getNode(int id){
        //todo
        return null;
    }

    /**
     * Method that add the Node n to this Graph
     * @param n the Node to add
     * @return true if the Node has been added, otherwise false
     */
    public boolean addNode(Node n){
        //todo
        return false;
    }

    /**
     * Method that add the Node n to this Graph
     * @param n the id of the Node to add
     * @return true if the Node has been added, otherwise false
     */
    public boolean addNode(int n){
        //todo
        return false;
    }

    /**
     * Method that remove the Node n to this Graph and all the connected Edges
     * @param n the Node to remove
     * @return true if the Node has been removed, otherwise false
     */
    public boolean removeNode(Node n){
        //todo
        return false;
    }

    /**
     * Method that remove the Node n to this Graph and all the connected Edges
     * @param n the id of the Node to remove
     * @return true if the Node has been removed, otherwise false
     */
    public boolean removeNode(int n){
        //todo
        return false;
    }

    /**
     * Method that return a List of all the Nodes of this Graph
     * @return a List of all the Nodes of this Graph
     */
    public List<Node> getAllNodes(){
        //todo
        return null;
    }
}
