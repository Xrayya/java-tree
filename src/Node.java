
/**
 * Author: Azhary Munir Abdiillah
 * NIM: 215150400111015
 */

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    T data;
    List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<Node<T>>();
    }
}
