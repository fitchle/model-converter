package com.fitchle.modelconverterapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public final class ModelConverter {
    private final File file;
    private final String textureLocation;
    private File animationFile;

    public ModelConverter(File file, String textureLocation) {
        this.file = file;
        this.textureLocation = textureLocation;
    }

    public ModelConverter(File file, String textureLocation, File animationFile) {
        this.file = file;
        this.textureLocation = textureLocation;
        this.animationFile = animationFile;
    }

    public JsonObject convert() throws IOException {
        if (!this.file.getName().endsWith(".json")) throw new IOException("That file isn't JSON file, please check file type.");

        JsonObject oldObject = getJsonObject(this.file);
        JsonObject newObject = new JsonObject();

        JsonObject geometryObj = oldObject.getAsJsonArray("minecraft:geometry").get(0).getAsJsonObject();
        int textureWidth = geometryObj.getAsJsonObject("description").get("texture_width").getAsInt();
        int textureHeight = geometryObj.getAsJsonObject("description").get("texture_height").getAsInt();

        JsonObject textureObj = new JsonObject();
        textureObj.addProperty("location", this.textureLocation);
        textureObj.addProperty("width", textureWidth);
        textureObj.addProperty("height", textureHeight);
        newObject.add("texture", textureObj);
        if (animationFile != null) {
            JsonObject animObj = getJsonObject(this.animationFile);
            JsonObject animationsObj = animObj.getAsJsonObject("animations");
            newObject.add("animation", animationsObj);
        }
        newObject.add("elements", geometryObj.getAsJsonArray("bones"));
        return newObject;
    }

    public void convertAndSave() throws IOException {
        JsonObject obj = this.convert();
        File f = new File(this.file.getParentFile(), this.file.getName().replace(".json", "") + ".benchionmodel");
        try {
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            writer.write(obj.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }


    private JsonObject getJsonObject(File f) throws FileNotFoundException {
        return JsonParser.parseReader(new FileReader(f)).getAsJsonObject();
    }
}
