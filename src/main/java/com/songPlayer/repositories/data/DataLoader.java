package com.songPlayer.repositories.data;

import java.util.HashMap;
import java.util.Map;

import com.songPlayer.exceptions.NoSuchDataException;

public class DataLoader {

    private static final Map<String, IData> dataMap = new HashMap<>();

    public void register(String dataName, IData iData){
        dataMap.put(dataName, iData);
    }

    public IData getIData(String dataName){
        return dataMap.get(dataName);
    }

    public void executeData(String dataName, String dataPath) throws NoSuchDataException{
        IData iData = getIData(dataName);
        if(iData == null)
            throw new NoSuchDataException();
        iData.load(dataPath, ",", "#");
    }
    
}