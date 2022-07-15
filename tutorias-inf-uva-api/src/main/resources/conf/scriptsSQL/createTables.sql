DROP TABLE IF EXISTS Tutoria;
DROP TABLE IF EXISTS SolicitudTutoria;
DROP TABLE IF EXISTS FranjaTutorias;
DROP TABLE IF EXISTS HorarioTutorias;
DROP TABLE IF EXISTS MencionesCurso;
DROP TABLE IF EXISTS AsignaturasMencion;
DROP TABLE IF EXISTS AsignaturasUsuario;
DROP TABLE IF EXISTS AsignaturasCurso;
DROP TABLE IF EXISTS Asignatura;
DROP TABLE IF EXISTS TitulacionesUsuario;
DROP TABLE IF EXISTS CursoAcademico;
DROP TABLE IF EXISTS Titulacion;
DROP TABLE IF EXISTS Profesor;
DROP TABLE IF EXISTS Alumno;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Dia;
DROP TABLE IF EXISTS TipoHorario;
DROP TABLE IF EXISTS EstadoSolicitud;
DROP TABLE IF EXISTS MencionTitulacion;
DROP TABLE IF EXISTS NivelTitulacion;

DROP SEQUENCE IF EXISTS SECUENCIA_ID_FRANJATUTORIAS;
DROP SEQUENCE IF EXISTS SECUENCIA_ID_HORARIOTUTORIAS;
DROP SEQUENCE IF EXISTS SECUENCIA_ID_SOLICITUDTUTORIA;
DROP SEQUENCE IF EXISTS SECUENCIA_ID_TUTORIA;


-- Enum
CREATE TABLE NivelTitulacion
(
	id SMALLINT NOT NULL,
	nivel VARCHAR(10) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

INSERT INTO NivelTitulacion VALUES
	(1, 'grado'),
	(2, 'master');


-- Enum
CREATE TABLE EstadoSolicitud
(
	id SMALLINT NOT NULL,
	estado VARCHAR(10) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

INSERT INTO EstadoSolicitud VALUES
	(1, 'pendiente'),
	(2, 'aceptada'),
	(3, 'rechazada'),
	(4, 'confirmada'),
	(5, 'cancelada');


-- Enum
CREATE TABLE TipoHorario
(
	id SMALLINT NOT NULL,
	tipo VARCHAR(25) NOT NULL UNIQUE,
	fechaInicio DATE NOT NULL,
	fechaFin DATE NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO TipoHorario VALUES
	(1, 'Primer Cuatrimestre', '2021-09-13', '2022-02-11'),
	(2, 'Segundo Cuatrimestre', '2022-02-11', '2022-08-28'),
	(3, 'Anual', '2021-09-13', '2022-08-28');


-- Enum
CREATE TABLE Dia
(
	id SMALLINT NOT NULL,
	nombre VARCHAR(10) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

INSERT INTO Dia VALUES
	(1, 'lunes'),
	(2, 'martes'),
	(3, 'miercoles'),
	(4, 'jueves'),
	(5, 'viernes');

-- Entity
CREATE TABLE MencionTitulacion
(
	id INTEGER NOT NULL,
	acronimo VARCHAR(5) NOT NULL UNIQUE,
	nombre VARCHAR(50) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

-- Entity
CREATE TABLE Usuario 
(
	identificador VARCHAR(15) NOT NULL UNIQUE,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	nif VARCHAR(15) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE UNIQUE,
	password VARCHAR(255) NOT NULL,
	PRIMARY KEY (identificador)
);


-- Entity
CREATE TABLE Alumno
(
	identificador VARCHAR(15) NOT NULL,
	nia INTEGER NOT NULL UNIQUE,
	PRIMARY KEY (identificador),
	FOREIGN KEY (identificador) REFERENCES Usuario(identificador)
);


-- Entity
CREATE TABLE Profesor
(
	identificador VARCHAR(15) NOT NULL,
	centroHabitual VARCHAR(100) NOT NULL,
	despacho VARCHAR(100) NOT NULL,
	telefono VARCHAR(50) NOT NULL,
	PRIMARY KEY (identificador),
	FOREIGN KEY (identificador) REFERENCES Usuario(identificador)
);


-- Entity
CREATE TABLE Titulacion
(
	codigo INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	plan SMALLINT NOT NULL,
	nivelTitulacionId SMALLINT NOT NULL,
	PRIMARY KEY (codigo),
	FOREIGN KEY (nivelTitulacionId) REFERENCES NivelTitulacion(id)
);


-- Association
CREATE TABLE TitulacionesUsuario
(
	identificador VARCHAR(15) NOT NULL,
	codigo INTEGER NOT NULL,
	PRIMARY KEY (identificador, codigo),
	FOREIGN KEY (identificador) REFERENCES Usuario(identificador),
	FOREIGN KEY (codigo) REFERENCES Titulacion(codigo)
);


-- Entity
CREATE TABLE CursoAcademico
(
	id INTEGER NOT NULL,
	curso VARCHAR(10) NOT NULL,
	cursoNumerico SMALLINT NOT NULL,
	codigo INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (codigo) REFERENCES Titulacion(codigo)
);

-- Entity
CREATE TABLE Asignatura
(
	codigo INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	acronimo VARCHAR(5) NOT NULL,
	PRIMARY KEY (codigo)
);


-- Association
CREATE TABLE AsignaturasCurso
(
	cursoId INTEGER NOT NULL,
	codigo INTEGER NOT NULL,
	PRIMARY KEY (cursoId, codigo), 
	FOREIGN KEY (cursoId) REFERENCES CursoAcademico(id),
	FOREIGN KEY (codigo) REFERENCES Asignatura(codigo)
);


-- Association
CREATE TABLE AsignaturasUsuario
(
	identificador VARCHAR(15) NOT NULL,
	codigo INTEGER NOT NULL,
	PRIMARY KEY (identificador, codigo),
	FOREIGN KEY (identificador) REFERENCES Usuario(identificador),
	FOREIGN KEY (codigo) REFERENCES Asignatura(codigo)
);

-- Association
CREATE TABLE AsignaturasMencion
(
	mencionId INTEGER NOT NULL,
	codigo INTEGER NOT NULL,
	cursoId INTEGER NOT NULL,
	PRIMARY KEY (mencionId, codigo),
	FOREIGN KEY (mencionId) REFERENCES MencionTitulacion(id),
	FOREIGN KEY (codigo) REFERENCES Asignatura(codigo),
	FOREIGN KEY (cursoId) REFERENCES CursoAcademico(id)
);


-- Association
CREATE TABLE MencionesCurso
(
	cursoId INTEGER NOT NULL,
	mencionId INTEGER NOT NULL,
	PRIMARY KEY (cursoId, mencionId), 
	FOREIGN KEY (cursoId) REFERENCES CursoAcademico(id),
	FOREIGN KEY (mencionId) REFERENCES MencionTitulacion(id)
);


-- Entity
CREATE TABLE HorarioTutorias
(
	id BIGINT NOT NULL,
	idProfesor VARCHAR(15) NOT NULL,
	tipoHorarioId SMALLINT NOT NULL,
	PRIMARY KEY (id), 
	FOREIGN KEY (idProfesor) REFERENCES Profesor(identificador),
	FOREIGN KEY (tipoHorarioId) REFERENCES TipoHorario(id)
);


-- Entity
CREATE TABLE FranjaTutorias
(
	id BIGINT NOT NULL,
	horaInicio TIME NOT NULL,
	horaFin TIME NOT NULL,
	centro VARCHAR(255) NOT NULL,
	despacho VARCHAR(50) NOT NULL,
	diaId SMALLINT NOT NULL,
	horarioTutoriasId INTEGER NOT NULL,
	PRIMARY KEY (id), 
	FOREIGN KEY (diaId) REFERENCES Dia(id),
	FOREIGN KEY (horarioTutoriasId) REFERENCES HorarioTutorias(id)
);


-- Entity
CREATE TABLE SolicitudTutoria
(
	id BIGINT NOT NULL,
	asunto VARCHAR(100) NOT NULL,
	comentarioAlumno TEXT,
	fechaSolicitud TIMESTAMP NOT NULL, 
	fechaTutoria DATE NOT NULL,
	horaInicio TIME NOT NULL,
	horaFin TIME NOT NULL,
	grupal BOOLEAN NOT NULL,
	motivoRechazo TEXT,
	rechazadaVistaPorAlumno BOOLEAN NOT NULL DEFAULT FALSE,
	ubicacionTutoria VARCHAR(255),
	comentarioProfesor TEXT,
	propuestaNuevoHorario BOOLEAN NOT NULL DEFAULT FALSE,
	codigoAsignatura INTEGER,
	idAlumno VARCHAR(15) NOT NULL,
	idProfesor VARCHAR(15) NOT NULL,
	estadoId SMALLINT NOT NULL,
	PRIMARY KEY (id), 
	FOREIGN KEY (codigoAsignatura) REFERENCES Asignatura(codigo),
	FOREIGN KEY (idAlumno) REFERENCES Alumno(identificador),
	FOREIGN KEY (idProfesor) REFERENCES Profesor(identificador),
	FOREIGN KEY (estadoId) REFERENCES EstadoSolicitud(id)
);


-- Entity
CREATE TABLE Tutoria
(
	id BIGINT NOT NULL,
	cancelada BOOLEAN NOT NULL,
	motivoCancelacion VARCHAR(255),
	solicitudId INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (solicitudId) REFERENCES SolicitudTutoria(id)
);

-- Sequence
CREATE SEQUENCE SECUENCIA_ID_FRANJATUTORIAS
    INCREMENT 1
    START 1
    OWNED BY franjatutorias.id;

ALTER SEQUENCE SECUENCIA_ID_FRANJATUTORIAS
    OWNER TO postgres;

COMMENT ON SEQUENCE SECUENCIA_ID_FRANJATUTORIAS
    IS 'Secuencia para la generación del id de las franjas de tutorías';


-- Sequence
CREATE SEQUENCE SECUENCIA_ID_HORARIOTUTORIAS
    INCREMENT 1
    START 1
    OWNED BY horariotutorias.id;

ALTER SEQUENCE SECUENCIA_ID_HORARIOTUTORIAS
    OWNER TO postgres;

COMMENT ON SEQUENCE SECUENCIA_ID_HORARIOTUTORIAS
    IS 'Secuencia para la generación del id de los horarios de tutorías';


-- Sequence
CREATE SEQUENCE SECUENCIA_ID_SOLICITUDTUTORIA
    INCREMENT 1
    START 1
    OWNED BY solicitudtutoria.id;

ALTER SEQUENCE SECUENCIA_ID_SOLICITUDTUTORIA
    OWNER TO postgres;

COMMENT ON SEQUENCE SECUENCIA_ID_SOLICITUDTUTORIA
    IS 'Secuencia para la generación del id de las solicitudes de tutoría';

	
-- Sequence
CREATE SEQUENCE SECUENCIA_ID_TUTORIA
    INCREMENT 1
    START 1
    OWNED BY tutoria.id;

ALTER SEQUENCE SECUENCIA_ID_TUTORIA
    OWNER TO postgres;

COMMENT ON SEQUENCE SECUENCIA_ID_TUTORIA
    IS 'Secuencia para la generación del id de las tutorías';