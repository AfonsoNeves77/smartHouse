import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import ActuatorsComponent from '../components/ActuatorsComponent';
import SensorsComponent from '../components/SensorsComponent';
import SensorReadings from '../components/SensorReadings';
import GoBackButton from '../components/GoBackButton'; // Import GoBackButton component
import '../css/FunctionalityPage.css';

// Custom hook to fetch actuators and sensors data
const useDeviceData = (deviceId) => {
    const [actuators, setActuators] = useState([]);
    const [sensors, setSensors] = useState([]);

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

    useEffect(() => {
        fetchDeviceData();
    }, [deviceId]);

    // Return the state and the fetch function
    return { actuators, setActuators, sensors, setSensors, fetchDeviceData };
};

const FunctionalityPage = () => {
    const { deviceId } = useParams();
    const { actuators, setActuators, sensors, setSensors, fetchDeviceData } = useDeviceData(deviceId);

    // Function to update actuators state
    const updateActuatorsState = (updatedActuators) => {
        setActuators(updatedActuators);
    };

    // Function to handle slider update
    const handleSliderUpdate = async (actuatorId, newStatus) => {
        try {
            // Make an API call to update the status
            const response = await fetch(`http://localhost:8080/actuators/${actuatorId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ status: newStatus }),
            });
            if (!response.ok) {
                throw new Error('Failed to update status');
            }
            // Refetch the device data to update the UI
            fetchDeviceData();
        } catch (error) {
            console.error('Error updating status:', error);
        }
    };

    // Function to handle adding a new actuator
    const onAddActuator = (newActuator) => {
        setActuators(prevActuators => [...prevActuators, newActuator]);
    };

    return (
        <>
            <Box className="functionality-page-container">
                <Box className="functionality-content"> {/* Updated class name */}
                    <Box className="functionality-box">
                        <Typography variant="h4" className="functionality-box-title" gutterBottom>Device Functionalities</Typography>
                        {/* Add GoBackButton component */}
                        <Box className="go-back-button" style={{ textAlign: 'right' }}>
                            <GoBackButton />
                        </Box>
                        <ActuatorsComponent
                            deviceID={deviceId}
                            actuators={actuators}
                            onAddActuator={onAddActuator}
                            onUpdate={updateActuatorsState}
                            handleSliderUpdate={handleSliderUpdate}
                        />
                        <SensorsComponent sensors={sensors} />
                        <SensorReadings deviceId={deviceId} sensors={sensors} setSensors={setSensors} />
                    </Box>
                </Box>
            </Box>
            <div className="set-error-messages">
                {/* Place set/error messages here */}
            </div>
        </>
    );
}

export default FunctionalityPage;
