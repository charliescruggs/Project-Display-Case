/**
 * Huffman tree node: Leaf class
 * 
 * Acknowledgments: This class was borrowed from the textbook, then modified by
 * me
 */
public class HuffLeafNode implements HuffBaseNode {
    private Byte element; // Element for this node
    private int weight; // Weight for this node

    /** Constructor */
    HuffLeafNode(byte el, int wt) {
        element = el;
        weight = wt;
    }

    /**
     * @return The element value
     */
    byte value() {
        return element;
    }

    /**
     * @return The weight
     */
    public int weight() {
        return weight;
    }

    /** Return true */
    public boolean isLeaf() {
        return true;
    }
}