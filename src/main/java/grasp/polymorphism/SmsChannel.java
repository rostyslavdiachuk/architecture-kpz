package grasp.polymorphism;

import common.Console;

public class SmsChannel implements Channel {

    private static final int MAX_LENGTH = 160;

    @Override
    public void deliver(String recipient, String text) {
        Console.step("SMS -> " + recipient + " : " + clip(text));
    }

    @Override
    public String previewLabel(String recipient, String text) {
        return "SMS to " + recipient + " (" + clip(text).length() + " chars)";
    }

    @Override
    public double costUah(String text) {
        return 0.85;
    }

    private static String clip(String s) {
        return s.length() <= MAX_LENGTH ? s : s.substring(0, MAX_LENGTH);
    }
}
