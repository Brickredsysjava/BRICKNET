import React, { useState } from "react";
import { Grid, Typography, Button, TextField, Checkbox, FormControlLabel } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

const LoginForm = () => {
    const [remember, setRemember] = useState(false);
    const navigate = useNavigate();

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    const onSubmit = async (data) => {
        console.log(data);
        navigate('/hello')
        try{
            // let {data} = await api(`/userProfile?email=${emailid}`);
            // localStorage.setItem(
            //     'userDetails',
            //     JSON.stringify({
            //         email: res.data.email,
            //         firstName: res.data.firstName,
            //         lastName: res.data.lastName,
            //         token: res.data.token,
            //     })
            // );
            
        }
        catch(errors){
            console.log("error", errors);
        }
    };
    return (
        <>
            <form onSubmit={handleSubmit(onSubmit)} >
                <Grid container>
                    <Grid item xs={12} lg={12} >
                        <TextField
                            {...register("email", { required: true })}
                            fullWidth
                            label="Username"
                            name="email"
                            autoComplete="email"
                            variant="standard"
                            sx={{ mt: "6%" }}
                        />
                        {errors.email && (
                            <Typography variant="body" className="warningTxt">
                                This field is required
                            </Typography>
                        )}
                    </Grid>

                    <Grid
                        item
                        xs={12}
                        lg={12}
                    >
                        <TextField
                            fullWidth
                            {...register("password", { required: true })}
                            label="Password"
                            type="password"
                            autoComplete="new-password"
                            variant="standard"
                            sx={{ mt: "5%" }}
                        />
                        {errors.password && (
                            <Typography variant="body" className="warningTxt">
                                This field is required
                            </Typography>
                        )}
                    </Grid>

                    <Grid item lg={12} xs={12} mt={2}>
                        <div className="otherLinkStyle">
                            <FormControlLabel
                                onClick={() => setRemember(!remember)}
                                control={<Checkbox checked={remember} />}
                                label="Remember me"
                            />

                            <Typography
                                variant="body1"
                                onClick={() => {
                                    navigate("/reset-password");
                                }}
                                style={{
                                    marginTop: "10px",
                                    cursor: "pointer",
                                }}
                            >
                                Forgot password?
                            </Typography>
                        </div>
                    </Grid>
                    <Grid
                        item
                        lg={12}
                        xs={12}
                    >
                        <Button
                            type="submit"
                            variant="contained"
                            fullWidth={true}
                            size="large"
                            sx={{
                                mt: "10px",
                                borderRadius: "8px",
                                color: "#ffffff",
                                backgroundColor: "#0077B6",
                            }}
                        >
                            Log in
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </>
    )
}

export default LoginForm