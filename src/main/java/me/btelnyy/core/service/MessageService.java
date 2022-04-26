package me.btelnyy.core.service;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MessageService {

    private final File                  messageSourceFile;
    private final Configuration         configurationDefaults;
    private final Map<String, String[]> messageByKeyMap;

    public MessageService(File messageSourceFile) {
        this(messageSourceFile, null);
    }

    public MessageService(File messageSourceFile, Configuration configurationDefaults) {
        this.messageSourceFile     = messageSourceFile;
        this.configurationDefaults = configurationDefaults;
        this.messageByKeyMap       = new HashMap<>();
    }

    public void loadMessages() {
        messageByKeyMap.clear();

        YamlConfiguration messageSource = YamlConfiguration.loadConfiguration(messageSourceFile);
        if (configurationDefaults != null)
            messageSource.addDefaults(configurationDefaults);
        for (String messageKey : messageSource.getKeys(true))
            if (messageSource.isString(messageKey))
                messageByKeyMap.put(messageKey, new String[] { messageSource.getString(messageKey) });
            else if (messageSource.isList(messageKey))
                messageByKeyMap.put(messageKey, messageSource.getStringList(messageKey).toArray(String[]::new));
    }

    public void sendMessage(CommandSender sender, String key) {
        sendMessage(sender, key, new HashMap<>());
    }

    public void sendMessage(CommandSender receiver, String key, Map<String, Object> replacements) {
        String[] messageArray = messageByKeyMap.get(key);
        if (messageArray == null)
            throw new NoSuchElementException(String.format("message key \"%s\" doesn't exist in the map", key));

        for (String message : messageArray) {
            message = ChatColor.translateAlternateColorCodes('&', message);

            StringBuilder messageBuilder = new StringBuilder();
            Matcher matcher = Pattern.compile("\\{\\{(.+?)}}").matcher(message);
            while (matcher.find()) {
                String replacementName = matcher.group(1);
                Object replacement = replacements.get(replacementName);
                if (replacement == null)
                    continue;

                matcher.appendReplacement(messageBuilder, replacement.toString());
            }

            matcher.appendTail(messageBuilder);

            receiver.sendMessage(messageBuilder.toString());
        }
    }
}
