package m1graphs2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UndirectedGraph extends Graph {

    /**
     * Constructor that create this empty UndirectedGraph
     */
    public UndirectedGraph(){
        super();
    }

    /**
     * Constructor that create this UndirectedGraph with a successor array to initialize
     * @param sa the successor array in unspecified number of integers
     */
    public UndirectedGraph(int... sa){
        super(sa);
    }

    /**
     * Constructor that create this UndirectedGraph with a successor array to initialize
     * @param sa the successor array in integers array
     */
    public UndirectedGraph(List<Integer> sa){
        super(sa);
    }

    /**
     * Constructor that create this Graph with a DOT file
     * @param file the path of the DOT file
     */
    public UndirectedGraph(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.matches(".*\\d+.*")) {
                    Scanner sc = new Scanner(ligne);
                    sc.useDelimiter("\\D+");
                    int i = 0;
                    Node from = null;
                    Node to = null;
                    Integer weight = null;
                    while (sc.hasNextInt()) {
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
                        i++;
                    }
                    addEdge(from,to,weight);
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that return the in-degree of the Node n
     * @param n the Node to test
     * @return the in-degree of the Node n
     */
    public int inDegree(Node n) {
        return degree(n);
    }

    /**
     * Method that return the out-degree of the Node n
     * @param n the Node to test
     * @return the out-degree of the Node n
     */
    public int outDegree(Node n){
        return degree(n);
    }

    /**
     * Method that return the degree of the Node n
     * @param n the Node to test
     * @return the degree of the Node n
     */
    public int degree(Node n){
        if(holdsNode(n)){
            int degree = 0;
            List<Edge> edgeList = getInEdges(n);
            for(Edge e : edgeList){
                if(Objects.equals(e.from(),e.to())){
                    degree += 2;
                } else {
                    degree++;
                }
            }
            return degree;
        }
        return -1;
    }

    /**
     * Method that return a List of the successors of the Node n in this UndirectedGraph
     * @param n the Node to test
     * @return a List of the successors of the Node n in this UndirectedGraph
     */
    public List<Node> getSuccessors(Node n){
        if(holdsNode(n)) {
            List<Edge> edges = getOutEdges(n);
            List<Node> successors = new ArrayList<>();
            for (Edge e : edges) {
                if(Objects.equals(e.to(),n)){
                    if (!successors.contains(e.from())) {
                        successors.add(e.from());
                    }
                } else {
                    if (!successors.contains(e.to())) {
                        successors.add(e.to());
                    }
                }
            }
            return successors;
        }
        return null;
    }

    /**
     * Method that return a List of the successors of the Node n in this UndirectedGraph with possible duplicates
     * @param n the Node to test
     * @return a List of the successors of the Node n in this UndirectedGraph with possible duplicates
     */
    public List<Node> getSuccessorsMulti(Node n){
        if(holdsNode(n)) {
            List<Edge> edges = getOutEdges(n);
            List<Node> successors = new ArrayList<>();
            for (Edge e : edges) {
                if(Objects.equals(e.to(),n)){
                    successors.add(e.from());
                } else {
                    successors.add(e.to());
                }
            }
            return successors;
        }
        return null;
    }

    /**
     * Method that return a List of all Edges leaving the Node n
     * @param n the Node to test
     * @return a List of all Edges leaving the Node n
     */
    public List<Edge> getOutEdges(Node n){
        return getInEdges(n);
    }

    /**
     * Method that return a List of all Edges entering the Node n
     * @param n the Node to test
     * @return a List of all Edges entering the Node n
     */
    public List<Edge> getInEdges(Node n){
        if(holdsNode(n)) {
            List<Edge> inEdges = new ArrayList<>();
            for (Node m : getAllNodes()) {
                for (Edge e : adjEdList.get(m)) {
                    if (Objects.equals(e.to(),n)){
                        inEdges.add(e);
                    }
                }
            }
            List<Edge> outEdges = adjEdList.get(n);
            for(Edge e : outEdges){
                if(!Objects.equals(e.from(),e.to())) {
                    inEdges.add(e);
                }
            }
            return inEdges;
        }
        return null;
    }

    /**
     * Method that return a List of all Edges incident to the Node n, union of in and out Edges
     * @param n the Node to test
     * @return a List of all Edges incident to the Node n, union of in and out Edges
     */
    public List<Edge> getIncidentEdges(Node n){
        if(holdsNode(n)){
            return getInEdges(n);
        }
        return null;
    }

    /**
     * Method that return a representation of this UndirectedGraph in a adjacency matrix
     * @return a representation of this UndirectedGraph in an adjacency matrix
     */
    public int[][] toAdjMatrix(){
        List<Node> allNode = getAllNodes();
        Collections.sort(allNode);
        int size = allNode.size();
        int[][] am = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                List<Edge> edgeList = getEdges(allNode.get(i),allNode.get(j));
                if(edgeList != null && edgeList.isEmpty()){
                    edgeList = getEdges(allNode.get(j),allNode.get(i));
                }
                if(edgeList != null){
                    am[i][j] = edgeList.size();
                    am[j][i] = edgeList.size();
                } else {
                    am[i][j] = 0;
                    am[j][i] = 0;
                }
            }
        }
        return am;
    }

    /**
     * Method that return the reverse of this UndirectedGraph
     * @return the reverse of this UndirectedGraph
     */
    public Graph getReverse(){
        return copy();
    }

    /**
     * Method that return the transitive closure of this UndirectedGraph
     * @return the transitive closure of this UndirectedGraph
     */
    public UndirectedGraph getTransitiveClosure(){
        UndirectedGraph tc = copy();
        List<Edge> inEdge;
        for(Node u : tc.getAllNodes()){
            inEdge = tc.getInEdges(u);
            for(Edge e1 : inEdge){
                for(Edge e2 : inEdge){
                    Edge newEdge1 = new Edge(e1.from(),e2.from());
                    Edge newEdge2 = new Edge(e1.to(),e2.to());
                    Edge newEdge3 = new Edge(e1.from(),e2.to());
                    Edge newEdge4 = new Edge(e1.to(),e2.from());
                    if(!Objects.equals(newEdge1.from(),newEdge1.to()) && !tc.existsEdge(newEdge1) && !tc.existsEdge(newEdge1.to(),newEdge1.from())) {
                        tc.addEdge(newEdge1);
                    }
                    if(!Objects.equals(newEdge2.from(),newEdge2.to()) && !tc.existsEdge(newEdge2) && !tc.existsEdge(newEdge2.to(),newEdge2.from())) {
                        tc.addEdge(newEdge2);
                    }
                    if(!Objects.equals(newEdge3.from(),newEdge3.to()) && !tc.existsEdge(newEdge3) && !tc.existsEdge(newEdge3.to(),newEdge3.from())) {
                        tc.addEdge(newEdge3);
                    }
                    if(!Objects.equals(newEdge4.from(),newEdge4.to()) && !tc.existsEdge(newEdge4) && !tc.existsEdge(newEdge4.to(),newEdge4.from())) {
                        tc.addEdge(newEdge4);
                    }
                }
            }
        }
        return tc;
    }

    /**
     * Method that return true if there is at least one other Edge from u to v in this UndirectedGraph, false otherwise
     * @param u the Node from of the Edge to test
     * @param v the Node to of the Edge to test
     * @return true if there is at least one other Edge from u to v in this UndirectedGraph, false otherwise
     */
    public boolean isMultiEdge(Node u, Node v){
        if(holdsNode(u) && holdsNode(v)){
            int num = 0;
            for(Edge e : getOutEdges(u)){
                if(Objects.equals(e.to(),v)){
                    num++;
                }
                if(num > 1){
                    return true;
                }
            }
            num = 0;
            for(Edge e : getOutEdges(v)){
                if(Objects.equals(e.to(),u)){
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
     * Method that return a transformed (possibly) this multi-UndirectedGraph into a simple one
     * @return a transformed (possibly) this multi-UndirectedGraph into a simple one
     */
    public UndirectedGraph toSimpleGraph(){
        UndirectedGraph simpleGraph = new UndirectedGraph();
        for (Node n : getAllNodes()) {
            simpleGraph.addNode(n.getId());
        }
        for(Edge e : getAllEdges()){
            if(!simpleGraph.existsEdge(e.from().getId(),e.to().getId()) || !simpleGraph.existsEdge(e.to().getId(),e.from().getId())){
                addEdge(e.from().getId(),e.from().getId(),e.getWeight());
            }
        }
        return simpleGraph;
    }

    /**
     * Method that return a copy of this UndirectedGraph
     * @return a copy of this UndirectedGraph
     */
    public UndirectedGraph copy(){
        UndirectedGraph copy = new UndirectedGraph();
        for(Node n : getAllNodes()){
            copy.addNode(n.getId());
        }
        for (Edge e : getAllEdges()){
            copy.addEdge(e.from().getId(),e.to().getId());
        }
        return copy;
    }

    /**
     * Method that return a UndirectedGraph from an imported DOT file
     * @param filename the path of the DOT file without extension
     * @return a UndirectedGraph from an imported DOT file
     */
    public static UndirectedGraph fromDotFile(String filename){
        return new UndirectedGraph(filename+".gv");
    }

    /**
     * Method that return a UndirectedGraph from an imported DOT file with a different extension
     * @param filename the path of the DOT file without extension
     * @param extension the extension of the DOT file
     * @return a UndirectedGraph from an imported DOT file with a different extension
     */
    public static UndirectedGraph fromDotFile(String filename, String extension){
        return new UndirectedGraph(filename+extension);
    }
}
