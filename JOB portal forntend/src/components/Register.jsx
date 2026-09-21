import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Card, Grid, Select, MenuItem, InputLabel, FormControl } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('USER');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setError('');

    try {
      // Post to the backend registration endpoint
      await axios.post('http://localhost:5000/register', {
        username,
        password,
        role
      });
      
      // Navigate to login page on success
      navigate('/login');
    } catch (err) {
      setError('Registration failed. Username may already exist.');
      console.log(err);
    }
  };

  return (
    <Grid container spacing={0} direction="column" alignItems="center" justifyContent="center" style={{ minHeight: '80vh' }}>
      <Card sx={{ padding: '4%', width: '400px', backgroundColor: '#f5f5f5', boxShadow: 3 }}>
        <Typography variant="h4" align="center" gutterBottom sx={{ fontFamily: 'sans-serif', fontWeight: 600 }}>
          Register
        </Typography>
        
        {error && (
          <Typography color="error" align="center" gutterBottom>
            {error}
          </Typography>
        )}

        <form onSubmit={handleRegister}>
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
          <Box sx={{ mb: 2 }}>
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
          <Box sx={{ mb: 3 }}>
            <FormControl fullWidth>
              <InputLabel id="role-select-label">Role</InputLabel>
              <Select
                labelId="role-select-label"
                value={role}
                label="Role"
                onChange={(e) => setRole(e.target.value)}
              >
                <MenuItem value="USER">Job Seeker (User)</MenuItem>
                <MenuItem value="ADMIN">Recruiter (Admin)</MenuItem>
              </Select>
            </FormControl>
          </Box>
          <Button fullWidth variant="contained" color="secondary" type="submit" size="large">
            Create Account
          </Button>
          <Box sx={{ mt: 2, textAlign: 'center' }}>
            <Button color="primary" onClick={() => navigate('/login')}>
              Already have an account? Login
            </Button>
          </Box>
        </form>
      </Card>
    </Grid>
  );
};

export default Register;
