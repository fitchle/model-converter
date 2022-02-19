package com.fitchle.modelconvertercli;

public enum ConsoleColor {
        TEXT_RESET("\u001B[0m"),
        TEXT_BLACK("\u001B[30m"),
        TEXT_RED("\u001B[31m"),
        TEXT_GREEN("\u001B[32m"),
        TEXT_YELLOW("\u001B[33m"),
        TEXT_BLUE("\u001B[34m"),
        TEXT_PURPLE("\u001B[35m"),
        TEXT_CYAN("\u001B[36m"),
        TEXT_WHITE("\u001B[37m");

        protected final String unicode;

        ConsoleColor(String unicode) {
            this.unicode = unicode;
        }

        public String getUnicode() {
            return unicode;
        }
}
