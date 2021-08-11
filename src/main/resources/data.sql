DROP TABLE IF EXISTS tipocambio;

CREATE TABLE tipocambio (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tipo_cambio VARCHAR(250) NOT NULL,
  moneda_origen VARCHAR(250) NOT NULL,
  moneda_destino VARCHAR(250) NOT NULL,
  operacion VARCHAR(250) NOT NULL
);

INSERT INTO tipocambio (tipo_cambio, moneda_origen, moneda_destino, operacion) VALUES
  ('4.25', 'SOLES', 'DOLARES', 'COMPRA'),
  ('4.05', 'DOLARES', 'SOLES', 'VENTA');