import * as React from 'react';
import {alpha} from '@mui/material';
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import SunCard from "./SunCard";
import TempCard from "./TempCard";
import {useEffect, useState} from "react";

export default function Welcome({userName}) {

    const [houseState, setHouseState] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/house')
            .then(response => response.json())
            .then(data => setHouseState(data))
            .catch(err => console.log(err))
    }, []);

    return (
        <Box
            id="welcome"
            sx={(theme) => ({
                width: '100%',
                minHeight: '100vh',
                backgroundImage:
                    theme.palette.mode === 'light'
                        ? 'linear-gradient(180deg, #CEE5FD, #FFF)'
                        : `linear-gradient(180deg, #02294F, ${alpha('#090E10', 0.0)})`,
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                margin: 0,
                padding: 0,
                paddingTop: '64px',
            })}
        >

            <Stack spacing={2} useFlexGap sx={{width: {xs: '100%', sm: '70%'}}}>
                <Typography
                    variant="h1"
                    sx={{
                        display: 'flex',
                        flexDirection: {xs: 'column', md: 'row'},
                        alignSelf: 'center',
                        textAlign: 'center',
                        fontSize: 'clamp(3.5rem, 10vw, 4rem)',
                    }}
                >
                    Welcome Home{userName ? `, ${userName}` : ''}!
                </Typography>
                <Typography
                    textAlign="center"
                    color="text.secondary"
                    sx={{alignSelf: 'center', width: {sm: '100%', md: '80%'}}}
                >
                    Start tailoring your home to be as smart, efficient and secure as you need it to be.
                </Typography>
            </Stack>

            <Box
                sx={{
                    display: 'flex',
                    flexDirection: {xs: 'column', sm: 'row'},
                    gap: 2, // Adjust the gap between the cards
                    mt: 4, // Margin top for spacing from the previous section
                }}>

                <div>
                    {houseState ? (
                        <SunCard latitude={houseState.latitude}
                                 longitude={houseState.longitude}/>
                    ) : (
                        <span>Loading sunrise/sunset data...</span>
                    )}
                </div>

                <div>
                    {houseState ? (
                        <TempCard latitude={houseState.latitude}
                                  longitude={houseState.longitude}/>
                    ) : (
                        <span>Loading sunrise/sunset data...</span>
                    )}
                </div>
            </Box>

        </Box>
    );
}