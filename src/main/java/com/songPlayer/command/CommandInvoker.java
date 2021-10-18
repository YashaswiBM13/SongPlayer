package com.songPlayer.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.songPlayer.exceptions.NoSuchCommandException;

public class CommandInvoker {

    private static final Map<String, ICommand> commandMap = new HashMap<>();

    public void registerCommand(String commandname, ICommand iCommand){
        commandMap.put(commandname, iCommand);
    }
    public ICommand getCommand(String commandName){
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException{
        ICommand iCommand = getCommand(commandName);
        if(iCommand == null){
            throw new NoSuchCommandException();
        }
        iCommand.execute(tokens);
    }
    
}