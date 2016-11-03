
package mypackage;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Cristian_JavierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cristian_JavierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Java" type="{}JavaType"/>
 *         &lt;element name="Android" type="{}AndroidType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cristian_JavierType", propOrder = {
    "java",
    "android"
})
@XmlRootElement(
        name="Cristian_Javier"
)
public class CristianJavierType {

    @XmlElement(name = "Java", required = true)
    protected List<JavaType> java;
    @XmlElement(name = "Android", required = true)
    protected List<AndroidType> android;



    public List<JavaType> getJava() {
        if (java == null) {
            java = new ArrayList<JavaType>();
        }
        return this.java;
    }
    public List<AndroidType> getAndroid() {
        if (android == null) {
            android = new ArrayList<AndroidType>();
        }
        return this.android;
    }
}
