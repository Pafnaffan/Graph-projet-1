package m1graphs2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
                if (ligne.contains("--")) {
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
     * Method that return the transitive closure of this UndirectedGraph
     * @return the transitive closure of this UndirectedGraph
     */
    public UndirectedGraph getTransitiveClosure(){
        UndirectedGraph tc = copy();
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
     * Method that return true if this UndirectedGraph is a multi-graph, false otherwise
     * @return true if this UndirectedGraph is a multi-graph, false otherwise
     */
    public boolean isMultiGraph(){
        for(Node u : getAllNodes()){
            for(Node v : getAllNodes()){
                if(isMultiEdge(u,v)){
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
