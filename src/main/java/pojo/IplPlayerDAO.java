package pojo;

import cricketleagueanalyser.CricketLeagueAnalyser;

import java.util.Comparator;

public class IplPlayerDAO {
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
    public double averageRunsGiven;
    public double economyRate;
    public double fourWickets;
    public double fiveWickets;
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
       return null;
   }
    public Object getIplDTO(CricketLeagueAnalyser.PlayerType playerType) {
        if (playerType.equals(CricketLeagueAnalyser.PlayerType.BATSMAN))
            return new IplBatsmanDTO(playerName, runsScored, battingAverage, battingStrikeRate, ballsFaced,fours, sixes);
        if (playerType.equals(CricketLeagueAnalyser.PlayerType.BOWLER)) {
            return new IplBowlerDTO(playerName, averageRunsGiven, bowlingStrikeRate, economyRate, fourWickets,
                                       fiveWickets, wicketsTaken, oversBowled);
        }
        return null;
    }
}
