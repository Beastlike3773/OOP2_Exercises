package topic05.exercise01.example.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public final class WriteItemListWithStax {

  /**
   * Main program.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    WriteItemListWithStax configFile = new WriteItemListWithStax();
    try {
      List<item.Item> itemList = new ArrayList<item.Item>();
      itemList.add(new item.Item("Jan 2018", 1, 1, 1));
      itemList.add(new item.Item("Feb 2018", 2, 2, 2));
      configFile.saveConfig(itemList, "config2.xml");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Write a list of Items in a xml file.
   * 
   * @param list a list of items
   * @param configFile XML file to wr�te
   * @throws XMLStreamException if something is wrong with the xml
   * @throws IOException if something is wrong with the file
   */
  public void saveConfig(List<item.Item> list, String configFile)
      throws IOException, XMLStreamException {
    // create an XMLOutputFactory
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

    try (OutputStream out = new FileOutputStream(configFile)) {
      // create XMLEventWriter
      XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(out);

      // create an EventFactory
      XMLEventFactory eventFactory = XMLEventFactory.newInstance();
      XMLEvent end = eventFactory.createCharacters("\n");

      // create and write Start Tag
      StartDocument startDocument = eventFactory.createStartDocument();
      eventWriter.add(startDocument);
      eventWriter.add(end);

      // create config open tag
      StartElement configStartElement = eventFactory.createStartElement("", "", "config");
      eventWriter.add(configStartElement);
      eventWriter.add(end);

      for (item.Item item : list) {

        // create item open tag
        StartElement itemElem = eventFactory.createStartElement("", "", "item");
        eventWriter.add(itemElem);
        // Add attribute
        eventWriter.add(eventFactory.createAttribute("date", item.getDate()));
        eventWriter.add(end);

        // write the different nodes
        createNode(eventWriter, "unit", item.getUnit().toString());
        createNode(eventWriter, "current", item.getCurrent().toString());
        createNode(eventWriter, "interactive", item.getInteractive().toString());

        eventWriter.add(eventFactory.createEndElement("", "", "item"));
        eventWriter.add(end);
      }

      eventWriter.add(eventFactory.createEndElement("", "", "config"));
      eventWriter.add(end);
      eventWriter.add(eventFactory.createEndDocument());

      // eventWriter.flush();
      eventWriter.close();
    }
  }

  private void createNode(XMLEventWriter eventWriter, String elementTag, String value)
      throws XMLStreamException {

    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createCharacters("\n");
    XMLEvent tab = eventFactory.createCharacters("\t");

    // create Start node
    StartElement startElement = eventFactory.createStartElement("", "", elementTag);
    eventWriter.add(tab);
    eventWriter.add(startElement);

    // create Content
    Characters characters = eventFactory.createCharacters(value);
    eventWriter.add(characters);

    // create End node
    EndElement endElement = eventFactory.createEndElement("", "", elementTag);
    eventWriter.add(endElement);
    eventWriter.add(end);
  }
}
