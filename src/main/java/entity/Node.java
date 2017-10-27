package entity;

import java.util.*;

public class Node {
    private Map<String, String> attribute;
    private String tag;
    private List<Node> childList;

    public void addAttribute(String attribute, String value) {
        this.attribute.put(attribute, value);
    }

    public String getValue() {
        return value;
    }

    public void addNode(Node node) {

        childList.add(node);
    }


    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Node> getChildList() {
        return childList;
    }


    public List<Node> getChildsByTag(String tag) {
        List<Node> list = new ArrayList<Node>(childList.size());
        for (Node node : childList) {
            if (node.tag.equals(tag)) {
                list.add(node);
            }
        }
        return list;
    }


    public Node getChildByTag(String tag) {
        for (Node node : childList) {
            if (node.tag.equals(tag)) {
                return node;
            }
        }
        return null;
    }

    public void setChildList(List childList) {
        this.childList = childList;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public Node(String tag) {
        this.attribute = new HashMap<String, String>();
        this.childList = new LinkedList();
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Node{" +
                "attribute=" + attribute +
                ", tag='" + tag + '\'' +
                ", childList size=" + childList.size() +
                ", value='" + value + '\'' +
                '}';
    }

}
