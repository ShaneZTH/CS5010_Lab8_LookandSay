package lookandsay;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LookAndSayIterator<T> implements RIterator {
    public static final BigInteger DEFAULT_MAX =
            ((new BigDecimal("1E100")).subtract(BigDecimal.ONE)).toBigInteger();

    private BigInteger startSeed;
    private BigInteger endValue;

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
        this.endValue = e;
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
        this.endValue = DEFAULT_MAX;
    }

    public LookAndSayIterator() {
        this.startSeed = BigInteger.ONE;
        this.endValue = DEFAULT_MAX;
    }

    @Override
    public boolean hasPrevious() {
        BigInteger prevVal = calcPrev();
        if (prevVal == startSeed) return false;
        return prevVal.compareTo(endValue) <= 0;
    }

    @Override
    public T prev() {
        BigInteger tmp = startSeed;
        startSeed = calcPrev();
        return (T)tmp;
    }

    private BigInteger calcPrev() {
        String str = String.valueOf(startSeed);
        if (str.length() % 2 != 0) return startSeed;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i+1 < str.length(); i+=2) {
            int count = str.charAt(i) - '0';
            char c = str.charAt(i+1);
            while (count-- > 0) sb.append(c);
        }
        return new BigInteger(sb.toString());
    }

    @Override
    public boolean hasNext() {
        return startSeed.compareTo(endValue) <= 0;
    }

    @Override
    public T next() {
        BigInteger tmp = startSeed;
        startSeed = calcNext();
        return (T)tmp;
    }

    private BigInteger calcNext() {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(startSeed);

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

            i += count;
        }
        return new BigInteger(sb.toString());
    }
}
