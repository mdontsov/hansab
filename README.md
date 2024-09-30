# Hansab Project

## Overview

This project is built using **Spring Boot** for the backend and **AngularJS** for the frontend. 
It also uses **Gradle** as the build automation tool. 
The backend serves a RESTful API, while the frontend is a static AngularJS-based UI served from the `static` folder. 
The project includes configuration files (`schema.sql` and `data.sql`) for database schema setup and initialization.

## Prerequisites

The easiest way to run the whole project at once is to use IntelliJ, Community or Ultimate

Make sure you have the following installed on your system:
- **JDK 17** or higher
- **Gradle** (if not using the included `gradlew` wrapper)
- **Node.js** and **npm** (for managing frontend dependencies)

## Project Structure

- `src/main/java`: Backend source code for the Spring Boot application.
- `src/main/resources/static`: Contains the frontend files (`index.html` and AngularJS app).
- `src/main/resources/templates`: Contains template files for the backend.
- `src/main/resources/schema.sql`: SQL file to set up the database schema.
- `src/main/resources/data.sql`: SQL file to initialize the database with sample data.
- `build.gradle`: Gradle build script.
- `package.json`: Frontend dependencies for the AngularJS app.

## Getting Started

```bash
git clone https://github.com/mdontsov/hansab.git

Next, open the project with IntelliJ, build it and navigate to terminal
cd hansab/src/main/resources/static
npm install

Run HansabApplication.java 
