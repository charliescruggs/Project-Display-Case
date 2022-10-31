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
 * File name and the second is the name of the decompressed file.
 * 
 * A sample execution might look like the following: $ java JMZip MyPaper.jmz MyPaper.doc.
 * 
 * @author Charles Scruggs
 * 
 * @version 10/29/17
 * 
 * Acknowledgments: I received help from professor Sprague on building
 * my frequency map, some code was borrowed from the TEXTBOOK
 */
public class JMUnzip {

    public static void main(String[] args) {
        HuffTree ht;
        BitSequence encoding;
        HuffmanSave hs;
        String compressedFileName;
        String decompressedFileName;

        File file;
        
		compressedFileName = "zippedResume.jmz";
		decompressedFileName = "unzippedResume.doc";

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

    /**
     * This method recreates the original file from the BitSequence using the huffman tree
     * 
     * @param node is the root of the huffman tree to be traversed as the file is read
     */
    private static void traverseTreeToFile(HuffBaseNode root,
            String decompressedFileName, BitSequence encoding, HuffTree ht) {
    	int num = 0;
        try {
          int byt;
          FileOutputStream fOutputStream = new FileOutputStream(decompressedFileName);
          BufferedOutputStream bOutputStream = new BufferedOutputStream(fOutputStream);
          while (num < encoding.length()) {

            while (!root.isLeaf()) {
              byt = encoding.getBit(num);
              num = num + 1;
              if (byt == 0) {
                root = ((HuffInternalNode) root).left();
              } else {
                root = ((HuffInternalNode) root).right();
              }
            }
            bOutputStream.write(((HuffLeafNode) root).value());
            root = ht.root();
          }
          System.out.print("unzip complete");
          bOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return a huffman tree based on the frequency hashmap
     */
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

    /**
     * @param fileName is the name of the file to be deserialized
     */
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