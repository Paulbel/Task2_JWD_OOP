package dao.impl;

import constant.ParserConstant;
import dao.ParserDAO;
import dao.exception.DAOException;
import dao.exception.IncorrectXMLFileException;
import entity.Document;
import entity.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class DOMParser implements ParserDAO {

    @Override
    public Document parse(String path) throws DAOException {
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            char[] fileContent = new char[(int) file.length()];
            if (fileReader.read(fileContent) == -1) {
                throw new DAOException();
            }
            String content = new String(fileContent);
            return parseFile(content);
        } catch (FileNotFoundException e) {
            throw new DAOException();
        } catch (IOException e) {
            throw new DAOException();
        }
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
        Set<String> tagSet = new HashSet<String>();
        parseBuffer(document, null, wholeWordMatcher, closeMatcher, tagSet);
        return document;
    }

    private void parseBuffer(Document document, Node rootNode, Matcher wholeWordMatcher, Matcher closeMatcher, Set<String> tagSet) {
        if (wholeWordMatcher.find()) {
            String tag = wholeWordMatcher.group(ParserConstant.OPEN_TAG_INDEX);
            Node node = new Node(tag);
            tagSet.add(tag);
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
            while (!tagSet.contains(closeMatcher.group(ParserConstant.CLOSE_TAG_INDEX))) {
                parseBuffer(document, node, wholeWordMatcher, closeMatcher, tagSet);
            }
            if (tag.equals(closeMatcher.group(ParserConstant.CLOSE_TAG_INDEX))) {
                if(closeMatcher.find()){
                    tagSet.remove(tag);
                }
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

