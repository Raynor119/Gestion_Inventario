CREATE TABLE Producto(codigo VARCHAR(255) PRIMARY KEY NOT NULL,nombre VARCHAR(255) NOT NULL,cantidad DOUBLE NOT NULL,TipoC VARCHAR(255) NOT NULL,CosteP BIGINT NOT NULL,precio BIGINT NOT NULL,Iva INT NOT NULL);

CREATE TABLE Venta(codigo INT AUTO_INCREMENT,Fecha DATETIME NOT NULL DEFAULT NOW(),Efectivo BIGINT NOT NULL,PRIMARY KEY (codigo));

CREATE TABLE VentasProductos(Id INT AUTO_INCREMENT,codigoV INT NOT NULL,codigoP VARCHAR(255),CantidadV DOUBLE NOT NULL,CantidadD DOUBLE NOT NULL,CostePV BIGINT NOT NULL,PrecioPV BIGINT NOT NULL,IvaPV INT NOT NULL,EstadoDevolucion VARCHAR(255) NOT NULL,ObservacionD VARCHAR(255),PRIMARY KEY (Id),FOREIGN KEY (codigoV) REFERENCES Venta(codigo),FOREIGN KEY (codigoP) REFERENCES Producto(codigo) ON UPDATE CASCADE);

