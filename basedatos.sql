CREATE TABLE Producto(codigo VARCHAR(255) PRIMARY KEY NOT NULL,nombre VARCHAR(255) NOT NULL,cantidad INT NOT NULL,CosteP BIGINT NOT NULL,precio BIGINT NOT NULL);

CREATE TABLE Venta(codigo SERIAL NOT NULL,Fecha DATETIME NOT NULL DEFAULT NOW(),Efectivo BIGINT NOT NULL,TotalV BIGINT NOT NULL,PRIMARY KEY (codigo));

CREATE TABLE VentasProductos(codigoV SERIAL NOT NULL,codigoP VARCHAR(255) NOT NULL,CantidadV INT NOT NULL,PRIMARY KEY (codigoV,codigoP),FOREIGN KEY (codigoV) REFERENCES Venta(codigo),FOREIGN KEY (codigoP) REFERENCES Producto(codigo));