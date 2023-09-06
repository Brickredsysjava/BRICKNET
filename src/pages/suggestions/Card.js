import * as React from "react";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import image from "../../assets/image";

const card = (
  <React.Fragment>
    <CardContent className="cardPadding">
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <Typography
          sx={{ fontSize: 16, fontWeight: 600, paddingTop: 2 }}
          color="text.secondary"
          gutterBottom
        >
          Title
        </Typography>
        <Typography
          sx={{
            fontSize: 16,
            fontWeight: 500,
            paddingTop: 2,
            color: "#64748B",
          }}
          color="text.secondary"
          gutterBottom
        >
          21-12-2021
        </Typography>
      </div>
      <Typography
        variant="body2"
        sx={{ fontWeight: 400, lineHeight: "24px", color: "#64748B" }}
      >
        Quis augue enim a magna feugiat massa, ligula. Proin libero vel in at
        hac. In ipsum, tempor velit, metus. Nibh dolor tortor quam volutpat
        sit..Quis augue enim a magna feugiat massa, ligula. Proin libero vel in
        at hac. In ipsum, tempor velit, metus. Nibh dolor tortor quam volutpat
        sit.Quis augue enim a magna feugiat massa, ligula. Proin libero vel in
        at hac. In ipsum, tempor velit, metus. Nibh dolor tortor quam volutpat
        sit.Quis augue enim a magna feugiat massa, ligula. Proin libero vel in
        at hac. In ipsum, tempor velit, metus. Nibh dolor tortor quam volutpat
        sit.
      </Typography>
    </CardContent>
    <CardActions sx={{ display: "flex", justifyContent: "space-between" }}>
      <div>
        <Button
          size="small"
          style={{ background: "transparent" }}
          disableRipple
        >
          <img src={image.like} alt="like" />
        </Button>
        <Button
          size="small"
          style={{ background: "transparent" }}
          disableRipple
        >
          <img src={image.dislike} alt="dislike" />
        </Button>
      </div>
      <div>
        <Typography
          sx={{
            fontSize: 12,
            fontWeight: 400,
            lineHeight: "24px",
            color: "#64748B",
          }}
        >
          Likes 55% <span>Dislikes 45%</span>
        </Typography>
      </div>
    </CardActions>
  </React.Fragment>
);

export default function OutlinedCard() {
  return (
    <Box sx={{ maxWidth: "80%" }}>
      <Card variant="outlined" style={{ borderRadius: "16px" }}>
        {card}
      </Card>
      <br />
      <Card variant="outlined" style={{ borderRadius: "16px" }}>
        {card}
      </Card>
    </Box>
  );
}
