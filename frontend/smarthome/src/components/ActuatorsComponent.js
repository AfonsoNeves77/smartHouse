import React, { useState, useEffect } from 'react';
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import StatusSlider from './StatusSlider';
import '../css/SensorActuatorStyling.css';
import AddFunctionalityButton from './AddFunctionalityButton';

const ActuatorsComponent = ({ deviceID, actuators, onAddActuator, onUpdate }) => {
    const handleSliderUpdate = async (actuatorId, newStatus) => {
        try {
            const response = await fetch(`http://localhost:8080/actuators/${actuatorId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
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
            <Box className="header-row">
                <Typography variant="h5" className="title"><b>Actuators</b></Typography>
                <Box className="button-container">
                    <AddFunctionalityButton deviceID={deviceID} type="actuator" onFunctionalityAdded={onAddActuator} />
                </Box>
            </Box>
            {actuators.length === 0 ? (
                <Typography variant="subtitle1" style={{ textAlign: 'center', marginTop: '20px' }}>
                    No installed Actuators, to install please press the Add button
                </Typography>
            ) : (
                <Box className="list">
                    <Box className="header">
                        <Box className="column name">Name</Box>
                        <Box className="column type">Type</Box>
                        <Box className="column status">Status</Box>
                        <Box className="column actions">Actions</Box>
                    </Box>
                    {actuators.map((actuator, index) => (
                        <Box key={index} className="row">
                            <Box className="column name">{actuator.actuatorName}</Box>
                            <Box className="column type">{actuator.actuatorTypeID}</Box>
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
