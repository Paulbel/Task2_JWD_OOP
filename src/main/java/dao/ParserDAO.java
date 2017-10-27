package dao;


import dao.exception.DAOException;
import entity.Document;


import java.io.IOException;

public interface ParserDAO {
    Document parse(String path) throws DAOException;
}
