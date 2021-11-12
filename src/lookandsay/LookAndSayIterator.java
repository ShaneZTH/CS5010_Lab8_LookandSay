package lookandsay;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LookAndSayIterator<T> implements RIterator {
    public static final BigInteger DEFAULT_MAX =
            ((new BigDecimal("1E100")).subtract(BigDecimal.ONE)).toBigInteger();

    public static final String TEST_R =
            "311311222113111231131112132112311321322112111312211312111322212311322113212221";
//    private Object startSeed;
//    private Object endValue;

    private BigInteger startSeed;
    private BigInteger endValue; // The number cannot be greater than this endValue

     BigInteger nextVal;
     BigInteger prevVal;

    public LookAndSayIterator(T startSeed, T endValue) throws IllegalArgumentException {
        if (!(startSeed instanceof BigInteger) || !(endValue instanceof BigInteger)) {
            throw new IllegalArgumentException("Must be BigInteger type");
        }

        BigInteger s = (BigInteger)startSeed;
        BigInteger e = (BigInteger)endValue;

        if (s.compareTo(e) > 0) {
            throw new IllegalArgumentException("Start seed must be smaller than end value");
        }

        if (s.compareTo(BigInteger.ZERO) < 0 || e.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Start seed cannot be negative");
        }

        if ((String.valueOf(s)).contains("0")) {
            throw new IllegalArgumentException("Start seed cannot contains '0'");
        }

        this.startSeed = s;
        this.nextVal = s;
        this.prevVal = s;

        this.endValue = e;

        // TODO:
//        this.startSeed = startSeed;
//        this.nextVal = (BigInteger)startSeed;
//        this.endValue = endValue;
    }

    public LookAndSayIterator(T startSeed) throws IllegalArgumentException {
        BigInteger s = (BigInteger)startSeed;

        if (s.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Start seed cannot be negative");
        }

        if ((String.valueOf(s)).contains("0")) {
            throw new IllegalArgumentException("Start seed cannot contains '0'");
        }

        this.startSeed = (BigInteger) startSeed;
        this.nextVal = (BigInteger)startSeed;
        this.prevVal = (BigInteger)startSeed;

        // TODO:
//        this.startSeed = startSeed;
        this.endValue = DEFAULT_MAX;
    }

    public LookAndSayIterator() {
        this.startSeed = BigInteger.ONE;
        this.nextVal = BigInteger.ONE;
        this.prevVal = BigInteger.ONE;

        this.endValue = DEFAULT_MAX;
    }

    @Override
    public boolean hasPrevious() {
        // Odd length cannot have previous action performed
        if ((String.valueOf(startSeed)).length() % 2 != 0) return false;

//        if (startSeed.equals(prevVal)) calcPrev();
    // FIXME:
//        prevVal = calcPrev();
        prevVal = startSeed;

        return prevVal.compareTo(endValue) <= 0;
    }

    @Override
    public T prev() {
//        if (!hasPrevious()) return (T)endValue;

        // FIXME:
//        if (startSeed.equals(prevVal)) calcPrev();
//        nextVal = startSeed;
//        startSeed = prevVal;

        // FIXME: trying new logic
        BigInteger tmp = startSeed;
        prevVal = calcPrev();
        startSeed = prevVal;
        nextVal = startSeed;
        return (T)tmp;
    }

    private BigInteger calcPrev() {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(startSeed);

        // FIXME: ? what about odds length ?
        // 113211
        // |
        // 12221
        boolean isT = false;
        if (str.equals(TEST_R)) {
            System.out.println("\n\nInput: " + str);
            isT = true;
        }

        if (str.length() % 2 != 0) {
            throw new IllegalStateException("Idiot Test\n\t" + str);
        }

        for (int i = 0; i < str.length(); i+=2) {
            int count = str.charAt(i) - '0';
            char c = str.charAt(i+1);

            while (count-- > 0) {
                sb.append(c);
            }
        }
        if (isT) System.out.println("calcPrev: " + sb);
        return new BigInteger(sb.toString());
    }

    // 31 13 11 22 21 13 11 12 31 13 11 12 13 21 12 311321322112111312211312111322212311322113212221
    // 111 3

    @Override
    public boolean hasNext() {
//        if (startSeed.equals(nextVal)) calcNext();
        // FIXME:
//        nextVal = calcNext();
        nextVal = startSeed;

        return nextVal.compareTo(endValue) <= 0;
    }

    @Override
    public T next() {
//        if (!hasNext()) return (T)endValue;

        // FIXME: the tester's logic is that next() could be called without calling hasNext() ahead
//        if (startSeed.equals(nextVal)) calcNext();
//        prevVal = startSeed;
//        startSeed = nextVal;
        BigInteger tmp = startSeed;
        nextVal = calcNext();
        startSeed = nextVal;
        prevVal = startSeed;
        return (T)tmp;
    }

    private BigInteger calcNext() {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(startSeed);

        // 11321
        // 311321
        //  |
        // 13 21

        for (int i = 0; i < str.length();) {
            char c = str.charAt(i);
            int count = 1;

            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(j) != c) break;

                count++;
            }

            // Process the curr result
            sb.append(count+"");
            sb.append(c);

            // Update i
            i += count;
        }

        System.out.println("\nInput: " + str);
        System.out.println("calcNext: " + sb);
        return new BigInteger(sb.toString());
    }

    // TODO: remove below
    public Object getStartSeed() {
        return startSeed;
    }

    public Object getEndValue() {
        return endValue;
    }
}
