package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.components.ConnectedLocations;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Holder class containing the content of the Location file type
 * @author Daniel
 *
 */
public class LocationGameContent extends AbstractGameContent {

	private final String locationID;
	private final String locationName;
	private final String locationPassiveEventID;
	private final ConnectedLocations connectedLocations;
	
	/**
	 * Constructor for when the location XML content is used
	 * @param document
	 * @throws XMLParseException
	 */
	public LocationGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new LocationGameContent object");
		contentType = ContentType.LOCATION;
		locationID = XMLUtils.getFirstMatchingTagContent(document, LocationGameContentTag.LOCATION_ID.getTag());
		locationName = XMLUtils.getFirstMatchingTagContent(document, LocationGameContentTag.LOCATION_NAME.getTag());
		locationPassiveEventID = XMLUtils.getFirstMatchingTagContent(document, LocationGameContentTag.LOCATION_PASSIVE_EVENT_ID.getTag());
		
		NodeList completedEventsNodeList = document.getElementsByTagName(LocationGameContentTag.CONNECTED_LOCATIONS.getTag());
		connectedLocations = new ConnectedLocations(completedEventsNodeList.item(0));
		
		SystemLogger.fine("A new %s was created", toString());
	}
	
	/**
	 * Constructor for use with junits
	 * @param locationID
	 * @param locationName
	 * @param locationPassiveEventID
	 * @param connectedLocations
	 */
	public LocationGameContent(String locationID, String locationName, String locationPassiveEventID, ConnectedLocations connectedLocations) {
		this.locationID = locationID;
		this.locationName = locationName;
		this.locationPassiveEventID = locationPassiveEventID;
		this.connectedLocations = connectedLocations;
	}
	
	/**
	 * @return Returns the ID of the location
	 */
	public String getLocationID() {
		return locationID;
	}
	
	/**
	 * @return Returns the name of the location
	 */
	public String getLocationName() {
		return locationName;
	}
	
	/**
	 * @return Returns the passive event ID for this location
	 */
	public String getLocationPassiveEventID() {
		return locationPassiveEventID;
	}
	
	/**
	 * @return the connectedLocations
	 */
	public ConnectedLocations getConnectedLocations() {
		return connectedLocations;
	}
	
	@Override
	public String toString() {
		return String.format("LocationGameContent object with values: locationName=%s", locationName);
	}
	
	/**
	 * private enum representing the tags available under the LOCATION element
	 * @author Daniel
	 *
	 */
	private enum LocationGameContentTag {
		LOCATION_ID("LocationID"),
		LOCATION_NAME("LocationName"),
		LOCATION_PASSIVE_EVENT_ID("LocationPassiveEventID"),
		CONNECTED_LOCATIONS("ConnectedLocations");
		
		private String tag;
		
		private LocationGameContentTag(String tag) {
			this.tag = tag;
		}
		
		/**
		 * Returns the exact format of the tag in the XSD/XML
		 * @return
		 */
		public String getTag() {
			return tag;
		}
	}
}
