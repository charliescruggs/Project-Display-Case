import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * JMZip will take two command line arguments. The first is the the file to
 * compress, and the second is the name of a file to create. A sample execution
 * might look like the following: $ java JMZip MyPaper.doc MyPaper.jmz
 * 
 * @author Charles Scruggs
 * 
 * @version 10/29/17
 * 
 *          Acknowledgments: I received help from professor sprague on building
 *          my frequency map some code was borrowed from the TEXTBOOK
 */
public class JMZip {
    // static String input = "src/newtext.txt"; //TEST VALUE

    public static void main(String[] args) {
        HuffTree ht;
        BitSequence encoding;
        HuffmanSave hs;
        String inputFileName;
        String compressedFileName;

        File file;

        if (args.length != 2) {
            System.out.println("error, enter two filenames");
            return;
        }

        if ((args[1]).contains("/")) {
            System.out.println("error, enter two filenames");
            return;
        }

        inputFileName = args[0];
        compressedFileName = args[1];

        file = new File(inputFileName);

        if (!file.exists()) {
            System.out.print("file does not exist");
            return;
        }

        // Builds Frequency HashMap of the input file's bytes
        HashMap<Byte, Integer> frequencies = buildFreqMap(file);

        if (frequencies.size() == 1) {
            encoding = new BitSequence();
            encoding.appendBit(1);

            hs = new HuffmanSave(encoding, frequencies);
        } else if (frequencies.size() != 0) {
            ht = buildHuffmanTree(frequencies);
            encoding = buildBitSeq(ht, file);

            hs = new HuffmanSave(encoding, frequencies);
        } else {
            hs = new HuffmanSave(new BitSequence(), frequencies);
        }

        try {
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(new FileOutputStream(compressedFileName));
            outputStream.writeObject(hs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method builds a huffman tree using the priorityQueue that is made
     * from the frequencies2 entrySet().
     * 
     * @param frequencies2
     *            the frequencies & their bytes to begin making a Huffman tree
     * @return the output file
     */
    private static HuffTree buildHuffmanTree(HashMap<Byte, Integer> frequencies2) {
        Comparator<HuffTree> comparator = new HuffComparator();
        PriorityQueue pq = new PriorityQueue<HuffTree>(frequencies2.size(), comparator);

        Set entrySet = frequencies2.entrySet();

        Iterator freqIt = entrySet.iterator();

        while (freqIt.hasNext()) {
            Map.Entry<Byte, Integer> frequencyVal = (Map.Entry<Byte, Integer>) freqIt.next();
            HuffTree tree = new HuffTree(frequencyVal.getKey(), frequencyVal.getValue());

            pq.add(tree);
        }

        return HuffTree.buildTree(pq);
    }

    /**
     * Creates a BitSequence/encoding.
     * 
     * @param hf
     * @param file
     * @return a bitsequence
     */
    private static BitSequence buildBitSeq(HuffTree hf, File file) {
        BitSequence bitSeq = new BitSequence();

        HashMap<Byte, String> byteMap = new HashMap<Byte, String>();

        buildByteMap(hf.root(), "", byteMap);

        try {
            FileInputStream f = new FileInputStream(file);
            BufferedInputStream inputStream =
                    new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));

            // byte to be read in
            byte eightBits;
            // byte converted to an int
            int intByte;

            while ((intByte = inputStream.read()) != -1) {
                eightBits = (byte) intByte;

                bitSeq.appendBits(byteMap.get(eightBits));
            }

            inputStream.close();
        } catch (Exception e) {
            System.out.println("error reading file");
            e.printStackTrace();

            return null;
        }

        return bitSeq;
    }

    /**
     * This method creates a HashMap of bytes as keys and the huffman encoded
     * bitSequence as the values. This method recursively builds the bitMap.
     * 
     * @param root
     *            the starting node
     * @param byteMap
     * @param string
     *            the bitSequence
     */
    private static void buildByteMap(HuffBaseNode root, String encoding,
            HashMap<Byte, String> byteMap) {
        boolean rootIsLeaf = root.isLeaf();
        if (rootIsLeaf) {
            byteMap.put(((HuffLeafNode) root).value(), encoding);
        } else {
            buildByteMap(((HuffInternalNode) root).left(), encoding += "0", byteMap);

            encoding = encoding.substring(0, encoding.length() - 1);

            buildByteMap(((HuffInternalNode) root).right(), encoding += "1", byteMap);
        }
    }

    /**
     * Builds frequency map.
     * 
     * @param frequencies
     * @return HashMap of frequencies
     */
    private static HashMap<Byte, Integer> buildFreqMap(File file) {
        // The hashMap to return
        HashMap<Byte, Integer> frequencies = new HashMap<Byte, Integer>();

        try {
            // wrap the input stream as a BufferedInputStream
            BufferedInputStream inputStream =
                    new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));

            if (inputStream.available() > 0) {
                // the byte to be read in
                byte eightBits;
                // byte converted to an int
                int intByte;

                while ((intByte = inputStream.read()) != -1) {
                    eightBits = (byte) intByte;

                    if (frequencies.containsKey(eightBits)) {
                        Integer frequency = frequencies.get(eightBits) + 1;

                        // removes existing byte from map and replaces it with
                        // the same byte but with an incremented frequency
                        frequencies.remove(eightBits);
                        frequencies.put(eightBits, frequency);
                    } else {
                        frequencies.put(eightBits, 1);
                    }
                }
            }
            return frequencies;
        } catch (IOException e) {
            System.out.println("error reading file");
            e.printStackTrace();

            return null;
        }
    }
}
