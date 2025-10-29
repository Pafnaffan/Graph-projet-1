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
     * Constructor that create this Graph with a Map to initialize
     * @param adjEdList the Map
     */
    public Graph(Map<Node, List<Edge>> adjEdList){
        this.adjEdList = adjEdList;
    }

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
     * Method that return true if the Node n have this Graph for holder, otherwise false
     * @param n the Node to test
     * @return true if the Node n have this Graph for holder, otherwise false
     */
    public boolean holdsNode(Node n){
        if(n != null){
            return n.getGraph() == this;
        }
        return false;
    }

    /**
     * Method that return true if the Node n is in this Graph, otherwise false
     * @param n the Node to test
     * @return true if the Node n is in this Graph, otherwise false
     */
    public boolean hasNode(Node n){
        if(n != null && n.getGraph() == this){
            for(Node v : getAllNodes()){
                if(Objects.equals(n,v)){
                    return true;
                }
            }
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
        return addNode(new Node(n,this));
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
        Node nRemove = getNode(n.getId());
        if(nRemove == null){
            return false;
        }
        List<Edge> inEdges = getInEdges(nRemove);
        for(Edge e : inEdges){
            removeEdge(e);
        }
        adjEdList.remove(nRemove);
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
        return getInEdges(n).size();
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
        return getOutEdges(n).size();
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
                if(Objects.equals(e.to(),v)){
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
        addEdge(from,to,null);
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the Node where the Edge start
     * @param to the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(Node from, Node to, Integer weight){
        if(!hasNode(from)){
            addNode(from);
        }
        if(!hasNode(to)){
            addNode(to);
        }
        if(weight == null) {
            adjEdList.get(from).add(new Edge(from,to));
        }
        else {
            adjEdList.get(from).add(new Edge(from,to,weight));
        }
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     */
    public void addEdge(int from, int to){
        Node nFrom = getNode(from);
        Node nTo = getNode(to);
        if(nFrom == null){
            nFrom = new Node(from,this);
        }
        if(nTo == null){
            nTo = new Node(to,this);
        }
        addEdge(nFrom,nTo);
    }

    /**
     * Method that add an Edge from the Node from to the Node to in this Graph, the Nodes need to already exists
     * @param from the id of the Node where the Edge start
     * @param to the id of the Node where the Edge end
     * @param weight the weight of the Edge
     */
    public void addEdge(int from, int to, Integer weight){
        Node nFrom = getNode(from);
        Node nTo = getNode(to);
        if(nFrom == null){
            nFrom = new Node(from,this);
        }
        if(nTo == null){
            nTo = new Node(to,this);
        }
        addEdge(nFrom,nTo,weight);
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
     * Method that remove all Edges who start with the Node from and end with the Node to in this Graph
     * @param from the start Node of the Edges to remove
     * @param to the end Node of the Edges to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Node from, Node to){
        if(holdsNode(from) && holdsNode(to)){
            List<Edge> e_remove = getEdges(from,to);
            boolean res = false;
            for(Edge e : e_remove){
                res = adjEdList.get(from).remove(e);
                if(!res){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method that remove all Edges who start with the Node from and end with the Node to with the weight in parameter in this Graph
     * @param from the start Node of the Edges to remove
     * @param to the end Node of the Edges to remove
     * @param weight the weight of the Edges to remove
     * @return true if the remove succeeded, otherwise false
     */
    public boolean removeEdge(Node from, Node to, Integer weight){
        if(holdsNode(from) && holdsNode(to)){
            List<Edge> e_remove = getEdges(from,to);
            boolean res = false;
            for(Edge e : e_remove){
                if(Objects.equals(e.getWeight(), weight)){
                    res = adjEdList.get(from).remove(e);
                    if(!res){
                        return false;
                    }
                }
            }
            return true;
        }
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
        if(e != null && holdsNode(e.from())){
            if(e.getWeight() != null){
                return removeEdge(e.from(),e.to(),e.getWeight());
            }
            else {
                return removeEdge(e.from(), e.to());
            }
        }
        return false;
    }

    /**
     * Method that return a List of all Edges leaving the Node n
     * @param n the Node to test
     * @return a List of all Edges leaving the Node n
     */
    public List<Edge> getOutEdges(Node n){
        if(holdsNode(n)){
            return adjEdList.get(n);
        }
        return null;
    }

    /**
     * Method that return a List of all Edges leaving the Node n
     * @param n the id of the Node to test
     * @return a List of all Edges leaving the Node n
     */
    public List<Edge> getOutEdges(int n){
        return getOutEdges(getNode(n));
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
                if (m != n) {
                    for (Edge e : adjEdList.get(m)) {
                        if (Objects.equals(e.to(),n)) {
                            inEdges.add(e);
                        }
                    }
                }
            }
            return inEdges;
        }
        return null;
    }

    /**
     * Method that return a List of all Edges entering the Node n
     * @param n the id of the Node to test
     * @return a List of all Edges entering the Node n
     */
    public List<Edge> getInEdges(int n){
        return getInEdges(getNode(n));
    }

    /**
     * Method that return a List of all Edges incident to the Node n, union of in and out Edges
     * @param n the Node to test
     * @return a List of all Edges incident to the Node n, union of in and out Edges
     */
    public List<Edge> getIncidentEdges(Node n){
        if(holdsNode(n)){
            List<Edge> incidentEdges = new ArrayList<>(getOutEdges(n));
            for(Edge e : getInEdges(n)){
                if(!incidentEdges.contains(e)){
                    incidentEdges.add(e);
                }
            }
            return incidentEdges;
        }
        return null;
    }

    /**
     * Method that return a List of all Edges incident to the Node n, union of in and out Edges
     * @param n the id of the Node to test
     * @return a List of all Edges incident to the Node n, union of in and out Edges
     */
    public List<Edge> getIncidentEdges(int n){
        return getIncidentEdges(getNode(n));
    }

    /**
     * Method that return a List of all the Edges going from the Node u to the Node v
     * @param u the Node where the Edges start
     * @param v the Nodes where the Edges end
     * @return a List of all the Edges going from the Node u to the Node v
     */
    public List<Edge> getEdges(Node u, Node v){
        if(holdsNode(u) && holdsNode(v)){
            List<Edge> res = new ArrayList<>();
            for(Edge e : adjEdList.get(u)){
                if(Objects.equals(e.to(),v)){
                    res.add(e);
                }
            }
            return res;
        }
        return null;
    }

    /**
     * Method that return a List of all the Edges going from the Node u to the Node v
     * @param u the id of the Node where the Edges start
     * @param v the id of the Nodes where the Edges end
     * @return a List of all the Edges going from the Node u to the Node v
     */
    public List<Edge> getEdges(int u, int v){
        return getEdges(getNode(u),getNode(v));
    }

    /**
     * Method that return a List of all the Edges of this Graph
     * @return a List of all the Edges of this Graph
     */
    public List<Edge> getAllEdges(){
        List<Edge> res = new ArrayList<>();
        for(Node n : adjEdList.keySet()){
            res.addAll(adjEdList.get(n));
        }
        return res;
    }

    /**
     * Method that return a representation of the Graph in a Successor Array
     * @return a representation of the Graph in a Successor Array
     */
    public int[] toSuccessorArray(){
        List<Node> allNode = getAllNodes();
        int[] sa = new int[allNode.size()*2];
        Collections.sort(allNode);
        int i = 0;
        for(Node n : allNode){
            for(Edge e : getOutEdges(n)){
                sa[i] = e.to().getId();
                i++;
            }
            sa[i] = 0;
            i++;
        }
        return sa;
    }

    /**
     * Method that return a representation of this Graph in a adjacency matrix
     * @return a representation of this Graph in an adjacency matrix
     */
    public int[][] toAdjMatrix(){
        List<Node> allNode = getAllNodes();
        Collections.sort(allNode);
        int[][] am = new int[allNode.size()][allNode.size()];
        for(int i = 0; i<allNode.size(); i++){
            for(int j = 0; j<allNode.size(); j++){
                if(existsEdge(allNode.get(i),allNode.get(j))){
                    am[i][j] = 1;
                } else {
                    am[i][j] = 0;
                }
            }
        }
        return am;
    }

    /**
     * Method that return the reverse of this Graph
     * @return the reverse of this Graph
     */
    public Graph getReverse(){
        Graph reverseGraph = new Graph();
        for(Node n : getAllNodes()){
            reverseGraph.addNode(n);
        }
        for(Edge e : getAllEdges()){
            reverseGraph.addEdge(e.to(),e.from());
        }
        return reverseGraph;
    }

    /**
     * Method that return the transitive closure of this Graph
     * @return the transitive closure of this Graph
     */
    public Graph getTransitiveClosure(){
        Graph tc = copy();
        for(Node u : tc.getAllNodes()){
            for(Edge p : tc.getInEdges(u)){
                for(Edge s : tc.getOutEdges(u)){
                    tc.addEdge(p.from(),s.to());
                }
            }
        }
        return tc;
    }

    /**
     * Method that return true if this Graph is a multi-graph, false otherwise
     * @return true if this Graph is a multi-graph, false otherwise
     */
    public boolean isMultiGraph(){
        for(Node u : adjEdList.keySet()){
            for(Node v : adjEdList.keySet()){
                if(isMultiEdge(u,v)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that return true if this Graph is a simple-graph, false otherwise
     * @return true if this Graph is a simple-graph, false otherwise
     */
    public boolean isSimpleGraph(){
        return !isMultiGraph();
    }

    /**
     * Method that return true if this Graph has self-loops, false otherwise
     * @return true if this Graph has self-loops, false otherwise
     */
    public boolean hasSelfLoops(){
        List<Node> nodes = this.getAllNodes();

        for (Node node : nodes) {
            List<Edge> outEdges = node.getOutEdges();

            for (Edge e : outEdges) {
                if (e.from().getId() == e.to().getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that return a transformed (possibly) this multi-graph into a simple one
     * @return a transformed (possibly) this multi-graph into a simple one
     */
    public Graph toSimpleGraph(){

        Graph simpleGraph = new Graph();
        for (Node node : this.getAllNodes()) {
            simpleGraph.addNode(new Node(node.getId(), simpleGraph));
        }

        for (Node u : this.getAllNodes()) {
            for (Edge e : u.getOutEdges()) {
                if (e.isSelfLoop()) continue;

                if (e.isMultiEdge()) continue;

                Node from = simpleGraph.getNode(e.from().getId());
                Node to = simpleGraph.getNode(e.to().getId());
                simpleGraph.addEdge(from, to);
            }
        }

        return simpleGraph;
    }

    /**
     * Method that return a copy of this Graph
     * @return a copy of this Graph
     */
    public Graph copy(){
        return new Graph(adjEdList);
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph
     * @return a List who is a Deep-First Search of this Graph
     */
    public List<Node> getDFS() {
        List<Node> visited = new ArrayList<>();
        Set<Integer> visitedIds = new HashSet<>();

        List<Node> sortedNodes = new ArrayList<>(this.getAllNodes());
        sortedNodes.sort(Comparator.comparingInt(Node::getId));

        for (Node node : sortedNodes) {
            if (!visitedIds.contains(node.getId())) {
                dfsHelper(node, visited, visitedIds);
            }
        }

        return visited;
    }

    /**
     * Helper method for recursive DFS traversal
     */
    private void dfsHelper(Node node, List<Node> visited, Set<Integer> visitedIds) {
        if (node == null || visitedIds.contains(node.getId())) {
            return;
        }

        visited.add(node);
        visitedIds.add(node.getId());

        List<Node> successors = new ArrayList<>(node.getSuccessors());
        successors.sort(Comparator.comparingInt(Node::getId));

        for (Node neighbor : successors) {
            dfsHelper(neighbor, visited, visitedIds);
        }
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph, starting from the Node u
     * @param u the Node where the DFS start
     * @return a List who is a Deep-First Search of this Graph, starting from the Node u
     */
    public List<Node> getDFS(Node u){
        List<Node> visited = new ArrayList<>();
        Set<Integer> visitedIds = new HashSet<>();

        dfsHelper(u, visited, visitedIds);
        return visited;
    }

    /**
     * Method that return a List who is a Deep-First Search of this Graph, starting from the Node u
     * @param u the id of the Node where the DFS start
     * @return a List who is a Deep-First Search of this Graph, starting from the Node u
     */
    public List<Node> getDFS(int u){
        return getDFS(getNode(u));
    }

    /**
     * Method that return a List who is a Breadth-First Search of this Graph
     * @return a List who is a Breadth-First Search of this Graph
     */
    public List<Node> getBFS(){
        List<Node> visited = new ArrayList<>();
        Set<Integer> visitedIds = new HashSet<>();

        List<Node> sortedNodes = new ArrayList<>(this.getAllNodes());
        sortedNodes.sort(Comparator.comparingInt(Node::getId));

        for (Node startNode : sortedNodes) {
            if (!visitedIds.contains(startNode.getId())) {
                bfsHelper(startNode, visited, visitedIds);
            }
        }

        return visited;
    }
    /**
     * Helper method implementing the BFS logic
     */
    private void bfsHelper(Node startNode, List<Node> visited, Set<Integer> visitedIds) {
        Queue<Node> queue = new LinkedList<>();

        visitedIds.add(startNode.getId());
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current);

            List<Node> successors = new ArrayList<>(current.getSuccessors());
            successors.sort(Comparator.comparingInt(Node::getId));

            for (Node neighbor : successors) {
                if (!visitedIds.contains(neighbor.getId())) {
                    visitedIds.add(neighbor.getId());
                    queue.add(neighbor);
                }
            }
        }
    }
    /**
     * Method that return a List who is a Breadth-First Search of this Graph, starting from the Node u
     * @param u the Node where the BFS start
     * @return a List who is a Breadth-First Search of this Graph, starting from the Node u
     */
    public List<Node> getBFS(Node u){
        List<Node> visited = new ArrayList<>();
        Set<Integer> visitedIds = new HashSet<>();
        bfsHelper(u, visited, visitedIds);
        return visited;
    }

    /**
     * Method that return a List who is a Breadth-First Search of this Graph, starting from the Node u
     * @param u the id of the Node where the BFS start
     * @return a List who is a Breadth-First Search of this Graph, starting from the Node u
     */
    public List<Node> getBFS(int u){
        return getBFS(getNode(u));
    }

    /**
     * Method that performs a rich Depth-First Search (DFS) traversal on this Graph,
     * starting from the node with the lowest identifier.
     * <p>
     * During the traversal, this method characterizes each node by its visit information
     * (color, predecessor, discovery and finish timestamps) and classifies each edge
     * according to its type (TREE, BACKWARD, FORWARD, CROSS).
     * </p>
     *
     * @param nodeVisit a map storing, for each node, its visit information
     *                  such as color, discovery/finish times, and predecessor
     * @param edgeVisit a map storing, for each edge, its visit type
     *                  (TREE, BACKWARD, FORWARD, or CROSS)
     * @return a list of nodes visited during the traversal,
     *         ordered by their finishing times
     */
    public List<Node> getDFSWithVisitInfo(Map<Node,NodeVisitInfo> nodeVisit,Map<Edge,EdgeVisitType> edgeVisit){
        List<Node> result = new ArrayList<>();
        List<Node> allNodes = this.getAllNodes();
        Collections.sort(allNodes);

        int[] time = {0};
        for (Node n : allNodes) {
            nodeVisit.put(n, new NodeVisitInfo());
        }

        for (Node n : allNodes) {
            if (nodeVisit.get(n).getColour() == NodeVisitInfo.NodeColour.WHITE) {
                dfsVisit(n, nodeVisit, edgeVisit, result, time);
            }
        }

        return result;
    }

    /**
     * Method that performs a rich Depth-First Search (DFS) traversal on this Graph,
     * starting from a specific node {@code u}.
     * <p>
     * The traversal computes, for each node, its color, predecessor,
     * discovery and finish timestamps, and for each edge, its classification type.
     * If some nodes are not reachable from {@code u}, additional DFS calls
     * are made to ensure that all nodes in the graph are visited.
     * </p>
     *
     * @param u the starting node for the DFS traversal
     * @param nodeVisit a map storing visit information for each node
     * @param edgeVisit a map storing edge classification types for each edge
     * @return a list of nodes visited during the traversal,
     *         ordered by their finishing times
     */
    public List<Node> getDFSWithVisitInfo(Node u,Map<Node,NodeVisitInfo> nodeVisit,Map<Edge,EdgeVisitType> edgeVisit){
        List<Node> result = new ArrayList<>();
        List<Node> allNodes = this.getAllNodes();
        int[] time = {0};

        for (Node n : allNodes) {
            nodeVisit.put(n, new NodeVisitInfo());
        }

        dfsVisit(u, nodeVisit, edgeVisit, result, time);

        for (Node n : allNodes) {
            if (nodeVisit.get(n).getColour() == NodeVisitInfo.NodeColour.WHITE) {
                dfsVisit(n, nodeVisit, edgeVisit, result, time);
            }
        }

        return result;
    }
    /**
     * Internal recursive helper method that performs the core logic of the rich DFS traversal.
     * <p>
     * This method updates the visit information of each node (color, discovery and finish times)
     * and classifies edges based on their traversal type (TREE, BACKWARD, FORWARD, CROSS).
     * It is called recursively for each unvisited neighbor of the current node.
     * </p>
     *
     * @param u the current node being explored
     * @param nodeVisit a map containing visit information for all nodes
     * @param edgeVisit a map containing classification types for all edges
     * @param result a list storing nodes in their finishing order
     * @param time an integer array of size 1 used as a mutable counter
     *             to record discovery and finishing timestamps
     */
    private void dfsVisit(Node u,
                          Map<Node, NodeVisitInfo> nodeVisit,
                          Map<Edge, EdgeVisitType> edgeVisit,
                          List<Node> result,
                          int[] time) {

        NodeVisitInfo infoU = nodeVisit.get(u);
        infoU.setColour(NodeVisitInfo.NodeColour.GRAY);
        infoU.setDiscoveryTime(++time[0]);

        for (Edge e : getOutEdges(u)) {
            Node v = e.to();
            NodeVisitInfo infoV = nodeVisit.get(v);

            if (infoV.getColour() == NodeVisitInfo.NodeColour.WHITE) {
                edgeVisit.put(e, EdgeVisitType.TREE);
                infoV.setPredecessor(u);
                dfsVisit(v, nodeVisit, edgeVisit, result, time);
            } else if (infoV.getColour() == NodeVisitInfo.NodeColour.GRAY) {
                edgeVisit.put(e, EdgeVisitType.BACKWARD);
            } else if (infoV.getColour() == NodeVisitInfo.NodeColour.BLACK) {
                if (infoU.getDiscoveryTime() < infoV.getDiscoveryTime()) {
                    edgeVisit.put(e, EdgeVisitType.FORWARD);
                } else {
                    edgeVisit.put(e, EdgeVisitType.CROSS);
                }
            }
        }

        infoU.setColour(NodeVisitInfo.NodeColour.BLACK);
        infoU.setFinishTime(++time[0]);
        result.add(u);
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
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append("\trankdir=LR\n");
        List<Node> nl = getAllNodes();
        Collections.sort(nl);
        for (Node n : nl) {
            List<Edge> el = getOutEdges(n);
            Collections.sort(el);
            for (Edge e : el) {
                sb.append("\t").append(e.from()).append(" -> ").append(e.to()).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
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
