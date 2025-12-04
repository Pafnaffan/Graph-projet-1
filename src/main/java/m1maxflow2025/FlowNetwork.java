package m1maxflow2025;

import m1graphs2025.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlowNetwork extends Graph {

    String name;
    Map<Edge,Integer> flow;

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
                    addEdge(from,to,weight);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            do {
                n = nList.get(random.nextInt(nList.size())).to();
            } while(!augmentingPath.contains(n));
            augmentingPath.add(n);
        }
        return augmentingPath;
    }

    /**
     * Method that return an augmenting path of this FlowNetwork. The algorithm is to make a Deep-First-Search.
     * @return the augmenting path
     */
    public List<Node> augmentingPathDFS(){
        ArrayList<Node> augmentingPath = new ArrayList<>();
        //TODO
        return augmentingPath;
    }

    /**
     * Method that return an augmenting path of this FlowNetwork. The algorithm is to make a Breadth-First-Search.
     * @return the augmenting path
     */
    public List<Node> augmentingPathBFS(){
        ArrayList<Node> augmentingPath = new ArrayList<>();
        //TODO
        return augmentingPath;
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
                residualNetwork.addEdge(e.to(),e.from(),actualFlow);
                residualNetwork.removeEdge(e);
                if(diff != 0) {
                    residualNetwork.addEdge(e.from(),e.to(),diff);
                }
            }
        }
        return residualNetwork;
    }

    /**
     * Method that compute the maximum flow in this FlowNetwork by using the Ford-Fulkerson method
     */
    public void fordFulkerson(){
        //TODO
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
         FlowNetwork copy = (FlowNetwork) super.copy();
         for(Edge e : flow.keySet()){
            copy.addFlow(e,flow.get(e));
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
                List<Edge> el = adjEdList.get(n);
                Collections.sort(el);
                for (Edge e : el) {
                    sb.append("\t").append(e.from().getName()).append(" -> ").append(e.to().getName());
                    if(e.isWeighted()){
                        sb.append(" [label=").append(e.getWeight()).append(", len=").append(e.getWeight()).append("]");
                    }
                    sb.append("\n");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
