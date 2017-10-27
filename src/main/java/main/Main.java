package main;

import entity.Document;
import service.ParserService;
import service.ServiceFactory;
import service.exception.ServiceException;

public class Main {
    public static void main(String[] args) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ParserService parserService =serviceFactory.getParserService();
        Document document = null;
        try {
            document = parserService.parseFile("..\\resources\\source.xml");
        } catch (ServiceException e) {
            System.out.println("Exception in service");
        }
        System.out.println(document);
    }
}
