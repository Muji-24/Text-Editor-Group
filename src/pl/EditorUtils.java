package pl;

public class EditorUtils {

    public static double calculateAvgWordLength(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }

        String[] words = content.split("\\s+");
        int totalLength = 0;
        int wordCount = 0;

        for (String word : words) {
            if (!word.isEmpty()) {
                totalLength += word.length();
                wordCount++;
            }
        }

        return wordCount == 0 ? 0 : (double) totalLength / wordCount;
    }

    public static int calculateWordCount(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        return text.trim().split("\\s+").length;
    }

    public static int calculateLineCount(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }
        String[] lines = content.split("\r?\n");
        return lines.length;
    }
}
