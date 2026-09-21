import React from 'react'
import {
    AppBar,
    Toolbar,
    Box,
    Grid,
    Typography,
    Button,
  } from "@mui/material";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Navbar = ({ isAuthenticated, setAuthStatus, isAdmin }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear localStorage
    localStorage.removeItem('auth');
    // Clear axios auth headers
    axios.defaults.auth = null;
    // Update global state
    setAuthStatus(false);
    // Redirect to login
    navigate('/login');
  };

  return (
    <div>
      <Grid container spacing={2} sx={{ margin: "2%" }}>
        <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" style={{ background: '#ADD8E6' }}>
            <Toolbar variant="dense">
              <Typography variant="h4" align='Left' component="div" sx={{ flexGrow: 1, fontFamily:"revert", fontSize:"500", color:"black" }}>
                Job Portal
              </Typography>

              {/* Show navigation buttons only if logged in */}
              {isAuthenticated && (
                <>
                  <Box sx={{ m: 0.5, mx: 'auto', width: 80 }}>
                    <Button variant="outlined" onClick={() => navigate('/')}>Home</Button>
                  </Box>
                  {isAdmin && (
                    <Box sx={{ m: 0.5, mx: 'auto', width: 100 }}>
                      <Button variant="outlined" onClick={() => navigate('/create')}>Add Job</Button>
                    </Box>
                  )}
                  <Box sx={{ m: 0.5, mx: 'auto', width: 180 }}>
                    <Button variant="outlined" href='https://telusko.com/'>Contact Us</Button>
                  </Box>
                  <Box sx={{ m: 0.5, mx: 'auto', width: 100 }}>
                    <Button variant="contained" color="error" onClick={handleLogout}>Logout</Button>
                  </Box>
                </>
              )}
        </Toolbar>
      </AppBar>
    </Box>
    </Grid>
      <Grid item xs={12} sx={12} md={12} lg={12}>
      </Grid>
    </div>
  )
}

export default Navbar
