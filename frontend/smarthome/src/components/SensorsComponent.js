import React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import '../css/SensorActuatorStyling.css';
import AddFunctionalityButton from "./AddFunctionalityButton";// Import CSS file

const SensorsComponent = ({ deviceId, sensors,onAddSensor }) => {
    return (
        <Box className="container">
            <Typography variant="h6" className="title"><b>Installed Sensors:</b></Typography>
            <Box className="list">
                <Box className="header">
                    <Box className="name">Name</Box>
                    <Box className="type">Sensor Type</Box>
                    <Box className="last-reading">Last Reading</Box>
                    <AddFunctionalityButton type="sensor" deviceId={deviceId}  onFunctionalityAdded={onAddSensor}  />

                </Box>
                {sensors.map((sensor, index) => (
                    <Box
                        key={index}
                        className="row"
                    >
                        <Box className="name">{sensor.sensorName}</Box>
                        <Box className="type">{sensor.sensorTypeID}</Box>
                        <Box className="last-reading">{sensor.lastReading || '-'}</Box>
                    </Box>
                ))}
            </Box>
        </Box>
    );
};

export default SensorsComponent;
