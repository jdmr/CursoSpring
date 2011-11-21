DROP TABLE IF EXISTS ALUMNOS;

CREATE TABLE ALUMNOS (
    ID BIGINT NOT NULL AUTO_INCREMENT
    , NOMBRE VARCHAR(32) UNIQUE
    , APELLIDO VARCHAR(32)
    , PRIMARY KEY (ID)
);