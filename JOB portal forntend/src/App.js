
import './App.css';
import React, { useState, useEffect } from 'react';
import AllPosts from './components/AllPosts';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Create from './components/Create';
import Navbar from './components/Navbar';
import Edit from './components/Edit';
import Login from './components/Login';
import Register from './components/Register';
import axios from 'axios';

// Global Axios Base URL
axios.defaults.baseURL = 'http://localhost:5000';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Check local storage for existing session
    const storedAuth = localStorage.getItem('auth');
    if (storedAuth) {
      const { username, password, role } = JSON.parse(storedAuth);
      axios.defaults.auth = { username, password };
      setIsAuthenticated(true);
      setUsername(username);
      // Determine role strictly from database returned role
      setIsAdmin(role === 'ADMIN');
    }
    setIsLoading(false);
  }, []);

  if (isLoading) {
    return null; // or a loading spinner
  }

  return (
    <BrowserRouter>
      {/* Pass auth states down to Navbar */}
      <Navbar
        isAuthenticated={isAuthenticated}
        setAuthStatus={setIsAuthenticated}
        isAdmin={isAdmin}
      />
      <Routes>
        {/* Protected Routes */}
        <Route
          path='/'
          element={isAuthenticated ? <AllPosts isAdmin={isAdmin} /> : <Navigate to="/login" />}
        />
        {/* Admin Only Routes */}
        <Route
          path="/create"
          element={isAuthenticated && isAdmin ? <Create /> : <Navigate to="/" />}
        />
        <Route
          path="/edit"
          element={isAuthenticated && isAdmin ? <Edit /> : <Navigate to="/" />}
        />

        {/* Auth Routes */}
        <Route
          path="/login"
          element={isAuthenticated ? <Navigate to="/" /> : <Login setAuthStatus={setIsAuthenticated} setIsAdmin={setIsAdmin} />}
        />
        <Route
          path="/register"
          element={isAuthenticated ? <Navigate to="/" /> : <Register />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
