package service;

import entity.Document;
import entity.Node;

import java.util.List;

public interface ParserService {
    Document parseFile(String path);
}
