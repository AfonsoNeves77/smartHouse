import React from 'react';
import { Card, CardContent, Typography, Box, Button, CardMedia } from "@mui/material";
import EditButton from '../EditButton';
import GetReadingsButton from './GetReadingsButton';
import DeactivateDeviceButton from './DeactivateDeviceButton';
import deviceImage from '../../images/device.png';

const DeviceCard = ({ deviceID, deviceName, deviceModel, deviceStatus, onButtonClick, fetchDevices }) => {
    return (
        <Card className="card" style={{
            border: '1px solid #ccc',
            borderRadius: '15px',
            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
            width: '400px',
            height: '500px',
        }}>
            <CardMedia
                component="img"
                height="auto"
                width="auto"
                image={deviceImage}
                alt="Device"
                style={{ objectFit: 'cover', borderRadius: '15px 15px 0 0' }}
            />
            <CardContent className="content-container">
                <Box className="text-container">
                    <Typography gutterBottom variant="h5" component="div" style={{ fontSize: '1.5rem', marginBottom: '8px' }}>
                        {deviceName}
                    </Typography>
                    <Typography variant="body1" color="text.secondary" style={{ marginBottom: '8px' }}>
                        Model: {deviceModel}<br />
                        Status: {deviceStatus ? 'On' : 'Off'}
                    </Typography>
                </Box>
                <Box className="button-container" sx={{ display: 'flex', justifyContent: 'space-between', gap: '16px' }}>
                    <Box sx={{ display: 'flex', gap: '8px' }}>
                        <Button variant="contained" color="primary" onClick={() => onButtonClick(deviceID)} sx={{ whiteSpace: 'pre-line' }}>
                            View Functionalities
                        </Button>
                        <GetReadingsButton deviceID={deviceID} />
                    </Box>
                    <Box sx={{ display: 'flex', gap: '8px' }}>
                        <EditButton />
                        <DeactivateDeviceButton deviceId={deviceID} deviceStatus={deviceStatus} fetchDevices={fetchDevices} />
                    </Box>
                </Box>
            </CardContent>
        </Card>
    );
};

export default DeviceCard;
