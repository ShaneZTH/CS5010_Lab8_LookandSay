package lookandsay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class LookAndSayIteratorTest {
    public static final BigInteger DEFAULT_MAX =
            ((new BigDecimal("1E100")).subtract(BigDecimal.ONE)).toBigInteger();

    LookAndSayIterator<BigInteger> iter0, iter1, iter2, iter3;
//    RIterator<BigInteger> iter1, iter2, iter3;

    BigInteger seed1 = BigInteger.valueOf(11321);
    BigInteger seed2 = BigInteger.valueOf(45183476);
    BigInteger seed3 = BigInteger.valueOf(2455);

    @Before
    public void setUp() throws Exception {
        iter0 = new LookAndSayIterator<>(seed1);
        iter1 = new LookAndSayIterator<>(seed1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initTest1() {

    }


    @Test(expected = IllegalArgumentException.class)
    public void testCustomInvalidSeed() {
        iter1 = new LookAndSayIterator<>(new BigInteger("666"),
                new BigInteger("665"));
    }

    @Test
    public void testReverseDefaultSeed() {
        LookAndSayIterator<BigInteger> iterator = new LookAndSayIterator();
        BigInteger current = new BigInteger("1");
        iterator.next(); // burn off the first one
        while (iterator.hasNext()) {
            current = iterator.next();
        }

        assertTrue("hasPrevious() should return true", iterator.hasPrevious());
        BigInteger beyondMax = iterator.prev();

        assertTrue("first prev() should return a number larger than " + DEFAULT_MAX + " but " +
                "didn't, only got " + beyondMax, beyondMax.compareTo(DEFAULT_MAX) > 0);

        BigInteger sameAsCurrent = iterator.prev();
        assertEquals("second prev() should return a number equal to last number returned by next()", current, sameAsCurrent);
        //assertEquals("First call to prev() should return same as last call to next()",
        //    current, sameMax);

//        while (iterator.hasPrevious()) {
//            BigInteger b = iterator.prev();
//            assertTrue("Number " + b.toString() + " cannot be read as " + current.toString(),
//                    decode(b, current));
//            current = b;
//        }
    }

    @Test
    public void prev1() {
        iter1 = new LookAndSayIterator<>(new BigInteger("1234"), new BigInteger("99999999"));
        System.out.println(iter1.prev());
    }

    @Test
    public void prev2() {
    }

    @Test
    public void testCustomValidSeedValidEnd1() {
        iter2 = new LookAndSayIterator<>(new BigInteger("1129141914"),
                new BigInteger("199493"));
        System.out.println(iter2.prev());
    }

    @Test
    public void next0() {
        System.out.println(iter0.next());
        System.out.println(iter0.next());
    }

    // Test for Default Seed
    @Test
    public void testDefaultSeed0() {
        iter1 = new LookAndSayIterator<>();

    }

    // Test for Default Seed
    @Test
    public void testDefaultSeed1() {
        iter1 = new LookAndSayIterator<>();

        assertEquals(BigInteger.valueOf(1), iter1.next());

//        assertTrue(iter1.hasNext());
        assertEquals(BigInteger.valueOf(11), iter1.next());
        assertEquals(BigInteger.valueOf(21), iter1.next());
        assertEquals(BigInteger.valueOf(1211), iter1.next());
        assertEquals(BigInteger.valueOf(111221), iter1.prev());
        assertEquals(BigInteger.valueOf(1211), iter1.prev());
        assertEquals(BigInteger.valueOf(21), iter1.prev());
        assertEquals(BigInteger.valueOf(11), iter1.prev());
    }

    @Test
    public void testDefaultSeed2() {
        LookAndSayIterator<BigInteger> iterator = new LookAndSayIterator();
        BigInteger previous = new BigInteger("1");
        iterator.next();

        while (iterator.hasNext()) {
            BigInteger b = iterator.next();
            assertTrue("Number " + previous.toString() + " cannot be read as " + b.toString(),
                    decode(previous, b));
            previous = b;

//            System.out.println(previous.toString() + " read as " + b.toString());

        }

        previous = iterator.next();
        assertTrue("The iterator ended but the last number was not more than " + "100 digits long",
                previous.toString().length() > 100);
    }

    private boolean decode(BigInteger a, BigInteger b) {
        return a.equals(b);
    }

    @Test
    public void testPrev1() {
        iter2 = new LookAndSayIterator<>(new BigInteger("1113122112132113"),
                new BigInteger("1113122112132113"));
    }
}