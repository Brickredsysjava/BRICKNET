import React from "react";
import { Grid, Typography, Divider } from "@mui/material";

import image from "../../assets/image";
import LoginForm from './LoginForm'
import './style.css';

const Login = () => {
  return (
    <Grid container className="bgColor" >
      <Grid item sm={12} md={6} lg={6}>
        <div className="loginScreenSideBar">
          <Typography variant="h1" class="animated-text">Bricknet</Typography>
          <img src={image.groupImg} alt={''} />
        </div>
      </Grid>

      <Grid item sm={12} md={6} lg={6} >
        <div className="loginMainStyle">
          <div style={{ width: '100%' }}>
            <div style={{ textAlign: 'center' }}>
              <img src={image.logo} alt="logo" />
            </div>

            <div className="loginFormStyle">
              <Typography variant="h2">Login</Typography>
              <Divider sx={{ width: "12%", margin: "2% 0", borderBottomWidth: '2px' }} />
              <Typography variant="h3">Welcome Onboard With Us !</Typography>
              <LoginForm/>
            </div>
          </div>
        </div>
      </Grid>
    </Grid>
  );
};

export default Login;