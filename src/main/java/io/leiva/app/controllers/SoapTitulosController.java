package io.leiva.app.controllers;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.ssl.PKCS8Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.leiva.app.soap.SoapClientTitulos;
@RestController
public class SoapTitulosController {
	@Autowired
    private SoapClientTitulos soapClientTitulos;
	
	private static String UPLOADED_FOLDER = "F://temp//";

    @GetMapping("/cargarTitulo")
    public String convertirNumeroPalabras() {
        return soapClientTitulos.consultaTitulo(303,"user","password");
    }
    
    
    @PostMapping("/generarFirma") // //new annotation since 4.3
    public String singleFileUpload (@RequestParam("files") MultipartFile[] files,HttpServletRequest request,
            RedirectAttributes redirectAttributes) throws Exception {
    	byte[] archivo_cer = null;
    	byte[] archivo_key = null;
    	String password = request.getParameter("password");
    	String cadena_original = request.getParameter("cadena_original");
    	
    	
		StringJoiner sj = new StringJoiner(" , ");
		
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue; //next pls
			}
			try {
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				if(extension.equals(".cer")) {
					archivo_cer = file.getBytes();
				}else if(extension.equals(".key")) {
					archivo_key = file.getBytes();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuffer result = new StringBuffer();
		result.append("Archivo CER (base 64):\n");
		result.append(Base64.encodeBase64String(archivo_cer));
		result.append("\n");
		result.append("\n");
		result.append("Firma (base 64):\n");
		result.append(
				sign(archivo_key,password,cadena_original)
				);
		return result.toString();
    }
    
    public static String sign(byte[] key, String password, String toSign) throws Exception {
		final PKCS8Key pkcs8Key = new PKCS8Key(key, password.toCharArray());

		final PrivateKey privateKey = pkcs8Key.getPrivateKey();

		final Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(toSign.getBytes("UTF-8"));

		return Base64.encodeBase64String(signature.sign());
	}

}

