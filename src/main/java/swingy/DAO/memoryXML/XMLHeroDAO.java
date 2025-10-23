package swingy.DAO.memoryXML;

import swingy.DAO.interfaceDAO.HeroDAO;
import swingy.business.entities.heroes.Hero;

import java.util.List;

public class XMLHeroDAO implements HeroDAO, XMLDAOInterface {

	@Override
	public Hero getById(int id) {
		return null;
	}

	@Override
	public List<Hero> getAll() {
		return List.of();
	}

	@Override
	public boolean create(Hero entity) {
		return false;
	}

	@Override
	public boolean update(Hero entity) {
		return false;
	}

	@Override
	public boolean delete(Hero entity) {
		return false;
	}

	@Override
	public boolean ifExist(Hero entity) {
		return false;
	}
}
