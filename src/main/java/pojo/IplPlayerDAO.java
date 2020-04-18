package pojo;

import cricketleagueanalyser.CricketLeagueAnalyser;

import java.util.Comparator;

public class IplPlayerDAO {
    public double averageRunsGiven;
    public String playerName;
    public int runsScored=1;
    public Double battingAverage =0.0;
    public Double battingStrikeRate=0.0;
    public int ballsFaced=0;
    public int fours=0;
    public int sixes=0;
    public double bowlingAverage=99;
    public double bowlingStrikeRate=99;
    public double bowlerEconomy=99;
    public double bowler5Wickets=0;
    public int wicketsTaken=1;
    public double bowler4Wickets=0;
    public int ballsBowled=999;
    public double oversBowled;
    public IplPlayerDAO() {
    }
    public IplPlayerDAO(IplBatsmanDTO batsmanData) {
        this.playerName = batsmanData.playerName;
        this.runsScored = Math.max(this.runsScored,batsmanData.runsScored);
        this.battingAverage = batsmanData.battingAverage;
        this.battingStrikeRate = batsmanData.battingStrikeRate;
        this.ballsFaced = batsmanData.ballsFaced;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
    }
    public IplPlayerDAO(IplBowlerDTO iplBowlerData) {
        this.playerName = iplBowlerData.playerName;
        this.bowlingAverage = iplBowlerData.averageRunsGiven;
        this.bowlingStrikeRate = iplBowlerData.bowlingStrikeRate;
        this.bowlerEconomy = iplBowlerData.economyRate;
        this.bowler4Wickets = iplBowlerData.fourWickets;
        this.bowler5Wickets = iplBowlerData.fiveWickets;
        this.averageRunsGiven = iplBowlerData.averageRunsGiven;
        this.wicketsTaken = Math.max(this.wicketsTaken,iplBowlerData.wicketsTaken);
        this.ballsBowled = (int) (Math.round(iplBowlerData.oversBowled)*6 + ((iplBowlerData.oversBowled*10)%10));
    }
   public static Comparator< ? super IplPlayerDAO> getSortComparator(CricketLeagueAnalyser.SortingMode mode){
       if (mode.equals(CricketLeagueAnalyser.SortingMode.BATTING_AVG))
           return  Comparator.comparing(iplBatsmanData -> iplBatsmanData.battingAverage, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.STRIKE_RATE))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.battingStrikeRate, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.SIXES))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.sixes, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.FOURS))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.fours, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.STRIKE_RATE_WITH_SIX)) {
           Comparator<IplPlayerDAO> strikeRate = Comparator.comparing(iplBatsmanData -> iplBatsmanData.battingStrikeRate, Comparator.reverseOrder());
           return strikeRate.thenComparing(iplBatsmanData -> iplBatsmanData.sixes, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.STRIKE_RATE_WITH_FOUR)) {
           Comparator<IplPlayerDAO> avg = Comparator.comparing(iplBatsmanData -> iplBatsmanData.battingStrikeRate, Comparator.reverseOrder());
           return avg.thenComparing(iplBatsmanData -> iplBatsmanData.fours, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.AVG_WITH_STRIKE_RATE)) {
           Comparator<IplPlayerDAO> avg = Comparator.comparing(iplBatsmanData -> iplBatsmanData.battingAverage, Comparator.reverseOrder());
           return avg.thenComparing(iplBatsmanData -> iplBatsmanData.battingStrikeRate, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.RUNS_WITH_AVG)) {
           Comparator<IplPlayerDAO> runs = Comparator.comparing(iplBatsmanData -> iplBatsmanData.runsScored, Comparator.reverseOrder());
           return runs.thenComparing(iplBatsmanData -> iplBatsmanData.battingAverage, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.BOWLING_AVG))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlingAverage, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.STRIKE_RATE_OF_BOWLER))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlingStrikeRate, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.ECONOMY_RATE))
           return Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlerEconomy, Comparator.reverseOrder());
       if (mode.equals(CricketLeagueAnalyser.SortingMode.SR_WITH_FIVE_WKT)) {
           Comparator<IplPlayerDAO> sr = Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlingStrikeRate, Comparator.reverseOrder());
           return sr.thenComparing(iplBatsmanData -> iplBatsmanData.bowler5Wickets, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.SR_WITH_FOUR_WKT)) {
           Comparator<IplPlayerDAO> sr = Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlingStrikeRate, Comparator.reverseOrder());
           return sr.thenComparing(iplBatsmanData -> iplBatsmanData.bowler4Wickets, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.BOWLING_AVG_WITH_SR)) {
           Comparator<IplPlayerDAO> bowlingAvg = Comparator.comparing(iplBatsmanData -> iplBatsmanData.bowlingAverage, Comparator.reverseOrder());
           return bowlingAvg.thenComparing(iplBatsmanData -> iplBatsmanData.bowlingStrikeRate, Comparator.reverseOrder());
       }
       if (mode.equals(CricketLeagueAnalyser.SortingMode.WKT_WITH_BOWLING_AVG)) {
           Comparator<IplPlayerDAO> wkt = Comparator.comparing(iplBatsmanData -> iplBatsmanData.wicketsTaken, Comparator.reverseOrder());
           return wkt.thenComparing(iplBatsmanData -> iplBatsmanData.bowlingAverage, Comparator.reverseOrder());
       }
       return null;
   }
    public Object getIplDTO(CricketLeagueAnalyser.PlayerType playerType) {
        if (playerType.equals(CricketLeagueAnalyser.PlayerType.BATSMAN))
            return new IplBatsmanDTO(playerName, runsScored, battingAverage, battingStrikeRate, ballsFaced,fours, sixes);
        if (playerType.equals(CricketLeagueAnalyser.PlayerType.BOWLER)) {
            return new IplBowlerDTO(playerName, bowlingAverage, bowlingStrikeRate, bowlerEconomy, bowler4Wickets,
                    bowler5Wickets, wicketsTaken, oversBowled);
        }
        return null;
    }
}
