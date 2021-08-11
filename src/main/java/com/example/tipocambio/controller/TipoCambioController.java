package com.example.tipocambio.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.tipocambio.controller.common.ApiRequest;
import com.example.tipocambio.controller.request.TipoCambioRequest;
import com.example.tipocambio.controller.response.TipoCambioResponse;
import com.example.tipocambio.model.TipoCambio;
import com.example.tipocambio.repository.TipoCambioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TipoCambioController {

	@Autowired
	private TipoCambioRepository tipoCambioRepository;

	@RequestMapping(value = "actualizaTipoCambio", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(@RequestBody ApiRequest<TipoCambioRequest> request) {
		if (request.getBody().getOperacion().equals("") || request.getBody().getMonedaOrigen().equals("")
				|| request.getBody().getMonedaDestino().equals("") || request.getBody().getTipoCambio().equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			String monedaOrigen = request.getBody().getMonedaOrigen();
			String monedaDestino = request.getBody().getMonedaDestino();
			String operacion = request.getBody().getOperacion();
			String tipoCambio = request.getBody().getTipoCambio();			
			if (isValorCorrecto(tipoCambio)) {
				List<TipoCambio> lstTipoCambio = tipoCambioRepository.findAll();
				if (lstTipoCambio.size() > 0) {
					for (TipoCambio objTipoCambio : lstTipoCambio) {
						if (monedaOrigen.equals(objTipoCambio.getMonedaOrigen())
								&& monedaDestino.equals(objTipoCambio.getMonedaDestino())
								&& operacion.equals(objTipoCambio.getOperacion())) {
							objTipoCambio.setTipoCambio(tipoCambio);
							objTipoCambio = tipoCambioRepository.save(objTipoCambio);
							if (objTipoCambio != null) {
								return new ResponseEntity<>(HttpStatus.OK);
							} else {
								return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}
					}
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@RequestMapping(value = "calculaTipoCambio", method = RequestMethod.POST)
	public ResponseEntity<TipoCambioResponse> calculate(@RequestBody ApiRequest<TipoCambioRequest> request) {
		TipoCambioResponse objTipoCambioResponse = new TipoCambioResponse();
		if (request.getBody().getMonto().equals("") || request.getBody().getMonedaOrigen().equals("")
				|| request.getBody().getMonedaDestino().equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			String monto = request.getBody().getMonto();
			String monedaOrigen = request.getBody().getMonedaOrigen();
			String monedaDestino = request.getBody().getMonedaDestino();
			String operacion = request.getBody().getOperacion();
			List<TipoCambio> lstTipoCambio = tipoCambioRepository.findAll();
			if (lstTipoCambio.size() > 0) {
				DecimalFormat df = new DecimalFormat("#####.##");
				for (TipoCambio objTipoCambio : lstTipoCambio) {
					if (monedaOrigen.equals(objTipoCambio.getMonedaOrigen())
							&& monedaDestino.equals(objTipoCambio.getMonedaDestino())
							&& operacion.equals(objTipoCambio.getOperacion())) {
						String tipoCambio = objTipoCambio.getTipoCambio();
						String resultado = "";
						if (operacion.equals("COMPRA")) {
							resultado = df.format(Double.parseDouble(monto) / Double.parseDouble(tipoCambio));
						} else if (operacion.equals("VENTA")) {
							resultado = df.format(Double.parseDouble(tipoCambio) * Double.parseDouble(monto));
						}
						objTipoCambioResponse.setMonto(monto);
						objTipoCambioResponse.setMonedaDestino(monedaDestino);
						objTipoCambioResponse.setMonedaOrigen(monedaOrigen);
						objTipoCambioResponse.setOperacion(operacion);
						objTipoCambioResponse.setTipoCambio(tipoCambio);
						objTipoCambioResponse.setMontoResultado(resultado);
						return new ResponseEntity<>(objTipoCambioResponse, HttpStatus.OK);						
					}
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);				
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	private static boolean isValorCorrecto(String valor){
		try {
			Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
