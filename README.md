# Instalación de Jenkins in Docker


### Paso 1: Descargar la imagen oficial de Jenkins
```
docker pull jenkins/jenkins:lts-jdk17
o
docker pull jenkins/jenkins:lts-jdk21
```

### Paso 2: Crear un contenedor de Jenkins
```
docker run -d  -p 8080:8080  -p 50000:50000  -v jenkins_home:/var/jenkins_home  --name jenkins  jenkins/jenkins:jdk17
o
docker run -d  -p 8080:8080  -p 50000:50000  -v jenkins_home:/var/jenkins_home  --name jenkins  jenkins/jenkins:jdk21
```

### Paso 3: Acceder a Jenkins
```
Ingresa a  http://localhost:8080/

aqui va a pedir un password, ejecutar el siguiente comando y pegar la contraseña que genera

0ba42ec8cf654acfb56b56745f746577
user: 70399903
pass: 70399903
NOmbre: Elmer Gamonal Saire
email: lesnar018@gmail.com
```

### Paso 4: Obtener la contraseña inicial
```
docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

### Paso 5: Completar la configuración inicial      
- Ingresa la contraseña obtenida en el paso anterior.
- Selecciona "Instalar los complementos recomendados".
- Crea el primer usuario administrador.

### Paso 6: Installar maven en el Contenedor de Jenkins 
```
docker exec -u root -it jenkins bash
aqui en el root (root@b35bf2418966:/#), digitar:
apt update
apt install maven -y
```

### Paso 7: Donde estan instalados Java, Maven y git en el contenedor de Jenkins
```
dirname $(dirname $(readlink -f $(which java))) 
    respuesta: bash: /opt/java/openjdk/bin: Is a directory

dirname $(dirname $(readlink -f $(which mvn)))
    respuesta: bash: /usr/share/maven/bin: Is a directory

which git
    respuesta: /usr/bin/git
```

### Paso 8: Configurar Jenkins Tools    
Ingresar a "Manage Jenkins" -> "System Configuration" -> "Tools"

<img src="images/jenkins_tools_1.png" alt="Jenkins Tools" width="600"/>
<img src="images/jenkins_tools_2.png" alt="Jenkins Tools" width="600"/>
<img src="images/jenkins_tools_3.png" alt="Jenkins Tools" width="600"/>

### Paso 9 : Crear un pipeline en Jenkins

<img src="images/pipeline_configure.png" alt="Jenkins Pipeline" width="600"/>

### Paso 10: Configurar el pipeline
- Seleccionar "Pipeline script"
- Ingresar el siguiente script:
```
pipeline {
    
    agent any
    
    stages {
        stage('Clone') {
            steps {
                sh 'rm -rf m6-sbp-c05-micro-user-service' // remove m6-sbp-c05-micro-user-service
                // Get some code from a GitHub repository
                sh ' git clone https://github.com/jgomezz/m6-sbp-c05-micro-user-service.git'
            }
        } // end 'Clone'

        stage('Compile') {
            steps {
                dir('m6-sbp-c05-micro-user-service') {
                    sh 'mvn clean compile'
                }
            }
        } // end 'Compile'
        
        stage('Test') {
            steps {
                dir('m6-sbp-c05-micro-user-service') {
                    sh 'mvn test'
                }
            }
        } // end 'Compile'
    }
}
  
```
<img src="images/pipeline_configure.png" alt="Jenkins Pipeline Configure" width="600"/>

### Paso 11: Ejecutar el pipeline
- Hacer clic en "Build Now"
- Verificar que el pipeline se ejecute correctamente
- Ver los logs de la ejecución


## PARTE 2   : Crear un pipeline con Jenkinsfile desde un repositorio GitHub

### Paso 1: Crear un nuevo pipeline y configurarlo 

<img src="images/pipeline_scm.png" alt="Jenkins Pipeline SCM" width="600"/>

