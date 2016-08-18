package comfees.processor.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fees.processor.pojo.Transaction;

public class XMLFileReader implements FileReader {

	@Override
	public List<Transaction> read(String inputRequest) {

		final List<Transaction> inputList = new ArrayList<Transaction>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuillder = null;
		try {
			dBuillder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			System.out.println("input file::" + inputRequest);
			doc = dBuillder.parse(inputRequest); // change
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		System.out.println("Root Node Name----->" + doc.getDocumentElement().getNodeName() + "\n\n");
		NodeList nListTransaction = doc.getElementsByTagName("transaction");

		// print the text content of each child
		for (int i = 0; i < nListTransaction.getLength(); i++) {
			Node nNode = nListTransaction.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;

				inputList.add(new Transaction(element.getElementsByTagName("externalId").item(0).getTextContent(), element.getElementsByTagName("clientId").item(0).getTextContent(),
						element.getElementsByTagName("securityId").item(0).getTextContent(), element.getElementsByTagName("transactionType").item(0).getTextContent(), element.getElementsByTagName("transactionDate").item(0).getTextContent(), Double
								.parseDouble(element.getElementsByTagName("marketValue").item(0).getTextContent()), element.getElementsByTagName("priorityFlag").item(0).getTextContent(), 0));
			}

		}

		return inputList;
	}
}
