CREATE DATABASE IF NOT EXISTS Escaneo_Piz;

USE Escaneo_Piz;

CREATE TABLE IF NOT EXISTS Sitios_web(
    id_web INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    url_web VARCHAR(255) NOT NULL,
    tipo_web VARCHAR(255) NOT NULL,
    descripcion_web VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Escaneo_Web(
    id_escaneo_web INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_escaneo DATETIME NOT NULL,
    resultado VARCHAR(50) NOT NULL,
    tipo_maldad VARCHAR(45) NOT NULL,
    num_escaneoWeb INT NOT NULL,
    url_Final VARCHAR(255),
    id_Web INT,
    FOREIGN KEY (id_Web) REFERENCES Sitios_web(id_web)
    );

CREATE TABLE IF NOT EXISTS Virus_web(
    id_Virus_Web INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_virus VARCHAR(45),
    descripcion_virus VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Escaneo_Virus(
    id_virusWeb INT, 
    id_EscaneoWeb INT,
    FOREIGN KEY (id_virusWeb) REFERENCES Virus_web (id_Virus_Web),
    FOREIGN KEY (id_EscaneoWeb) REFERENCES Escaneo_Web (id_escaneo_web)
);

CREATE TABLE IF NOT EXISTS Archivo(
    id_archivo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45),
    ruta VARCHAR(255),
    tamano DOUBLE,
    tipo_archivo VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Escaneo_archivo(
    id_escaneo_archivo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_escaneo DATETIME NOT NULL,
    resultado VARCHAR(255) NOT NULL,
    nivel_amenaza VARCHAR(45) NOT NULL,
    num_escaneoArhcivo INT NOT NULL,
    id_archivoEscaneo INT,
    FOREIGN KEY (id_archivoEscaneo) REFERENCES Archivo (id_archivo)
);

CREATE TABLE IF NOT EXISTS Registro_Archivo(
    idRegistro_Archivo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tipo_antivirus_escaneado VARCHAR(45),
    descripcion_antivirus VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Escaneo_Registro_Archivo(
    escaneo_Registro INT,
    registro_Escaneo INT,
    FOREIGN KEY (escaneo_Registro) REFERENCES Escaneo_archivo(id_escaneo_archivo),
    FOREIGN KEY (registro_Escaneo) REFERENCES Registro_Archivo(idRegistro_Archivo)
);