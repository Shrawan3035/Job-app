import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Card, Grid } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = ({ setAuthStatus, setIsAdmin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      // Temporarily set axios defaults to test credentials
      axios.defaults.auth = { username, password };
      
      // Test the credentials by calling the /me endpoint
      const response = await axios.get('http://localhost:5000/me');
      const role = response.data.role;
      
      // If successful, save to localStorage
      localStorage.setItem('auth', JSON.stringify({ username, password, role }));
      
      // Update global auth status in App.js
      setAuthStatus(true);
      setIsAdmin(role === 'ADMIN');
      
      // Redirect to home
      navigate('/');
    } catch (err) {
      // If 401 Unauthorized, credentials are wrong
      axios.defaults.auth = null; // reset
      setError('Invalid username or password. Please try again.');
    }
  };

  return (
    <Grid container spacing={0} direction="column" alignItems="center" justifyContent="center" style={{ minHeight: '80vh' }}>
      <Card sx={{ padding: '4%', width: '400px', backgroundColor: '#f5f5f5', boxShadow: 3 }}>
        <Typography variant="h4" align="center" gutterBottom sx={{ fontFamily: 'sans-serif', fontWeight: 600 }}>
          Login
        </Typography>
        
        {error && (
          <Typography color="error" align="center" gutterBottom>
            {error}
          </Typography>
        )}

        <form onSubmit={handleLogin}>
          <Box sx={{ mb: 2 }}>
            <TextField
              fullWidth
              label="Username"
              variant="outlined"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </Box>
          <Box sx={{ mb: 3 }}>
            <TextField
              fullWidth
              label="Password"
              type="password"
              variant="outlined"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Box>
          <Button fullWidth variant="contained" color="primary" type="submit" size="large">
            Sign In
          </Button>
          <Box sx={{ mt: 2, textAlign: 'center' }}>
            <Button color="secondary" onClick={() => navigate('/register')}>
              Don't have an account? Sign up
            </Button>
          </Box>
        </form>
      </Card>
    </Grid>
  );
};

export default Login;
