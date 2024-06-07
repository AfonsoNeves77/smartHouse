import React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import '../css/SensorActuatorStyling.css'; // Import CSS file

const ActuatorsComponent = ({ actuators }) => {
    return (
        <Box className="container">
            <Typography variant="h6" className="title"><b>Installed Actuators:</b></Typography>
            <Box className="list">
                <Box className="header">
                    <Box className="name">Name</Box>
                    <Box className="type">Type</Box>
                    <Box className="status">Status</Box>
                </Box>
                {actuators.map((actuator, index) => (
                    <Box
                        key={index}
                        className="row"
                    >
                        <Box className="name">{actuator.actuatorName}</Box>
                        <Box className="type">{actuator.actuatorType}</Box>
                        <Box className="status">{actuator.status}</Box>
                    </Box>
                ))}
            </Box>
        </Box>
    );
};

export default ActuatorsComponent;
