import React, { useEffect, useState } from 'react';
import { Container, Grid, Card, CardContent, Typography } from '@mui/material';
import { styled } from '@mui/material/styles';
import WbSunnyIcon from '@mui/icons-material/WbSunny';
import WbTwilightIcon from '@mui/icons-material/WbTwilight';
import axios from 'axios';
import { yellow } from '@mui/material/colors';

const StyledContainer = styled(Container)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: theme.spacing(4),
}));

const StyledCard = styled(Card)(({ theme }) => ({
    width: '300px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundImage:
        theme.palette.mode === 'light'
            ? 'linear-gradient(315deg, #f6f6f6 0%, #e9e9e9 74%)'
            : 'linear-gradient(315deg, #2a2a2a 0%, #1a1a1a 74%)',
}));

const StyledContent = styled(CardContent)(({ theme }) => ({
    textAlign: 'center',
}));

const formatTime = (timeString) => {
    try {
        const timePart = timeString.split('T')[1]; // Extract time part
        const [hours, minutes] = timePart.split(':');
        return `${hours}h${minutes}m`;
    } catch (error) {
        console.error('Error formatting time:', error);
        return 'Invalid time';
    }
};

const SunCard = ({ latitude, longitude }) => {
    const [sunrise, setSunrise] = useState('Loading...');
    const [sunset, setSunset] = useState('Loading...');

    useEffect(() => {
        const fetchSunData = async (sensorTypeId) => {
            const date = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
            try {
                const res = await axios.post(`http://localhost:8080/logs/get-sun-reading`, null, {
                    params: {
                        date: date,
                        latitude: latitude,
                        longitude: longitude,
                        sensorTypeId: sensorTypeId
                    }
                });
                console.log(`Response for ${sensorTypeId}: `, res.data);
                return res.data;
            } catch (error) {
                console.error(`Error fetching the ${sensorTypeId} data: `, error);
                return 'Error';
            }
        };

        const getSunTimes = async () => {
            const sunriseData = await fetchSunData('SunriseSensor');
            const sunsetData = await fetchSunData('SunsetSensor');
            setSunrise(formatTime(sunriseData));
            setSunset(formatTime(sunsetData));
        };

        getSunTimes();
    }, [latitude, longitude]);

    return (
        <StyledContainer>
            <StyledCard>
                <StyledContent>
                    <Grid container spacing={4} justifyContent='center'>
                        <Grid item xs={12} md={6} container direction='column' alignItems='center'>
                            <WbSunnyIcon sx={{ color: "#f0d43a", fontSize: 50 }} />
                            <Typography variant="h7">Sunrise</Typography>
                            <Typography variant="body1" noWrap>{sunrise}</Typography>
                        </Grid>
                        <Grid item xs={12} md={6} container direction='column' alignItems='center'>
                            <WbTwilightIcon sx={{ color: yellow[800], fontSize: 50 }} />
                            <Typography variant="h7">Sunset</Typography>
                            <Typography variant="body1" noWrap>{sunset}</Typography>
                        </Grid>
                    </Grid>
                </StyledContent>
            </StyledCard>
        </StyledContainer>
    );
};

export default SunCard;
