import React from 'react'
import { Grid, Typography } from "@mui/material";
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
import './style.css'
import image from '../assets/image';

const TopHeader = ({ title }) => {
    const handleNotification = () => {
        console.log("helo",);
    }

    return (
        <Grid container className='sectionHeader'>
            <Grid item lg={9}>
                <div className='sectionHeading'>
                    <Typography variant='h1'>{title}</Typography>
                </div>
            </Grid>
            <Grid item lg={3}>
                <div className='otherSection'>
                    <div style={{position:'relative'}}>
                        <span className='activeUser'></span>
                        <img src={image.avtar} alt='' />
                    </div>
                    <div style={{position:'relative'}}>
                        <NotificationsNoneIcon onClick={handleNotification} />
                        <span className='activeNotification'></span>
                    </div>
                </div>
            </Grid>
        </Grid>
    )
}

export default TopHeader