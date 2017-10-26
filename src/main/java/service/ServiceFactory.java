package service;


import service.impl.GlassesService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ProductService productService = new GlassesService();

    private ServiceFactory() {
    }

    public ProductService getApplianceService() {
        return productService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
