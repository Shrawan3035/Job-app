# Job Portal Application

A full-stack Job Portal application built with React and Spring Boot.

## Architecture

This project consists of two main parts:
- **Frontend**: A React-based single-page application.
- **Backend**: A Java Spring Boot REST API.

## Technologies Used

### Frontend (`JOB portal forntend`)
- **React.js**
- **Material UI** (`@mui/material`) for styling and components
- **Axios** for API requests
- **React Router DOM** for navigation
- **tsParticles** for particle animations

### Backend (`jobportal backend`)
- **Java**
- **Spring Boot**
- **Spring Data JPA** for ORM
- **Spring Security** for authentication and authorization
- **PostgreSQL** for the relational database
- **Lombok** to reduce boilerplate code

## Getting Started

### Prerequisites
- **Node.js** & **npm** (for the frontend)
- **Java JDK** (for the backend)
- **PostgreSQL** (running locally or remotely)

### Database Setup
1. Ensure your PostgreSQL server is running.

### Running the Backend
1. Navigate to the backend directory:
   ```bash
   cd "jobportal backend"
   ```
2. Ensure your database connection properties (URL, username, password) are correctly configured in `src/main/resources/application.properties` or `application.yml`.
3. Run the application using the included Maven wrapper:
   - On Windows: 
     ```cmd
     mvnw.cmd spring-boot:run
     ```
   - On Mac/Linux: 
     ```bash
     ./mvnw spring-boot:run
     ```
   The backend API will start up (typically on `http://localhost:8080`).

### Running the Frontend
1. Navigate to the frontend directory:
   ```bash
   cd "JOB portal forntend"
   ```
2. Install the necessary dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```
   The frontend application will start and should automatically open in your default browser at `http://localhost:3000`.
