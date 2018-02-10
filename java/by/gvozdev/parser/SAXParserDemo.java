package by.gvozdev.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo extends DefaultHandler {

	public static void main(String[] args) throws Exception {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SAXHandler handler = new SAXHandler();
		parser.parse(Constant.PATH_TO_XML, handler);
	}
}

class SAXHandler extends DefaultHandler {

	List<Mother> motherList = new ArrayList<>();
	Map<Integer, List<Mother>> arr = new HashMap<Integer, List<Mother>>();
	Mother mother = null;
	String content = null;
	int count = 0;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case Constant.TAG_MOTHER:
			mother = new Mother();
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		count++;
		switch (qName) {
		case Constant.TAG_MOTHER: /// >>>>>>> Here you are can take data from xml (mather, father, child)

			motherList.add(mother);

			arr.put(count, motherList);

			for (Map.Entry entry : arr.entrySet()) {
				System.out.println(entry.getValue());
			}

			break;
		case Constant.TAG_NAME:
			mother.setName(content.toString());
			break;
		case Constant.TAG_SURNAME:
			mother.setSurname(content.toString());
			break;
		case Constant.TAG_AGE:
			mother.setAge(Integer.valueOf(content.toString()));
			break;
		case Constant.TAG_MAIDEN_NAME:
			mother.setMaidenName(content.toString());
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}
