package topic05.exercise03.sources.sonnet;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Based on the XmlDoc program by Doug Tidwell, 17 February 2000.
 * 
 * @author lua1, 2016
 */
public class XMLDomProcessing {

	public static void processXMLDOM(String xmlFileName, String xsdFileName)
			throws ParserConfigurationException, SAXException, IOException {

		// create the DOM factory
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringElementContentWhitespace(true);
		factory.setNamespaceAware(true);

		// Create the schema factory
		final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		factory.setValidating(true);

		// Attach the schema
		final Schema schema = sf.newSchema(new File(xsdFileName));
		factory.setSchema(schema);

		// Create the document builder and set the error handler
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new DefaultHandler());

		// read the xml file
		Document doc = builder.parse(new File(xmlFileName));

		// process it
		System.out.println("---- original document ----");
		printNode(doc);
	
		// modify the document and print it again
		modifyDocument(doc);
		System.out.println();
		System.out.println("---- modified document ----");
		printNode(doc);

	}

	/** Insert some nodes into the document */
	private static Document modifyDocument(Document doc) {
		if (doc != null) {
			// create an additional element
			Element placeOfBirth = doc.createElement("place-of-birth");
			placeOfBirth.setTextContent("Stratford-upon-Avon");

			// get the root element
			Element rootEl = doc.getDocumentElement();
			// navigate to the author element
			NodeList authors = rootEl.getElementsByTagName("author");
			Element author = (Element) authors.item(0);

			NodeList nationalities = author.getElementsByTagName("nationality");
			Element nationality = (Element) nationalities.item(0);
			// modify the nationality
			nationality.setTextContent("English");

			NodeList yearODs = author.getElementsByTagName("year-of-death");
			Element yearOfDeath = (Element) yearODs.item(0);
			// insert it there - Attention the xml is not valid anymore
			author.insertBefore(placeOfBirth, yearOfDeath);

			NodeList elements = rootEl.getElementsByTagName("title");
			Element title = (Element) elements.item(0);
			title.setTextContent("*** " + title.getTextContent() + " ***");

			NodeList lines = rootEl.getElementsByTagName("lines").item(0).getChildNodes();

			for(int i = 0; i < lines.getLength(); i += 2){
                Element line = doc.createElement("line");
                line.setTextContent("-------------");
                rootEl.getElementsByTagName("lines").item(0).insertBefore(line, lines.item(i));
            }
            Element line = doc.createElement("line");
            line.setTextContent("-------------");
            Element parentLine = (Element) rootEl.getElementsByTagName("lines").item(0);
			parentLine.insertBefore(line, null);

			Comment comment = doc.createComment("A comment ...");
			rootEl.insertBefore(comment, rootEl.getElementsByTagName("author").item(0));


			// NOTE: DOM does not re-validate the document
			// when you modify it programmatically !!
		}
		return doc;
	}

	/** Print a specific node including - if present - its contents. */
	private static void printNode(Node node) {
		
		int type = node.getNodeType();
		// System.out.println("Node Type: " + type);
		NodeList children = null;
		NamedNodeMap attrs = null;

		switch (type) {
		case Node.DOCUMENT_NODE:
			System.out.print("Document Node: <?xml");

			System.out.print(" version=\"" + ((Document) node).getXmlVersion());
			String encoding = ((Document) node).getXmlEncoding();
			if (encoding != null)
				System.out.print(" encoding=" + encoding);
			System.out.println("\" ?>");

			children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++)
					printNode(children.item(i));
			}
			break;

		case Node.DOCUMENT_TYPE_NODE:
			String subset = ((DocumentType) node).getInternalSubset();
			System.out.println("<!DOCTYPE " + node.getNodeName() + " [" + subset + "]>");
			break;

		case Node.ELEMENT_NODE:
			System.out.print("<");
			System.out.print(node.getNodeName());
			attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
			}
			System.out.print(">\n");

			children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++)
					printNode(children.item(i));
			}

			System.out.println("</" + node.getNodeName() + ">");
			break;

		case Node.ENTITY_REFERENCE_NODE:
			System.out.print("&");
			System.out.print(node.getNodeName());
			System.out.println(";");
			break;

		case Node.CDATA_SECTION_NODE:
			System.out.print("<![CDATA[");
			System.out.print(node.getNodeValue());
			System.out.println("]]>\n");
			break;

		case Node.TEXT_NODE:
			System.out.print(node.getNodeValue().trim());
			break;

		case Node.PROCESSING_INSTRUCTION_NODE:
			System.out.print("<?");
			System.out.print(node.getNodeName());
			System.out.print(" " + node.getNodeValue());
			System.out.println("?>\n");
			break;

		case Node.COMMENT_NODE:
			System.out.print("<!--");
			System.out.print(node.getNodeValue());
			System.out.println("-->");
			break;

		default:
			System.out.print("Unknown node");
			break;

		}
	}

	public static void main(String[] args) {
		if (args.length > 1) {
			try {
				processXMLDOM(args[0], args[1]);
			} catch (ParserConfigurationException e) {
				System.err.println("Document Bilder not correctly configured...");
				e.printStackTrace();
			} catch (SAXException e) {
				System.err.println("Problems to read the xsd file...");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Problems to read the xml file...");
				e.printStackTrace();
			}
		} else {
			System.out.println("Usage: "+XMLDomProcessing.class.getSimpleName()+" xml_file xsd_file");
			System.out.println("No xml or xsd file supplied.");
		}
	}

}
