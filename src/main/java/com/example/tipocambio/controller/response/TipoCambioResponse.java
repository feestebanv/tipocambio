package com.example.tipocambio.controller.response;

public class TipoCambioResponse {
	private String monto;
	private String monedaOrigen;
	private String monedaDestino;
	private String tipoCambio;
	private String operacion;
	private String montoResultado;
	
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getMontoResultado() {
		return montoResultado;
	}
	public void setMontoResultado(String montoResultado) {
		this.montoResultado = montoResultado;
	}
		
}
