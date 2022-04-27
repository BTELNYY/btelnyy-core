package me.btelnyy.core.service;

import java.io.File;
import java.util.logging.Logger;

public class RulesMessageService extends TextFileMessageService {

    public RulesMessageService(File file, Logger logger) {
        super(file, logger);
    }
}
