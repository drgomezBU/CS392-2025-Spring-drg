import java.util.Iterator;
import java.util.function.Consumer;

public interface Iterable2<T> extends Iterable<T>
{
    Iterator<T>    iterator(); // forward
    Iterator<T>    riterator(); // backward
    void           forEach(Consumer<? super T> action); // forward
    void           rforEach(Consumer<? super T> action); // backward
}
