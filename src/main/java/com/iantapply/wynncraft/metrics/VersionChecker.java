package com.iantapply.wynncraft.metrics;

public class VersionChecker {

    public static boolean isVersionLower(String current, String latest) {
        return compareVersions(current, latest) < 0;
    }

    public static boolean isVersionHigher(String current, String latest) {
        return compareVersions(current, latest) > 0;
    }

    /**
     * Compares two version strings (e.g., "1.2.3" vs. "1.2.4").
     * Returns:
     *  - A negative value if v1 is less than v2
     *  - Zero if v1 == v2
     *  - A positive value if v1 greater than v2
     */
    public static int compareVersions(String v1, String v2) {
        String[] v1Parts = v1.split("\\.");
        String[] v2Parts = v2.split("\\.");

        int length = Math.max(v1Parts.length, v2Parts.length);
        for (int i = 0; i < length; i++) {
            int part1 = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
            int part2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;
            if (part1 != part2) {
                return Integer.compare(part1, part2);
            }
        }
        return 0;
    }
}
