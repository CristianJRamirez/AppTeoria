
package mypackage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mypackage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CristianJavier_QNAME = new QName("", "Cristian_Javier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mypackage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CristianJavierType }
     * 
     */
    public CristianJavierType createCristianJavierType() {
        return new CristianJavierType();
    }

    /**
     * Create an instance of {@link JavaType }
     * 
     */
    public JavaType createJavaType() {
        return new JavaType();
    }

    /**
     * Create an instance of {@link AndroidType }
     * 
     */
    public AndroidType createAndroidType() {
        return new AndroidType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CristianJavierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Cristian_Javier")
    public JAXBElement<CristianJavierType> createCristianJavier(CristianJavierType value) {
        return new JAXBElement<CristianJavierType>(_CristianJavier_QNAME, CristianJavierType.class, null, value);
    }

}
