package cricketleagueadapter;

import opencsv.CSVBuilderException;
import opencsv.CsvBuilderFactory;
import opencsv.ICsvBuilder;
import org.apache.commons.collections.map.HashedMap;
import pojo.IplBatsmanDTO;
import pojo.IplBowlerDTO;
import pojo.IplPlayerDAO;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CricketLeagueAdapter {
    public abstract Map<String, IplPlayerDAO> loadIplData(String csvFilePath) throws CSVBuilderException ;

    <E> Map<String, IplPlayerDAO> loadIplData(Class<E> iplCSVClass, String getPath) throws CSVBuilderException {
        Map<String, IplPlayerDAO> iplPlayerDAOMap = new HashedMap();
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCsvFileIterator(reader, iplCSVClass);
            Iterable<E> csvIplIterable = () -> csvFileIterator;
            if (iplCSVClass.getName().equals("pojo.IplBatsmanDTO"))
                StreamSupport.stream(csvIplIterable.spliterator(), false)
                        .map(IplBatsmanDTO.class::cast)
                        .forEach(IplBatsmanDTO -> iplPlayerDAOMap.put(IplBatsmanDTO.playerName, new IplPlayerDAO(IplBatsmanDTO)));
            if (iplCSVClass.getName().equals("pojo.IplBowlerDTO"))
                StreamSupport.stream(csvIplIterable.spliterator(), false)
                        .map(IplBowlerDTO.class::cast)
                        .forEach(IplBowlerDTO -> iplPlayerDAOMap.put(IplBowlerDTO.playerName, new IplPlayerDAO(IplBowlerDTO)));
            return iplPlayerDAOMap;
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
