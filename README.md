
# AGENT IA

Application web intelligente qui permet d'uploader un CV au format PDF, puis de poser des questions pour en extraire des informations contextuelles à l'aide de l'intelligence artificielle.



## Technologies utilisées

### Frontend

- Angular 19

- Bootstrap

- HTTPClient (upload + requêtes GET)

### Backend (Java Spring Boot)

- Spring Boot 3

- Spring AI (LLM client)

- Embedding + VectorStore

- PDF reader

### LLM

- Ollama3 (Locale)
## Fonctionnalités

- Upload de fichiers PDF (CV)

- Indexation vectorielle automatique

- Recherche contextuelle

- Interrogation via IA (RAG)

- Interface utilisateur simple et responsive
## Structure du projet

```bash
chatbotApp/
├── .idea/
├── chatbotApp/
│   ├── .mvn/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── project/
│   │   │   │       └── chatbotApp/
│   │   │   │           ├── config/
│   │   │   │           ├── controller/
│   │   │   │           ├── service/
│   │   │   │           │   └── ChatAiService.java
│   │   │   │           └── ChatbotAppApplication.java
│   │   └── resources/
│   └── test/
│   └── target/
├── .gitattributes
├── .gitignore
├── compose.yaml
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── frontend/
└── target/
```

## Installation locale
### Prérequis

- Node.js + npm

- JDK 21

- Maven 3.8+

- Postgresql 17

- Ollama 3

### Démarrer le LLM (cmd)
```bash
ollama run llama3
```

### Démarrer la base de données
```bash
docker compose -build
docker compse up
```

### Démarrer le backend
```bash
cd chatbotApp
./mvnw spring-boot:run
```
### Démarrer le frontend
```bash
cd frontend
npm install
ng serve
```

Visiter : http://localhost:4200


## Variables d'environnement (.env.example)

### LLM Configuration

```bash
spring.ai.ollama.chat.model=llama3
spring.ai.ollama.base-url=http://localhost:11434
```
### Base de données

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/your_base
spring.datasource.username=user
spring.datasource.password=password
```
## Exemple d'utilisation

#### 1 Choisir un fichier .pdf

#### 2 Cliquer sur "Uploader PDF"

une question comme :

- "Quel est le nom du candidat ?"

- "Quelle est son expérience ?"

Recevoir une réponse générée par l'IA
## Licence

Libre à usage éducatif et professionnel.git dtatud
