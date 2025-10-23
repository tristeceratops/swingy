package swingy.DAO.factory;

import swingy.DAO.interfaceDAO.HeroDAO;
import swingy.DAO.memoryXML.XMLHeroDAO;

public class XMLDAOFactory extends DAOFactory {

	@Override
	public HeroDAO getHeroDAO() {
		return new XMLHeroDAO();
	}
}
