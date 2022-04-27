package me.btelnyy.core.service;

import java.io.File;
import java.util.logging.Logger;

public class MotdMessageService extends TextFileMessageService {

    public MotdMessageService(File file, Logger logger) {
        super(file, logger);
    }
}
