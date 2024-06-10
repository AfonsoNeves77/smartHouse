import React from 'react';
import { Card, CardContent, CardMedia, Typography, Box, Button } from "@mui/material"; // Add Button to the import statement
import placeholderImage from '../../images/room2.png'; // Importing a placeholder image
import EditButton from '../EditButton'; // Importing the new component

const RoomCard = ({ roomName, roomHeight, roomLength, roomWidth, onButtonClick }) => {
    return (
        <Card className="card" style={{ border: '1px solid #ccc', borderRadius: '15px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)' }}>
            {/* Container for the image */}
            <Box className="image-container">
                <CardMedia
                    component="img"
                    height="auto" // Adjusted height
                    width="auto" // Adjusted width
                    image={placeholderImage}
                    alt="Room Placeholder"
                    style={{ objectFit: 'cover', borderRadius: '0 0px 0px 0' }}
                />
            </Box>
            {/* Container for the text and buttons */}
            <CardContent className="content-container">
                {/* Text content */}
                <Box className="text-container">
                    {/* Room name on the top right side */}
                    <Typography gutterBottom variant="h5" component="div" style={{ fontSize: '1.5rem', marginBottom: '8px' }}>
                        {roomName}
                    </Typography>
                    {/* Room details */}
                    <Typography variant="body1" color="text.secondary" style={{ marginBottom: '8px' }}>
                        <b>Height:</b> {roomHeight} meters<br/>
                        <b>Length:</b> {roomLength} meters<br/>
                        <b>Width:</b> {roomWidth} meters<br/>
                    </Typography>
                </Box>
                {/* Buttons at the bottom-right */}
                <Box className="button-container">
                    <Button variant="contained" color="primary" onClick={onButtonClick} sx={{ marginRight: '8px' }}>
                        View Devices
                    </Button>
                    <EditButton /> {/* Replace the "Edit" button with the new component */}
                </Box>
            </CardContent>
        </Card>
    );
};

export default RoomCard;
