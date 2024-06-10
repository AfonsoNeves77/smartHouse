import React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import '../../css/SensorActuatorStyling.css';
import AddFunctionalityButton from "./AddFunctionalityButton";
import Button from "@mui/material/Button";

const formatType = (type) => {
    // Add a space before each capital letter, except for the first character
    return type.replace(/([A-Z])/g, ' $1').trim();
};

const SensorsComponent = ({ deviceID, sensors, onAddSensor }) => {
    return (
        <Box className="container">
            <Box className="header-row">
                <Typography variant="h5" className="title"><b>Sensors</b></Typography>
                <Box className="button-container">
                    <AddFunctionalityButton deviceID={deviceID} type="sensor" onFunctionalityAdded={onAddSensor} />
                </Box>
            </Box>

            {sensors.length === 0 ? (
                <Typography variant="subtitle1" style={{ textAlign: 'center', marginTop: '20px' }}>
                    No installed Sensors, to install please&nbsp;
                    <span style={{ fontWeight: 'bold', textDecoration: 'underline' }}>press</span>&nbsp;the <b>Add</b> button
                </Typography>
            ) : (
                <Box className="list">
                    <Box className="header">
                        <Box className="column name">Name</Box>
                        <Box className="column type">Type</Box>
                        <Box className="column status">Reading</Box>
                        <Box className="column actions">Actions</Box>
                    </Box>
                    {sensors.map((sensor, index) => (
                        <Box key={index} className="row">
                            <Box className="column name">{sensor.sensorName}</Box>
                            <Box className="column type">{formatType(sensor.sensorTypeID)}</Box>
                            <Box className="column status">-</Box> {/* Consistent column name with header */}
                            <Box className="column actions">
                                <Button
                                    variant="contained"
                                    style={{ backgroundColor: 'transparent', color: 'black' }}
                                    disabled
                                >
                                    Future Release
                                </Button>
                            </Box>
                        </Box>
                    ))}
                </Box>
            )}
        </Box>
    );
};

export default SensorsComponent;
