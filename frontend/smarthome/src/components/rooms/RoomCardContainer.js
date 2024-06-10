import React from 'react';
import { Grid } from '@mui/material';
import RoomCard from './RoomCard';
import Box from "@mui/material/Box";

const drawerWidth = 125;

const RoomCardContainer = ({ rooms, onButtonClick }) => {
    return (
        <Box sx={{
            width: `calc(100% - ${drawerWidth}px)`,
            marginLeft: `${drawerWidth}px`,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '20px',
            paddingTop: '64px' // Ensure there's enough space for the AppBar
        }}>
        <Grid container spacing={3}>
            {rooms.map((room, index) => (
                <Grid item key={index} xs={12} sm={6} md={4} lg={3}>
                    <RoomCard
                        roomName={room.roomName}
                        roomHeight={room.roomHeight}
                        roomLength={room.roomLength}
                        roomWidth={room.roomWidth}
                        onButtonClick={() => onButtonClick(room.id)}
                    />
                </Grid>
            ))}
        </Grid>
        </Box>
    );
};

export default RoomCardContainer;
