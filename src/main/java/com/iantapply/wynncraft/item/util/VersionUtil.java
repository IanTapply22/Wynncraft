package com.iantapply.wynncraft.item.util;

/**
 * Handles all version utilities for items
 */
public class VersionUtil {

    /**
     * Check if the current version is lower than the latest version
     * @param currentVersion The current version
     * @param latestVersion The latest version
     * @return If the current version is lower than the latest version
     */
    public static boolean isLowerLatestVersion(String currentVersion, String latestVersion) {
        // Split the version numbers into their parts
        String[] currentVersionParts = currentVersion.split("\\.");
        String[] latestVersionParts = latestVersion.split("\\.");

        // Compare the parts of the version numbers
        for (int i = 0; i < currentVersionParts.length; i++) {
            // If the current version part is less than the latest version part, return true
            if (Integer.parseInt(currentVersionParts[i]) < Integer.parseInt(latestVersionParts[i])) {
                return true;
            }
            // If the current version part is greater than the latest version part, return false
            if (Integer.parseInt(currentVersionParts[i]) > Integer.parseInt(latestVersionParts[i])) {
                return false;
            }
        }

        // If the current version is equal to the latest version, return false
        return false;
    }
}
