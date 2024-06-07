import React, { useEffect, useState } from 'react';
import { TextField, MenuItem } from '@mui/material';

const FunctionalityTypeDropdown = ({ value, onChange, type }) => {
    const [sensorTypes, setSensorTypes] = useState([]);
    const [actuatorTypes, setActuatorTypes] = useState([]);

    useEffect(() => {
        if (type === 'sensor') {
            fetchSensorTypes();
        } else {
            fetchActuatorTypes();
        }
    }, [type]);

    const fetchSensorTypes = () => {
        fetch(`http://localhost:8080/sensortypes`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data._embedded && data._embedded['sensorTypeDTOList']) {
                    setSensorTypes(data._embedded['sensorTypeDTOList']);
                } else {
                    console.error('Sensor types not found in response:', data);
                }
            })
            .catch(error => {
                console.error('Error fetching sensor types:', error);
            });
    };

    const fetchActuatorTypes = () => {
        fetch(`http://localhost:8080/actuatortypes`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data._embedded && data._embedded['actuatorTypeDTOList']) {
                    setActuatorTypes(data._embedded['actuatorTypeDTOList']);
                } else {
                    console.error('Actuator types not found in response:', data);
                }
            })
            .catch(error => {
                console.error('Error fetching actuator types:', error);
            });
    };

    const types = type === 'sensor' ? sensorTypes : actuatorTypes;
    const idField = type === 'sensor' ? 'sensorTypeID' : 'actuatorTypeID';

    return (
        <TextField
            select
            label={`${type === 'sensor' ? 'Sensor' : 'Actuator'} Type`}
            value={value}
            onChange={(e) => onChange(e.target.value)}
            fullWidth
        >
            {types.map((type, index) => (
                <MenuItem key={type[idField] + index} value={type[idField]}>

                    {type[idField]}
                </MenuItem>
            ))}
        </TextField>
    );
};

export default FunctionalityTypeDropdown;
