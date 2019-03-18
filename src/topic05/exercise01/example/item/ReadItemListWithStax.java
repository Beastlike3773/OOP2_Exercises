package item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public final class ReadItemListWithStax {

  /**
   * Based on the Item class.
   */

  private static final String DATE = "date";
  private static final String ITEM = "item";
  private static final String UNIT = "unit";
  private static final String CURRENT = "current";
  private static final String INTERACTIVE = "interactive";
  
  /**
   * Main program.
   * @param args unused
   */
  public static void main(String[] args) {
    ReadItemListWithStax read = new ReadItemListWithStax();
    List<Item> readConfig;
    try {
      readConfig = read.readConfig("config.xml");
      for (Item item : readConfig) {
        System.out.println(item);
      }  
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }   
  }

  /**
   * Read the XML files and creates a list of Items.
   * @param configFile XML file to read
   * @return list of Items
   * @throws XMLStreamException if something is wrong with the xml
   * @throws IOException if something is wrong with the file
   * @throws NumberFormatException if something is wrong with numbers
   */
  public List<Item> readConfig(String configFile) 
      throws XMLStreamException, IOException, NumberFormatException {
    List<Item> items = new ArrayList<Item>();
    try (InputStream in = new FileInputStream(configFile)) {
      // First, create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      // Setup a new eventReader
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

      // prepare a Item reference
      Item item = null;

      // loop over all events
      while (eventReader.hasNext()) {

        // get the next event
        XMLEvent event = eventReader.nextEvent();

        // process only events of interest
        if (event.isStartElement()) {

          // convert to Start element
          StartElement startElement = event.asStartElement();

          // if we have an item element, we create a new item
          if (startElement.getName().getLocalPart().equals(ITEM)) {
            item = new Item();

            // We read the attributes from this tag and add
            // the date attribute to our object
            // get an iterator to iterate over
            // the attributes list
            Iterator<Attribute> attributes = startElement.getAttributes();
            while (attributes.hasNext()) {
              // get one after the other
              Attribute attribute = attributes.next();
              if (attribute.getName().toString().equals(DATE)) {
                // set the field in item
                item.setDate(attribute.getValue());
              }
            }
          } else {
            // treat the rest
            if (startElement.getName().getLocalPart().equals(UNIT)) {
              event = eventReader.nextEvent();
              item.setUnit(Integer.parseInt(event.asCharacters().getData()));
            } else if (startElement.getName().getLocalPart().equals(CURRENT)) {
              event = eventReader.nextEvent();
              item.setCurrent(Integer.parseInt(event.asCharacters().getData()));
            } else if (startElement.getName().getLocalPart().equals(INTERACTIVE)) {
              event = eventReader.nextEvent();
              item.setInteractive(Integer.parseInt(event.asCharacters().getData()));
            }
          }
        } else {
          // if we reach the end of an item element, we add it to the list
          if (event.isEndElement()) {
            EndElement endElement = event.asEndElement();
            // it must be an ITEM tag
            if (endElement.getName().getLocalPart() == (ITEM)) {
              items.add(item);
            }
          }
        }
      }
    }
    return items;
  }
}
