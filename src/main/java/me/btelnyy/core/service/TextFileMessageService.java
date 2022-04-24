package me.btelnyy.core.service;

import me.btelnyy.core.command.CommandRules;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TextFileMessageService {

    private static final Random RANDOM = new SecureRandom();

    private final File   file;
    private final Logger logger;

    private String[] messages;

    public TextFileMessageService(File file, Logger logger) {
        this.file   = file;
        this.logger = logger;
    }

    public void loadMessages() {
        try {
            this.messages = Files.readAllLines(file.toPath()).toArray(String[]::new);
        }
        catch (IOException exception) {
            logger.log(Level.SEVERE, "Error encountered while reading text message file");
            exception.printStackTrace();
        }
    }

    private void ensureLoaded() {
        if (messages == null)
            throw new NullPointerException("messages have not been loaded");
    }

    public String getRandomMessage() throws NoSuchElementException {
        ensureLoaded();
        if (messages.length == 0)
            throw new NoSuchElementException(String.format("no messages present in \"%s\"", file.toString()));
        return messages[RANDOM.nextInt(messages.length)];
    }

    public String[] getAllMessages() {
        ensureLoaded();
        String[] messages = new String[this.messages.length];
        System.arraycopy(this.messages, 0, messages, 0, this.messages.length);
        return messages;
    }
}
