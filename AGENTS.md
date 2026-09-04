# AGENTS.md

## Project Overview

Java learning repository with standalone exercises and mini-projects. Each directory is an independent project (no shared build system).

## Tech Stack

- **Language:** Java 17–25
- **Build:** Maven (used in the Spring Boot, microservice, servlet and gestor projects)
- **Frameworks:** Spring Boot 4.1.x, Spring Cloud 2025.1.3 (Netflix Eureka), Jakarta Servlet 6.0
- **IDEs:** IntelliJ IDEA, Eclipse

## Repository Structure

```
oop1–oop6/                  # OOP concepts (composition → interfaces)
firstIdea/                  # ArrayList basics
practicesArrays/            # ArrayList operations
practiceHashSets/           # HashSet basics
praticeHashMap/             # HashMap basics
manejoExcepciones/          # Custom exceptions
first/                      # Functional interfaces, lambdas, Streams
tablasMultiplicar/          # Console app, Scanner menu (multiplication tables)
Demo Threads/               # synchronized, wait/notify thread coordination
syncAndLocks/               # ReentrantLock thread-safe counter
threadPoolsExecutors/       # ExecutorService / fixed thread pool
ConsoleBankApp/             # Banking app v1 (inline logic)
ConsoleBankAppUp/           # Banking app v2 (refactored)
MatriculacionManual/        # Enrollment system (full OOP)
MatriculacionManualJava/    # Same, single-file version
subscriptions_gestor/       # Subscription manager v1 (plain Java + CSV)
subscription_gestor/        # Subscription manager v2 (Maven, Java 25)
Maven APP/                  # Maven setup exercise
Proyecto Servlet JSP/       # Servlet + JSP (WAR)
Springboot/                 # Spring DI example
Use-of-Annotation/          # Spring @Component / @ComponentScan (no web server)
demo/                       # Spring Boot scaffold
demoApp/                    # Spring Data JPA + H2, REST CRUD, exception handling
Microservices/              # Single REST app with Alpha/Beta devices (no Eureka)
EurekaDiscoveryServer/      # Netflix Eureka service registry (port 8761)
AlphaMicroservice/          # Eureka client microservice (port 8086)
BetaMicroservice/           # Eureka client microservice (port 8087)
OnlineStore/                # Spring MVC + JSP consuming Alpha/Beta via RestTemplate
```

## Conventions

- Most projects are plain Java with `Main.java` entry points
- No shared `pom.xml` at root — each Maven project is self-contained
- OOP exercises use package names matching directory (e.g., `oop1/src/oop1/`)
- Compiled output goes to `out/` (not tracked)
- Code is in Spanish (variable names, comments, class names)
- `Demo Threads/` is the only directory with a space in its name — quote it in commands
- Spring Boot/microservice projects segregate Spring Cloud code under `Microservices/`-style layouts; keep them independent

## Commands

```powershell
# Compile a plain Java project (example: oop1)
javac -d out oop1\src\TestDrive.java oop1\src\oop1\Car.java oop1\src\oop1\Engine.java
java -cp out TestDrive

# Maven project
cd "Springboot\DependencyInjection"
mvn spring-boot:run

# Microservices demo: start the registry first, then the services
cd EurekaDiscoveryServer; mvn spring-boot:run
cd AlphaMicroservice; mvn spring-boot:run
cd BetaMicroservice; mvn spring-boot:run
cd OnlineStore; mvn spring-boot:run
```

## Guidelines for AI Agents

- Do NOT refactor variable names from Spanish to English
- Do NOT merge separate projects into a single module
- Keep each directory as an independent, runnable project
- Prefer editing existing files over creating new ones
- When adding exercises, follow the naming convention: `oop{N}/`, `practice{Concept}/`