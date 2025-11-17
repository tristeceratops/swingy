package swingy.model;

import swingy.DAO.Persistance;
import swingy.DAO.factory.DAOFactory;
import swingy.DAO.interfaceDAO.HeroDAO;
import swingy.DAO.memoryXML.XMLHeroDAO;
import swingy.business.entities.heroes.Hero;
import java.util.List;
import java.util.UUID;

//todo: find a way to store info like bonus stat from equipments

/*
* How heroes are save
<heroes>
    <hero name="Josianne" class="Barbarian" level="1" experience="0" attack="2" defence="0" health="15"/>
    <hero name="Ulric" class="Paladin" level="5" experience="420" attack="10" defence="8" health="40"/>
</heroes>
*/
public class HeroModel {

	List<Hero> heroList;
	HeroDAO xmlHeroDAO = DAOFactory.getDAOFactory(Persistance.XML).getHeroDAO();

	public HeroModel() {
		try {
			this.heroList = xmlHeroDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//generate UUID here
	public void addHero(Hero hero){
		UUID id = UUID.randomUUID();
		hero.setId(id);
		heroList.add(hero);
	}

	public void deleteHero(Hero hero){
		heroList.remove(hero);
	}

	public List<Hero> getHeroes(){
		return heroList;
	}

	public void saveHeroes(){
		try {
			xmlHeroDAO.save(heroList); //TODO: problem with interface, here xmlHeroDao is save as HeroDAO so no access to saveHeroes
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(){
	}
}
