package by.gvozdev.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXParserDemo {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

		List<Mother> mothersList = null;
		Map<Integer, List<Mother>> arr = new HashMap<Integer, List<Mother>>();
		Mother currMothers = null;
		String tagContent = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fileXml = new FileInputStream(Constant.PATH_TO_XML);
		XMLStreamReader reader = factory.createXMLStreamReader(fileXml);

		int count = 0;

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if (Constant.TAG_MOTHER.equals(reader.getLocalName())) {
					currMothers = new Mother();
					mothersList = new ArrayList<>();
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				tagContent = reader.getText().trim();
				break;

			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {

				case Constant.TAG_MOTHER: /// >>>>>>> Here you are can take data from xml (mather, father, child)

					mothersList.add(currMothers);

					arr.put(count, mothersList);

					for (Map.Entry entry : arr.entrySet()) {
						System.out.println(entry.getValue());
					}

					break;

				case Constant.TAG_NAME:
					currMothers.setName(tagContent);
					break;
				case Constant.TAG_SURNAME:
					currMothers.setSurname(tagContent);
					break;
				case Constant.TAG_AGE:
					currMothers.setAge(Integer.valueOf(tagContent));
					break;
				case Constant.TAG_MAIDEN_NAME:
					currMothers.setMaidenName(tagContent);
					break;
				}
				break;

			case XMLStreamConstants.START_DOCUMENT:
				mothersList = new ArrayList<>();
				break;
			}
		}
	}
}
