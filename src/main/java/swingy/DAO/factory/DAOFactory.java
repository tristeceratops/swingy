package swingy.DAO.factory;

import swingy.DAO.Persistance;
import swingy.DAO.interfaceDAO.HeroDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory(Persistance target) {
		DAOFactory daoFactory = null;
		switch (target) {
			case SQL:
				daoFactory = new SQLDAOFactory();
				break;
			case XML:
				daoFactory = new XMLDAOFactory();
				break;
		}
		return daoFactory;
	}

	public abstract HeroDAO getHeroDAO();
}
