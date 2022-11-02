
/**
 * Author: Azhary Munir Abdiillah
 * NIM: 215150400111015
 */

import java.util.LinkedList;
import java.util.List;

public class Pohon<T> {
    Node<T> root;

    /**
     * Constructs an empty Pohon object.
     */
    public Pohon() {
        this.root = null;
    }

    /**
     * Constructs Pohon object with specified data as the root of the tree.
     * 
     * @param data data that will be the root
     *
     */
    public Pohon(T data) {
        this.root = new Node<T>(data);
    }

    /**
     * Return {@code true} if there is no element in the tree.
     * 
     * @return {@code true} if there is no element in the tree or {@code false} if
     *         one or more element exist in the tree
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * clear all element from the tree.
     */
    public void clear() {
        this.root = null;
    }

    /**
     * traverse through all nodes in the tree.
     * 
     * @param startNode starting node to perform a search.
     * @return list of node or {@code null} if tree is empty
     *
     */
    public List<Node<T>> traverse(Node<T> startNode) {
        List<Node<T>> list = new LinkedList<Node<T>>();
        list.add(startNode);
        for (Node<T> child : startNode.children) {
            List<Node<T>> children = this.traverse(child);
            children.forEach(node -> list.add(node));
        }

        return list;
    }

    /**
     * Add a new node and attach it to a node in the tree.
     * 
     * @param newNode new node to attach
     * @param parent  node where the new node is attached
     */
    public void add(Node<T> newNode, Node<T> parent) {
        parent.children.add(newNode);
    }

    /**
     * Remove specified node from the tree.
     * 
     * @param node node to be removed
     */
    public void remove(Node<T> node) {
        Node<T> parent = this.getParent(node, this.root);
        parent.children.remove(node);
    }

    /**
     * find the node object of specified data
     * 
     * @param data      data to search for.
     * @param startNode starting node to perform a search
     * @return node of specified data
     */
    public Node<T> find(T data, Node<T> startNode) {
        if (startNode.data == data) {
            return startNode;
        }

        for (Node<T> child : startNode.children) {
            Node<T> foundNode = this.find(data, child);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    /**
     * @param node      node to search for.
     * @param startNode starting node to perform a search
     * @return parent node of specified node.
     */
    public Node<T> getParent(Node<T> node, Node<T> startNode) {
        if (startNode.children.contains(node)) {
            return startNode;
        }
        for (Node<T> child : startNode.children) {
            Node<T> foundNode = this.getParent(node, child);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    /**
     * Recursively calculate max height of specified ndoe.
     * 
     * @param node node that it's height you want to find.
     * @return height of specified node
     */
    public int heightOf(Node<T> node) {
        int output = 0;
        for (Node<T> currentNode : node.children) {
            output = Math.max(output, heightOf(currentNode) + 1);
        }
        return output;
    }

    /**
     * Recursively calculate level of specified node recursively.
     * 
     * @param searchedNode node that it's level you want to find.
     * @param startNode    starting node to perform a search.
     * @return level of specified node
     */
    private int calcLvl(Node<T> searchedNode, Node<T> startNode) {
        if (startNode == searchedNode) {
            return 1;
        }

        for (Node<T> child : startNode.children) {
            int foundNode = this.calcLvl(searchedNode, child);
            if (foundNode != -1) {
                return foundNode + 1;
            }
        }

        return -1;
    }

    /**
     * Return level of specified node.
     * 
     * @param node node that it's level you want to find.
     * @return level of the specified node
     */
    public int levelOf(Node<T> node) {
        return this.calcLvl(node, this.root);
    }

    /**
     * Returnn depth of specified node.
     * 
     * @param node node that it's depth you want to find.
     * @return depth of the specified node
     */
    public int depthOf(Node<T> node) {
        return levelOf(node) - 1;
    }

    /**
     * Recursively find all leaf nodes.
     *
     * @param startNode starting node to perform a search.
     */
    public void findLeafNodes(Node<T> startNode) {
        if (startNode.children.isEmpty()) {
            System.out.print(startNode.data + " ");
        }

        for (Node<T> child : startNode.children) {
            findLeafNodes(child);
        }
    }

    /**
     * Recursively find all non-leaf nodes.
     *
     * @param startNode starting node to perform a search.
     */
    public void findNonLeafNodes(Node<T> node) {
        if (!node.children.isEmpty()) {
            System.out.print(node.data + " ");
        }

        for (Node<T> child : node.children) {
            findNonLeafNodes(child);
        }
    }

    /**
     * Recursively find ancestors of specified node and return them as a List
     * 
     * @param node      node that it's ancestors you want to find
     * @param startNode starting node to perform a search
     * @return list of ancestors of specified node.
     */
    public LinkedList<Node<T>> findAncestor(Node<T> node, Node<T> startNode) {
        LinkedList<Node<T>> ancestors = new LinkedList<Node<T>>();

        if (startNode.children.contains(node)) {
            ancestors.add(startNode);
            return ancestors;
        }

        for (Node<T> child : startNode.children) {
            LinkedList<Node<T>> foundNode = findAncestor(node, child);
            foundNode.forEach(e -> ancestors.add(e));
            if (!ancestors.isEmpty()) {
                ancestors.add(startNode);

                return ancestors;
            }
        }

        return ancestors;
    }

    /**
     * Recursively find decendant of specified node and return them as a List
     * 
     * @param node node that it's decendant you want to find
     * @return list of decendant of specified node.
     */
    public List<Node<T>> decendantOf(Node<T> node) {
        List<Node<T>> list = new LinkedList<Node<T>>();
        for (Node<T> child : node.children) {
            list.add(child);
            List<Node<T>> children = this.decendantOf(child);
            if (!children.isEmpty()) {
                children.forEach(e -> list.add(e));
            }
        }
        return list;
    }

    public int degreeOf(Node<T> node) {
        return node.children.size();
    }

    /**
     * Return siblings list of secifed node.
     * 
     * @param node nodea that it's node you want to find
     * @return siblings list of specified node
     */
    public List<Node<T>> siblingsOf(Node<T> node) {
        List<Node<T>> list = new LinkedList<Node<T>>();
        for (final Node<T> child : this.getParent(node, this.root).children) {
            if (child != node) {
                list.add(child);
            }
        }

        return list;
    }

    @Override
    public String toString() {
        return this.traverse(this.root).toString();
    }
}
