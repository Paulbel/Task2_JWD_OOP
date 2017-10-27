package service;

import entity.Document;
import service.exception.ServiceException;

public interface ParserService {
    Document parseFile(String path) throws ServiceException;
}
