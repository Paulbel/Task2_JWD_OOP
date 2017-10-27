package dao;


import entity.Document;


import java.io.IOException;

public interface ParserDAO {
    public Document parse(String path) throws IOException;
}
