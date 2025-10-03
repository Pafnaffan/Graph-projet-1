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
        /**
        int i = 1;
        for(int x : sa){
            Node from = new Node(i,this);
            addNode(from);
            if(x == 0){
                i++;
            }
            else {

            }
        }
         */
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
        return adjEdList.size();
    }

    /**
     * Method that return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     * @param n the Node to test
     * @return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     */
    public boolean usesNode(Node n){
        return adjEdList.containsKey(n);
    }

    /**
     * Method that return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     * @param n the id of the Node to test
     * @return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     */
    public boolean usesNode(int n){
        return adjEdList.containsKey(getNode(n));
    }

    /**
     * Method that return true if the Node n is a Node of this Graph, otherwise false
     * @param n the Node to test
     * @return true if the Node n is a Node of this Graph, otherwise false
     */
    public boolean holdsNode(Node n){
        return n.getGraph() == this;
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

    /**
     * Method that return the largest id used by a Node of this Graph
     * @return the largest id used by a Node of this Graph
     */
    public int largestNodeId(){
        //todo
        return -1;
    }

    /**
     * Method that return the smallest id used by a Node of this Graph
     * @return the smallest id used by a Node of this Graph
     */
    public int smallestNodeId(){
        //todo
        return -1;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph
     * @param n the Node to test
     * @return a List of the successors of the Node n in this Graph
     */
    public List<Node> getSuccessors(Node n){
        //todo
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph without duplicates
     * @param n the id of the Node to test
     * @return a List of the successors of the Node n in this Graph without duplicates
     */
    public List<Node> getSuccessors(int n){
        //todo
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph with possible duplicates
     * @param n the Node to test
     * @return a List of the successors of the Node n in this Graph with possible duplicates
     */
    public List<Node> getSuccessorsMulti(Node n){
        //todo
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph with possible duplicates
     * @param n the id of the Node to test
     * @return a List of the successors of the Node n in this Graph with possible duplicates
     */
    public List<Node> getSuccessorsMulti(int n){
        //todo
        return null;
    }

    /**
     * Method that return true if the Nodes u and v are adjacent in this Graph, otherwise false
     * @param u the first Node to test
     * @param v the second Node to test
     * @return true if the Nodes u and v are adjacent in this Graph, otherwise false
     */
    public boolean adjacent(Node u, Node v){
        //todo
        return false;
    }

    /**
     * Method that return true if the Nodes u and v are adjacent in this Graph, otherwise false
     * @param u the id of the first Node to test
     * @param v the id of the second Node to test
     * @return true if the Nodes u and v are adjacent in this Graph, otherwise false
     */
    public boolean adjacent(int u, int v){
        //todo
        return false;
    }

    /**
     * Method that return the in-degree of the Node n
     * @param n the Node to test
     * @return the in-degree of the Node n
     */
    public int inDegree(Node n){
        //todo
        return -1;
    }

    /**
     * Method that return the in-degree of the Node n
     * @param n the id of the Node to test
     * @return the in-degree of the Node n
     */
    public int inDegree(int n){
        //todo
        return -1;
    }

    /**
     * Method that return the out-degree of the Node n
     * @param n the Node to test
     * @return the out-degree of the Node n
     */
    public int outDegree(Node n){
        //todo
        return -1;
    }

    /**
     * Method that return the out-degree of the Node n
     * @param n the id of the Node to test
     * @return the out-degree of the Node n
     */
    public int outDegree(int n){
        //todo
        return -1;
    }

    /**
     * Method that return the degree of the Node n
     * @param n the Node to test
     * @return the degree of the Node n
     */
    public int degree(Node n){
        //todo
        return -1;
    }

    /**
     * Method that return the degree of the Node n
     * @param n the id of the Node to test
     * @return the degree of the Node n
     */
    public int degree(int n){
        //todo
        return -1;
    }

    /**
     * Method that return the number of Edges of this Graph
     * @return the number of Edges of this Graph
     */
    public int nbEdges(){
        //todo
        return -1;
    }

    /**
     * Method that return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     * @param u the first Node to test
     * @param v the second Node to test
     * @return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     */
    public boolean existsEdge(Node u, Node v){
        //todo
        return false;
    }

    /**
     * Method that return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     * @param u the id of the first Node to test
     * @param v the id of the second Node to test
     * @return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     */
    public boolean existsEdge(int u, int v){
        //todo
        return false;
    }

    /**
     * Method that return true if the Edge e exist in this Graph, false otherwise
     * @param e the Edge to test
     * @return true if the Edge e exist in this Graph, false otherwise
     */
    public boolean existsEdge(Edge e){
        //todo
        return false;
    }

    /**
     * Method that return true if there is at least one other Edge from u to v in this Graph, false otherwise
     * @param u the first Node to test
     * @param v the second Node to test
     * @return true if there is at least one other Edge from u to v in this Graph, false otherwise
     */
    public boolean isMultiEdge(Node u, Node v){
        //todo
        return false;
    }

    /**
     * Method that return true if there is at least one other Edge from u to v in this Graph, false otherwise
     * @param u the id of the first Node to test
     * @param v the id of the second Node to test
     * @return true if there is at least one other Edge from u to v in this Graph, false otherwise
     */
    public boolean isMultiEdge(int u, int v){
        //todo
        return false;
    }

    /**
     * Method that return true if there is at least one other Edge like the Edge e in this Graph, false otherwise
     * @param e the Edge to test
     * @return true if there is at least one other Edge like the Edge e in this Graph, false otherwise
     */
    public boolean isMultiEdge(Edge e){
        //todo
        return false;
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the Node where the Edge start
     * @param to the Node where the Edge end
     */
    public void addEdge(Node from, Node to){
        //todo
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the Node where the Edge start
     * @param to the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(Node from, Node to, Integer weight){
        //todo
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     */
    public void addEdge(int from, int to){
        //todo
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(int from, int to, Integer weight){
        //todo
    }

    /**
     * Method that add the Edge e in this Graph, the Nodes where the Edge start and end need to already exists
     * @param e the Edge to add
     */
    public void addEdge(Edge e){
        //todo
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the start Node of the Edge to remove
     * @param to the end Node of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Node from, Node to){
        //todo
        return false;
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the start Node of the Edge to remove
     * @param to the end Node of the Edge to remove
     * @param weight the weight of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Node from, Node to, Integer weight){
        //todo
        return false;
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the id of the start Node of the Edge to remove
     * @param to the id of the end Node of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(int from, int to){
        //todo
        return false;
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the id of the start Node of the Edge to remove
     * @param to the id of the end Node of the Edge to remove
     * @param weight the weight of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(int from, int to, Integer weight){
        //todo
        return false;
    }

    /**
     * Method that remove the Edge e in this Graph
     * @param e the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Edge e){
        //todo
        return false;
    }

    /**
     * Method that return a List of all Edges leaving the Node n
     * @param n the Node to test
     * @return a List of all Edges leaving the Node n
     */
    public List<Edge> getOutEdges(Node n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all Edges leaving the Node n
     * @param n the id of the Node to test
     * @return a List of all Edges leaving the Node n
     */
    public List<Edge> getOutEdges(int n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all Edges entering the Node n
     * @param n the Node to test
     * @return a List of all Edges entering the Node n
     */
    public List<Edge> getInEdges(Node n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all Edges entering the Node n
     * @param n the id of the Node to test
     * @return a List of all Edges entering the Node n
     */
    public List<Edge> getInEdges(int n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all Edges incident to the Node n, union of in and out Edges
     * @param n the Node to test
     * @return a List of all Edges incident to the Node n, union of in and out Edges
     */
    public List<Edge> getIncidentEdges(Node n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all Edges incident to the Node n, union of in and out Edges
     * @param n the id of the Node to test
     * @return a List of all Edges incident to the Node n, union of in and out Edges
     */
    public List<Edge> getIncidentEdges(int n){
        //todo
        return null;
    }

    /**
     * Method that return a List of all the Edges going from the Node u to the Node v
     * @param u the Node where the Edges start
     * @param v the Nodes where the Edges end
     * @return a List of all the Edges going from the Node u to the Node v
     */
    public List<Edge> getEdges(Node u, Node v){
        //todo
        return null;
    }

    /**
     * Method that return a List of all the Edges going from the Node u to the Node v
     * @param u the id of the Node where the Edges start
     * @param v the id of the Nodes where the Edges end
     * @return a List of all the Edges going from the Node u to the Node v
     */
    public List<Edge> getEdges(int u, int v){
        //todo
        return null;
    }

    /**
     * Method that return a List of all the Edges of this Graph
     * @return a List of all the Edges of this Graph
     */
    public List<Edge> getAllEdges(){
        //todo
        return null;
    }

    /**
     * Method that return a representation of the Graph in a Successor Array
     * @return a representation of the Graph in a Successor Array
     */
    public int[] toSuccessorArray(){
        //todo
        return null;
    }

    /**
     * Method that return a representation of this Graph in a adjacency matrix
     * @return a representation of this Graph in an adjacency matrix
     */
    public int[][] toAdjMatrix(){
        //todo
        return null;
    }

    /**
     * Method that return the reverse of this Graph
     * @return the reverse of this Graph
     */
    public Graph getReverse(){
        //todo
        return null;
    }

    /**
     * Method that return the transitive closure of this Graph
     * @return the transitive closure of this Graph
     */
    public Graph getTransitiveClosure(){
        //todo
        return null;
    }

    /**
     * Method that return true if this Graph is a multi-graph, false otherwise
     * @return true if this Graph is a multi-graph, false otherwise
     */
    public boolean isMultiGraph(){
        //todo
        return false;
    }

    /**
     * Method that return true if this Graph is a simple-graph, false otherwise
     * @return true if this Graph is a simple-graph, false otherwise
     */
    public boolean isSimpleGraph(){
        //todo
        return false;
    }

    /**
     * Method that return true if this Graph has self-loops, false otherwise
     * @return true if this Graph has self-loops, false otherwise
     */
    public boolean hasSelfLoops(){
        //todo
        return false;
    }

    /**
     * Method that return a transformed (possibly) this multi-graph into a simple one
     * @return a transformed (possibly) this multi-graph into a simple one
     */
    public Graph toSimpleGraph(){
        //todo
        return null;
    }

    /**
     * Method that return a copy of this Graph
     * @return a copy of this Graph
     */
    public Graph copy(){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph
     * @return a List who is a Deep-First Search of this Graph
     */
    public List<Node> getDFS(){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph, starting from the Node u
     * @param u the Node where the DFS start
     * @return a List who is a Deep-First Search of this Graph, starting from the Node u
     */
    public List<Node> getDFS(Node u){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph, starting from the Node u
     * @param u the id of the Node where the DFS start
     * @return a List who is a Deep-First Search of this Graph, starting from the Node u
     */
    public List<Node> getDFS(int u){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Breadth-First Search of this Graph
     * @return a List who is a Breadth-First Search of this Graph
     */
    public List<Node> getBFS(){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Breadth-First Search of this Graph, starting from the Node u
     * @param u the Node where the BFS start
     * @return a List who is a Breadth-First Search of this Graph, starting from the Node u
     */
    public List<Node> getBFS(Node u){
        //todo
        return null;
    }

    /**
     * Method that return a List who is a Breadth-First Search of this Graph, starting from the Node u
     * @param u the id of the Node where the BFS start
     * @return a List who is a Breadth-First Search of this Graph, starting from the Node u
     */
    public List<Node> getBFS(int u){
        //todo
        return null;
    }

    /**
     * Method that
     * @param nodeVisit
     * @param edgeVisit
     * @return
     */
    public List<Node> getDFSWithVisitInfo(Map<Node,NodeVisitInfo> nodeVisit,Map<Edge,EdgeVisitType> edgeVisit){
        //todo
        return null;
    }

    /**
     * Method that
     * @param u
     * @param nodeVisit
     * @param edgeVisit
     * @return
     */
    public List<Node> getDFSWithVisitInfo(Node u,Map<Node,NodeVisitInfo> nodeVisit,Map<Edge,EdgeVisitType> edgeVisit){
        //todo
        return null;
    }

    /**
     * Method that return a Graph from an imported DOT file
     * @param filename the path of the DOT file without extension
     * @return a Graph from an imported DOT file
     */
    public static Graph fromDotFile(String filename){
        //todo
        return null;
    }

    /**
     * Method that return a Graph from an imported DOT file with a different extension
     * @param filename the path of the DOT file without extension
     * @param extension the extension of the DOT file
     * @return a Graph from an imported DOT file with a different extension
     */
    public static Graph fromDotFile(String filename, String extension){
        //todo
        return null;
    }

    /**
     * Method that return a String of this Graph in the DOT syntax
     * @return a String of this Graph in the DOT syntax
     */
    public String toDotString(){
        //todo
        return null;
    }

    /**
     * Method that export a DOT file of this Graph
     * @param fileName the path of the DOT file without extension
     */
    public void toDotFile(String fileName){
        //todo
    }

    /**
     * Method that export a DOT file of this Graph with a different extension
     * @param fileName the path of the DOT file without extension
     * @param extension the extension of the DOT file
     */
    public void toDotFile(String fileName, String extension){
        //todo
    }
}
