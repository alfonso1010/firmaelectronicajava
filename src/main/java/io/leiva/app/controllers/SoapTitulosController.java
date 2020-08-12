package io.leiva.app.controllers;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.ssl.PKCS8Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
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
    public Map<String, String> singleFileUpload (@RequestParam("files") MultipartFile[] files,HttpServletRequest request,
            RedirectAttributes redirectAttributes) throws Exception {
    	byte[] archivo_cer = null;
    	byte[] archivo_key = null;
    	String password = request.getParameter("password");
    	String cadena_original = request.getParameter("cadena_original");
    	
    	HashMap<String, String> map = new HashMap<>();
		StringJoiner sj = new StringJoiner(" , ");
		
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue; //next pls
			}
			try {
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				if(extension.equals(".cer")) {
					archivo_cer = file.getBytes();
					map.put("nombre_archivo_cer", file.getOriginalFilename());
				}else if(extension.equals(".key")) {
					archivo_key = file.getBytes();
					map.put("nombre_archivo_key", file.getOriginalFilename());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			
		    map.put("firma",sign(archivo_key,password,cadena_original));
		    return map;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ocurrió un error generando la firma");
		}
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

