package swingy.model.entities.heroes;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

//todo: find a way to store info like bonus stat from equipments

/*
* How heroes are save
<heroes>
	<hero>
		<name><name>
		<class><class>
		<level><level>
	<hero>
<heroes>
*/
public class HeroModel {

	private final String filePath = "heroes.xml";
	File heroFile;
	List<Hero> heroList;

	public HeroModel() {
		heroFile = new File(filePath);
	}

	private void loadHeroes() throws ParserConfigurationException, IOException, SAXException {
		if (heroFile.exists() && heroFile.isFile()){
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder();

			Document document = builder.parse(filePath);

			NodeList nodeList = document.getElementsByTagName("hero");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				int level =  Integer.parseInt(node.getAttributes().getNamedItem("level").getNodeValue());
				String name = node.getAttributes().getNamedItem("name").getNodeValue();
				String className = node.getAttributes().getNamedItem("class").getNodeValue();
				Hero hero = HeroFactory.create(className).name(name).level(level).build();
				hero.setLevel(level);
				heroList.add(hero);
			}
		}
	}
}
