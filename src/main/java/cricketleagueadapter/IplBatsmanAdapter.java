package cricketleagueadapter;

import opencsv.CSVBuilderException;
import pojo.IplBatsmanDTO;
import pojo.IplPlayerDAO;

import java.util.Map;

public class IplBatsmanAdapter extends CricketLeagueAdapter{
        @Override
        public Map<String, IplPlayerDAO> loadIplData(String csvFilePath) throws CSVBuilderException {
            return super.loadIplData(IplBatsmanDTO.class, csvFilePath);
        }
}
