package swingy.DAO.memoryXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import swingy.DAO.interfaceDAO.HeroDAO;
import swingy.business.entities.heroes.Hero;
import swingy.business.entities.heroes.HeroFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class XMLHeroDAO implements HeroDAO, XMLDAOInterface {

	private List<Hero> heroList;

	public XMLHeroDAO() throws ParserConfigurationException, IOException, SAXException {
		heroList = loadHeroes();
	}

	@Override
	public Optional<Hero> getById(UUID id) {
		return heroList.stream().filter(h -> h.getId() == id).findFirst();
	}

	@Override
	public List<Hero> getAll() {
		return heroList;
	}

	//idea: set id here in the creation to assure unique id
	@Override
	public boolean create(Hero entity) {
		Optional<Hero> optional = getById(entity.getId());
		if (optional.isPresent()) {
			return false;
		}
		heroList.add(entity);
		return true;
	}

	@Override
	public boolean update(Hero entity) {
		Optional<Hero> optionalHero = getById(entity.getId());

		if (optionalHero.isPresent()) {
			Hero hero = optionalHero.get();
			hero.setLevel(entity.getLevel());
			hero.setExperience(entity.getExperience());
			hero.setAttack(entity.getAttack());
			hero.setDefence(entity.getDefence());
			hero.setHitpoints(entity.getHitpoints());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Hero entity) {
		Optional<Hero> optionalHero = getById(entity.getId());

		optionalHero.ifPresent(hero -> heroList.remove(hero));
		return false;
	}

	@Override
	public boolean ifExist(Hero entity) {
		return heroList.stream().anyMatch(h -> h.getId() == entity.getId());
	}

	protected void saveHeroes(List<Hero> heroes) throws ParserConfigurationException {
		File heroFile = new File(HERO_FILE_PATH);
		if (!heroFile.exists() || !heroFile.isFile() || !heroFile.canRead() || !heroFile.canWrite()) {
			try {
				if (!heroFile.createNewFile())
					throw new IOException("can't create file");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DocumentBuilder builder = createDocumentBuilder();
		Document document = builder.newDocument();

		Element rootElement = document.createElement(ROOT_ELEMENT);
		document.appendChild(rootElement);

		for (Hero hero : heroList) {
			Element heroElement = document.createElement(HERO_ELEMENT);
			heroElement.setAttribute("id", String.valueOf(hero.getId()));
			heroElement.setAttribute("name", hero.getName());
			heroElement.setAttribute("class", hero.getHeroClass());
			heroElement.setAttribute("level", String.valueOf(hero.getLevel()));
			heroElement.setAttribute("experience", String.valueOf(hero.getExperience()));
			heroElement.setAttribute("attack", String.valueOf(hero.getAttack()));
			heroElement.setAttribute("defence", String.valueOf(hero.getDefence()));
			heroElement.setAttribute("health", String.valueOf(hero.getHitpoints()));

			rootElement.appendChild(heroElement);
		}

		try {
			Transformer transformer = createTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(heroFile);
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	protected List<Hero> loadHeroes() throws ParserConfigurationException, IOException, SAXException {
		File heroFile = new File(HERO_FILE_PATH);
		if (heroFile.exists() && heroFile.isFile() && heroFile.canRead()) {

			//clear list to assure no copies
			heroList.clear();

			DocumentBuilder builder = createDocumentBuilder();

			Document document = builder.parse(HERO_FILE_PATH);

			NodeList nodeList = document.getElementsByTagName(HERO_ELEMENT);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				UUID id = UUID.fromString(node.getAttributes().getNamedItem("id").getTextContent());
				int level = Integer.parseInt(node.getAttributes().getNamedItem("level").getNodeValue());
				String name = node.getAttributes().getNamedItem("name").getNodeValue();
				String className = node.getAttributes().getNamedItem("class").getNodeValue();
				int attack = Integer.parseInt(node.getAttributes().getNamedItem("attack").getNodeValue());
				int defence = Integer.parseInt(node.getAttributes().getNamedItem("defence").getNodeValue());
				int health = Integer.parseInt(node.getAttributes().getNamedItem("health").getNodeValue());

				Hero hero = HeroFactory.create(className).id(id).name(name).level(level).attack(attack).defence(defence).hitpoints(health).build();
				heroList.add(hero);
			}
		}
		return this.heroList;
	}
}
