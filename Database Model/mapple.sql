CREATE DATABASE  IF NOT EXISTS `mapple` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mapple`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mapple
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `codCategoria` int(11) NOT NULL,
  PRIMARY KEY (`codCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1),(2),(3),(4);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_idioma`
--

DROP TABLE IF EXISTS `categoria_idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_idioma` (
  `codIdioma` varchar(2) NOT NULL,
  `codCategoria` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codIdioma`,`codCategoria`),
  KEY `fk_Categoria_Idioma_Categoria1_idx` (`codCategoria`),
  CONSTRAINT `fk_Categoria_Idioma_Categoria1` FOREIGN KEY (`codCategoria`) REFERENCES `categoria` (`codCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Categoria_Idioma_Idioma1` FOREIGN KEY (`codIdioma`) REFERENCES `idioma` (`codIdioma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_idioma`
--

LOCK TABLES `categoria_idioma` WRITE;
/*!40000 ALTER TABLE `categoria_idioma` DISABLE KEYS */;
INSERT INTO `categoria_idioma` VALUES ('en',1,'Phones'),('en',2,'Tablets'),('en',3,'Laptops'),('en',4,'Accessories'),('es',1,'Telefonos'),('es',2,'Tablets'),('es',3,'Ordenadores'),('es',4,'Accesorios');
/*!40000 ALTER TABLE `categoria_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idioma` (
  `codIdioma` varchar(2) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codIdioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES ('en',''),('es','');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineapedido`
--

DROP TABLE IF EXISTS `lineapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineapedido` (
  `precioUnidad` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `codPedido` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  PRIMARY KEY (`codPedido`,`codProducto`),
  KEY `fk_LineaPedido_Pedido1_idx` (`codPedido`),
  KEY `fk_LineaPedido_Producto1_idx` (`codProducto`),
  CONSTRAINT `fk_LineaPedido_Pedido1` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LineaPedido_Producto1` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineapedido`
--

LOCK TABLES `lineapedido` WRITE;
/*!40000 ALTER TABLE `lineapedido` DISABLE KEYS */;
INSERT INTO `lineapedido` VALUES (450,2,7,1),(450,2,8,1);
/*!40000 ALTER TABLE `lineapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `importeTotal` varchar(45) NOT NULL,
  `correoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`codPedido`,`correoUsuario`),
  KEY `fk_Pedido_Usuario1_idx` (`correoUsuario`),
  CONSTRAINT `fk_Pedido_Usuario1` FOREIGN KEY (`correoUsuario`) REFERENCES `usuario` (`correoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (7,'2018-03-15','900.0','hld@gmail.com'),(8,'2018-03-15','900.0','hld@gmail.com');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `codProducto` int(11) NOT NULL,
  `precioUnitario` double NOT NULL,
  `unidadesEnStock` int(11) NOT NULL,
  `codCategoria` int(11) NOT NULL,
  `medidaPantalla` double DEFAULT NULL,
  PRIMARY KEY (`codProducto`,`codCategoria`),
  KEY `fk_Producto_Categoria1_idx` (`codCategoria`),
  CONSTRAINT `fk_Producto_Categoria1` FOREIGN KEY (`codCategoria`) REFERENCES `categoria` (`codCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,450,50,1,NULL),(2,650,50,1,NULL),(3,800,50,1,NULL),(4,500,50,2,NULL),(5,550,50,2,NULL),(6,850,50,2,NULL),(7,1500,50,3,NULL),(8,1700,50,3,NULL),(9,2800,50,3,NULL),(10,180,50,4,NULL),(11,250,50,4,NULL),(12,50,50,4,NULL),(13,70,50,4,NULL),(14,90,50,4,NULL),(15,300,50,4,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_idioma`
--

DROP TABLE IF EXISTS `producto_idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto_idioma` (
  `codProducto` int(11) NOT NULL,
  `codIdioma` varchar(2) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`codProducto`,`codIdioma`),
  KEY `fk_Producto_Idioma_Producto1_idx` (`codProducto`),
  KEY `fk_Producto_Idioma_Idioma1_idx` (`codIdioma`),
  CONSTRAINT `fk_Producto_Idioma_Idioma1` FOREIGN KEY (`codIdioma`) REFERENCES `idioma` (`codIdioma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Idioma_Producto1` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_idioma`
--

LOCK TABLES `producto_idioma` WRITE;
/*!40000 ALTER TABLE `producto_idioma` DISABLE KEYS */;
INSERT INTO `producto_idioma` VALUES (1,'en','Iphone 5','Welcome to iPhone SE, the most powerful 4 inch phone ever. To create it, we started with a beloved design, then reinvented it from the inside out. The A9 is the same advanced chip used in iPhone 6s. The 12‑megapixel camera captures incredible photos and 4K videos. And Live Photos bring your images to life. The result is an iPhone that looks small. But lives large.'),(1,'es','Iphone 5','Presentamos el iPhone SE, el teléfono de 4 pulgadas más potente hasta la fecha. Para crearlo, partimos de un diseño que es todo un éxito y lo reinventamos de arriba abajo. El avanzado chip A9 es el mismo que lleva el iPhone 6s. La cámara de 12 megapíxeles hace unas fotos increíbles y graba vídeos en 4K espectaculares. Y con Live Photos, tus recuerdos están más vivos que nunca. El resultado es un iPhone que parece pequeño, pero está a la altura de los grandes.'),(2,'en','Iphone 6','The Apple iPhone 6 up ante Apple with a larger screen of 4.7 inches protected by an ultra-resistant glass, new A8 processor, 16GB, 64GB or 128GB of internal storage, 8 megapixel rear camera with flash, 1.2 front camera'),(2,'es','Iphone 6','El Apple iPhone 6 sube la apuesta de Apple con una pantalla de mayor tamaño de 4.7 pulgadas protegida por un cristal ultra resistente, nuevo procesador A8, 16GB, 64GB o 128GB de almacenamiento interno, cámara trasera de 8 megapixels con flash, cámara frontal de 1.2MP, conectividad 4G LTE y iOS 8.'),(3,'en','Iphone 7','The iPhone 7 is the first Apple to be water resistant with an IP67 certificate and on an aesthetic level is not very different from its predecessor, but internally if it has changed and for good. The traditional connector that we all know of 3.5 mm has disappeared, has an excellent and new camera, in addition to this, its microprocessor and RAM create an optimal environment for enviable performance. It has a Retina HD Enhanced screen which is 25% brighter. And finally, also note that it has incredible stereo speakers. The iPhone 7 takes a giant step in each and every one of the aspects that make the iPhone unique. New advanced camera system. The best performance and the most autonomy that have been seen in an iPhone. Stereo speakers Resistance to water and splashes.1 And the brightest iPhone screen with the most colors to date. It is a beauty. It is a beast. Its 7.'),(3,'es','Iphone 7','El iPhone 7 es el primero de Apple en ser resistente al agua con un certificado IP67 y a nivel estético no es muy diferente a su antecesor, pero a nivel interno si que ha cambiado y para bien. El conector tradicional que todos conocemos de 3.5 mm ha desaparecido, posee un excelente y novedosa cámara, además de esto, su microprocesador y memoria RAM crean un ambiente óptimo para un rendimiento envidiable. Cuenta con una pantalla Retina HD Mejorada la cual es un 25% mas brillante. Y por último también destacar que posee unas increíbles bocinas estéreo. El iPhone 7 da un paso de gigante en todos y cada uno de los aspectos que hacen del iPhone algo único. Nuevo sistema avanzado de cámaras. El mejor rendimiento y la mayor autonomía que se han visto en un iPhone. Altavoces estéreo. Resistencia al agua y a las salpicaduras. Y la pantalla de iPhone más brillante y con más colores hasta la fecha. Es una belleza. Es una bestia. Es el 7.'),(4,'en','Ipad Mini','There’s more to mini than meets the eye. iPad mini 4 puts uncompromising performance and potential in your hand. It’s thinner and lighter than ever before, yet powerful enough to help you take your ideas even further.'),(4,'es','Ipad Mini','El iPad mini 4 es muy grande. Pone mil y una posibilidades en la palma de tu mano, es más fino y ligero que nunca, y tiene potencia de sobra para darle forma a todas y cada una de tus ideas. Ni se te ocurra pensar que es mini a secas: es mucho mini.'),(5,'en','Ipad','Learn, play, surf, create. iPad gives you the incredible display, performance, and apps to do what you love to do. Anywhere. Easily. Magically.'),(5,'es','Ipad','Aprende, juega, navega y crea. El iPad te ofrece una pantalla increíble, un rendimiento de primera y montones de apps para que hagas lo que quieras, donde quieras. Todo es tan fácil que te parecerá magia.'),(6,'en','Ipad pro','No matter the task, the new iPad Pro is up to it — and then some. It offers far more power than most PC laptops, yet is delightfully simple to use. The redesigned Retina display is as stunning to look at as it is to touch. And it all comes together with iOS, the world’s most advanced mobile operating system. iPad Pro. Everything you want modern computing to be. Now even, well, better'),(6,'es','Ipad pro','Hagas lo que hagas, con el nuevo iPad Pro lo bordas. Porque es mucho más potente que la mayoría de los portátiles PC y muy fácil de usar. Porque su pantalla Retina, rediseñada por completo, es un festival para la vista y el tacto. Y porque viene con iOS, el sistema operativo móvil más avanzado del mundo. En pocas palabras, el iPad Pro es todo lo que esperas de un ordenador moderno. Pero mejor'),(7,'en','MacBook','Our goal with MacBook was to do the impossible: engineer a full‑size experience into the thinnest, lightest Mac notebook yet. And not only is it compact — it’s more powerful than ever. The new MacBook delivers up to 20 percent faster performance with new seventh‑generation Intel Core m3, i5, and i7 processors,1 and up to 50 percent faster SSD storage.'),(7,'es','MacBook','Con el MacBook nos propusimos lo imposible: diseñar el portátil Mac más compacto sin renunciar a nada. Por eso reinventamos cada elemento hasta hacerlo no solo extremadamente fino y ligero, sino también más potente. El nuevo MacBook aumenta su rendimiento hasta un 20 % gracias a los procesadores Intel Core m3, i5 e i7 de séptima generación1 y al almacenamiento SSD hasta un 50 % más eficiente.'),(8,'en','MacBook Air','MacBook Air lasts up to an incredible 12 hours between charges. So from your morning coffee till your evening commute, you can work unplugged. When it’s time to kick back and relax, you can get up to 12 hours of iTunes movie playback. And with up to 30 days of standby time, you can go away for weeks and pick up right where you left off.'),(8,'es','MacBook Air','El MacBook Air te ofrece hasta 12 horas de autonomía con una sola carga. Vamos, que puedes encenderlo por la mañana y olvidarte de cargarlo hasta la noche. Por ejemplo, si te apetece una maratón de películas de iTunes, tienes hasta 12 largas horas por delante. Además aguanta hasta un mes en reposo, así que puedes pasarte semanas sin usarlo y estará listo cuando tú quieras'),(9,'en','MacBook Pro','It’s razor thin, feather light, and even faster and more powerful than before. It has the brightest, most colorful Mac notebook display ever. And it features the Touch Bar — a Multi-Touch enabled strip of glass built into the keyboard for instant access to the tools you want, right when you want them. MacBook Pro is built on groundbreaking ideas. And it’s ready for yours'),(9,'es','MacBook Pro','Es más rápido y potente que antes, pero más fino y ligero que nunca. Tiene la pantalla con más color y brillo jamás vista en un portátil Mac. Y viene con la Touch Bar, una barra de vidrio Multi-Touch integrada en el teclado para que siempre tengas a mano las herramientas que necesitas. El MacBook Pro es el resultado de nuestras ideas más innovadoras. Y puede hacer realidad las tuyas'),(10,'en','AirPods','If we talk about headphones, the AirPods are another story. They are activated and connected alone to your iPhone, Apple Watch, iPad or Mac as soon as you take them out of the case. The audio starts to sound when you put them on and stops when you take them off. And you can activate Siri with two taps to adjust the volume, change the song, get directions or make a call. AirPods incorporate Apples W1 chip and use optical sensors and an accelerometer to detect if youre wearing them. Whether you are using the two or only one, the W1 chip distributes the sound and activates the microphone automatically. When you talk on the phone or ask Siri for something, a second accelerometer activates the microphones with beamforming technology to filter the background noise and bring your voice to the foreground. The W1 chip is so efficient that AirPods work for 5 hours on a single charge, something unique in the market (2). But they are also always ready to give you ears thanks to the case, which gives you several additional charges so you can enjoy more than 24 hours of use (3). Hurry? Put the headphones in the case for 15 minutes and you will have charged them for 3 hours (4).'),(10,'es','AirPods','Si hablamos de auriculares, los AirPods son otra historia. Se activan y se conectan solos a tu iPhone, Apple Watch, iPad o Mac en cuanto los sacas del estuche. El audio comienza a sonar cuando te los pones y se detiene cuando te los quitas. Y puedes activar Siri con dos toques para ajustar el volumen, cambiar de canción, obtener indicaciones o hacer una llamada. Los AirPods incorporan el chip W1 de Apple y utilizan sensores ópticos y un acelerómetro para detectar si los llevas puestos. Tanto si estás usando los dos como uno solo, el chip W1 distribuye el sonido y activa el micrófono automáticamente. Cuando hablas por teléfono o pides algo a Siri, un segundo acelerómetro activa los micrófonos con tecnología beamforming para filtrar el ruido de fondo y llevar tu voz al primer plano. El chip W1 es tan eficiente que los AirPods funcionan durante 5 horas con una sola carga, algo único en el mercado (2). Pero es que además están siempre listos para regalarte los oídos gracias al estuche, que te da varias cargas adicionales para que disfrutes de más de 24 horas de uso (3). ¿Tienes prisa? Mete los auriculares en el estuche durante 15 minutos y los tendrás cargados para 3 horas (4).'),(11,'en','Beats Pill','Put the city at your feet with the Beats Pill + of the Neighborhood Collection, a speaker designed for those who want to leave their personal mark on everything they do. Express yourself wherever you go with the best wireless sound and its urban colors: grass green and gray asphalt. Connect with your music.'),(11,'es','Beats Pill','Pon la ciudad a tus pies con el Beats Pill+ de la Neighbourhood Collection, un altavoz diseñado para los que quieren dejar su huella personal en todo lo que hacen. Exprésate allá donde vayas con el mejor sonido inalámbrico y sus colores urbanos: verde hierba y gris asfalto. Conecta con tu música.'),(12,'en','Iphone Case','This silicone case, specially designed by Apple for the iPhone, fits perfectly with the volume buttons, the side button and the curves of the phone, and does not bulge at all. The soft microfiber lining protects your iPhone and the outer silicone has a nice touch that will surprise you. You can also leave the case always on, even during wireless charging of the iPhone 8.'),(12,'es','Funda Iphone','Esta funda de silicona, diseñada especialmente por Apple para el iPhone, se adapta perfectamente a los botones de volumen, el botón lateral y las curvas del teléfono, y no abulta nada. El forro de suave microfibra protege tu iPhone y la silicona exterior tiene un tacto tan agradable que te va a sorprender. Además puedes dejar la funda siempre puesta, incluso durante la carga inalámbrica del iPhone 8.'),(13,'en','Ipad Case','The beautiful Smart Cover case protects the screen of your iPad Pro. Open it to activate the iPad Pro instantly and close it to put it at rest. Available in several colors to match your other Apple accessories.'),(13,'es','Funda Ipad','La preciosa funda Smart Cover protege la pantalla de tu iPad Pro. Ábrela para activar el iPad Pro al instante y ciérrala para ponerlo en reposo. Disponible en varios colores a juego con tus otros accesorios de Apple.'),(14,'en','MacBook Case','This case designed by Apple is made of premium European leather and has a soft microfiber lining. Its design allows you to charge your 12-inch MacBook and take it well protected everywhere.'),(14,'es','Funda MacBook','Esta funda diseñada por Apple está hecha de piel europea de primera calidad y lleva un forro suave de microfibra. Su diseño te permite cargar tu MacBook de 12 pulgadas y llevarlo bien protegido a todas partes.'),(15,'en','Apple Watch','Track your activity. Measure your workouts. Monitor your health. Apple Watch Series 1 lets you do it all in an instant, thanks to a dual-core processor and the powerful new features of watchOS 4.'),(15,'es','Apple Watch','Registra al detalle tu actividad física. Mide tus entrenamientos. Controla tu salud. El procesador de doble núcleo del Apple Watch Series 1 y las potentes prestaciones nuevas de watchOS 4 te ayudan a llevar un estilo de vida sano y hacerlo todo en un instante. Tu tiempo es más precioso que nunca.');
/*!40000 ALTER TABLE `producto_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `correoUsuario` varchar(50) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(80) NOT NULL,
  `telefono` int(11) NOT NULL,
  `clave` varchar(256) NOT NULL,
  PRIMARY KEY (`correoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('amr@gmail.com','Alejandro','Montes Ramirez',626564895,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('atv@gmail.com','Alberto','Taboada Varela',695264135,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('cbm@gmail.com','Cesar','Bueu Montalvo',654782015,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('drc@gmail.com','Daniel','Romay Cobelas',665021398,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('hld@gmail.com','Hector','Ledo Doval',612345678,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('ljr@gmail.com','Laura','Jaramillo Rey',648521300,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('mhf@gmail.com','Melisa','Hernandez Fidalgo',621003455,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('mra@gmail.com','Miguel','Ramos',664562580,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('rco@gmail.com','Rosa','Camelo Osorio',695032648,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('rgd@gmail.com','Ramón','García Dubra',654254130,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('sfc@gmail.com','Santiago','Fernandez Cabanillas',623254655,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1'),('smv@gmail.com','Sara','Millan Verdano',612534895,'3FTUrxdAAHuwAt5NHtLluQ0NWRplKejUE4Clsy4s3ESBgacsbzXvb8A1HKkdL7P1');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mapple'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-12 17:56:33
