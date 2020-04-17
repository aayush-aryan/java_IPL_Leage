package cricketleagueadapter;

import cricketleagueanalyser.CricketLeagueAnalyser;
import opencsv.CSVBuilderException;

public class CricketLeagueAdapterFactory {
    public static CricketLeagueAdapter getIplData(CricketLeagueAnalyser.PlayerType playerType) throws CSVBuilderException {
        switch (playerType) {
            case BATSMAN:
                return new IplBatsmanAdapter();
            case BOWLER:
                return new IplBowlerAdapter();
            default:
                throw new CSVBuilderException("incorrect Type", CSVBuilderException.ExceptionType.INCORRECT_PLAYER_TYPE);
        }
    }
}
