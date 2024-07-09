package su.ANV.island.io;

public class TextOut extends Out {
    private static TextOut textOut = null;

    private TextOut() {}

    public static TextOut getTextOut() {
        if (textOut == null) {
            textOut = new TextOut();
        }
        return textOut;
    }

    @Override
    public void write(String msg, int level) {
        if (outLevels.contains(level)) {
            System.out.print(msg);
        }
    }

    @Override
    public void writeln(String msg, int level) {
        if (outLevels.contains(level)) {
            System.out.println(msg);
        }
    }
}
