package opencsv;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICsvBuilder {
    public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
}
