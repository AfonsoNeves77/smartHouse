import React, { useEffect } from 'react';

const SensorReadings = ({ deviceId, sensors, setSensors }) => {
    useEffect(() => {
        const fetchReadings = async () => {
            for (const sensor of sensors) {
                try {
                    const response = await fetch(`http://localhost:8080/logs?deviceId=${deviceId}&sensorId=${sensor.sensorID}`);
                    const data = await response.json();
                    if (data._embedded && data._embedded.logDTOList && data._embedded.logDTOList.length > 0) {
                        const lastReading = data._embedded.logDTOList[0];
                        setSensors(prevSensors => prevSensors.map(prevSensor => {
                            if (prevSensor.sensorID === sensor.sensorID) {
                                return { ...prevSensor, lastReading: lastReading.reading };
                            }
                            return prevSensor;
                        }));
                    }
                } catch (error) {
                    console.error('Error fetching readings for sensor:', sensor.sensorID, error);
                }
            }
        };

        if (sensors.some(sensor => !sensor.lastReading)) {
            fetchReadings();
        }
    }, [deviceId, sensors, setSensors]);

    return null;
};

export default SensorReadings;
