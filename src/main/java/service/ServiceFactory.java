package service;


import service.impl.ServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ParserService parserService = new ServiceImpl();

    private ServiceFactory() {
    }

    public ParserService getParserService() {
        return parserService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
