import React, {useEffect, useState} from 'react';
import {Container, Grid, Card, CardContent, Typography} from '@mui/material';
import {styled} from '@mui/material/styles';
import WbSunnyIcon from '@mui/icons-material/WbSunny';
import WbTwilightIcon from '@mui/icons-material/WbTwilight';
import axios from "axios";
import {yellow} from "@mui/material/colors";

const StyledContainer = styled(Container)(({theme}) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: theme.spacing(4),
}));

const StyledCard = styled(Card)(({theme}) => ({
    width: '300px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundImage:
        theme.palette.mode === 'light'
            ? 'linear-gradient(315deg, #f6f6f6 0%, #e9e9e9 74%)'
            : 'linear-gradient(315deg, #2a2a2a 0%, #1a1a1a 74%)',
}));

const StyledContent = styled(CardContent)(({theme}) => ({
    textAlign: 'center',
}));

const SunCard = ({latitude, longitude}) => {
    const [sunrise, setSunrise] = useState('');
    const [sunset, setSunset] = useState('');

    useEffect(() => {
        const fetchSunData = async () => {
            try {
                const res = await axios.get(`https://api.sunrise-sunset.org/json?lat=${latitude}&lng=${longitude}`);
                setSunrise(res.data.results.sunrise);
                setSunset(res.data.results.sunset);
            } catch (error) {
                console.error("Error fetching the sunrise/sunset data: ", error);
            }
        };
        fetchSunData();
    }, [latitude, longitude]);

    return (
        <StyledContainer>
            <StyledCard>
                <StyledContent>
                    <Grid container spacing={4} justifyContent='center'>
                        <Grid item xs={12} md={6} container direction='column' alignItems='center'>
                            <WbSunnyIcon sx={{color: "#f0d43a", fontSize: 50}}/>
                            <Typography variant="h7">Sunrise</Typography>
                            <Typography variant="body1" noWrap>{sunrise || 'Loading...'}</Typography>
                        </Grid>
                        <Grid item xs={12} md={6} container direction='column' alignItems='center'>
                            <WbTwilightIcon sx={{color: yellow[800], fontSize: 50}}/>
                            <Typography variant="h7">Sunset</Typography>
                            <Typography variant="body1" noWrap>{sunset || 'Loading...'}</Typography>
                        </Grid>
                    </Grid>
                </StyledContent>
            </StyledCard>
        </StyledContainer>
    );
};

export default SunCard;
