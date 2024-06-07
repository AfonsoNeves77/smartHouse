import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';

const AddFunctionalityForm = ({ deviceID, type, onFunctionalityAdded, onClose }) => {
    const [formData, setFormData] = useState({});

    const handleInputChange = (fieldName, value) => {
        if (fieldName === "deviceID") {
            return; // Ignore deviceID changes
        }

        setFormData(prevState => ({
            ...prevState,
            [fieldName]: value,
            deviceID, // Include deviceID here
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            console.log(formData)
            const response = await fetch(`http://localhost:8080/${type}s`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData), // Include deviceID here
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            onFunctionalityAdded(data); // Trigger rerender in the parent component
            setFormData({});
            onClose();
        } catch (error) {
            console.error(`Error adding ${type}:`, error);
        }
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
                <TextField
                    label={`${type === 'sensor' ? 'Sensor' : 'Actuator'} Type`}
                    value={formData[`${type}Type`] || ''}
                    onChange={(e) => handleInputChange(`${type}Type`, e.target.value)}
                    fullWidth
                />
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

