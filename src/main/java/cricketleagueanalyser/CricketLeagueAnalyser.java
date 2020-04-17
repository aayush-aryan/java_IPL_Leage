package cricketleagueanalyser;
import com.google.gson.Gson;
import cricketleagueadapter.CricketLeagueAdapter;
import cricketleagueadapter.CricketLeagueAdapterFactory;
import opencsv.CSVBuilderException;

import pojo.IplPlayerDAO;
import java.util.*;
import java.util.stream.Collectors;
public class CricketLeagueAnalyser {
    public enum SortingMode {BATTING_AVG,STRIKE_RATE,SIXES,FOURS,STRIKE_RATE_WITH_SIX,STRIKE_RATE_WITH_FOUR,
                          AVG_WITH_STRIKE_RATE,RUNS_WITH_AVG,BOWLING_AVG}
    Map<String, IplPlayerDAO> iplPlayerDAOMap = new HashMap<String, IplPlayerDAO>();
    public enum PlayerType {BATSMAN,BOWLER}
    private PlayerType playerType;
    public CricketLeagueAnalyser(){}
    public CricketLeagueAnalyser(PlayerType playerType) {
        this.playerType = playerType;
    }
    public int loadIplData(String csvFilePath) throws CSVBuilderException {
        CricketLeagueAdapter iplDataLoader = CricketLeagueAdapterFactory.getIplData(playerType);
        iplPlayerDAOMap = iplDataLoader.loadIplData(csvFilePath);
        return iplPlayerDAOMap.size();
    }
    public String getSortedIplData(SortingMode mode) throws CSVBuilderException {
        if (iplPlayerDAOMap == null || iplPlayerDAOMap.size() == 0)
            throw new CSVBuilderException("No Ipl Data",CSVBuilderException.ExceptionType.NO_IPL_DATA);
        ArrayList iplSortedData = iplPlayerDAOMap.values().stream()
                .sorted(IplPlayerDAO.getSortComparator(mode))
                .map(IplPlayerDAO  -> IplPlayerDAO.getIplDTO(playerType))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(iplSortedData);
    }
    public String sortBasedOnBowlingAverage() throws CSVBuilderException {
        return getSortedIplData(SortingMode.BOWLING_AVG);
    }
}
