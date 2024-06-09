import React, { useEffect, useState } from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import Box from "@mui/material/Box";
import {Accordion, AccordionDetails, AccordionSummary, alpha, Button} from "@mui/material";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import Typography from "@mui/material/Typography";
import AddDeviceButton from "../components/AddDeviceButton";
import DeactivateDeviceButton from "../components/DeactivateDeviceButton";
import GetReadingsButton from "../components/GetReadingsButton"; // Import GetReadingsButton

export default function Devices() {
    const { roomId } = useParams();
    const [devices, setDevices] = useState([]);
    const navigate = useNavigate();

    const fetchDevices = () => {
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
    };

    useEffect(() => {
        fetchDevices();
    }, [roomId]);

    const handleAddDevice = (newDevice) => {
        setDevices((prevDevices) => [...prevDevices, newDevice]);
    }

    const handleFunctionalityView = (deviceId) => {
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
            <>
                <AddDeviceButton roomID={roomId} onDeviceAdded={handleAddDevice} />
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
                            <AccordionDetails sx={{ display: 'flex', justifyContent: 'space-between' }}>
                                <Box sx={{ marginRight: 'auto', paddingRight: 10 }}>
                                    <Typography>
                                        <b>Model:</b> {device.deviceModel}<br />
                                        <b>Status:</b> {device.deviceStatus === "true" ? "On" : "Off"}<br />
                                    </Typography>
                                </Box>
                                <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        onClick={() => handleFunctionalityView(device.deviceID)}
                                        sx={{ mb: 1 }}
                                    >
                                        View Functionalities
                                    </Button>
                                    <GetReadingsButton deviceID={device.deviceID} /> {/* Add GetReadingsButton here */}
                                    <DeactivateDeviceButton
                                        deviceId={device.deviceID}
                                        deviceStatus={device.deviceStatus}
                                        fetchDevices={fetchDevices}
                                        sx={{ mt: 1 }}
                                    />
                                </Box>
                            </AccordionDetails>
                        </Accordion>
                    ))}
                </div>
            </>
        </Box>
    );
}