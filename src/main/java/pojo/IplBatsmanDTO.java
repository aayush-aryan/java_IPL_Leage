package pojo;

import com.opencsv.bean.CsvBindByName;

public class IplBatsmanDTO {
    @CsvBindByName(column = "POS", required = true)
    public int position;

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Mat", required = true)
    public int matchesPlayed;

    @CsvBindByName (column = "Inns", required = true)
    public int inningsPlayed;

    @CsvBindByName (column = "NO", required = true)
    public int notOuts;

    @CsvBindByName (column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName (column = "HS", required = true)
    public String highScore;

    @CsvBindByName (column = "Avg", required = true)
    public Double battingAverage;

    @CsvBindByName (column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName (column = "SR", required = true)
    public Double battingStrikeRate;

    @CsvBindByName (column = "100", required = true)
    public int hundreds;

    @CsvBindByName (column = "50", required = true)
    public int fifties;

    @CsvBindByName (column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public IplBatsmanDTO(String playerName, int runsScored, Double battingAverage,
                         Double battingStrikeRate, int ballsFaced, int fours, int sixes) {
        this.playerName = playerName;
        this.runsScored = runsScored;
        this.battingAverage = battingAverage;
        this.battingStrikeRate = battingStrikeRate;
        this.ballsFaced = ballsFaced;
        this.fifties = fifties;
        this.fours = fours;
        this.sixes = sixes;
    }
    public IplBatsmanDTO(){}
}
