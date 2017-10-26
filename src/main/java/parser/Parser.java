package parser;

import parser.domParser.Document;

import java.io.File;
import java.io.IOException;

public interface Parser {
    Document parse() throws IOException;
}
