import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
/**
 * JMUnZip will take two command line arguments. The first is the compressed
 * File name and the second is the name of the decompressed file. A sample
 * execution might look like the following: $ java JMZip MyPaper.doc 
 * MyPaper.jmz.
 * 
 * @author Charles Scruggs
 * 
 * @version 10/29/17
 * 
 */
public class JMUnzip {

    public static void main(String[] args) {
        HuffTree ht;
        BitSequence encoding;
        HuffmanSave hs;
        String compressedFileName;
        String decompressedFileName;

        File file;

        if (args.length != 2) {
            System.out.println("error, enter two filenames");
            return;
        }

        if (!(args[0]).endsWith(".jmz")) {
            System.out.println("error, enter two filenames");
            return;
        }

        compressedFileName = args[0];
        decompressedFileName = args[1];

        file = new File(compressedFileName);

        if (!file.exists()) {
            System.out.println("File does not exists");
            return;
        }

        // Is this ok??
        hs = decompress(compressedFileName);

        // get the encoding from the HuffmanSave Object
        encoding = hs.getEncoding();

        // get the frequencies HashMap from the encoded HuffmanSave file
        HashMap<Byte, Integer> frequencies = hs.getFrequencies();

        // use frequencies HashMap and encoding to determine if the file has
        // only one byte.
        if (encoding.length() == 1) {
            byte eightBits;
            Set set = frequencies.entrySet();
            Iterator iterator = set.iterator();

            Map.Entry<Byte, Integer> frequency = (Map.Entry<Byte, Integer>) iterator.next();

            eightBits = frequency.getKey();

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(decompressedFileName));

                for (int i = 0; i < frequency.getValue(); i++) {
                    outputStream.write(eightBits);
                }

                return;
            } catch (Exception e) {
                System.out.println("error reading file");
                e.printStackTrace();
            }
        }

        /**
         * if the encodings length is zero, make a Huffman Tree
         */
        if (encoding.length() != 0) {
            ht = buildHuffmanTree(frequencies);

            traverseTreeToFile(ht.root(), decompressedFileName, encoding, ht);
        } else {
            try {
                new BufferedOutputStream(new FileOutputStream(decompressedFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void traverseTreeToFile(HuffBaseNode root,
            String decompressedFileName, BitSequence encoding, HuffTree ht) {
        try {
            int intByte;
            boolean rootIsLeaf = root.isLeaf();

            BufferedOutputStream outputStream =
                    new BufferedOutputStream(new FileOutputStream(decompressedFileName));

            for (int i = 0; i < encoding.length(); i++) {
                while (!rootIsLeaf) {
                    intByte = encoding.getBit(i);
                    if (intByte == 0) {
                        root = ((HuffInternalNode) root).left();
                    } else {
                        root = ((HuffInternalNode) root).right();
                    }
                }

                root = ht.root();

                outputStream.write(((HuffLeafNode) root).value());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HuffTree buildHuffmanTree(HashMap<Byte, Integer> frequencies) {
        Comparator<HuffTree> comparator = new HuffComparator();
        PriorityQueue<HuffTree> pq = new PriorityQueue<HuffTree>(frequencies.size(), comparator);

        HuffTree tree;

        Set entrySet = frequencies.entrySet();
        Iterator freqIt = entrySet.iterator();

        while (freqIt.hasNext()) {
            Map.Entry<Byte, Integer> frequencyVal = (Map.Entry<Byte, Integer>) freqIt.next();
            tree = new HuffTree(frequencyVal.getKey(), frequencyVal.getValue());

            pq.add(tree);
        }

        return HuffTree.buildTree(pq);
    }

    private static HuffmanSave decompress(String compressedFileName) {
        HuffmanSave hs;
        try {
            ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(compressedFileName));

            hs = (HuffmanSave) inputStream.readObject();

            inputStream.close();

            return hs;
        } catch (Exception e) {
            System.out.println("error reading file");
            e.printStackTrace();

            return null;
        }
    }

}
