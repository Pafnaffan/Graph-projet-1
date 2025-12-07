package m1maxflow2025;

import m1graphs2025.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlowNetwork extends Graph {

    private String name;
    private HashMap<Edge,Integer> flow = new HashMap<>();

    /**
     * Constructor that create this empty FlowNetwork
     */
    public FlowNetwork(){}

    /**
     * Constructor that create this FlowNetwork with a DOT file
     * @param file the path of the DOT file
     */
    public FlowNetwork(String file){
        int max = 0;
        ArrayList<String> nodelist = new ArrayList<>();
        Integer id1 = -1;
        Integer id2 = -1;
        int weight = -1;
        boolean needLetter = false;
        int sid = -1;
        int tid = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligne = br.readLine();
            while ((ligne = br.readLine()) != null) {
                Scanner scNumber = new Scanner(ligne);
                scNumber.useDelimiter("[^a-zA-Z0-9]+");
                id1 = -1;
                id2 = -1;
                weight = -1;
                while (scNumber.hasNext()) {
                    String x = scNumber.next();
                    if(x.matches("[0-9]+")){
                        if(id1 == -1){
                            id1 = Integer.parseInt(x);
                        }
                        else if(id2 == -1){
                            id2 = Integer.parseInt(x);
                        }
                    }
                    if(x.equals("label")){
                        if(id2 == -1){
                            needLetter = true;
                        }
                    }
                    if(x.equals("s")){
                        if(needLetter){
                            sid = id1;
                            needLetter = false;
                        }
                        if(id1 == -1){
                            id1 = 0;
                        }
                        if(id2 == -1){
                            id2 = 0;
                        }
                    }
                    if(x.equals("t")){
                        if(needLetter){
                            tid = id1;
                            needLetter = false;
                        }
                        if(id1 == -1){
                            id1 = 0;
                        }
                        if(id2 == -1){
                            id2 = 0;
                        }
                    }
                }
                if(id1 > max){
                    max = id1;
                }
                if(id2 > max){
                    max = id2;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(sid == -1){
            sid = 1;
        }
        if(tid == -1){
            tid = max+1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String ligne = br.readLine();
            Scanner scName = new Scanner(ligne);
            scName.next();
            String testName = scName.next();
            if(!Objects.equals(testName, "{")){
                name = testName;
            }

            while ((ligne = br.readLine()) != null) {
                Scanner sc = new Scanner(ligne);
                sc.useDelimiter("[^a-zA-Z0-9]+");
                id1 = -1;
                id2 = -1;
                weight = -1;
                while (sc.hasNext()) {
                    String x = sc.next();
                    if(x.matches("[0-9]+")){
                        if(id1 == -1){
                            id1 = Integer.parseInt(x);
                        }
                        else if(id2 == -1){
                            id2 = Integer.parseInt(x);
                        }
                        else {
                            weight = Integer.parseInt(x);
                        }
                    }
                    if(x.equals("s")){
                        if(id1 == -1){
                            id1 = sid;
                        }
                        else if (id2 == -1){
                            id2 = sid;
                        }
                    }
                    if(x.equals("t")){
                        if(id1 == -1){
                            id1 = tid;
                        }
                        else if (id2 == -1){
                            id2 = tid;
                        }
                    }
                }
                if(id1 != -1 && id2 != -1 && weight != -1){
                    Node from = getNode(id1);
                    if(from == null){
                        from = new Node(id1,this,(id1 == sid)? "s" : (id1 == tid)? "t" : id1.toString());
                        addNode(from);
                    }
                    Node to = getNode(id2);
                    if(to == null){
                        to = new Node(id2,this,(id2 == sid)? "s" : (id2 == tid)? "t" : id2.toString());
                        addNode(to);
                    }
                    Edge edge = new Edge(from,to,weight);
                    addEdge(edge);
                    addFlow(edge,0);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method change the name of this FlowNetwork
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that return the name of this FlowNetwork
     * @return the name of this FlowNetwork
     */
    public String getName() {
        return name;
    }

    /**
     * Method that return an augmenting path of this FlowNetwork. The algorithm is to take random edges.
     * @return the augmenting path
     */
    public List<Node> augmentingPathRandom(){
        ArrayList<Node> augmentingPath = new ArrayList<>();
        Random random = new Random();
        augmentingPath.add(getNode(1));
        List<Edge> nList = getOutEdges(getNode(1));
        Node n = nList.get(random.nextInt(nList.size())).to();
        augmentingPath.add(n);
        while (!Objects.equals(n.getName(), "t")){
            nList = getOutEdges(n);
            List<Node> visited = new ArrayList<>();
            do {
                n = nList.get(random.nextInt(nList.size())).to();
                if(!visited.contains(n)){
                    visited.add(n);
                }
            } while(!augmentingPath.contains(n) && visited.size() < nList.size());
            augmentingPath.add(n);
        }
        return augmentingPath;
    }

    /**
     * Method that return an augmenting path of this FlowNetwork. The algorithm is to make a Deep-First-Search.
     * @return the augmenting path
     */
    public List<Node> augmentingPathDFS(){
        Node source = null;
        Node sink = null;
        for (Node n : getAllNodes()) {
            if ("s".equals(n.getName())) source = n;
            if ("t".equals(n.getName())) sink = n;
        }
        if (source == null || sink == null) return new ArrayList<>();
        Map<Node, Node> parent = new HashMap<>();
        parent.put(source, null);
        if (dfsSearch(source, sink, parent)) {
            LinkedList<Node> path = new LinkedList<>();
            Node current = sink;
            while (current != null) {
                path.addFirst(current);
                current = parent.get(current);
            }
            return path;
        }
        return new ArrayList<>();
    }

    private boolean dfsSearch(Node current, Node sink, Map<Node, Node> parent){
        if (current.equals(sink)) return true;
        for (Edge e : getOutEdges(current)) {
            Node next = e.to();
            if (!parent.containsKey(next)) {
                parent.put(next, current);
                if (dfsSearch(next, sink, parent)) return true;
            }
        }
        return false;
    }


    /**
     * Method that return an augmenting path of this FlowNetwork. The algorithm is to make a Breadth-First-Search.
     * @return the augmenting path
     */
    public List<Node> augmentingPathBFS() {
        Node source = null;
        Node sink = null;
        for (Node n : getAllNodes()) {
            if ("s".equals(n.getName())) source = n;
            if ("t".equals(n.getName())) sink = n;
        }
        if (source == null || sink == null) return new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> parent = new HashMap<>();
        queue.add(source);
        parent.put(source, null);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(sink)) break;
            for (Edge e : getOutEdges(current)) {
                Node next = e.to();
                if (!parent.containsKey(next)) {
                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }
        if (!parent.containsKey(sink)) return new ArrayList<>();
        LinkedList<Node> path = new LinkedList<>();
        Node n = sink;
        while (n != null) {
            path.addFirst(n);
            n = parent.get(n);
        }
        return path;
    }

    /**
     * Method that create a residual network from the actual flow of the FlowNetwork
     * @return the residual network
     */
    public FlowNetwork computeResidualNetwork(){
        FlowNetwork residualNetwork = copy();
        for(Edge e : residualNetwork.getAllEdges()){
            int actualFlow = residualNetwork.getFlow(e);
            if(actualFlow != 0){
                int diff = e.getWeight() - residualNetwork.getFlow(e);
                Edge reverseEdge = new Edge(e.to(),e.from(),actualFlow);
                residualNetwork.addEdge(reverseEdge);
                residualNetwork.addFlow(reverseEdge,0);
                residualNetwork.removeEdge(e);
                residualNetwork.removeFlow(e);
                if(diff != 0) {
                    Edge prevEdge = new Edge(e.from(),e.to(),diff);
                    residualNetwork.addEdge(prevEdge);
                    residualNetwork.addFlow(prevEdge,0);
                }
            }
        }
        return residualNetwork;
    }

    /**
     * Method that compute the maximum flow in this FlowNetwork by using the Ford-Fulkerson method
     */
    public void fordFulkerson() {
        while (true) {
            FlowNetwork residual = computeResidualNetwork();
            List<Node> path = residual.augmentingPathDFS();
            if (path.isEmpty()) {
                break;
            }
            int bottle = Integer.MAX_VALUE;
            for (int i = 0; i < path.size() - 1; i++) {
                Node u = path.get(i);
                Node v = path.get(i + 1);
                List<Edge> forwardEdges = residual.getEdges(u.getId(), v.getId());
                Edge forward = (forwardEdges == null || forwardEdges.isEmpty()) ? null : forwardEdges.get(0);
                if (forward != null) {
                    int residualCap = forward.getWeight();
                    bottle = Math.min(bottle, residualCap);
                } else {
                    throw new RuntimeException("Residual path edge not found in original graph.");
                }
            }
            for (int i = 0; i < path.size() - 1; i++) {
                Node u = path.get(i);
                Node v = path.get(i + 1);
                List<Edge> forwardEdges = getEdges(u.getId(), v.getId());
                List<Edge> backwardEdges = getEdges(v.getId(), u.getId());
                Edge forward = forwardEdges.isEmpty() ? null : forwardEdges.get(0);
                Edge backward = backwardEdges.isEmpty() ? null : backwardEdges.get(0);
                if (forward != null) {
                    editFlow(forward, flow.get(forward) + bottle);
                } else {
                    editFlow(backward, flow.get(backward) - bottle);
                }
            }
        }
    }

    /**
     * Method that add a flow value for the Edge e
     * @param e the key Edge
     * @param i the flow value
     * @return true if the flow has been added, otherwise false
     */
    public boolean addFlow(Edge e, int i){
        if(e != null){
            flow.put(e,i);
            return true;
        }
        return false;
    }

    /**
     * Method that return the flow value of the Edge e
     * @param e the key Edge
     * @return the flow value of the Edge e
     */
    public Integer getFlow(Edge e){
        if(e != null){
            return flow.get(e);
        }
        return null;
    }

    /**
     * Method that permit to edit the flow value of the Edge e
     * @param e the Edge
     * @param i the new flow value of the Edge e
     * @return true if the flow value has been edited, otherwise false
     */
    public boolean editFlow(Edge e, int i){
        if(e != null && flow.containsKey(e)){
            return flow.put(e,i) != null;
        }
        return false;
    }

    /**
     * Method that remove the Edge e and his flow to the Map
     * @return true if it's removed, otherwise false
     */
    public boolean removeFlow(Edge e){
        if(e != null){
            return flow.remove(e) != null;
        }
        return false;
    }

    /**
     * Method that make a copy of the FlowNetwork
     * @return a copy of the FlowNetwork
     */
    public FlowNetwork copy() {
        FlowNetwork copy = new FlowNetwork();
        copy.setName(getName());
        for(Node n : getAllNodes()){
            copy.addNode(new Node(n.getId(),copy,n.getName()));
        }
        for (Edge e : getAllEdges()){
            Edge newEdge = new Edge(e.from().getId(),e.to().getId(),e.getWeight(),copy);
            copy.addEdge(newEdge);
            copy.addFlow(newEdge,flow.get(e));
        }
        return copy;
    }

    /**
     * Method that return a String of this FlowNetwork in the DOT syntax
     * @return a String of this FlowNetwork in the DOT syntax
     */
    @Override
    public String toDotString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph "+((name != null) ? name : "")+" {\n");
        sb.append("\trankdir=LR\n");
        List<Node> nl = getAllNodes();
        Collections.sort(nl);
        for (Node n : nl) {
            if(getIncidentEdges(n).isEmpty()){
                sb.append("\t").append(n).append("\n");
            } else {
                List<Edge> el = getOutEdges(n);
                Collections.sort(el);
                for (Edge e : el) {
                    sb.append("\t").append(e.from().getName()).append(" -> ").append(e.to().getName());
                    if(e.isWeighted()) {
                        sb.append(" [label=");
                        if(getFlow(e) != 0) {
                            sb.append("\"").append(getFlow(e)).append("/");
                        }
                        sb.append(e.getWeight());
                        if(getFlow(e) != 0){
                            sb.append("\"");
                        }
                        sb.append(", len=").append(e.getWeight()).append("]");
                    }
                    sb.append("\n");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
