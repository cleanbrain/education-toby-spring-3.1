package springbook.learningtest.factorybean;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-02-26
 */
public class Message {
    String text;

    private Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
