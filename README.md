[![CircleCI](https://circleci.com/gh/jabranemohamed/SuiviBudget/tree/master.svg?style=svg)](https://circleci.com/gh/jabranemohamed/SuiviBudget/tree/master)
[![codecov](https://codecov.io/gh/jabranemohamed/SuiviBudget/branch/master/graph/badge.svg)](https://codecov.io/gh/jabranemohamed/SuiviBudget)

<h1 align="center">
  <br>
  <a><img src="https://github.com/jabranemohamed/SuiviBudget/blob/master/docs/images/spring-framework.png" alt="spring boot"></a>
  <br>
  Suivi Budget
  <br>
</h1>

<h4 align="center">un POC de Suivie du réalisé</h4>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.2.2-brightgreen.svg" />
    </a>
    <a alt="Bootstrap">
        <img src="https://img.shields.io/badge/Postgres-v9.5-yellowgreen.svg">
    </a>
    <a alt="Material">
        <img src="https://img.shields.io/badge/Angular-8-orange.svg">  
    </a>      
    <a alt="Dependencies">
        <img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" />
    </a>
    <a alt="Contributions">
        <img src="https://img.shields.io/badge/contributions-welcome-orange.svg" />
    </a>
    
</p>

## Table of Contents ##
1. [Application](#Application)
2. [Schéma des données](#Schéma-des-données)
3. [Technologies](#Technologies)
4. [Structure Application](#Structure-Application)
5. [Executer application localement](#Executer-application-localement)
6. [Documentation API](#Documentation-API)
7. [Interface graphique](#Interface-graphique)
8. [Contribuer](#Contribuer)

## Application ##
Cette application est preuve de concept du projet suivi du réalisé.C'est une application de suivi des commandes par rapport à des budgets .

## Technologies ##
Following libraries were used during the development of this starter kit :

- **Spring Boot** - Pour la partie Serveur 
- **PostgreSQL** - Base de donnée SQL 
- **Swagger** - Pour la documentation de l'API
- **Angular** - Pour le front End 
- **JWT** - Mechanisme d'Authentication pour l'API REST

## Structure Application ##
<img src="https://github.com/jabranemohamed/SuiviBudget/blob/master/docs/images/project-structure.png" alt="project structure">
  

**_Models_**

Les differents models  de l'application sont organisés sous le package **_domain_**

**_DAOs_**


**_Security_**


**_Controllers_**

**_API_**

## Reponse et gestion d'exception ##

## Executer application localement ##
Importer le projet localement en on utilisant un IDE (intellij par exemple).
Configurer la base de donnée soit en changement la configuration de la base dans application-dev.properties
soit en créeant des variables d'environement suivants et en precisant des valeurs.

${SERVER_PORT}
${POSTGRES_SCHEMA_URL}
${POSTGRES_HOST}
${POSTGRES_PASSWORD}

## Documentation API ##
La documentation a été générer par Swagger2, vous pouvez voir la documentation sur cette URL - 

http://localhost:8081/swagger-ui.html

<img src="https://github.com/jabranemohamed/SuiviBudget/blob/master/docs/images/swagger-screens/api.png" alt="project structure">

## Interface graphique ##

## Contributors ##
[JABRANE MOHAMED](https://www.linkedin.com/in/mohamedhj/)

