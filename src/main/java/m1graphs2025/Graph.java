package m1graphs2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    private Map<Node, List<Edge>> adjEdList = new HashMap<>();

    /**
     * Constructor that create this empty Graph
     */
    public Graph(){}

    /**
     * Constructor that create this Graph with a successor array to initialize
     * @param sa the successor array in unspecified number of integers
     */
    public Graph(int... sa){
        if(sa.length > 0){
            int i_node = 1;
            Node from = new Node(i_node, this);
            addNode(from);
            for (int i = 0; i < sa.length; i++) {
                if (sa[i] == 0 && i < sa.length - 1) {
                    i_node++;
                    from = getNode(i_node);
                    if(from == null) {
                        from = new Node(i_node, this);
                        addNode(from);
                    }
                } else if (sa[i] != 0) {
                    Node to = getNode(sa[i]);
                    if (to == null) {
                        to = new Node(sa[i], this);
                        addNode(to);
                    }
                    addEdge(from, to);
                }
            }
        }
    }

    /**
     * Constructor that create this Graph with a successor array to initialize
     * @param sa the successor array in integers array
     */
    public Graph(List<Integer> sa){
        if(!sa.isEmpty()) {
            int i_node = 1;
            Node from = new Node(i_node, this);
            addNode(from);
            for (int i = 0; i < sa.size(); i++) {
                if (sa.get(i) == 0 && i < sa.size() - 1) {
                    i_node++;
                    from = new Node(i_node, this);
                    addNode(from);
                } else if (sa.get(i) != 0) {
                    Node to = getNode(sa.get(i));
                    if (to == null) {
                        to = new Node(sa.get(i), this);
                        addNode(to);
                    }
                    addEdge(from, to);
                }
            }
        }
    }

    /**
     * Constructor that create this Graph with a DOT file
     * @param file the path of the DOT file
     */
    public Graph(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.contains("->")) {
                    Scanner sc = new Scanner(ligne);
                    int i = 0;
                    Node from = null;
                    Node to = null;
                    Integer weight = null;
                    while (sc.hasNext()) {
                        if (sc.hasNextInt()) {
                            int x = sc.nextInt();
                            if(i == 0){
                                from = getNode(x);
                                if(from == null){
                                    from = new Node(x,this);
                                    addNode(from);
                                }
                            }
                            else if(i == 1) {
                                to = getNode(x);
                                if(to == null){
                                    to = new Node(x,this);
                                    addNode(to);
                                }
                            }
                            else if(i == 2) {
                                weight = x;
                            }
                        }
                        else {
                            addEdge(from,to,weight);
                            sc.next();
                        }
                        i++;
                    }
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(n != null && holdsNode(n)){
            return adjEdList.containsKey(n);
        }
        return false;
    }

    /**
     * Method that return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     * @param n the id of the Node to test
     * @return true if the id of the Node n is already used by another Node in this Graph, otherwise false
     */
    public boolean usesNode(int n){
        return usesNode(getNode(n));
    }

    /**
     * Method that return true if the Node n is a Node of this Graph, otherwise false
     * @param n the Node to test
     * @return true if the Node n is a Node of this Graph, otherwise false
     */
    public boolean holdsNode(Node n){
        if(n != null){
            return n.getGraph() == this;
        }
        return false;
    }

    /**
     * Method that return the Node held by this Graph with the id, otherwise null
     * @param id the id of the Node to get
     * @return the Node held by this Graph with the id, otherwise null
     */
    public Node getNode(int id){
        for (Node n : adjEdList.keySet()) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    /**
     * Method that add the Node n to this Graph
     * @param n the Node to add
     * @return true if the Node has been added, otherwise false
     */
    public boolean addNode(Node n){
        if(n == null || n.getGraph() != this || getNode(n.getId()) != null){
            return false;
        }
        adjEdList.put(n,new ArrayList<>());
        return true;
    }

    /**
     * Method that add the Node n to this Graph
     * @param n the id of the Node to add
     * @return true if the Node has been added, otherwise false
     */
    public boolean addNode(int n){
        return addNode(getNode(n));
    }

    /**
     * Method that remove the Node n to this Graph and all the connected Edges
     * @param n the Node to remove
     * @return true if the Node has been removed, otherwise false
     */
    public boolean removeNode(Node n){
        if(n == null){
            return false;
        }
        Node n_remove = getNode(n.getId());
        if(n_remove == null){
            return false;
        }
        adjEdList.remove(n_remove);
        return false;
    }

    /**
     * Method that remove the Node n to this Graph and all the connected Edges
     * @param n the id of the Node to remove
     * @return true if the Node has been removed, otherwise false
     */
    public boolean removeNode(int n){
        return removeNode(getNode(n));
    }

    /**
     * Method that return a List of all the Nodes of this Graph
     * @return a List of all the Nodes of this Graph
     */
    public List<Node> getAllNodes(){
        return new ArrayList<>(adjEdList.keySet());
    }

    /**
     * Method that return the largest id used by a Node of this Graph
     * @return the largest id used by a Node of this Graph
     */
    public int largestNodeId(){
        ArrayList<Node> node_list = new ArrayList<>(adjEdList.keySet());
        int res = -1;
        if(!node_list.isEmpty()){
            res = node_list.get(0).getId();
        }
        for(int i = 1; i<node_list.size(); i++){
            if(node_list.get(i).getId() > res){
                res = node_list.get(i).getId();
            }
        }
        return res;
    }

    /**
     * Method that return the smallest id used by a Node of this Graph
     * @return the smallest id used by a Node of this Graph
     */
    public int smallestNodeId(){
        ArrayList<Node> node_list = new ArrayList<>(adjEdList.keySet());
        int res = -1;
        if(!node_list.isEmpty()){
            res = node_list.get(0).getId();
        }
        for(int i = 1; i<node_list.size(); i++){
            if(node_list.get(i).getId() < res){
                res = node_list.get(i).getId();
            }
        }
        return res;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph
     * @param n the Node to test
     * @return a List of the successors of the Node n in this Graph
     */
    public List<Node> getSuccessors(Node n){
        if(holdsNode(n)) {
            List<Edge> edges = adjEdList.get(n);
            List<Node> successors = new ArrayList<>();
            for (Edge e : edges) {
                if (!successors.contains(e.to())) {
                    successors.add(e.to());
                }
            }
            return successors;
        }
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph without duplicates
     * @param n the id of the Node to test
     * @return a List of the successors of the Node n in this Graph without duplicates
     */
    public List<Node> getSuccessors(int n){
        return getSuccessors(getNode(n));
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph with possible duplicates
     * @param n the Node to test
     * @return a List of the successors of the Node n in this Graph with possible duplicates
     */
    public List<Node> getSuccessorsMulti(Node n){
        if(holdsNode(n)) {
            List<Edge> edges = adjEdList.get(n);
            List<Node> successors = new ArrayList<>();
            for (Edge e : edges) {
                successors.add(e.to());
            }
            return successors;
        }
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this Graph with possible duplicates
     * @param n the id of the Node to test
     * @return a List of the successors of the Node n in this Graph with possible duplicates
     */
    public List<Node> getSuccessorsMulti(int n){
        return getSuccessorsMulti(getNode(n));
    }

    /**
     * Method that return true if the Nodes u and v are adjacent in this Graph, otherwise false
     * @param u the first Node to test
     * @param v the second Node to test
     * @return true if the Nodes u and v are adjacent in this Graph, otherwise false
     */
    public boolean adjacent(Node u, Node v){
        if(holdsNode(u) && holdsNode(v)) {
            for (Edge e : adjEdList.get(u)) {
                if (e.to() == v) {
                    return true;
                }
            }
            for (Edge e : adjEdList.get(v)) {
                if (e.to() == u) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Method that return true if the Nodes u and v are adjacent in this Graph, otherwise false
     * @param u the id of the first Node to test
     * @param v the id of the second Node to test
     * @return true if the Nodes u and v are adjacent in this Graph, otherwise false
     */
    public boolean adjacent(int u, int v){
        return adjacent(getNode(u),getNode(v));
    }

    /**
     * Method that return the in-degree of the Node n
     * @param n the Node to test
     * @return the in-degree of the Node n
     */
    public int inDegree(Node n) {
        if(holdsNode(n)) {
            int in_degree = 0;
            for (Node m : getAllNodes()) {
                if (m != n) {
                    for (Edge e : adjEdList.get(m)) {
                        if (e.to() == n) {
                            in_degree++;
                        }
                    }
                }
            }
            return in_degree;
        }
        return -1;
    }

    /**
     * Method that return the in-degree of the Node n
     * @param n the id of the Node to test
     * @return the in-degree of the Node n
     */
    public int inDegree(int n){
        return inDegree(getNode(n));
    }

    /**
     * Method that return the out-degree of the Node n
     * @param n the Node to test
     * @return the out-degree of the Node n
     */
    public int outDegree(Node n){
        if(holdsNode(n)){
            return adjEdList.get(n).size();
        }
        return -1;
    }

    /**
     * Method that return the out-degree of the Node n
     * @param n the id of the Node to test
     * @return the out-degree of the Node n
     */
    public int outDegree(int n){
        return outDegree(getNode(n));
    }

    /**
     * Method that return the degree of the Node n
     * @param n the Node to test
     * @return the degree of the Node n
     */
    public int degree(Node n){
        if(holdsNode(n)){
            return inDegree(n) + outDegree(n);
        }
        return -1;
    }

    /**
     * Method that return the degree of the Node n
     * @param n the id of the Node to test
     * @return the degree of the Node n
     */
    public int degree(int n){
        return degree(getNode(n));
    }

    /**
     * Method that return the number of Edges of this Graph
     * @return the number of Edges of this Graph
     */
    public int nbEdges(){
        int number = 0;
        for(Node n : getAllNodes()){
            number += adjEdList.get(n).size();
        }
        return number;
    }

    /**
     * Method that return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     * @param u the first Node to test
     * @param v the second Node to test
     * @return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     */
    public boolean existsEdge(Node u, Node v){
        return adjacent(u,v);
    }

    /**
     * Method that return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     * @param u the id of the first Node to test
     * @param v the id of the second Node to test
     * @return true if an Edge exists between the Nodes u and v in this Graph, false otherwise
     */
    public boolean existsEdge(int u, int v){
        return adjacent(u,v);
    }

    /**
     * Method that return true if the Edge e exist in this Graph, false otherwise
     * @param e the Edge to test
     * @return true if the Edge e exist in this Graph, false otherwise
     */
    public boolean existsEdge(Edge e){
        if(e != null && holdsNode(e.from())){
            for(Edge f : getAllEdges()){
                if(f == e){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that return true if there is at least one other Edge from u to v in this Graph, false otherwise
     * @param u the Node from of the Edge to test
     * @param v the Node to of the Edge to test
     * @return true if there is at least one other Edge from u to v in this Graph, false otherwise
     */
    public boolean isMultiEdge(Node u, Node v){
        if(holdsNode(u) && holdsNode(v)){
            int num = 0;
            for(Edge e : getOutEdges(u)){
                if(e.to() == v){
                    num++;
                }
                if(num > 1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that return true if there is at least one other Edge from u to v in this Graph, false otherwise
     * @param u the Node from of the Edge to test
     * @param v the Node to of the Edge to test
     * @return true if there is at least one other Edge from u to v in this Graph, false otherwise
     */
    public boolean isMultiEdge(int u, int v){
        return isMultiEdge(getNode(u),getNode(v));
    }

    /**
     * Method that return true if there is at least one other Edge like the Edge e in this Graph, false otherwise
     * @param e the Edge to test
     * @return true if there is at least one other Edge like the Edge e in this Graph, false otherwise
     */
    public boolean isMultiEdge(Edge e){
        return isMultiEdge(e.from(),e.to());
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the Node where the Edge start
     * @param to the Node where the Edge end
     */
    public void addEdge(Node from, Node to){
        if(holdsNode(from) && holdsNode(to)){
            if(holdsNode(from) && holdsNode(to)){
                adjEdList.get(from).add(new Edge(from,to));
            }
        }
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the Node where the Edge start
     * @param to the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(Node from, Node to, Integer weight){
        if(holdsNode(from) && holdsNode(to)){
            if(weight == null){
                addEdge(from,to);
            }
            else {
                if(holdsNode(from) && holdsNode(to)){
                    adjEdList.get(from).add(new Edge(from,to,weight));
                }
            }
        }
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     */
    public void addEdge(int from, int to){
        addEdge(getNode(from),getNode(to));
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(int from, int to, Integer weight){
        addEdge(getNode(from),getNode(to),weight);
    }

    /**
     * Method that add the Edge e in this Graph, the Nodes where the Edge start and end need to already exists
     * @param e the Edge to add
     */
    public void addEdge(Edge e){
        if(e != null && holdsNode(e.from())){
            adjEdList.get(e.from()).add(e);
        }
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the start Node of the Edge to remove
     * @param to the end Node of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Node from, Node to){
        if(holdsNode(from) && holdsNode(to)){
            List<Edge> e_remove = getEdges(from,to);
            for(Edge e : e_remove){
                adjEdList.get(from).remove(e);
            }
            return true;
        }
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
        return removeEdge(getNode(from),getNode(to));
    }

    /**
     * Method that remove an Edge who start with the Node from and end with the Node to in this Graph
     * @param from the id of the start Node of the Edge to remove
     * @param to the id of the end Node of the Edge to remove
     * @param weight the weight of the Edge to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(int from, int to, Integer weight){
        return removeEdge(getNode(from),getNode(to),weight);
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
