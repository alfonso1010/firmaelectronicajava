package io.leiva.app.soap;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import mx.sep.mec.web.ws.schemas.AutenticacionType;
import mx.sep.mec.web.ws.schemas.CancelaTituloElectronicoRequest;
import mx.sep.mec.web.ws.schemas.CancelaTituloElectronicoResponse;
import mx.sep.mec.web.ws.schemas.CargaTituloElectronicoRequest;
import mx.sep.mec.web.ws.schemas.CargaTituloElectronicoResponse;
import mx.sep.mec.web.ws.schemas.ConsultaProcesoTituloElectronicoRequest;
import mx.sep.mec.web.ws.schemas.ConsultaProcesoTituloElectronicoResponse;
import mx.sep.mec.web.ws.schemas.DescargaTituloElectronicoRequest;
import mx.sep.mec.web.ws.schemas.DescargaTituloElectronicoResponse;

@Service
@Component("TitulosElectronicos")
@Primary
public class SoapClientTitulos  extends WebServiceGatewaySupport {
	 private String endpoint_qa = "https://metqa.siged.sep.gob.mx/met-ws/services/TitulosElectronicos.wsdl";
	 private String endpoint_produccion = "https://metqa.siged.sep.gob.mx/met-ws/services/TitulosElectronicos.wsdl";
	 
	 public Map<String, Object> enviarTitulo(String nombre,byte[] titulo,String usuario,String password,String ambiente){
		HashMap<String, Object> map = new HashMap<>();
		AutenticacionType autenticate = new AutenticacionType();
    	autenticate.setUsuario(usuario);
    	autenticate.setPassword(password);
    	CargaTituloElectronicoRequest request = new CargaTituloElectronicoRequest();
    	request.setAutenticacion(autenticate);
    	request.setNombreArchivo(nombre);
    	request.setArchivoBase64(titulo);
    	if(ambiente.equals("0")) {
        	CargaTituloElectronicoResponse response = (CargaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_qa,
                    request);
        	map.put("numeroLote",response.getNumeroLote());
        	map.put("mensaje",response.getMensaje());
        	map.put("ambiente","QA");
            return map;
        }else {
        	CargaTituloElectronicoResponse response = (CargaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_produccion,
                    request);
        	map.put("numeroLote",response.getNumeroLote());
        	map.put("mensaje",response.getMensaje());
        	map.put("ambiente","PR");
            return map;
        }
	 }
	 
	 public  Map<String, Object> consultaTitulo(Integer noLote, String usuario, String password, String ambiente) {
		HashMap<String, Object> map = new HashMap<>();
    	AutenticacionType autenticate = new AutenticacionType();
    	autenticate.setUsuario(usuario);
    	autenticate.setPassword(password);
    	ConsultaProcesoTituloElectronicoRequest request = new ConsultaProcesoTituloElectronicoRequest();
    	request.setAutenticacion(autenticate);
        request.setNumeroLote(BigInteger.valueOf(noLote));
        if(ambiente.equals("0")) {
        	ConsultaProcesoTituloElectronicoResponse response = (ConsultaProcesoTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_qa,
                    request);
        	map.put("numeroLote",response.getNumeroLote());
        	map.put("mensaje",response.getMensaje());
        	map.put("estatus",response.getEstatusLote());
        	map.put("ambiente","QA");
            return map;
        }else {
        	ConsultaProcesoTituloElectronicoResponse response = (ConsultaProcesoTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_produccion,
                    request);
        	map.put("numeroLote",response.getNumeroLote());
        	map.put("mensaje",response.getMensaje());
        	map.put("estatus",response.getEstatusLote());
        	map.put("ambiente","PR");
        	return map;
        }
    }
	 
	 public  Map<String, Object> descargaTitulo(Integer noLote, String usuario, String password, String ambiente) {
			HashMap<String, Object> map = new HashMap<>();
	    	AutenticacionType autenticate = new AutenticacionType();
	    	autenticate.setUsuario(usuario);
	    	autenticate.setPassword(password);
	    	DescargaTituloElectronicoRequest request = new DescargaTituloElectronicoRequest();
	    	request.setAutenticacion(autenticate);
	        request.setNumeroLote(BigInteger.valueOf(noLote));
	        if(ambiente.equals("0")) {
	        	DescargaTituloElectronicoResponse response = (DescargaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_qa,
	                    request);
	        	map.put("numeroLote",response.getNumeroLote());
	        	map.put("mensaje",response.getMensaje());
	        	map.put("titulosBase64",response.getTitulosBase64());
	        	map.put("ambiente","QA");
	            return map;
	        }else {
	        	DescargaTituloElectronicoResponse response = (DescargaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_produccion,
	                    request);
	        	map.put("numeroLote",response.getNumeroLote());
	        	map.put("mensaje",response.getMensaje());
	        	map.put("titulosBase64",response.getTitulosBase64());
	        	map.put("ambiente","PR");
	        	return map;
	        }
	  }
	 
	 public  Map<String, Object> cancelaTitulo(String folio_control, String usuario, String password,String motivo, String ambiente) {
			HashMap<String, Object> map = new HashMap<>();
	    	AutenticacionType autenticate = new AutenticacionType();
	    	autenticate.setUsuario(usuario);
	    	autenticate.setPassword(password);
	    	CancelaTituloElectronicoRequest request = new CancelaTituloElectronicoRequest();
	    	request.setAutenticacion(autenticate);
	    	request.setFolioControl(folio_control);
	    	request.setMotCancelacion(motivo);
	        if(ambiente.equals("0")) {
	        	CancelaTituloElectronicoResponse response = (CancelaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_qa,
	                    request);
	        	map.put("codigo",response.getCodigo());
	        	map.put("mensaje",response.getMensaje());
	        	map.put("ambiente","QA");
	            return map;
	        }else {
	        	CancelaTituloElectronicoResponse response = (CancelaTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint_produccion,
	                    request);
	        	map.put("codigo",response.getCodigo());
	        	map.put("mensaje",response.getMensaje());
	        	map.put("ambiente","PR");
	        	return map;
	        }
	    }
}
