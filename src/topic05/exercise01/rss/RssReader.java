package topic05.exercise01.rss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class RssReader {
    private final URL url;
    private Feed feed;

    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";

    public RssReader(String feedUrl) throws MalformedURLException {
        this.url = new URL(feedUrl);
        this.feed = new Feed();
    }

    public RssReader(URL url) {
        this.url = url;
        this.feed = new Feed();
    }

    private Feed readFeed() {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try(InputStream in = url.openStream()){
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            List<FeedMessage> feedMessages = new ArrayList<>();

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLEvent.START_DOCUMENT:

                        break;
                    case XMLEvent.START_ELEMENT:
                        StartElement se = event.asStartElement();
                        if(se.getName().getLocalPart().equals("channel")){
                            Iterator<Attribute> attributes = se.getAttributes();
                            while(attributes.hasNext()){
                                Attribute attribute = attributes.next();
                               switch(attribute.getName().getLocalPart()){
                                   case TITLE:
                                       feed.setTitle(attribute.getValue());
                                       break;
                                   case LINK:
                                       feed.setLink(attribute.getValue());
                                       break;
                                   case DESCRIPTION:
                                       feed.setDescription(attribute.getValue());
                                       break;
                                   default:
                                       System.err.println("Attribute not found in class: " + attribute.getName());
                                       break;
                               }
                            }
                        }
                        if(se.getName().getLocalPart().equals("item")){
                            Iterator<Attribute> attributes = se.getAttributes();
                            while(attributes.hasNext()){
                                Attribute attribute = attributes.next();
                                FeedMessage fm = new FeedMessage();
                                switch(attribute.getName().getLocalPart()){
                                    case TITLE:
                                        fm.setTitle(attribute.getValue());
                                        feedMessages.add(fm);
                                        break;
                                    case LINK:
                                        fm.setLink(attribute.getValue());
                                        feedMessages.add(fm);
                                        break;
                                    case DESCRIPTION:
                                        fm.setDescription(attribute.getValue());
                                        feedMessages.add(fm);
                                        break;
                                    default:
                                        System.err.println("Attribute not found in class: " + attribute.getName());
                                        break;
                                }
                            }
                        }
                        break;
                    case XMLEvent.END_ELEMENT:
                        //System.out.println("End Element " + event);
                        break;
                    case XMLEvent.END_DOCUMENT:
                        //System.out.print("EndDocument" + event);
                        break;
                    case XMLEvent.CHARACTERS:
                        //System.out.println("Characters " + event);
                        break;
                    default:
                        //System.out.print(event.getEventType());
                        break;
                }
            }

            feed.setMessages(feedMessages);
        } catch(IOException e){

        } catch(XMLStreamException e){

        } catch(NumberFormatException e){

        }

        return feed;
    }


    /**
     * Main program.
     *
     * @param args unused
     */
    public static void main(String[] args) throws MalformedURLException {
        RssReader reader;

        // TODO Handle exceptions correctly

        // Variant to read from file (easier to debug)
        URL furl = new File("20Minuten_2019_03_12.xml").toURI().toURL();
        reader = new RssReader(furl);

        // Variant to read directly from the url
        reader = new RssReader("http://api.20min.ch/rss/view/1");
        Feed feed = reader.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }
}
