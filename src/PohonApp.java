
/**
 * Author: Azhary Munir Abdiillah
 * NIM: 215150400111015
 */

public class PohonApp {
    public static void main(String[] args) {
        Pohon<String> p = new Pohon<String>("H");

        p.add(new Node<String>("D"), p.root);
        p.add(new Node<String>("K"), p.root);

        p.add(new Node<String>("F"), p.find("D", p.root));
        p.add(new Node<String>("B"), p.find("D", p.root));

        Pohon<String> p2 = new Pohon<String>("J");
        p2.add(new Node<String>("I"), p2.root);

        p.add(p2.root, p.find("K", p.root));

        System.out.print("Ancestors of I are: ");
        p.findAncestor(p.find("I", p.root), p.root).forEach(node -> System.out.print(node.data + " "));

        System.out.println();

        System.out.print("Ancestors of B are: ");
        p.findAncestor(p.find("B", p.root), p.root).forEach(node -> System.out.print(node.data + " "));
    }
}
