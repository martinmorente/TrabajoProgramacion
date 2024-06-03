CREATE DATABASE IF NOT EXISTS Escaneo_Piz;
USE Escaneo_Piz;

CREATE TABLE IF NOT EXISTS Archivo(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ruta VARCHAR(255) NOT NULL,
    hash VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS EscaneoArchivo(
    id INT PRIMARY KEY AUTO_INCREMENT,
   
    fecha_Escaneo DATETIME NOT NULL,
    resultado VARCHAR(50) NULL,
    num_Escaneo INT NOT NULL,
    nombreAntivirus VARCHAR(255),
    id_archivo INT PRIMARY KEY,
    FOREIGN KEY (id_archivo) REFERENCES Archivo(id)
);

CREATE TABLE IF NOT EXISTS Escaneo_Web(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fechaEscaneo DATETIME NOT NULL,
    resultado VARCHAR(50)  NULL,
    num_escaneoWeb INT NOT NULL,
    idSitiosWeb INT,
    nombreAntivirus VARCHAR(255),
    FOREIGN KEY (idSitiosWeb) REFERENCES Sitios_web(id)
);

CREATE TABLE IF NOT EXISTS Sitios_web(
    id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255) NOT NULL
);