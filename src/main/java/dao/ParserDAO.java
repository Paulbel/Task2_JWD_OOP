package dao;


import dao.exception.DAOException;
import entity.Document;

public interface ParserDAO {
    Document parse(String path) throws DAOException;
}
