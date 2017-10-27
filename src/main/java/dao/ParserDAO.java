package dao;


import entity.Document;


import java.io.IOException;

public interface ParserDAO {
    Document parse(String path) throws IOException;
}
