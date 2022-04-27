package me.btelnyy.core.service;

import java.io.File;
import java.util.logging.Logger;

public class SuicideMessageService extends TextFileMessageService {

    public SuicideMessageService(File file, Logger logger) {
        super(file, logger);
    }
}
