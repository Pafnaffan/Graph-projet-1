package m1maxflow2025;

import m1graphs2025.*;

import java.util.Map;

public class FlowNetwork extends Graph {

    Map<Edge,Integer> flow;

    /**
     * Constructor that create this FlowNetwork with a DOT file
     * @param file the path of the DOT file
     */
    public FlowNetwork(String file){
        //TODO
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
     * Method that remove an Edge and his flow to the Map
     * @return true if it's removed, otherwise false
     */
    public boolean removeFlow(){
        //TODO
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
        //TODO
        return super.toDotString();
    }
}
