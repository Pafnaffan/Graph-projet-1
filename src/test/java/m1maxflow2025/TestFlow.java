package m1maxflow2025;

public class TestFlow {

    public static void main(String[] args) {
        FlowNetwork fn = new FlowNetwork("maxFlowTest_STLabels.gv");
        System.out.println(fn.toDotString());
    }
}
