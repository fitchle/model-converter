package com.fitchle.modelconvertercli;

import com.fitchle.modelconverterapi.ModelConverter;

import java.io.File;
import java.io.IOException;

public final class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println(ConsoleColor.TEXT_WHITE.getUnicode() + "-----------------------------------------------");
            System.out.println(ConsoleColor.TEXT_RED.getUnicode() + "[FITCHLE-MODEL-CONVERTER/ERROR] " + ConsoleColor.TEXT_WHITE.getUnicode() + "Wrong argument usage!");
            System.out.println(ConsoleColor.TEXT_GREEN.getUnicode() + "[FITCHLE-MODEL-CONVERTER/INFO] " + ConsoleColor.TEXT_WHITE.getUnicode() + "Example Usage: java -jar [<jarfile>] [<*.json>] [<texture_location>] [animation_location]");
            System.out.println(ConsoleColor.TEXT_WHITE.getUnicode() + "-----------------------------------------------");
            return;
        }
        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println("File Not Found!");
            return;
        }
        ModelConverter converter = new ModelConverter(f, args[1]);
        if (args.length == 3) {
            converter = new ModelConverter(f, args[1], new File(args[2]));
        }
        converter.convertAndSave();
        System.out.println(ConsoleColor.TEXT_WHITE.getUnicode() + "-----------------------------------------------");
        System.out.println(ConsoleColor.TEXT_GREEN.getUnicode() + "[FITCHLE-MODEL-CONVERTER/INFO] " + ConsoleColor.TEXT_WHITE.getUnicode() + "Converted succesfully!");
        System.out.println(ConsoleColor.TEXT_GREEN.getUnicode() + "[FITCHLE-MODEL-CONVERTER/INFO] " + ConsoleColor.TEXT_WHITE.getUnicode() + "File Location: " + f.getAbsolutePath().replace(".json", "") + ".benchionmodel");
        System.out.println(ConsoleColor.TEXT_WHITE.getUnicode() + "-----------------------------------------------");
    }
}
