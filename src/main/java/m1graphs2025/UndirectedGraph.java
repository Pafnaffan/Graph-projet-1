package m1graphs2025;

import java.util.List;

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
     * Method that return the transitive closure of this UndirectedGraph
     * @return the transitive closure of this UndirectedGraph
     */
    public UndirectedGraph getTransitiveClosure(){
        //todo
        return null;
    }

    /**
     * Method that return a transformed (possibly) this multi-graph into a simple one
     * @return a transformed (possibly) this multi-graph into a simple one
     */
    public UndirectedGraph toSimpleGraph(){
        //todo
        return null;
    }

    /**
     * Method that return a copy of this UndirectedGraph
     * @return a copy of this UndirectedGraph
     */
    public UndirectedGraph copy(){
        //todo
        return null;
    }

    /**
     * Method that return a UndirectedGraph from an imported DOT file
     * @param filename the path of the DOT file without extension
     * @return a UndirectedGraph from an imported DOT file
     */
    public static UndirectedGraph fromDotFile(String filename){
        //todo
        return null;
    }

    /**
     * Method that return a UndirectedGraph from an imported DOT file with a different extension
     * @param filename the path of the DOT file without extension
     * @param extension the extension of the DOT file
     * @return a UndirectedGraph from an imported DOT file with a different extension
     */
    public static UndirectedGraph fromDotFile(String filename, String extension){
        //todo
        return null;
    }
}
