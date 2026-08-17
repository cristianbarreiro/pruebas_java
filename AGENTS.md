# AGENTS.md

## Project Overview

Java learning repository with standalone exercises and mini-projects. Each directory is an independent project (no shared build system).

## Tech Stack

- **Language:** Java 17–25
- **Build:** Maven (only in `Maven APP/`, `Springboot/`, `Proyecto Servlet JSP/`, `demo/`)
- **Frameworks:** Spring Boot 4.1.0, Jakarta Servlet 6.0
- **IDEs:** IntelliJ IDEA, Eclipse

## Repository Structure

```
oop1–oop6/                  # OOP concepts (composition → interfaces)
firstIdea/                  # ArrayList basics
practicesArrays/            # ArrayList operations
practiceHashSets/           # HashSet basics
praticeHashMap/             # HashMap basics
manejoExcepciones/          # Custom exceptions
ConsoleBankApp/             # Banking app v1 (inline logic)
ConsoleBankAppUp/           # Banking app v2 (refactored)
MatriculacionManual/        # Enrollment system (full OOP)
MatriculacionManualJava/    # Same, single-file version
Maven APP/                  # Maven setup exercise
Proyecto Servlet JSP/       # Servlet + JSP (WAR)
Springboot/                 # Spring DI example
demo/                       # Spring Boot scaffold
```

## Conventions

- Most projects are plain Java with `Main.java` entry points
- No shared `pom.xml` at root — each Maven project is self-contained
- OOP exercises use package names matching directory (e.g., `oop1/src/oop1/`)
- Compiled output goes to `out/` (not tracked)
- Code is in Spanish (variable names, comments, class names)

## Commands

```powershell
# Compile a plain Java project (example: oop1)
javac -d out oop1\src\TestDrive.java oop1\src\oop1\Car.java oop1\src\oop1\Engine.java
java -cp out TestDrive

# Maven project
cd "Springboot\DependencyInjection"
mvn spring-boot:run
```

## Guidelines for AI Agents

- Do NOT refactor variable names from Spanish to English
- Do NOT merge separate projects into a single module
- Keep each directory as an independent, runnable project
- Prefer editing existing files over creating new ones
- When adding exercises, follow the naming convention: `oop{N}/`, `practice{Concept}/`
