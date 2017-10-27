package dao.impl;

import constant.ParserConstant;
import dao.ParserDAO;
import dao.exception.IncorrectXMLFileException;
import entity.Document;
import entity.Node;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class DOMParser implements ParserDAO {

    @Override
    public Document parse(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        char[] fileContent = new char[(int) file.length()];
        fileReader.read(fileContent);
        String content = new String(fileContent);
        return parseFile(content);
    }

    private Document parseFile(CharSequence content) {
        Document document = new Document();
        Matcher wholeWordMatcher = ParserConstant.OPEN_TAG_VALUE_PATTERN.matcher(content);
        Matcher closeMatcher = ParserConstant.CLOSE_TAG_PATTERN.matcher(content);
        if(!closeMatcher.find()){
            try {
                throw new IncorrectXMLFileException();
            } catch (IncorrectXMLFileException e) {
                e.printStackTrace();
            }
        }
        Map<String, Node> tagMap = new HashMap<String, Node>();
        parseBuffer(document, null, wholeWordMatcher, closeMatcher, tagMap);
        return document;
    }

    private void parseBuffer(Document document, Node rootNode, Matcher wholeWordMatcher, Matcher closeMatcher, Map<String, Node> map) {
        if (wholeWordMatcher.find()) {
            String tag = wholeWordMatcher.group(ParserConstant.OPEN_TAG_INDEX);
            Node node = new Node(tag);
            map.put(tag, node);
            if (rootNode == null) {
                document.setRootNode(node);
            } else {
                rootNode.addNode(node);
            }
            String attribute = wholeWordMatcher.group(ParserConstant.ATTRIBUTE_INDEX);
            if (!attribute.equals("")) {
                setAttribute(node, attribute);
            }
            String value = wholeWordMatcher.group(ParserConstant.VALUE_INDEX);
            if (value != null) {
                node.setValue(value);
            }
            while (!map.containsKey(closeMatcher.group(1))) {
                parseBuffer(document, node, wholeWordMatcher, closeMatcher, map);
            }
            if (tag.equals(closeMatcher.group(1))) {
                closeMatcher.find();
                map.remove(tag);
            }
        }
    }

    private void setAttribute(Node node, String attribute) {
        Matcher matcher = ParserConstant.ATTRIBUTE_PATTERN.matcher(attribute);
        while (matcher.find()) {
            String attributeName = matcher.group(ParserConstant.ATTRIBUTE_NAME_INDEX);
            String attributeValue = matcher.group(ParserConstant.ATTRIBUTE_VALUE_INDEX);
            node.addAttribute(attributeName, attributeValue);
        }
    }
}

