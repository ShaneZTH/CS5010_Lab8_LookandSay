package lookandsay;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LookAndSayIterator<T> implements RIterator {
    public static final BigInteger DEFAULT_END =
            ((new BigDecimal("1E100")).subtract(BigDecimal.ONE)).toBigInteger();
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
        this.endValue = DEFAULT_END;
    }

    public LookAndSayIterator() {
        this.startSeed = BigInteger.ONE;
        this.nextVal = BigInteger.ONE;
        this.prevVal = BigInteger.ONE;

        this.endValue = DEFAULT_END;
    }

    @Override
    public boolean hasPrevious() {
        // Odd length cannot have previous action performed
        if ((String.valueOf(startSeed)).length() % 2 != 0) return false;

        if (startSeed.equals(prevVal)) calcPrev();

        return prevVal.compareTo(endValue) <= 0;
    }

    @Override
    public T prev() {
        if (!hasPrevious()) return (T)endValue;

        if (startSeed.equals(prevVal)) calcPrev();

        nextVal = startSeed;
        startSeed = prevVal;
        return (T)startSeed;
    }

    private void calcPrev() {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(startSeed);

        // FIXME: ? what about odds length ?
        // 113211
        // |
        // 12221

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

        prevVal = new BigInteger(sb.toString());
    }

    @Override
    public boolean hasNext() {
        if (startSeed.equals(nextVal)) calcNext();

        return nextVal.compareTo(endValue) <= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) return (T)endValue;

        if (startSeed.equals(nextVal)) calcNext();

        prevVal = startSeed;
        startSeed = nextVal;
        return (T)startSeed;
    }

    private void calcNext() {
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
        nextVal = new BigInteger(sb.toString());
    }

    // TODO: remove below
    public Object getStartSeed() {
        return startSeed;
    }

    public Object getEndValue() {
        return endValue;
    }
}
