import React, {useEffect, useState} from 'react';
import {Container, Card, CardContent, Typography} from '@mui/material';
import {styled} from '@mui/material/styles';
import DeviceThermostatIcon from '@mui/icons-material/DeviceThermostat';
import axios from 'axios';
import config from './config';

const StyledContainer = styled(Container)(({theme}) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: theme.spacing(4),
}));

const StyledCard = styled(Card)(({theme}) => ({
    width: '150px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundImage:
        theme.palette.mode === 'light'
            ? 'linear-gradient(315deg, #f6f6f6 0%, #e9e9e9 74%)'
            : 'linear-gradient(315deg, #2a2a2a 0%, #1a1a1a 74%)',
}));

const StyledContent = styled(CardContent)(({theme}) => ({
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
}));

const TempCard = () => {
    const [temperature, setTemperature] = useState(''); // State to store the temperature
    const [loading, setLoading] = useState(true); // State to manage loading status

    const primaryApiUrl = `${config.apiBaseUrl}/InstantaneousTemperature`; // Primary API URL
    const groupNumber = 4; // Group number for the API request
    const latitude = 40.00; // Example latitude for the location
    const longitude = -8.00; // Example longitude for the location
    const fallbackApiUrl = `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&units=metric&appid=3fd6f0bafafc6b4a19601d364ff909d3`; // Fallback API URL

    // fetchPrimaryTemperature: Function to fetch temperature from the primary API
    const fetchPrimaryTemperature = async (hour) => {
        try {
            const response = await axios.get(`${primaryApiUrl}?groupNumber=${groupNumber}&hour=${hour}`); // Make GET request to primary API
            console.log('Primary API response:', response.data); // Log the response
            return response.data.measurement; // Return the temperature measurement
        } catch (error) {
            console.log('Primary API request failed:', error.response ? error.response.data : error.message); // Log error if request fails
            return null; // Return null if there's an error
        }
    };

    // fetchFallbackTemperature: Function to fetch temperature from the fallback API
    const fetchFallbackTemperature = async () => {
        try {
            const response = await axios.get(fallbackApiUrl); // Make GET request to fallback API
            console.log('Fallback API response:', response.data); // Log the response
            return response.data.main.temp; // Return the temperature in Celsius
        } catch (error) {
            console.log('Fallback API request failed:', error.response ? error.response.data : error.message); // Log error if request fails
            return null; // Return null if there's an error
        }
    };

    // registerLocation: Function to register the location with the primary API
    const registerLocation = async () => {
        try {
            await axios.post(`${config.apiBaseUrl}/WeatherServiceConfiguration`, {
                groupNumber,
                latitude,
                longitude,
            }); // Make POST request to register location
            console.log('Location registered successfully'); // Log success message
        } catch (error) {
            console.log('Location registration failed:', error.response ? error.response.data : error.message); // Log error if request fails
        }
    };

    // fetchTemperature: Function to fetch the temperature from primary or fallback API
    const fetchTemperature = async () => {
        try {
            setLoading(true); // Set loading state to true
            console.log('Starting fetchTemperature'); // Log start of fetching process

            await registerLocation(); // Register the location

            const currentHour = new Date().getHours(); // Get the current hour

            let temp = await fetchPrimaryTemperature(currentHour); // Try fetching from primary API

            if (temp === null) { // If primary API fails
                console.log('Primary API did not provide a valid reading, using fallback API'); // Log fallback message
                temp = await fetchFallbackTemperature(); // Try fetching from fallback API
            }

            if (temp !== null) { // If temperature is fetched successfully
                console.log('Fetched temperature:', temp); // Log the fetched temperature
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
    };

    // useEffect hook to fetch temperature initially and set interval to refetch every 15 minutes
    useEffect(() => {
        fetchTemperature(); // Fetch temperature on component mount
        const interval = setInterval(fetchTemperature, 15 * 60 * 1000); // Set interval to fetch temperature every 15 minutes
        return () => clearInterval(interval); // Clear interval on component unmount
    }, []); // Empty dependency array means this runs only on mount and unmount

    return (
        <StyledContainer>
            <StyledCard>
                <StyledContent>
                    <DeviceThermostatIcon fontSize="small"/>
                    <Typography variant="h6">Temperature</Typography>
                    <Typography variant="body1">
                        {loading ? 'Loading...' : `${temperature} °C`}
                    </Typography>
                </StyledContent>
            </StyledCard>
        </StyledContainer>
    );
}

export default TempCard;