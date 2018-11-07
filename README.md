# Exogene Services

Job recruitment management system

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java IDE of choice (preferably IntelliJ IDEA), JavaFX, Scene Builder and SQLiteStudio

### Installing

The links to the required software are listed below, keep in mind that some of these may not be the latest versions.

Start by installing IntelliJ IDEA

```
https://www.jetbrains.com/idea/download/#section=windows
```

Download JavaFX

```
https://www.oracle.com/technetwork/java/javase/downloads/index.html
```

Scene Builder integration with IntelliJ doc

```
https://docs.oracle.com/javase/8/scene-builder-2/installation-guide/jfxsb-installation_2_0.htm
```

### Project Specifications

```
CLIENTS: Companies
CANDIDATES: People to place in job postings from companies
JOB ORDER: Job posting from a company

- Add and manage clients
  - Add multiple phone numbers
  - Add multiple emails

- Add and manage candidates
  - Add multiple phone numbers
  - Add multiple emails
  
- Add and manage job orders

```

### Workflow

```
1 - Manage clients, candidates and job orders
2 - Add a CV to a candidate (preview of CV in candidate details)
3 - New job posting from a client
4.1 (optional) - Create candidate groups under the job order
4.2 - Assign a candidate to a job order from job order view (from group or from the list of all candidates)
  4.2.1 - Start steps process, ie. 1st interview, 2nd interview, phone interview, etc... (steps are custom named by user)
4.3 - Assign a job order to a candidate from the candidates view

```

## Authors

* **David Légaré**
* **Nicolas Habak**

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
