import React from "react";
import { Typography } from '@mui/material'
import '../style.css'

const index = () => {

  return (
    <div className="serviceStyle">
      <Typography className="headingTxt" variant="h3">Latest Announcements</Typography>
      <div className="serviceScrollStyle mtNone">
        <Typography variant="body1" className="activeAnnouncement">We are excited to announce the launch of our new company’s Quarterly Newsletter</Typography>
        <Typography variant="body1" className="activeAnnouncement">We are excited to announce the launch of our new company’s Quarterly Newsletter</Typography>
        <Typography variant="body1" className="activeAnnouncement">We are excited to announce the launch of our new company’s Quarterly Newsletter</Typography>
        <Typography variant="body1" className="activeAnnouncement">We are excited to announce the launch of our new company’s Quarterly Newsletter</Typography>
      </div>
    </div>
  );
};

export default index;