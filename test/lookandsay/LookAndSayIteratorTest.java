package lookandsay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class LookAndSayIteratorTest {
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
        System.out.println(((LookAndSayIterator) (iter1)).getEndValue());
    }


    @Test (expected = IllegalArgumentException.class)
    public void testCustomInvalidSeed() {
        iter1 = new LookAndSayIterator<>(new BigInteger("666"),
                new BigInteger("665"));
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
        System.out.println(iter0.getStartSeed());
        System.out.println(iter0.getEndValue());
        System.out.println(iter0.next());
        System.out.println(iter0.next());
    }

    // Test for Default Seed
    @Test
    public void testDefaultSeed1() {
        iter1 = new LookAndSayIterator<>();

        assertEquals(BigInteger.valueOf(1), iter1.getStartSeed());

        assertTrue(iter1.hasNext());
        assertEquals(BigInteger.valueOf(11), iter1.next());

        assertTrue(iter1.hasNext());
        assertEquals(BigInteger.valueOf(21), iter1.next());
    }


    // 1113122112132113
    // 1 3 2 11 2 3 11 3
    // 1321123113
    // 3 11 2 111 3
    // 31121113
    // 111 2 1 3
    // 111213
    // 1 2 3

    // 111111121113

    // Test for Default Seed
    @Test
    public void testDefaultSeed2() {
        iter1 = new LookAndSayIterator<>();

        assertEquals(BigInteger.valueOf(1), iter1.getStartSeed());

        assertTrue(iter1.hasNext());
        assertEquals(BigInteger.valueOf(11), iter1.next());

        assertEquals(BigInteger.valueOf(1), iter1.prev());

        iter1.next();
        assertEquals(BigInteger.valueOf(21), iter1.next());
    }

    @Test
    public void testPrev1() {
        iter2 = new LookAndSayIterator<>(new BigInteger("1113122112132113"),
                new BigInteger("1113122112132113"));
        System.out.println(iter2.prev());
        System.out.printf("\t%s %s %s\n", iter2.getStartSeed(), iter2.prevVal, iter2.nextVal);
        System.out.println(iter2.prev());
        System.out.printf("\t%s %s %s\n", iter2.getStartSeed(), iter2.prevVal, iter2.nextVal);
        System.out.println(iter2.prev());
        System.out.printf("\t%s %s %s\n", iter2.getStartSeed(), iter2.prevVal, iter2.nextVal);
        System.out.println(iter2.prev());
        System.out.printf("\t%s %s %s\n", iter2.getStartSeed(), iter2.prevVal, iter2.nextVal);

        System.out.println(iter2.next());
        System.out.printf("\t%s %s %s\n", iter2.getStartSeed(), iter2.prevVal, iter2.nextVal);
        System.out.println(iter2.next());
        System.out.printf("\t%s\n", iter2.getStartSeed());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
    }
}