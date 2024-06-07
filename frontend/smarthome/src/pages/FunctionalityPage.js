import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import ActuatorsComponent from '../components/ActuatorsComponent';
import SensorsComponent from '../components/SensorsComponent';
import SensorReadings from '../components/SensorReadings';

import '../css/FunctionalityPage.css';

// Custom hook to fetch actuators and sensors data
const useDeviceData = (deviceId) => {
    const [actuators, setActuators] = useState([]);
    const [sensors, setSensors] = useState([]);

    useEffect(() => {
        const fetchDeviceData = async () => {
            try {
                const actuatorResponse = await fetch(`http://localhost:8080/actuators?deviceId=${deviceId}`);
                const sensorResponse = await fetch(`http://localhost:8080/sensors?deviceId=${deviceId}`);

                const actuatorData = await actuatorResponse.json();
                const sensorData = await sensorResponse.json();

                if (actuatorData._embedded && actuatorData._embedded.actuatorDTOList) {
                    setActuators(actuatorData._embedded.actuatorDTOList);
                } else {
                    setActuators([]);
                }

                if (sensorData._embedded && sensorData._embedded.sensorDTOList) {
                    setSensors(sensorData._embedded.sensorDTOList);
                } else {
                    setSensors([]);
                }
            } catch (error) {
                console.error('Error fetching device data:', error);
            }
        };

        fetchDeviceData();

    }, [deviceId]);

    return { actuators, sensors, setActuators, setSensors };
};

const FunctionalityPage = () => {
    const { deviceId } = useParams();
    const { actuators, sensors, setActuators, setSensors } = useDeviceData(deviceId);

    // Function to update actuators
    const onAddActuator = (newActuator) => {
        setActuators(prevActuators => [...prevActuators, newActuator]);
    };

    return (
        <>
            <Box className="functionality-page-container">
                <Box className="functionality-content"> {/* Updated class name */}
                    <Box className="functionality-box">
                        <Typography variant="h4" className="functionality-box-title" gutterBottom>Device Functionalities</Typography>

                        <ActuatorsComponent deviceID={deviceId} actuators={actuators} onAddActuator={onAddActuator} />
                        <SensorsComponent deviceID={deviceId} sensors={sensors} />
                        <SensorReadings deviceId={deviceId} sensors={sensors} setSensors={setSensors} />
                    </Box>
                </Box>
            </Box>
        </>
    );
}

export default FunctionalityPage;
