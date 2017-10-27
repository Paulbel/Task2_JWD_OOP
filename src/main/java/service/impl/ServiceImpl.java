package service.impl;


import dao.DAOFactory;
import dao.ParserDAO;
import entity.Document;
import service.ParserService;

import java.io.IOException;


public class ServiceImpl implements ParserService {
    @Override
    public Document parseFile(String path) {
        DAOFactory factory = DAOFactory.getInstance();
        ParserDAO parserDAO = factory.getDomParser();
        try {
            return parserDAO.parse(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
