import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import FunctionalityTypeDropdown from "./FunctionalityTypeDropdown";
import DecimalInput from "./DecimalInput";

const AddFunctionalityForm = ({ deviceID, type, onFunctionalityAdded, onClose }) => {
    const [formData, setFormData] = useState({});
    const [selectedActuatorType, setSelectedActuatorType] = useState('');

    const handleInputChange = (fieldName, value) => {
        setFormData(prevState => ({
            ...prevState,
            [fieldName]: value,
            deviceID, // Include deviceID here
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            console.log(formData);
            const response = await fetch(`http://localhost:8080/${type}s`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`Network response was not ok: ${errorMessage}`);
            }

            const data = await response.json();
            onFunctionalityAdded(data); // Trigger rerender in the parent component
            onClose();
        } catch (error) {
            console.error(`Error adding ${type}:`, error.message);
        }
    };

    const handleActuatorTypeChange = (value) => {
        setSelectedActuatorType(value);
        handleInputChange('actuatorTypeID', value);
    };

    return (
        <Paper elevation={3} sx={{ p: 2, mt: 3, mb: 3 }}>
            <Typography variant="h6" gutterBottom>
                Add New {type === 'sensor' ? 'Sensor' : 'Actuator'}
            </Typography>
            <Box
                component="form"
                onSubmit={handleSubmit}
                sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
            >
                <TextField
                    label={`${type === 'sensor' ? 'Sensor' : 'Actuator'} Name`}
                    value={formData[`${type}Name`] || ''}
                    onChange={(e) => handleInputChange(`${type}Name`, e.target.value)}
                    fullWidth
                />

                {type === 'actuator' && (
                    <>
                        <FunctionalityTypeDropdown
                            value={formData['actuatorTypeID'] || ''}
                            onChange={handleActuatorTypeChange}
                            type={type} // Pass the type prop to the ActuatorTypeDropdown
                        />

                        {selectedActuatorType === 'DecimalValueActuator' && (
                            <>
                                <DecimalInput
                                    label="Upper Limit"
                                    value={formData.upperLimit || ''}
                                    onChange={(value) => handleInputChange('upperLimit', value)}
                                />
                                <DecimalInput
                                    label="Lower Limit"
                                    value={formData.lowerLimit || ''}
                                    onChange={(value) => handleInputChange('lowerLimit', value)}
                                />
                                <DecimalInput
                                    label="Precision"
                                    value={formData.precision || ''}
                                    onChange={(value) => handleInputChange('precision', value)}
                                />
                            </>
                        )}

                        {selectedActuatorType === 'IntegerValueActuator' && (
                            <>
                                <TextField
                                    label="Upper Limit"
                                    value={formData.upperLimit || ''}
                                    onChange={(e) => handleInputChange('upperLimit', e.target.value)}
                                    fullWidth
                                />
                                <TextField
                                    label="Lower Limit"
                                    value={formData.lowerLimit || ''}
                                    onChange={(e) => handleInputChange('lowerLimit', e.target.value)}
                                    fullWidth
                                />
                            </>
                        )}
                    </>
                )}

                {type === 'sensor' && (
                    <FunctionalityTypeDropdown
                        value={formData['sensorTypeID'] || ''}
                        onChange={(value) => handleInputChange('sensorTypeID', value)}
                        type={type} // Pass the type prop to the SensorTypeDropdown
                    />
                )}

                <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 1, mt: 2 }}>
                    <Button
                        variant="contained"
                        sx={{
                            backgroundColor: 'red',
                            color: 'white',
                            '&:hover': {
                                backgroundColor: 'darkred',
                            },
                        }}
                        onClick={onClose}
                    >
                        Cancel
                    </Button>
                    <Button
                        type="submit"
                        variant="contained"
                        sx={{
                            backgroundColor: 'green',
                            color: 'white',
                            '&:hover': {
                                backgroundColor: 'darkgreen',
                            },
                        }}
                    >
                        Save
                    </Button>
                </Box>
            </Box>
        </Paper>
    );
};

export default AddFunctionalityForm;
