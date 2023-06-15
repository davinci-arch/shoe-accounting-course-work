package com.example.logs.layouts;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Plugin(name = "CustomConsoleLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class CustomConsoleLayout extends AbstractStringLayout {
    protected CustomConsoleLayout(Charset charset) {
        super(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder build = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM");
        LocalDateTime localDate = LocalDateTime.now();
        return build.append("l:[").append(event.getSource().getLineNumber())
                .append("] [").append(localDate.format(dateTimeFormatter))
                .append("] [").append(event.getThreadName())
                .append("] [").append(event.getLoggerName())
                .append("] [").append(event.getSource().getClassName())
                .append("] [").append(event.getSource().getMethodName())
                .append("] [").append(event.getLevel())
                .append("] ").append(event.getMessage().getFormattedMessage()).append("\n").toString();
    }

    @PluginFactory
    public static CustomConsoleLayout getConsoleLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new CustomConsoleLayout(charset);
    }



}
