package swingy.DAO.factory;

import swingy.DAO.Persistance;
import swingy.DAO.interfaceDAO.HeroDAO;

public class DAOFactory {

	public static DAOFactoryInterface getDAOFactory(Persistance target) {
		DAOFactoryInterface daoFactory = null;
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
}
