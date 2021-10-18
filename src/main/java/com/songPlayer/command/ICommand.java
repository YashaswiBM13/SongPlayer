package com.songPlayer.command;

import java.util.List;

public interface ICommand {
    public void execute(List<String> tokens);
}