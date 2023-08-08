# HttpSession con Java EE / Jakarta
Sencilla aplicación con Java EE / Jakarta. Trabajamos el uso de HttpSession para un manejo de sesiones con servlets.

## ¿Qué es HttpSession?
Es una interfaz en Java EE que se utiliza para mantener información del estado de una sesión entre el cliente y el servidor en una aplicación web. Una sesión representa una serie de interacciones entre un usuario y una aplicación web durante un período de tiempo determinado. Durante esta sesión, se pueden mantener datos específicos del usuario para personalizar la experiencia del usuario y mantener el estado de la aplicación.

La `HttpSession` se basa en el concepto de cookies, aunque no depende exclusivamente de ellas. Cuando un usuario visita una página web por primera vez, se crea una sesión para ese usuario y se le asigna un identificador único llamado ID de sesión. Este ID de sesión se almacena generalmente en una cookie que se envía al navegador del usuario. En cada solicitud posterior del mismo usuario, el navegador envía automáticamente el ID de sesión al servidor para identificar al usuario y asociar su solicitud con la sesión correspondiente.

Con HttpSessions podemos guardar objetos con todos sus atributos, en cambio con una cookie solamente podiamos guardar. normalmente un String y nada mas, por lo que lo hace mucho mas robusto y mas flexible, cada vez que el cliente accede por primera vez a nuestra aplicación, siempre SI o SI se crea de forma automatica una sesión de usuario y devuelve al cliente un cookie quer contiene el ID de esta sesión que ha sido creada, por medio de este ID el cliente va a pode acceder a su sesión, siempre se va a estar enviando el Session ID.
## Manejo de estados
-  Los datos no se comparte entre diferentes objetos de sesión (El cliente solo puede acceder a  los datos desde su sesión)
-  Las sesiones `HTTP` nos permiten una forma de mantener información del usuario entre peticiones y poder recordarlas
-  También contiene pares clave-valor, pero en comparacion con una cookie, una sesión puede contener un objeto como valor

## Crear una sesión
Se crea de forma automática por cada cliente o navegador web y la accedemos mediante el objeto `HttpServletRequest`
````java
HttpSession session = request.getSession();
````
Si queremos guardar un objeto en la sesión HTTP sería:
````java
session.setAttribute("username", user);
````
Donde `user`es un objeto.
Ahora, si queremos obtener el objeto o valor de la sesión HTTP sería:
````java
String username = session.getAttribute("username");
````
## Eliminar y/o invalidar una sesión HTTP
Para eliminar un objeto de la sesión HTTP sería:
````java
session.removeAttribute("username");
````
Para invalidar una sesión HTTP sería:
````java    
session.invalidate();
````