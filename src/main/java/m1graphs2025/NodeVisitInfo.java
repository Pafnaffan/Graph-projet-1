package m1graphs2025;

public class NodeVisitInfo {
    enum NodeColour {WHITE, GRAY, BLACK}

    private NodeColour colour;
    private Node predecessor;
    private Integer discoveryTime;
    private Integer finishTime;

    public NodeVisitInfo() {
        this.colour = NodeColour.WHITE;
        this.predecessor = null;
        this.discoveryTime = null;
        this.finishTime = null;
    }

    public NodeColour getColour() { return colour; }
    public void setColour(NodeColour colour) { this.colour = colour; }

    public Node getPredecessor() { return predecessor; }
    public void setPredecessor(Node predecessor) { this.predecessor = predecessor; }

    public Integer getDiscoveryTime() { return discoveryTime; }
    public void setDiscoveryTime(Integer discoveryTime) { this.discoveryTime = discoveryTime; }

    public Integer getFinishTime() { return finishTime; }
    public void setFinishTime(Integer finishTime) { this.finishTime = finishTime; }

}

