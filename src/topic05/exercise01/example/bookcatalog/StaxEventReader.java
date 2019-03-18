package topic05.exercise01.example.bookcatalog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Class that prints out the main StAX events.
 * @author lua1
 */
public class StaxEventReader {

  /**
   * Main program.
   * @param args unused
   */
  public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

    // First, create a new XMLInputFactory
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    // Setup a new eventReader
    InputStream in = new FileInputStream("BookCatalogue.xml");
    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

    int nbr = 1;
    // read the entire XML document - loop over all events
    while (eventReader.hasNext()) {
      // get next event
      XMLEvent event = eventReader.nextEvent();
      System.out.print("# " + (nbr++) + " : ");
      switch (event.getEventType()) {
        case XMLEvent.START_DOCUMENT:
          System.out.print("StartDocument");
          break;
        case XMLEvent.START_ELEMENT:
          System.out.print("StartElement:");
          StartElement s = event.asStartElement();
          System.out
              .print(" qname=" + s.getName().getLocalPart() + ":" + s.getName().getNamespaceURI());
          Iterator<Attribute> attributes = s.getAttributes();
          System.out.print(" attributes=");
          if (!attributes.hasNext()) {
            System.out.print("null");
          } else {
            while (attributes.hasNext()) {
              System.out.print(attributes.next().getName() + ", ");
            }
          }
          break;
        case XMLEvent.END_ELEMENT:
          System.out.print("EndElement:");
          EndElement e = event.asEndElement();
          System.out.print(" qname=" + e.getName().getLocalPart());
          break;
        case XMLEvent.END_DOCUMENT:
          System.out.print("EndDocument");
          break;
        case XMLEvent.CHARACTERS:
          System.out.print("Characters: ");
          Characters c = event.asCharacters();
          System.out.print("isCDate=" + c.isCData());
          if (!c.isWhiteSpace()) {
            System.out.print(" data=\"" + c.getData() + "\"");
          }
          System.out.print(" isWhiteSpace=" + c.isWhiteSpace());
          break;
        default:
          System.out.print(event.getEventType());
          break;
      }
      System.out.printf("%n");
    }

  }

}
