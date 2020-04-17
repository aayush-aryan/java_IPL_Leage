package leagueanalysertest;

import com.google.gson.Gson;
import cricketleagueanalyser.CricketLeagueAnalyser;
import opencsv.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;
import pojo.IplBatsmanDTO;

public class CricketLeagueAnalyserTest {
    public static final String CORRECTED_BATTING_CSV = "./src/test/resources/IPL2019FactSheetMostRun.csv";
    public static final String CSV_WITH_DELIMITER_ERROR = "./src/test/resources/CsvWithDelimiterErr.csv";
    public static final String CSV_WITH_HEADER_ERROR = "./src/test/resources/CsvWithHeaderErr.csv";
    public static final String INCORRECT_FILE = "./src/test/resources/IPLData.csv";
    CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.PlayerType.BATSMAN);
    @Test
    public void givenCricketLeagueCsvFileIfHasCorrectNumberOfRecordsShouldReturnNumberOfRecord() {
        try {
            int numberOfRecords = leagueAnalyser.loadIplData(CORRECTED_BATTING_CSV);
            Assert.assertEquals(100, numberOfRecords);
        } catch (CSVBuilderException e) {
               e.printStackTrace();
            }
    }
    @Test
    public void givenCricketLeagueCsvFileIfHasDelimiterErrorsInItShouldThrowException() {
        try {
              leagueAnalyser.loadIplData(CSV_WITH_DELIMITER_ERROR);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }
    @Test
    public void givenCricketLeagueCsvFileIfHasHeaderErrorsInItShouldThrowException() {
        try {
            leagueAnalyser.loadIplData(CSV_WITH_HEADER_ERROR);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }
    @Test
    public void givenWrongFileAsInputToTheFileLoaderShouldThrowException() {
        try {
            leagueAnalyser.loadIplData(INCORRECT_FILE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenCricketLeagueCsvFileShouldReturnWhoHadMaxRunWithBestAverageAtFirstPosition() {
        try {
            leagueAnalyser.loadIplData(CORRECTED_BATTING_CSV);
            String sortBasedOnBattingAverage = leagueAnalyser.sortBasedOnRunsWithAverage();
            IplBatsmanDTO[] batsmenArray = new Gson().fromJson(sortBasedOnBattingAverage,IplBatsmanDTO[].class);
            Assert.assertEquals("David Warner",batsmenArray[0].playerName);
        } catch (CSVBuilderException e) {
                e.printStackTrace();
        }
    }
    @Test
    public void givenCricketLeagueCsvFileShouldReturnWhoHadMaxRunWithBestAverageAtSecondPosition() {
        try {
            leagueAnalyser.loadIplData(CORRECTED_BATTING_CSV);
            String sortBasedOnBattingAverage = leagueAnalyser.sortBasedOnRunsWithAverage();
            IplBatsmanDTO[] batsmenArray = new Gson().fromJson(sortBasedOnBattingAverage,IplBatsmanDTO[].class);
            Assert.assertEquals("KL Rahul",batsmenArray[1].playerName);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}
