import React, { useEffect, useState } from 'react';
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import StatusSlider from './StatusSlider'; // Make sure to adjust this import if necessary
import '../../css/SensorActuatorStyling.css'; // Adjust the CSS import path as needed
import AddFunctionalityButton from './AddFunctionalityButton';
import { useTheme } from "@mui/material";
import clsx from "clsx";

const formatActuatorType = (type) => {
    // Add a space before each capital letter, except for the first character
    return type.replace(/([A-Z])/g, ' $1').trim();
};

const ActuatorsComponent = ({ deviceID, actuators, onAddActuator, onUpdate }) => {
    const theme = useTheme();
    const [deviceStatus, setDeviceStatus] = useState('');

    // Fetch device status effect
    useEffect(() => {
        const fetchDeviceStatus = async () => {
            try {
                const response = await fetch(`http://localhost:8080/devices/${deviceID}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch device status');
                }
                const data = await response.json();
                setDeviceStatus(data.deviceStatus); // Assuming the API returns the status field
            } catch (error) {
                console.error('Error fetching device status:', error);
                setDeviceStatus('false'); // Default to inactive if status fetch fails
            }
        };

        fetchDeviceStatus();
    }, [deviceID]);

    // Handle slider update for actuators
    const handleSliderUpdate = async (actuatorId, newStatus) => {
        try {
            const response = await fetch(`http://localhost:8080/actuators/${actuatorId}`, {
                method: 'PUT', // Adjust HTTP method based on your API requirements
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ status: newStatus }),
            });
            if (!response.ok) {
                throw new Error('Failed to update status');
            }
            const updatedActuators = actuators.map(actuator => {
                if (actuator.actuatorId === actuatorId) {
                    return { ...actuator, status: newStatus };
                }
                return actuator;
            });
            onUpdate(updatedActuators);
        } catch (error) {
            console.error('Error updating status:', error);
        }
    };

    return (
        <Box className="container">
            <Box className={clsx("header-row", {"dark-theme": theme.palette.mode === 'dark'})}>
                <Typography variant="h5" className="title"><b>Actuators</b></Typography>
                <Box className="button-container">
                    <AddFunctionalityButton deviceID={deviceID} type="actuator" onFunctionalityAdded={onAddActuator} deviceStatus={deviceStatus} />
                </Box>
            </Box>
            {actuators.length === 0 ? (
                <Typography variant="subtitle1" style={{ textAlign: 'center', marginTop: '20px' }}>
                    No installed Actuators, to install please&nbsp;
                    <span style={{ fontWeight: 'bold', textDecoration: 'underline' }}>press</span>&nbsp;the <b>Add</b> button
                </Typography>
            ) : (
                <Box className="list">
                    <Box className={clsx("header", {"dark-theme": theme.palette.mode === 'dark'})}>
                        <Box className="column name">Name</Box>
                        <Box className="column type">Type</Box>
                        <Box className="column status">Status</Box>
                        <Box className="column actions">Actions</Box>
                    </Box>
                    {actuators.map((actuator, index) => (
                        <Box key={index} className={clsx("row", {"dark-theme": theme.palette.mode === 'dark'})}>
                            <Box className="column name">{actuator.actuatorName}</Box>
                            <Box className="column type">{formatActuatorType(actuator.actuatorTypeID)}</Box>
                            <Box className="column status">{actuator.status}</Box>
                            <Box className="column actions">
                                {actuator.actuatorTypeID === "RollerBlindActuator" ? (
                                    <StatusSlider
                                        initialStatus={actuator.status}
                                        actuatorId={actuator.actuatorId}
                                        onUpdate={(newStatus) => handleSliderUpdate(actuator.actuatorId, newStatus)}
                                    />
                                ) : (
                                    <Button
                                        variant="contained"
                                        style={{ backgroundColor: 'transparent', color: 'black' }} // Set background to transparent and text to black
                                        disabled
                                    >
                                        Future Release
                                    </Button>
                                )}
                            </Box>
                        </Box>
                    ))}
                </Box>
            )}
        </Box>
    );
};

export default ActuatorsComponent;
