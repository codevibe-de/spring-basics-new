package summer;

// TODO Demo 1: am Ende dieser Datei die Klassen BeansElement (<beans>, Liste von <bean>) und
//  BeanElement (Attribute name und class) anlegen und per JAXB-Annotationen auf die XML-Elemente
//  abbilden (@XmlRootElement, @XmlElement, @XmlAttribute)

/**
 * Bean-Container, der seine Bean-Definitionen aus einer XML-Datei im Classpath liest, z. B.
 * {@code new XmlConfigBeanContainer("/beans.xml")}.
 */
public class XmlConfigBeanContainer extends BeanContainer {

    public XmlConfigBeanContainer(String xmlResourcePath) {
        // TODO Demo 2: Bean-Definitionen aus der XML-Datei laden (load), danach die Beans erzeugen (refresh)
    }

    private void load(String xmlResourcePath) {
        // TODO Demo 3: XML-Datei per getClass().getResourceAsStream() öffnen, fehlt sie, eine
        //  BeanDefinitionStoreException werfen; per JAXB (JAXBContext, Unmarshaller) in ein
        //  BeansElement einlesen und je BeanElement defineBean(name, Class.forName(className)) aufrufen
    }
}
