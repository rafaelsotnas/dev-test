import React from 'react';

import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';

import { Link, useHistory } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { TokenState } from '../../../store/tokens/tokensReducer';

import { addToken } from '../../../store/tokens/actions';

import './Navbar.css';

function Navbar() {

    let history = useHistory();
    const dispatch = useDispatch();
    const token = useSelector<TokenState, TokenState['tokens']>(
        (state) => state.tokens
    );

    function goLogout() {
        dispatch(addToken(''))
        alert("Usuário deslogado")
        history.push('/login')
    }

    var navbarComponent;

    if (token !== '') {

        navbarComponent =
            <Box className="menu" sx={{ flexGrow: 1 }}>
                <AppBar className="menu" position="static">
                    <Toolbar>
                        <Box mx={1} onClick={goLogout}>
                            <Link className="logout" to="/login">
                                <Button color="inherit">
                                    Logout
                                </Button>
                            </Link>
                        </Box>
                    </Toolbar>
                </AppBar>
            </Box>
    }

    return (
        <>
            {navbarComponent}
        </>
    );
}

export default Navbar;