import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';

const AddDeviceForm = ({ roomID, onDeviceAdded, onClose }) => {
    const [deviceName, setDeviceName] = useState('');
    const [deviceModel, setDeviceModel] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        const newDevice = {
            deviceName,
            deviceModel,
            roomID: roomID,
        };

        try {
            const response = await fetch('http://localhost:8080/devices', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newDevice),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            onDeviceAdded(data);
            setDeviceName('');
            setDeviceModel('');
            onClose(); // Close the modal after adding the device
        } catch (error) {
            console.error('Error adding device:', error);
        }
    };

    return (
        <Paper elevation={3} sx={{ p: 2, mt: 3, mb: 3 }}>
            <Typography variant="h6" gutterBottom>
                Add New Device
            </Typography>
            <Box
                component="form"
                onSubmit={handleSubmit}
                sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
            >
                <TextField
                    label="Device Name"
                    value={deviceName}
                    onChange={(e) => setDeviceName(e.target.value)}
                    fullWidth
                />
                <TextField
                    label="Device Model"
                    value={deviceModel}
                    onChange={(e) => setDeviceModel(e.target.value)}
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

export default AddDeviceForm;
