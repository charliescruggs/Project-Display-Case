import java.util.PriorityQueue;

/**
 * A Huffman coding tree
 * 
 * Acknowledgements: Parts of this class were borrowed from the TEXTBOOK
 * getMinByteValue() method idea from classmate's Piazza Post titled:
 * "getMinByteValue" for recursive function to check for ties.
 */
public class HuffTree implements Comparable {

    private HuffBaseNode root;

    /** Constructors */
    HuffTree(byte el, int wt) {
        root = new HuffLeafNode(el, wt);
    }

    HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) {
        root = new HuffInternalNode(l, r, wt);
    }

    HuffBaseNode root() {
        return root;
    }

    int weight() {
        return root.weight();
    }

    public int compareTo(Object t) {
        HuffTree that = (HuffTree) t;

        if (root.weight() < that.weight()) {
            return -1;
        } else if (root.weight() == that.weight()) {
            // Checks if the current minimum Byte is less than the object t's
            // tree weight
            if (getMinByteValue(root) < getMinByteValue(that.root)) {
                return -1;
            }

            return 1;
        } else {
            return 1;
        }
    }

    static HuffTree buildTree(PriorityQueue<HuffTree> pq) {
        HuffTree tmp1 = null;
        HuffTree tmp2 = null;
        HuffTree tmp3 = null;

        while (pq.size() > 1) {
            tmp1 = pq.remove();
            tmp2 = pq.remove();

            int weight = tmp1.weight() + tmp2.weight();

            tmp3 = new HuffTree(tmp1.root(), tmp2.root(), weight);

            pq.add(tmp3);
        }

        return tmp3;
    }

    /**
     * This method recursively traverses the HuffmanTree to find its Minimum
     * Byte value and return it. The base case allows us to stop recursion calls
     * when we reach the minimum Byte which must be a Leaf.
     * 
     * @param root2
     *            the node to start traversing at
     * @return the minimum Byte in the Huffmantree
     */
    private int getMinByteValue(HuffBaseNode root2) {
        if (root2.isLeaf()) {
            return ((HuffLeafNode) root2).value();
        } else {
            return Math.min(getMinByteValue(((HuffInternalNode) root2).left()),
                    getMinByteValue(((HuffInternalNode) root2).right()));
        }
    }
}