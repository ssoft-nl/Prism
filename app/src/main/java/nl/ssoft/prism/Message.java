package nl.ssoft.prism;

/**
 * Created by erispre on 3/8/15.
 */
public class Message {
    protected String provider;
    protected String shortText;
    protected String text;

    public Message (String provider, String shortText, String text) {
        this.provider = provider;
        this.shortText = shortText;
        this.text = text;
    }

    public String getProvider() { return provider; }

    public String getShortText() { return shortText; }

    public String getText() { return text; }
}
