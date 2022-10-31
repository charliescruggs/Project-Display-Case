/**
 * Huffman tree node: Leaf class
 * 
 * Acknowledgments: This class was borrowed from the textbook, then modified by
 * me
 */
public class HuffInternalNode implements HuffBaseNode {
    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    /** Constructor */
    HuffInternalNode(HuffBaseNode l, HuffBaseNode r, int wt) {
        left = l;
        right = r;
        weight = wt;
    }

    /**
     * @return The left child
     */
    HuffBaseNode left() {
        return left;
    }

    /**
     * @return The right child
     */
    HuffBaseNode right() {
        return right;
    }

    /**
     * @return The weight
     */
    public int weight() {
        return weight;
    }

    /** Return false */
    public boolean isLeaf() {
        return false;
    }
}