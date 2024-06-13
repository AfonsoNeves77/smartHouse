import React from 'react';
import { Dialog, DialogTitle} from '@mui/material';
import { styled } from '@mui/system';

const CustomDialog = styled(Dialog)(({ theme }) => ({
    '& .MuiDialogTitle-root': {
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.primary.contrastText,
        textAlign: 'center',
        padding: theme.spacing(2),
    },
    '& .MuiDialogContent-root': {
        padding: theme.spacing(2),
    },
}));

const Popup = ({ open, onClose }) => {
    return (
        <CustomDialog open={open} onClose={onClose}>
            <DialogTitle>Inactive Device</DialogTitle>

        </CustomDialog>
    );
};

export default Popup;
