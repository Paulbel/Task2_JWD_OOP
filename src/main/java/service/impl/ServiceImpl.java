package service.impl;


import dao.DAOFactory;
import dao.ParserDAO;
import dao.exception.DAOException;
import entity.Document;
import service.ParserService;
import service.exception.ServiceException;

import java.io.IOException;


public class ServiceImpl implements ParserService {
    @Override
    public Document parseFile(String path) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ParserDAO parserDAO = factory.getDomParser();
        Document document;
        try {
            document =  parserDAO.parse(path);
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return document;
    }

}
