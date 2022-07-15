INSERT INTO MencionTitulacion VALUES
	(1, 'IS', 'Ingeniería de Software'),
	(2, 'TI', 'Tecnologías de la Información'),
	(3, 'CO', 'Computación');

INSERT INTO Usuario VALUES
	('alumno1', 'Richard', 'Albán Fernández', '11111111A', 'richard.alban@alumnos.uva.es', 'password'),
	('alumno2', 'Patricia', 'Hernández García', '22222222A', 'alumno2@alumnos.uva.es', 'password'),
	('alumno3', 'Gael', 'Esclavo Caballero', '33333333A', 'alumno3@alumnos.uva.es', 'password'),
	('profesor1', 'David', 'de Castro Buendía', '44444444A', 'profesor1@uva.es', 'password'),
	('profesor2', 'Antonio', 'Fuertes Gutierrez', '55555555A', 'profesor2@uva.es', 'password'),
	('profesor3', 'Malenia', 'Espada de Miquella', '66666666A', 'profesor3@uva.es', 'password');

INSERT INTO Alumno VALUES
	('alumno1', 1),
	('alumno2', 2),
	('alumno3', 3);

INSERT INTO Profesor VALUES
	('profesor1', 'Escuela de Ingeniería Informática', 'Despacho 1', '111 11 11 11'),
	('profesor2', 'Escuela de Ingeniería Informática', 'Despacho 2', '222 22 22 22'),
	('profesor3', 'Escuela de Ingeniería Informática', 'Despacho 3', '333 33 33 33');

INSERT INTO Titulacion VALUES
	(1, 'Grado en Ingeniería Informática', 2016, 1),
	(2, 'Master en Ingeniería Informática', 2021, 2);

INSERT INTO TitulacionesUsuario VALUES
	('alumno1',1),
	('alumno2',1),
	('alumno3',2),
	('profesor1',1),
	('profesor2',1),
	('profesor3',1),
	('profesor3',2);

INSERT INTO CursoAcademico VALUES
	(1, 'Primero', 1, 1),
	(2, 'Segundo', 2, 1),
	(3, 'Tercero', 3, 1),
	(4, 'Cuarto', 4, 1),
	(5, 'Primero', 1, 2);

-- Asignaturas Grado
INSERT INTO Asignatura VALUES
 (1,'Fundamentos de Matemáticas','FMAT'),
 (2,'Fundamentos de Organización de Empresas','FOE'),
 (3,'Fundamentos de Programación','FPRO'),
 (4,'Matemática Discreta','MDIS'),
 (5,'Sistemas Digitales','SDIG'),
 (6,'Física','PHI'),
 (7,'Ampliación de Matemáticas','AMAT'),
 (8,'Fundamentos de Computadoras','FCOM'),
 (9,'Fundamentos de Redes de Computadoras','FRED'),
 (10,'Paradigmas de Programación','PAR'),
 (11,'Estadística','EST'),
 (12,'Arquitectura y Organización de Computadoras','AOC'),
 (13,'Estructuras de Datos y Algoritmos','EDA'),
 (14,'Fundamentos de Sistemas Operativos','FSO'),
 (15,'Programación Orientada a Objetos','POO'),
 (16,'Estructura de Sistemas Operativos','ESO'),
 (17,'Fundamentos de Ingeniería del Software','FIS'),
 (18,'Fundamentos de Inteligencia Artificial','FIA'),
 (19,'Interacción Persona-Computadora','IPC'),
 (20,'Sistemas Distribuidos','SDIS'),
 (21,'Análisis y Diseño de Algoritmos','ADA'),
 (22,'Análisis y Diseño de Bases de Datos','ADBD'),
 (23,'Ingeniería del Conocimiento','ICON'),
 (24,'Modelado de Sistemas Software','MOD'),
 (25,'Seguridad de Redes y Sistemas','SRS'),
 (26,'Tecnologías para el Desarrollo del Software','TDS'),
 (27,'Computación Paralela','CPAR'),
 (28,'Diseño de Software','DIS'),
 (29,'Economía del Cambio Tecnológico','ECT'),
 (30,'Evaluación y Rendimiento de Sistemas Software','ERSS'),
 (31,'Lenguajes de Programación','LP'),
 (32,'Servicios y Sistemas Web','SSW'),
 (33,'Técnicas de Aprendizaje Automático','TAA'),
 (34,'Administración de Sistemas Operativos','ASO'),
 (35,'Arquitectura de Redes y Servicios','ARS'),
 (36,'Diseño, Administración y Seguridad de Redes','DASR'),
 (37,'Garantía y Seguridad de la Información','GSI'),
 (38,'Sistemas Multimedia','SMUL'),
 (39,'Tecnología y Diseño de Bases de Datos','TDBD'),
 (40,'Arquitecturas de Computación Avanzadas','ACA'),
 (41,'Diseño, Integración y Adaptación de Software','DIAS'),
 (42,'Evaluación de Sistemas Informáticos','ESI'),
 (43,'Sistemas Empotrados','SEMP'),
 (44,'Sistemas Inteligentes','SINT'),
 (45,'Algoritmos y Computación','ALGC'),
 (46,'Códigos y Criptografía','CRIP'),
 (47,'Diseño y Evaluación de Sistemas Interactivos','DESI'),
 (48,'Programación de Aplicaciones Gráficas','PAG'),
 (49,'Gramáticas y Lenguajes Formales','GLF'),
 (50,'Inferencia Estadística I','INFE1'),
 (51,'Modelos para la Toma de Decisiones','MTD'),
 (52,'Sistemas Avanzados de Integración de Información','SAII'),
 (53,'Calidad de Software','CALS'),
 (54,'Desarrollo basado en Componentes y Servicios','DBCS'),
 (55,'Informática Forense','IFOR'),
 (56,'Planificación y Gestión de Proyectos','PGP'),
 (57,'Profesión y Sociedad','PYS'),
 (58,'Sistemas Móviles','SMOV'),
 (59,'Principios de Análisis Económico-Financiero','PAEF'),
 (60,'Administración de Bases de Datos','ABD'),
 (61,'Planificación y Gestión de Plataformas Informáticas','PGPI'),
 (62,'Plataformas de Aplicaciones Distribuidas y Web','PADW'),
 (63,'Sistemas de Información y Dirección de Organizaciones','SIDO'),
 (64,'Diseño de Sistemas Digitales','DSD'),
 (65,'Hardware Empotrado','HEMP'),
 (66,'Rendimiento y Evaluación de Computadoras','REC'),
 (67,'Diseño de Hardware Específico','DHE'),
 (68,'Estadística Descriptiva','ESTD'),
 (69,'Planificación y Diseño de Sistemas Computacionales','PDSC'),
 (70,'Regresión y ANOVA','RANO'),
 (71,'Señales y Sistemas','SYS'),
 (72,'Modelos de Investigación Operativa','MIO');

-- Asignaturas Master
INSERT INTO Asignatura VALUES
 (73,'Asignatura Master 1','AM1'),
 (74,'Asignatura Master 2','AM2'),
 (75,'Asignatura Master 3','AM3'),
 (76,'Asignatura Master 4','AM4'),
 (77,'Asignatura Master 4','AM5');

 INSERT INTO AsignaturasCurso VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,10),
	(2,11),
	(2,12),
	(2,13),
	(2,14),
	(2,15),
	(2,16),
	(2,17),
	(2,18),
	(2,19),
	(2,20),
	(3,21),
	(3,22),
	(3,23),
	(3,24),
	(3,25),
	(3,26),
	(3,27),
	(3,28),
	(3,29),
	(3,30),
	(3,31),
	(3,32),
	(3,33),
	(3,34),
	(3,35),
	(3,36),
	(3,37),
	(3,38),
	(3,39),
	(3,40),
	(3,41),
	(3,42),
	(3,43),
	(3,44),
	(3,45),
	(3,46),
	(3,47),
	(3,48),
	(3,49),
	(3,50),
	(3,51),
	(3,52),
	(4,53),
	(4,54),
	(4,55),
	(4,56),
	(4,57),
	(4,58),
	(4,59),
	(4,60),
	(4,61),
	(4,62),
	(4,63),
	(4,64),
	(4,65),
	(4,66),
	(4,67),
	(4,68),
	(4,69),
	(4,70),
	(4,71),
	(4,72),
	(4,29),
	(4,30),
	(4,35),
	(4,37),
	(4,43),
	(5,73),
	(5,74),
	(5,75),
	(5,76),
	(5,77);

INSERT INTO MencionesCurso VALUES
	(3, 1),
	(3, 2),
	(3, 3),
	(4, 1),
	(4, 2),
	(4, 3);
	
INSERT INTO AsignaturasMencion VALUES
	(1,21,3),
	(1,22,3),
	(1,23,3),
	(1,24,3),
	(1,25,3),
	(1,26,3),
	(1,27,3),
	(1,28,3),
	(1,29,3),
	(1,30,3),
	(1,31,3),
	(1,32,3),
	(1,33,3),
	(1,35,4),
	(1,43,4),
	(1,53,4),
	(1,54,4),
	(1,55,4),
	(1,56,4),
	(1,57,4),
	(1,58,4),
	(1,59,4),
	(2,27,3),
	(2,32,3),
	(2,34,3),
	(2,35,3),
	(2,36,3),
	(2,37,3),
	(2,38,3),
	(2,39,3),
	(2,40,3),
	(2,41,3),
	(2,42,3),
	(2,43,3),
	(2,44,3),
	(2,55,4),
	(2,57,4),
	(2,58,4),
	(2,60,4),
	(2,61,4),
	(2,62,4),
	(2,63,4),
	(2,64,4),
	(2,65,4),
	(2,66,4),
	(2,67,4),
	(3,22,3),
	(3,23,3),
	(3,27,3),
	(3,29,4),
	(3,30,4),
	(3,32,3),
	(3,33,3),
	(3,37,4),
	(3,45,3),
	(3,46,3),
	(3,47,3),
	(3,48,3),
	(3,49,3),
	(3,50,3),
	(3,51,3),
	(3,52,3),
	(3,57,4),
	(3,63,4),
	(3,68,4),
	(3,69,4),
	(3,70,4),
	(3,71,4),
	(3,72,4);

INSERT INTO AsignaturasUsuario VALUES
	('alumno1',1),
	('alumno1',2),
	('alumno1',3),
	('alumno1',4),
	('alumno1',5),
	('alumno1',6),
	('alumno1',7),
	('alumno1',8),
	('alumno1',9),
	('alumno1',10),
	('alumno2',11),
	('alumno2',19),
	('alumno2',21),
	('alumno2',22),
	('alumno2',23),
	('alumno2',24),
	('alumno2',26),
	('alumno2',27),
	('alumno2',28),
	('alumno2',29),
	('alumno2',30),
	('alumno2',31),
	('alumno3',73),
	('alumno3',74),
	('alumno3',75),
	('alumno3',76),
	('alumno3',77),
	('profesor1',3),
	('profesor1',8),
	('profesor1',13),
	('profesor1',15),
	('profesor1',17),
	('profesor1',19),
	('profesor1',28),
	('profesor1',32),
	('profesor1',38),
	('profesor2',1),
	('profesor2',2),
	('profesor2',3),
	('profesor2',4),
	('profesor2',6),
	('profesor2',7),
	('profesor2',11),
	('profesor2',13),
	('profesor2',17),
	('profesor2',18),
	('profesor2',21),
	('profesor2',24),
	('profesor2',26),
	('profesor2',25),
	('profesor2',28),
	('profesor2',31),
	('profesor2',32),
	('profesor2',43),
	('profesor2',42),
	('profesor2',44),
	('profesor2',45),
	('profesor2',48),
	('profesor2',51),
	('profesor2',57),
	('profesor2',59),
	('profesor2',60),
	('profesor2',62),
	('profesor2',65),
	('profesor2',67),
	('profesor3',71),
	('profesor3',68),
	('profesor3',49),
	('profesor3',30),
	('profesor3',31),
	('profesor3',33),
	('profesor3',35),
	('profesor3',37),
	('profesor3',10),
	('profesor3',12),
	('profesor3',19),
	('profesor3',23),
	('profesor3',73),
	('profesor3',74),
	('profesor3',75),
	('profesor3',76),
	('profesor3',77);

INSERT INTO HorarioTutorias VALUES
 (1, 'profesor1', 1),
 (2, 'profesor1', 2),
 (3, 'profesor2', 1),
 (4, 'profesor2', 2),
 (5, 'profesor3', 3);
SELECT setval( 'secuencia_id_horariotutorias', (select max(id) from HorarioTutorias));

INSERT INTO FranjaTutorias VALUES
 (1,'12:00:00','15:00:00','Escuela de Ingeniería Informática','Despacho 1',1,1),
 (2,'12:00:00','15:00:00','Escuela de Ingeniería Informática','Despacho 1',3,1),
 (3,'13:00:00','14:00:00','Facultad de Ciencias','Despacho 1',1,2),
 (4,'14:00:00','16:00:00','Facultad de Ciencias','Despacho 1',5,2),
 (5,'11:00:00','14:00:00','Escuela de Ingeniería Informática','Despacho 2',1,3),
 (6,'09:00:00','11:00:00','Escuela de Ingeniería Industrial','Despacho 2',4,3),
 (7,'11:00:00','14:00:00','Escuela de Ingeniería Informática','Despacho 2',1,4),
 (8,'09:00:00','11:00:00','Escuela de Ingeniería Industrial','Despacho 2',4,4),
 (9,'12:00:00','15:00:00','Escuela de Ingeniería Informática','Despacho 3',2,5),
 (10,'16:00:00','19:00:00','Facultad de Ciencias','Despacho 3',5,5);
SELECT setval( 'secuencia_id_franjatutorias', (select max(id) from FranjaTutorias));

INSERT INTO SolicitudTutoria (id,asunto,comentarioalumno,fechasolicitud,fechatutoria,horainicio,horafin,grupal,motivorechazo,rechazadavistaporalumno,ubicaciontutoria,comentarioprofesor,propuestanuevohorario,codigoasignatura,idalumno,idprofesor,estadoid) VALUES
	 (3,'Duda con máquinas de Turing','No entiendo los ejercicios de máquinas de Turing, me gustaría que me los explicaras. Un saludo.','2022-07-12 02:10:00.871','2022-07-26','15:00:00','15:30:00',false,NULL,false,NULL,NULL,false,10,'alumno1','profesor3',1),
	 (4,'Duda con Trabajo final de grado','Tengo dudas de como afrontar el TFG. Querría una tutoría por si me pudieras ayudar. Un saludo.','2022-07-12 03:26:41.734','2022-07-26','16:00:00','17:00:00',true,NULL,false,NULL,NULL,false,NULL,'alumno1','profesor1',5),
	 (2,'Duda con práctica de MIPS','Buenas.
Mi compañero y yo teníamos unas dudas con la práctica de MIPS y queríamos resolverlas.','2022-07-12 02:06:25.609','2022-07-29','14:00:00','14:15:00',true,NULL,false,'Mi despacho','Llegaré 5 minutos tarde',false,8,'alumno1','profesor1',5),
	 (5,'Duda con la corrección del exámen','Buenas, me gustaría poder ver mi examen porque no lo esperaba suspender.','2022-07-12 03:54:27.986','2022-07-29','14:45:00','15:00:00',true,NULL,false,'Mi despacho',NULL,false,3,'alumno1','profesor1',4),
	 (7,'No entiendo el patrón observador',NULL,'2022-07-12 04:36:58.708','2022-07-29','15:45:00','16:00:00',true,NULL,false,NULL,NULL,false,28,'alumno2','profesor1',1),
	 (8,'Prueba respuesta intermedia',NULL,'2022-07-12 04:39:31.115','2022-07-29','15:15:00','16:00:00',true,NULL,false,NULL,NULL,false,3,'alumno1','profesor1',1),
	 (9,'Prueba para rechazar',NULL,'2022-07-12 04:40:32.239','2022-07-28','16:00:00','17:00:00',true,NULL,false,NULL,NULL,false,4,'alumno1','profesor2',1);
SELECT setval('secuencia_id_solicitudtutoria' , (select max(id) from SolicitudTutoria));

INSERT INTO Tutoria (id,cancelada,motivocancelacion,solicitudid) VALUES
	 (1,false,NULL,5);
SELECT setval( 'secuencia_id_tutoria', (select max(id) from Tutoria));