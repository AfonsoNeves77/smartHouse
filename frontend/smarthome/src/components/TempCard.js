import React, { useEffect, useState, useCallback } from 'react';
import { Container, Card, CardContent, Typography } from '@mui/material';
import { styled } from '@mui/material/styles';
import DeviceThermostatIcon from '@mui/icons-material/DeviceThermostat';
import axios from 'axios';
import config from './config';
import { red } from '@mui/material/colors';

const StyledContainer = styled(Container)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: theme.spacing(4),
}));

const StyledCard = styled(Card)(({ theme }) => ({
    width: '150px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundImage:
        theme.palette.mode === 'light'
            ? 'linear-gradient(315deg, #f6f6f6 0%, #e9e9e9 74%)'
            : 'linear-gradient(315deg, #2a2a2a 0%, #1a1a1a 74%)',
}));

const StyledContent = styled(CardContent)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
}));

const TempCard = ({ latitude, longitude }) => {
    const [temperature, setTemperature] = useState(''); // State to store the temperature
    const [loading, setLoading] = useState(true); // State to manage loading status

    const primaryApiUrl = `${config.apiBaseUrl}/InstantaneousTemperature`; // Primary API URL
    const groupNumber = 4; // Group number for the API request
    const fallbackApiUrl = `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&units=metric&appid=3fd6f0bafafc6b4a19601d364ff909d3`; // Fallback API URL

    // fetchPrimaryTemperature: Function to fetch temperature from the primary API with timeout
    const fetchPrimaryTemperature = useCallback(async (hour, remainingTime) => {
        const source = axios.CancelToken.source();
        const timeout = setTimeout(() => {
            source.cancel(`Request timeout after ${remainingTime} ms`);
        }, remainingTime);

        try {
            const response = await axios.get(`${primaryApiUrl}?groupNumber=${groupNumber}&hour=${hour}`, {
                cancelToken: source.token,
            }); // Make GET request to primary API
            clearTimeout(timeout);
            console.log('Primary API response:', response.data); // Log the response
            return response.data.measurement; // Return the temperature measurement
        } catch (error) {
            if (axios.isCancel(error)) {
                console.log('Primary API request cancelled:', error.message); // Log timeout message
            } else {
                console.log('Primary API request failed:', error.response ? error.response.data : error.message); // Log error if request fails
            }
            return null; // Return null if there's an error
        }
    }, [primaryApiUrl]);

    // fetchFallbackTemperature: Function to fetch temperature from the fallback API
    const fetchFallbackTemperature = useCallback(async () => {
        try {
            const response = await axios.get(fallbackApiUrl); // Make GET request to fallback API
            console.log('Fallback API response:', response.data); // Log the response
            return response.data.main.temp; // Return the temperature in Celsius
        } catch (error) {
            console.log('Fallback API request failed:', error.response ? error.response.data : error.message); // Log error if request fails
            return null; // Return null if there's an error
        }
    }, [fallbackApiUrl]);

    // registerLocation: Function to register the location with the primary API with timeout
    const registerLocation = useCallback(async (remainingTime) => {
        const source = axios.CancelToken.source();
        const timeout = setTimeout(() => {
            source.cancel(`Request timeout after ${remainingTime} ms`);
        }, remainingTime);

        try {
            await axios.post(`${config.apiBaseUrl}/WeatherServiceConfiguration`, {
                groupNumber,
                latitude,
                longitude,
            }, {
                cancelToken: source.token,
            }); // Make POST request to register location
            clearTimeout(timeout);
            console.log('Location registered successfully'); // Log success message
        } catch (error) {
            if (axios.isCancel(error)) {
                console.log('Location registration request cancelled:', error.message); // Log timeout message
            } else {
                console.log('Location registration failed:', error.response ? error.response.data : error.message); // Log error if request fails
            }
        }
    }, [latitude, longitude]);

    // fetchTemperature: Function to fetch the temperature from primary or fallback API
    const fetchTemperature = useCallback(async () => {
        try {
            setLoading(true); // Set loading state to true
            console.log('Starting fetchTemperature'); // Log start of fetching process

            const totalTimeout = 3000; // Total timeout of 3 seconds
            const start = Date.now();

            await registerLocation(totalTimeout); // Register the location

            const elapsed = Date.now() - start;
            const remainingTime = totalTimeout - elapsed;

            if (remainingTime <= 0) {
                console.log("Total timeout exceeded during location registration, proceeding to fallback API");
            } else {
                const currentHour = new Date().getHours(); // Get the current hour

                let temp = await fetchPrimaryTemperature(currentHour, remainingTime); // Try fetching from primary API

                if (temp !== null) { // If temperature is fetched successfully from primary API
                    console.log('Fetched temperature from primary API:', temp); // Log the fetched temperature
                    setTemperature(temp.toFixed(2)); // Set the temperature with 2 decimal places
                    return;
                }
            }

            // If the primary API fails or the remaining time is insufficient, use the fallback API
            console.log('Using fallback API');
            const temp = await fetchFallbackTemperature(); // Try fetching from fallback API

            if (temp !== null) { // If temperature is fetched successfully from fallback API
                console.log('Fetched temperature from fallback API:', temp); // Log the fetched temperature
                setTemperature(temp.toFixed(2)); // Set the temperature with 2 decimal places
            } else {
                console.log('Temperature data is undefined from both APIs'); // Log failure message
                setTemperature('N/A'); // Set temperature to 'N/A'
            }
        } catch (error) {
            console.error("Error fetching the temperature data: ", error); // Log error
            setTemperature('N/A'); // Set temperature to 'N/A' if there's an error
        } finally {
            setLoading(false); // Set loading state to false
        }
    }, [fetchFallbackTemperature, fetchPrimaryTemperature, registerLocation]); // Dependency array for useCallback

    // useEffect hook to fetch temperature initially and set interval to refetch every 15 minutes
    useEffect(() => {
        fetchTemperature().catch(error => console.error("Error in fetchTemperature", error)); // Fetch temperature on component mount
        const interval = setInterval(() => {
            fetchTemperature().catch(error => console.error("Error in fetchTemperature", error));
        }, 15 * 60 * 1000); // Set interval to fetch temperature every 15 minutes
        return () => clearInterval(interval); // Clear interval on component unmount
    }, [fetchTemperature]); // Empty dependency array means this runs only on mount and unmount

    return (
        <StyledContainer>
            <StyledCard>
                <StyledContent>
                    <DeviceThermostatIcon sx={{ fontSize: 50, color: red[500] }} />
                    <Typography variant="h7">Temperature</Typography>
                    <Typography variant="body1">
                        {loading ? 'Loading...' : `${temperature} °C`}
                    </Typography>
                </StyledContent>
            </StyledCard>
        </StyledContainer>
    );
}

export default TempCard;
