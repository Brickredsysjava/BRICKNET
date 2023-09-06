import React from 'react'
import { Grid, Typography } from '@mui/material'

import TopHeader from '../../components/TopHeader'
import TaskDetails from './TaskDetails'
import CommunityComponent from './Community'
import Newsletter from './Newsletters'
import Announcements  from './Announcements'
import EventSlider from './EventSlider/SlickSlider'
import '../../assets/common/common.css'

const Home = () => {
  return (
    <>
      <TopHeader title={'Home'}/>
      <div className='sectionSpacing'>
        <Grid container spacing={2} mt={0}>
          
          <Grid item lg={12} className='ptNone' mb={1}>
            <Typography className='sectionText' variant='h3'>Welcome Jeni &#128515;</Typography>
            <Typography className='sectionSubHeading' variant='body1'>Happy to see you here congratulations on your boarding</Typography>
          </Grid>

          <Grid item xl={4} lg={6}>
            <TaskDetails/>
          </Grid>

          <Grid item xl={4} lg={6}>
            <CommunityComponent/>
          </Grid>

          <Grid item xl={4} lg={6}>
            <Newsletter/>
          </Grid>

          <Grid item xl={4} lg={6}>
            <Announcements/>
          </Grid>

          {/* <Grid item xl={4} lg={6}>
            <EventSlider/>
          </Grid> */}
        
        </Grid>
      </div>
    </>
  )
}

export default Home