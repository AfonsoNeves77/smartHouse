// DeviceCardContainer.js
import React from 'react';
import {Grid, useMediaQuery, useTheme} from '@mui/material';
import DeviceCard from './DeviceCard';
import Box from "@mui/material/Box";

const drawerWidth = 125;

const DeviceCardContainer = ({ devices, onViewDetails, fetchDevices }) => {

    const theme = useTheme();
    const isSmallScreen = useMediaQuery(theme.breakpoints.down('sm'));

    return (
        <Box sx={{
            width: isSmallScreen ? '100%' : `calc(100% - ${drawerWidth}px)`,
            marginLeft: isSmallScreen ? '0px' : `${drawerWidth}px`,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '20px',
            paddingTop: '64px' // Ensure there's enough space for the AppBar
        }}>
            <Grid container spacing={3} flex-wrap='wrap'>
            {devices.map((device, index) => (
                <Grid item key={index} xs={12} sm={6} md={4} lg={3}>
                    <DeviceCard
                        deviceID={device.deviceID}
                        deviceName={device.deviceName}
                        deviceModel={device.deviceModel}
                        deviceStatus={device.deviceStatus}
                        onButtonClick={() => onViewDetails(device.deviceID)}
                        fetchDevices={fetchDevices} // Pass fetchDevices here if needed
                    />
                </Grid>
            ))}
        </Grid>
        </Box>
    );
};

export default DeviceCardContainer;
