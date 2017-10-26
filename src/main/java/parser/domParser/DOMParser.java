package parser.domParser;

import parser.Parser;
import parser.ParserConsts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class DOMParser implements Parser {
    public static final String BLA_BLABLA = "";
    private boolean gotAllLine;

    public DOMParser() {
        this.gotAllLine = false;
    }

    @Override
    public Document parse() throws IOException {
        //URL fileURL = this.getClass().getClassLoader().getResource("source.xml");
        //System.out.print(fileURL);
        //String path = fileURL.getPath();
        File file = new File("..\\resources\\source.xml");
        FileReader fileReader = new FileReader(file);
        char[] fileContent = new char[(int)file.length()];
        fileReader.read(fileContent);
        String content = new String(fileContent);
        Document document = parseFile(content);
        return document;
    }

    private Document parseFile(CharSequence content) {
        Document document = new Document();
        Matcher wholeWordMatcher = ParserConsts.OPEN_TAG_VALUE_PATTERN.matcher(content);
        Matcher closeMatcher = ParserConsts.CLOSE_TAG_PATTERN.matcher(content);
        closeMatcher.find();
        Map<String, Node> tagMap = new HashMap<String, Node>();
        parseBuffer(document, null, wholeWordMatcher, closeMatcher, tagMap);
        return document;
    }

    private void parseBuffer(Document document, Node rootNode, Matcher wholeWordMatcher, Matcher closeMatcher, Map<String, Node> map) {
        if (wholeWordMatcher.find()) {
            String tag = wholeWordMatcher.group(ParserConsts.OPEN_TAG_INDEX);
            Node node = new Node(tag);
            map.put(tag,node);
            //System.out.println(tag);
            if (rootNode == null) {
                document.setRootNode(node);
            } else {
                rootNode.addNode(node);
            }
            String attribute = wholeWordMatcher.group(ParserConsts.ATTRIBUTE_INDEX);
            if(!attribute.equals("")){
                setAttribute(node,attribute);
            }

            String value = wholeWordMatcher.group(ParserConsts.VALUE_INDEX);
            if (value != null) {
                node.setValue(value);
            }
            while (!map.containsKey(closeMatcher.group(1))) {
                //System.out.println(closeMatcher.group(1));
                parseBuffer(document, node, wholeWordMatcher, closeMatcher, map);
            }
            if (tag.equals(closeMatcher.group(1))) {
                closeMatcher.find();
                map.remove(tag);
            }
            return;
        }
    }

    private void setAttribute(Node node, String attribute){
     //   System.out.println(attribute);
        Matcher matcher = ParserConsts.ATTRIBUTE_PATTERN.matcher(attribute);
        while (matcher.find()){
            String attributeName = matcher.group(ParserConsts.ATTRIBUTE_NAME_INDEX);
            String attributeValue = matcher.group(ParserConsts.ATTRIBUTE_VALUE_INDEX);
            node.addAttribute(attributeName,attributeValue);
        }
    }
}

