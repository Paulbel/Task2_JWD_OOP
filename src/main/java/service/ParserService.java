package service;

import entity.Document;

public interface ParserService {
    Document parseFile(String path);
}
