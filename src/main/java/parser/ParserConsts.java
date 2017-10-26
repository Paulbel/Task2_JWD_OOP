package parser;

import java.util.regex.Pattern;

public final class ParserConsts {
    public final static int ATTRIBUTE_NAME_INDEX = 1;
    public final static int ATTRIBUTE_VALUE_INDEX = 2;
    public final static int OPEN_TAG_INDEX = 2;
    public final static int VALUE_INDEX = 5;
    public final static int ATTRIBUTE_INDEX = 3;
    public final static Pattern CLOSE_TAG_PATTERN;
    public final static Pattern ATTRIBUTE_PATTERN;
    public final static Pattern OPEN_TAG_VALUE_PATTERN;

    static {
        ATTRIBUTE_PATTERN = Pattern.compile("(\\w+)=\"(\\w+)\"")    ;
        OPEN_TAG_VALUE_PATTERN = Pattern.compile("<(([\\w-]+)((\\s*\\w+=\"\\w+\")*))>(([^\\n\\r<]\\s*)+)*");
        CLOSE_TAG_PATTERN = Pattern.compile("</([\\w-]+)>");
    }
    private ParserConsts(){};
}
