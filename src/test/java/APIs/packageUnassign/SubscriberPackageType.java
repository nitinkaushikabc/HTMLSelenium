//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.24 at 11:03:06 AM CEST 
//


package APIs.packageUnassign;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberPackageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberPackageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriberDataAttributes" type="{}subscriberDataAttributesType"/>
 *         &lt;element name="packageData" type="{}packageDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriberPackageType", propOrder = {
    "subscriberDataAttributes",
    "packageData"
})
public class SubscriberPackageType {

    @XmlElement(required = true)
    protected SubscriberDataAttributesType subscriberDataAttributes;
    @XmlElement(required = true)
    protected PackageDataType packageData;

    /**
     * Gets the value of the subscriberDataAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberDataAttributesType }
     *     
     */
    public SubscriberDataAttributesType getSubscriberDataAttributes() {
        return subscriberDataAttributes;
    }

    /**
     * Sets the value of the subscriberDataAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberDataAttributesType }
     *     
     */
    public void setSubscriberDataAttributes(SubscriberDataAttributesType value) {
        this.subscriberDataAttributes = value;
    }

    /**
     * Gets the value of the packageData property.
     * 
     * @return
     *     possible object is
     *     {@link PackageDataType }
     *     
     */
    public PackageDataType getPackageData() {
        return packageData;
    }

    /**
     * Sets the value of the packageData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageDataType }
     *     
     */
    public void setPackageData(PackageDataType value) {
        this.packageData = value;
    }

}
