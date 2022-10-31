import java.util.Comparator;

public class HuffComparator implements Comparator<HuffTree> {

    @Override
    public int compare(HuffTree arg0, HuffTree arg1) {
        int result = arg0.compareTo(arg1);

        return result;
    }

}
