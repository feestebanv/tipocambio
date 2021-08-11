package com.example.tipocambio.model;

import javax.persistence.*;

@Entity
@Table(name = "tipocambio")
public class TipoCambio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "tipo_cambio")
	private String tipoCambio;
	
	@Column(name = "moneda_origen")
	private String monedaOrigen;
	
	@Column(name = "moneda_destino")
	private String monedaDestino;
	
	@Column(name = "operacion")
	private String operacion;

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
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

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
}
