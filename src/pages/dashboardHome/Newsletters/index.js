import React from "react";
import { Typography } from '@mui/material'
import '../style.css'
import image from "../../../assets/image";

const index = () => {

  return (
    <div className="serviceStyle">
      <Typography className="headingTxt" variant="h3">Community</Typography>
      <div className="newsLetterStyle">
        <img src={image.newsLetter} alt=''/>
        <Typography variant="h4" className="headingText mleft">Dear All,</Typography>
        <Typography variant="body1" className="subHeadingText mleft">We are excited to announce the launch of our new company’s Quarterly Newsletter! The purpose of this newsletter is to keep you informed about the latest news, events, and updates from our organization.</Typography>
      </div>
    </div>
  );
};

export default index;