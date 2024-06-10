import React from 'react';
import { Grid } from '@mui/material';
import RoomCard from './RoomCard';

const RoomCardContainer = ({ rooms, onButtonClick }) => {
    return (
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
    );
};

export default RoomCardContainer;
