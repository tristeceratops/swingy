package swingy.model.entities.heroes;

import java.util.List;

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

	public HeroModel() {
		try {
			this.heroList = HeroesSavingFile.getInstance().loadHeroes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHero(Hero hero){
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
			HeroesSavingFile.getInstance().saveHeroes(this.heroList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
