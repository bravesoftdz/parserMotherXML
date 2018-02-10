package by.gvozdev.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParserDemo {

	public static void main(String[] args) throws Exception {

		try {
			File xmlFile = new File(Constant.PATH_TO_XML);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();

			Mother mother = new Mother();
			List<Mother> motherList = new ArrayList<>();

			System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			NodeList nodeList = document
					.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Mother");
					System.out.println("NAME: "
							+ element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("SURNAME: "
							+ element.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("AGE: "
							+ element.getElementsByTagName("age").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("MAIDEN-NAME: " + element.getElementsByTagName("maiden-name").item(0)
							.getChildNodes().item(0).getNodeValue());

					String bName = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
					String bSurname = element.getElementsByTagName("surname").item(0).getChildNodes().item(0)
							.getNodeValue();
					String bAge = element.getElementsByTagName("age").item(0).getChildNodes().item(0).getNodeValue();
					String bMaidenName = element.getElementsByTagName("maiden-name").item(0).getChildNodes().item(0)
							.getNodeValue();

					mother.setName(bName);
					mother.setSurname(bSurname);
					mother.setAge(Integer.valueOf(bAge));
					mother.setMaidenName(bMaidenName);

					motherList.add(mother);

				}
			}

			System.out.println(motherList);

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
