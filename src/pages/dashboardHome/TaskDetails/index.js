import React from "react";
import { Typography } from '@mui/material'
import { Link } from "react-router-dom";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import { tasks } from './contant'
import '../style.css'

const index = () => {
  return (
    <div className="serviceStyle">
      <div className="flexDivStyle">
        <Typography className="headingTxt" variant="h3">Today's Task <span>4</span></Typography>
        <Link to='' className='commonBtn'>See More <ArrowForwardIosIcon/></Link>
      </div>
      <div className="serviceScrollStyle">
        {
          tasks.map((item)=>(
            <div className="flexDivStyle marginBottom">
              <Typography className="serviceSubText">{item.taskName}</Typography>
              <img src={item.progressIcon} alt="Status" />
              <img
                className="profileIcon"
                src={item.profileIcon}
                alt="userFace"
              />
            </div>
          ))
        }
      </div>
    </div>
  );
};

export default index;