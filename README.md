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
2. [Schéma des données](#Database-Schema)
3. [Technologies](#Technologies)
4. [Structure de l'application ](#Application-Structure)
5. [Executer l'application localement](#Running-the-server-locally)
6. [Documentation API ](#API-Documentation)
7. [Interface graphique (IHM)](#User-Interface)
8. [Contribuer](#Contributor)

## Application ##
Cette application est preuve de concept du projet suivi du réalisé.C'est une application de suivi des commandes par rapport à des budgets .

## Technologies ##
Following libraries were used during the development of this starter kit :

- **Spring Boot** - Pour la partie Serveur 
- **PostgreSQL** - Base de donnée SQL 
- **Swagger** - Pour la documentation de l'API
- **Angular** - Pour le front End 
- **JWT** - Mechanisme d'Authentication pour l'API REST

## Application Structure ##
<img src="https://github.com/jabranemohamed/SuiviBudget/blob/master/docs/images/projects-structure.png" alt="project structure">
  

**_Models_**

Les differents models  de l'application sont organisés sous de package **_model_**