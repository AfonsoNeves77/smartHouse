import React from 'react';
import {Card, CardContent, Container, Grid, Typography} from '@mui/material';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import {styled} from "@mui/material/styles";

const DateTimeContainer = styled(Container)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'right',
    alignItems: 'top',
    marginTop: theme.spacing(0),
    position: 'absolute',
    top: 100,
    right: 0,
}));

const DateTimeCard = styled(Card)(({ theme }) => ({
    width: '200px',
    display: 'flex',
    justifyContent: 'right',
    alignItems: 'up',
    // background: 'radial-gradient(circle, rgba(128,91,5,1) 0%, rgba(179,118,9,1) 44%, rgba(218,219,22,1) 100%)',
    backgroundImage:
        theme.palette.mode==='light'
            ? 'linear-gradient(315deg, #f6f6f6 0%, #e9e9e9 74%)'
            : 'linear-gradient(315deg, #2a2a2a 0%, #1a1a1a 74%)',
}));

const DateTimeContent = styled(CardContent)(({ theme }) => ({
    textAlign: 'center',
}));

const DateAndTimeCard = () => {
    const today = new Date();

    return (

        <DateTimeContainer>
            <DateTimeCard>
                <DateTimeContent>
                    <AccessTimeIcon fontSize="small"/>
                    <Typography variant="body1">
                        {today.toLocaleDateString()} - {today.toLocaleTimeString()}
                    </Typography>
                </DateTimeContent>
            </DateTimeCard>
        </DateTimeContainer>
    );
};

export default DateAndTimeCard;