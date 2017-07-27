//I'm extracting the third closest train station distance here.
//There is more work to complete here. Need to extract the CRS Code of the above station.
//For that, a hashmap Should be used with a Comparator. Due to time constraints, I was unable to complete that part.
/*Possible improvements
1. Use a hashmap to store CRS Code and Distance. Then sort the hashmap using the Comparator.
2. Use seperate user defined methods to add results to a hashmap and to return those.
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HelloXML {

	public static void main(String[] args) {
		try{
		File respXml = new File("src/AB356.xml");
		
		ArrayList<Double> distance = new ArrayList<Double>();
		HashMap<String, Double> hmap = new HashMap<String, Double>();
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(respXml);
		doc.getDocumentElement().normalize();
		System.out.println("Root element of the XML is :"+doc.getDocumentElement().getNodeName());
		
		NodeList listOfStations = doc.getElementsByTagName("PostcodeStationWithCoord");
		int totalStations = listOfStations.getLength();
		System.out.println("Total number of stations is : "+totalStations);
		
		for (int i=0 ; i<listOfStations.getLength(); i++){
			Node nNode = listOfStations.item(i);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               String crsCode = eElement.getElementsByTagName("Crs").item(0).getTextContent();
	               System.out.println(crsCode);
	               
	               double distanceToStation = Double.parseDouble(eElement.getElementsByTagName("Distance").item(0).getTextContent());
	               distance.add(distanceToStation);
	               hmap.put(crsCode, distanceToStation);
	               
			}
			else 
				System.out.println("Not an element node");
		}
		
		Collections.sort(distance);
		System.out.println("Sorted array list is:"+distance);
		double thirdDistance = distance.get(2);
		System.out.println("Third closest station distance is:"+thirdDistance);
		System.out.println("Third closest station CRS Code is:"); //more code should go here to retrieve the correct CRS code
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Program successful");
	}

}
