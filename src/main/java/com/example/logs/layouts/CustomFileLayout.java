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

@Plugin(name = "CustomFileLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class CustomFileLayout extends AbstractStringLayout {

    public CustomFileLayout(Charset charset) {
        super(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder build = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM");
        LocalDateTime localDate = LocalDateTime.now();
        return build.append("{\n")
                .append("\t\"line\":").append("\"").append(event.getSource().getLineNumber()).append("\"\n")
                .append("\t\"date\":").append("\"").append(localDate.format(dateTimeFormatter)).append("\"\n")
                .append("\t\"threadName\":").append("\"").append(event.getThreadName()).append("\"\n")
                .append("\t\"logName\":").append("\"").append(event.getLoggerName()).append("\"\n")
                .append("\t\"className\":").append("\"").append(event.getSource().getClassName()).append("\"\n")
                .append("\t\"methodName\":").append("\"").append(event.getSource().getMethodName()).append("\"\n")
                .append("\t\"level\":").append("\"").append(event.getLevel()).append("\"\n")
                .append("\t\"message\":").append("\"").append(event.getMessage().getFormattedMessage()).append("\"\n")
                .append("}\n").toString();
    }

    @PluginFactory
    public static CustomFileLayout getFileLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new CustomFileLayout(charset);
    }
}
