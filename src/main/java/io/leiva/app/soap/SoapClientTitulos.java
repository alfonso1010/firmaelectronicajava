package io.leiva.app.soap;

import java.math.BigInteger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import mx.sep.mec.web.ws.schemas.AutenticacionType;
import mx.sep.mec.web.ws.schemas.ConsultaProcesoTituloElectronicoRequest;
import mx.sep.mec.web.ws.schemas.ConsultaProcesoTituloElectronicoResponse;

@Service
@Component("TitulosElectronicos")
@Primary
public class SoapClientTitulos  extends WebServiceGatewaySupport {
	 private String endpoint = "https://metqa.siged.sep.gob.mx/met-ws/services/TitulosElectronicos.wsdl";
	 
	
	 
    public String consultaTitulo(Integer noLote, String usuario, String password) {
    	AutenticacionType autenticate = new AutenticacionType();
    	autenticate.setUsuario(usuario);
    	autenticate.setPassword(password);
    	ConsultaProcesoTituloElectronicoRequest request = new ConsultaProcesoTituloElectronicoRequest();
    	request.setAutenticacion(autenticate);
        request.setNumeroLote(BigInteger.valueOf(noLote));
        ConsultaProcesoTituloElectronicoResponse response = (ConsultaProcesoTituloElectronicoResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint,
                request);
        return response.getMensaje();
    }
}
