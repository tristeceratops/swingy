package swingy.model.entities.heroes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* How heroes are save
<heroes>
    <hero name="Josianne" class="Barbarian" level="1" experience="0" attack="2" defence="0" health="15"/>
    <hero name="Ulric" class="Paladin" level="5" experience="420" attack="10" defence="8" health="40"/>
</heroes>
*/

public class HeroesSavingFile {

	private final String filePath = "heroes.xml";
	private File heroFile;
	private List<Hero> heroList;
	private static HeroesSavingFile instance = null;

	private HeroesSavingFile() {
		heroFile = new File(filePath);
		heroList = new ArrayList<Hero>();
		try {
			loadHeroes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HeroesSavingFile getInstance() {
		if (instance == null) {
			instance = new HeroesSavingFile();
		}
		return instance;
	}

	protected void saveHeroes(List<Hero> heroes) throws ParserConfigurationException {
		if (!heroFile.exists()) {
			try {
				if (!heroFile.createNewFile())
					throw new IOException("can't create file");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = documentFactory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element rootElement = document.createElement("heroes");
		document.appendChild(rootElement);

		for (Hero hero : heroList) {
			Element heroElement = document.createElement("hero");
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
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(heroFile);
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	protected List<Hero> loadHeroes() throws ParserConfigurationException, IOException, SAXException {
		if (heroFile.exists() && heroFile.isFile()) {

			//clear list to assure no copies
			heroList.clear();

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder();

			Document document = builder.parse(filePath);

			NodeList nodeList = document.getElementsByTagName("hero");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				int level = Integer.parseInt(node.getAttributes().getNamedItem("level").getNodeValue());
				String name = node.getAttributes().getNamedItem("name").getNodeValue();
				String className = node.getAttributes().getNamedItem("class").getNodeValue();
				int attack = Integer.parseInt(node.getAttributes().getNamedItem("attack").getNodeValue());
				int defence = Integer.parseInt(node.getAttributes().getNamedItem("defence").getNodeValue());
				int health = Integer.parseInt(node.getAttributes().getNamedItem("health").getNodeValue());

				Hero hero = HeroFactory.create(className).name(name).level(level).attack(attack).defence(defence).hitpoints(health).build();
				heroList.add(hero);
			}
		}

		return this.heroList;
	}
}
