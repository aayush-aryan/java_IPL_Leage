package cricketleagueadapter;

import opencsv.CSVBuilderException;
import pojo.IplBatsmanDTO;
import pojo.IplBowlerDTO;
import pojo.IplPlayerDAO;

import java.util.Map;

public class IplBowlerAdapter extends CricketLeagueAdapter {

    @Override
    public Map<String, IplPlayerDAO> loadIplData(String csvFilePath) throws CSVBuilderException {
        return super.loadIplData(IplBowlerDTO.class, csvFilePath);
    }
}
