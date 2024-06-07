import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Box from "@mui/material/Box";
import { Accordion, AccordionDetails, AccordionSummary, Button, Typography } from "@mui/material";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import { alpha } from "@mui/material/styles";

export default function DevicesPage() {
    const { roomId } = useParams();
    const [devices, setDevices] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/devices?roomID=${roomId}`)
            .then(response => response.json())
            .then(data => {
                if (data._embedded && data._embedded.deviceDTOList) {
                    setDevices(data._embedded.deviceDTOList);
                } else {
                    setDevices([]);
                }
            })
            .catch(err => console.log(err));
    }, [roomId]);

    const handleShowFunctionalities = (roomId, deviceId) => {
        navigate(`/rooms/${roomId}/devices/${deviceId}`);
    };

    return (
        <Box sx={(theme) => ({
            width: '100%',
            minHeight: '100vh',
            backgroundImage:
                theme.palette.mode === 'light'
                    ? 'linear-gradient(180deg, #CEE5FD, #FFF)'
                    : `linear-gradient(180deg, #02294F, ${alpha('#090E10', 0.0)})`,
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            margin: 0,
            padding: 0,
        })}>
            <div>
                {devices.map((device, index) => (
                    <Accordion key={index + 1}>
                        <AccordionSummary
                            expandIcon={<ArrowDropDownIcon />}
                            aria-controls={`panel${index + 1}-content`}
                            id={`panel${index + 1}-header`}
                        >
                            <Typography>{device.deviceName}</Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Box sx={{
                                display: 'flex',
                                justifyContent: 'space-between',
                                alignItems: 'flex-start',
                                width: '100%'
                            }}>
                                <Typography>
                                    <b>Model:</b> {device.deviceModel}<br />
                                    <b>Status:</b> {device.deviceStatus ? "On" : "Off"}<br />
                                </Typography>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={() => handleShowFunctionalities(roomId, device.deviceID)}
                                    sx={{ height: '25%' }}
                                >
                                    View Functionalities
                                </Button>
                            </Box>
                        </AccordionDetails>
                    </Accordion>
                ))}
            </div>
        </Box>
    );
}
