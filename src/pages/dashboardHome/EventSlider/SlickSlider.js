import React, { useRef } from "react";
import { Typography } from '@mui/material'

import Slider from 'react-slick'
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'

import '../style.css'
import image from "../../../assets/image";

const SlickSlider = () => {
  const sliderRef = useRef();
  const sliderSettings = {
    dots: true,
    arrows: false,
    infinite: false,
    speed: 1200,
    slidesToShow: 1,
    fade: true,
    slidesToScroll: 1,
};

  return (
    <div className="serviceStyle">
      <Typography className="headingTxt" variant="h3">Todays Events</Typography>
      <div className="eventSlider">
        <Slider {...sliderSettings} ref={sliderRef} style={{ marginBottom: '10px' }}>
          <img src={image.imageSlider1} alt=''/>
          <img src={image.imageSlider2} alt=''/>
          <img src={image.imageSlider3} alt=''/>
        </Slider>
      </div>
    </div>
  );
};

export default SlickSlider;