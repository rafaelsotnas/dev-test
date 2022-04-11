import React from 'react';

import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';

import './Navbar.css';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <>
            <Box className="decorator" sx={{ flexGrow: 1 }}>
                <AppBar position="static">
                    <Toolbar className="decorator">
                        <IconButton 
                            size="large"
                            edge="start"
                            color="inherit"
                            aria-label="menu"
                            sx={{ mr: 2 }}
                        >
                        </IconButton>
                        <Typography className="decorator" variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Bankadia
                        </Typography>
                        <Typography className="decorator" variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            <Link className="decorator" to="/home">
                            Home
                            </Link>
                        </Typography>
                        <Typography className="decorator" variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Cliente
                        </Typography>
                        <Typography className="decorator" variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Extrato
                        </Typography>
                        <Typography className="decorator" variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Saldo
                        </Typography>
                        <Button color="inherit">
                            <Link to="/login" className="decorator">
                            Logout
                            </Link>
                        </Button>
                    </Toolbar>
                </AppBar>
            </Box>
        </>
    );
}

export default Navbar;