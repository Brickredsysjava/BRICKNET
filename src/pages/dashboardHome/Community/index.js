import React from "react";
import { Typography } from '@mui/material'
import { Link } from "react-router-dom";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import { communityContent } from './contant'
import '../style.css'

const index = () => {
  const MAX_LENGTH = 250;

  return (
    <div className="serviceStyle">
      <Typography className="headingTxt" variant="h3">Community</Typography>
      <div className="serviceScrollStyle">
        {
          communityContent.map((item)=>(
            <div className="w100">
              <div className="flexDivStyle">
                <div className="flexDivStyle">
                  <img
                    className="profileIcon"
                    src={item.profileIcon}
                    alt="userFace"
                  />
                  <div>
                    <Typography variant="h4" className="headingText">{item.userName}</Typography>
                    <Typography variant="body1" className="subHeadingText">{item.postCategory}</Typography>
                  </div>
                </div>
                <Typography variant="body1" className="subHeadingText">{item.postDate}</Typography>
              </div>
              <Typography variant="body1" className="suggestionText">{item.postContent.substring(0, MAX_LENGTH)}</Typography>
            </div>
          ))
        }
      </div>
    </div>
  );
};

export default index;