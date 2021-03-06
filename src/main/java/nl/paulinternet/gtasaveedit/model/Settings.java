package nl.paulinternet.gtasaveedit.model;

import javax.swing.*;
import java.io.*;

public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int UNKNOWN = 0, NO = 1, YES = 2;
    public static final int DIR_DEFAULT = 1, DIR_ACTIVE = 2, DIR_CUSTOM = 3;

    private static final File settingsFile = new File(System.getProperty("user.home"), ".gta_sa_savegame_editor");
    private static final Settings instance = loadSettings();

    private int savegameDirectoryType;
    private String customSavegameDirectory;
    private int warnOverwriteFile;
    private int warnDeleteFile;
    private String lookAndFeelClassName;
    private int sanAndreasDirectoryType;
    private String customSanAndreasDirectory;
    private int showClothes;
    private int windowWidth, windowHeight, windowMaximized;
    private int soundOnAboutPage;

    private Settings() {
    }

    public static int getSavegameDirectoryType() {
        return instance.savegameDirectoryType;
    }

    public static void setSavegameDirectoryType(int savegameDirectoryType) {
        instance.savegameDirectoryType = savegameDirectoryType;
    }

    public static String getCustomSavegameDirectory() {
        return instance.customSavegameDirectory;
    }

    public static void setCustomSavegameDirectory(String customSavegameDirectory) {
        instance.customSavegameDirectory = customSavegameDirectory;
    }

    public static int getWarnOverwriteFile() {
        return instance.warnOverwriteFile;
    }

    public static void setWarnOverwriteFile(int warnOverwriteFile) {
        instance.warnOverwriteFile = warnOverwriteFile;
    }

    public static int getWarnDeleteFile() {
        return instance.warnDeleteFile;
    }

    public static void setWarnDeleteFile(int warnDeleteFile) {
        instance.warnDeleteFile = warnDeleteFile;
    }

    public static String getLookAndFeelClassName() {
        return instance.lookAndFeelClassName;
    }

    public static void setLookAndFeelClassName(String lookAndFeelClassName) {
        instance.lookAndFeelClassName = lookAndFeelClassName;
    }

    public static int getSanAndreasDirectoryType() {
        return instance.sanAndreasDirectoryType;
    }

    public static void setSanAndreasDirectoryType(int sanAndreasDirectoryType) {
        instance.sanAndreasDirectoryType = sanAndreasDirectoryType;
    }

    public static String getCustomSanAndreasDirectory() {
        return instance.customSanAndreasDirectory;
    }

    public static void setCustomSanAndreasDirectory(String customSanAndreasDirectory) {
        instance.customSanAndreasDirectory = customSanAndreasDirectory;
    }

    public static int getShowClothes() {
        return instance.showClothes;
    }

    public static void setShowClothes(int showClothes) {
        instance.showClothes = showClothes;
    }

    public static int getWindowWidth() {
        return instance.windowWidth;
    }

    public static void setWindowWidth(int windowWidth) {
        instance.windowWidth = windowWidth;
    }

    public static int getWindowHeight() {
        return instance.windowHeight;
    }

    public static void setWindowHeight(int windowHeight) {
        instance.windowHeight = windowHeight;
    }

    public static int getWindowMaximized() {
        return instance.windowMaximized;
    }

    public static void setWindowMaximized(int windowMaximized) {
        instance.windowMaximized = windowMaximized;
    }

    public static int getSoundOnAboutPage() {
        return instance.soundOnAboutPage;
    }

    public static void setSoundOnAboutPage(int soundOnAboutPage) {
        instance.soundOnAboutPage = soundOnAboutPage;
    }

    public static void save() {
        // Try to save the settings
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(settingsFile))) {
            out.writeObject(instance);
        } catch (IOException ignored) {
        }
    }

    private static Settings loadSettings() {
        // Read settings

        Settings settings;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(settingsFile))) {
            settings = (Settings) in.readObject();
        } catch (Exception e) {
            settings = new Settings();
        }

        // Change things
        if (settings.savegameDirectoryType == UNKNOWN) settings.savegameDirectoryType = DIR_DEFAULT;
        if (settings.sanAndreasDirectoryType == UNKNOWN) settings.sanAndreasDirectoryType = DIR_DEFAULT;
        if (settings.customSavegameDirectory == null) settings.customSavegameDirectory = "";
        if (settings.warnOverwriteFile == UNKNOWN) settings.warnOverwriteFile = YES;
        if (settings.warnDeleteFile == UNKNOWN) settings.warnDeleteFile = YES;
        if (settings.lookAndFeelClassName == null) settings.lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        if (settings.customSanAndreasDirectory == null) settings.customSanAndreasDirectory = "";
        if (settings.showClothes == UNKNOWN) settings.showClothes = YES;
        if (settings.windowWidth == 0) settings.windowWidth = 900;
        if (settings.windowHeight == 0) settings.windowHeight = 900;
        if (settings.windowMaximized == UNKNOWN) settings.windowMaximized = NO;
        if (settings.soundOnAboutPage == UNKNOWN) settings.soundOnAboutPage = YES;

        // Return
        return settings;
    }

}
