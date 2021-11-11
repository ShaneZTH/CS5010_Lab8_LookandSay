package lookandsay;

import java.math.BigInteger;
import java.util.Iterator;

public interface RIterator<T> extends Iterator{

    T prev();

    @Override
    T next();

    boolean hasPrevious();
}
