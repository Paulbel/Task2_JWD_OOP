package entity;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private Node rootNode;

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }


    public List<Node> findListNodesByTag(String tag) {
        List<Node> list = new ArrayList<Node>();
        findNodesByTag(list, rootNode, tag);
        return list;
    }

    private void findNodesByTag(List<Node> list, Node parentNode, String tag) {
        //System.out.println(parentNode);
        if (parentNode.getTag().equals(tag)) {
            list.add(parentNode);
        }
        for (Node node : parentNode.getChildList()) {
            findNodesByTag(list, node, tag);
        }
    }


    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line = printInfo(rootNode, line, 0);
        return String.valueOf(line);
    }


    private StringBuilder printInfo(Node node, StringBuilder line, int count) {
        boolean addedNumeration = false;
        List<Node> childNodes = node.getChildList();
        if (childNodes.size() == 0) {
            line.append(node.getValue());
            line.append(" ");
        } else {
            for (Node childNode : childNodes) {
                if (childNode.getValue() != null) {
                    if (!addedNumeration) {
                        line.append(count);
                        line.append(". ");
                        addedNumeration = true;

                    } else {
                        line.append("- ");
                    }
                }
                count++;
                printInfo(childNode, line, count);

            }
            if (addedNumeration){
                line.append("\n");
            }

        }
        return line;
    }

}
