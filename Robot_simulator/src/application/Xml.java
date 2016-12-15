package application;


	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;

	public class Xml {

		public static Element eElement;
		public static double speed;


		public static void xml(TextArea text_area){

		    try {
		    	
				FileChooser fc = new FileChooser();
				fc.setInitialDirectory(new File("../Robot_simulator/car_type"));
				fc.getExtensionFilters().addAll(new ExtensionFilter("xml files","*.xml"));
				File selectFile = fc.showOpenDialog(null);

			File fXmlFile = selectFile;
					//new File("../Robot_simulator/car_type/test.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);


			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("car");

			//System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				org.w3c.dom.Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					
					

					
					text_area.appendText("Car name : " + eElement.getElementsByTagName("carname").item(0).getTextContent() + "\n");
					text_area.appendText("Width : " + eElement.getElementsByTagName("width").item(0).getTextContent()+ "\n");
					text_area.appendText("Length : " + eElement.getElementsByTagName("length").item(0).getTextContent()+ "\n");
					text_area.appendText("Static turning speed : " + eElement.getElementsByTagName("static_turning_degree").item(0).getTextContent()+ "\n");
					text_area.appendText("Speed : " + eElement.getElementsByTagName("speed").item(0).getTextContent()+ "\n\n");

					speed=Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent());

					
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	}