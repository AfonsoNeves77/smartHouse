// DeviceCardContainer.js
import React from 'react';
import { Grid } from '@mui/material';
import DeviceCard from './DeviceCard';

const DeviceCardContainer = ({ devices, onViewDetails, fetchDevices }) => {
    return (
        <Grid container spacing={3}>
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
    );
};

export default DeviceCardContainer;
