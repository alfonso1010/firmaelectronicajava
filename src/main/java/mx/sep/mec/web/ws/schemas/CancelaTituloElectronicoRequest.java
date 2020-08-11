//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.06.08 a las 04:20:27 PM CDT 
//


package mx.sep.mec.web.ws.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioControl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="motCancelacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="autenticacion" type="{http://ws.web.mec.sep.mx/schemas}autenticacionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "folioControl",
    "motCancelacion",
    "autenticacion"
})
@XmlRootElement(name = "cancelaTituloElectronicoRequest")
public class CancelaTituloElectronicoRequest {

    @XmlElement(required = true)
    protected String folioControl;
    @XmlElement(required = true)
    protected String motCancelacion;
    @XmlElement(required = true)
    protected AutenticacionType autenticacion;

    /**
     * Obtiene el valor de la propiedad folioControl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioControl() {
        return folioControl;
    }

    /**
     * Define el valor de la propiedad folioControl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioControl(String value) {
        this.folioControl = value;
    }

    /**
     * Obtiene el valor de la propiedad motCancelacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotCancelacion() {
        return motCancelacion;
    }

    /**
     * Define el valor de la propiedad motCancelacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotCancelacion(String value) {
        this.motCancelacion = value;
    }

    /**
     * Obtiene el valor de la propiedad autenticacion.
     * 
     * @return
     *     possible object is
     *     {@link AutenticacionType }
     *     
     */
    public AutenticacionType getAutenticacion() {
        return autenticacion;
    }

    /**
     * Define el valor de la propiedad autenticacion.
     * 
     * @param value
     *     allowed object is
     *     {@link AutenticacionType }
     *     
     */
    public void setAutenticacion(AutenticacionType value) {
        this.autenticacion = value;
    }

}
