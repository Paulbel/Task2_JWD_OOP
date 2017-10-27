package dao;


import dao.impl.DOMParser;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final ParserDAO domParser = new DOMParser();
	
	private DAOFactory() {}

	public ParserDAO getDomParser() {
		return domParser;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
