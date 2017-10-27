package main;

import entity.Document;
import service.ParserService;
import service.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ParserService parserService =serviceFactory.getParserService();
        Document document = parserService.parseFile("..\\resources\\source.xml");
        System.out.println(document);
    }
}
