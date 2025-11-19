package m1maxflow2025;

import m1graphs2025.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FlowNetwork extends Graph {

    String name;
    Map<Edge,Integer> flow;

    /**
     * Constructor that create this FlowNetwork with a DOT file
     * @param file the path of the DOT file
     */
    public FlowNetwork(String file){
        //TODO
    }

    /**
     * Method that create a residual network from the actual flow of the FlowNetwork
     * @return the residual network
     */
    public FlowNetwork computeResidualNetwork(){
        //TODO
        return null;
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
