import React, {useState} from "react";
import {Box, List, Typography, Divider, ListItem, ListItemButton} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { Outlet } from "react-router-dom";

import { mainMenuItem, otherMenuItem } from './contant'
import { Drawer } from './styled'
import images from '../../assets/image'
import './dashboard.css'

export default function DashboardLayout() {
  const [open, setOpen] = useState(false);

  const handleDrawerOpen = () => {
    setOpen(!open);
  };

  const getSideBarDetails=(record, title)=><List>
    <Typography variant="body1">{title}</Typography>
  {record.map((text, index) => (
    <ListItem key={index} disablePadding sx={{ display: "block" }}>
      <ListItemButton>
        <>{text.icon}</>
        <Typography className="sideBarTextStyle" sx={{ opacity: open ? 1 : 0, marginLeft: "10px" }}>
          {text.name}
        </Typography>
      </ListItemButton>
    </ListItem>
  ))}
</List>

  return (
    <Box sx={{ display: "flex" }}>
      <Drawer variant="permanent" open={open} className="sideBarMenuStyle">
        <div className="imgContainer"> { open ? <img src={images.transparentLogo} alt='asd' className="smallLogo"/> : <img src={images.closelogo} alt='asd' className="largeLogo"/> }</div>
          {
              getSideBarDetails(mainMenuItem, 'Main')
          }

          <Divider />
          {
              getSideBarDetails(otherMenuItem, 'Other')
          }
      </Drawer>
      <div style={{width: '-webkit-fill-available'}}>
        <MenuIcon onClick={handleDrawerOpen} className="sideBarButton"/>
        <Outlet/>
      </div>
    </Box>
  );
}
