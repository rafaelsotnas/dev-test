import React from 'react';

import { Typography, Box, Grid } from '@material-ui/core';
import GitHubIcon from '@material-ui/icons/GitHub';

import { useSelector } from 'react-redux';

import { TokenState } from '../../../store/tokens/tokensReducer';

import './Footer.css';

function Footer() {

    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    );

    var footerComponent;

    if (token !== "") {
        footerComponent =
            <Grid container direction="row" justifyContent="center" alignItems="center" className="footer">
                <Grid alignItems="center" item xs={12}>
                    <Box>
                        <Box paddingTop={1} display="flex" alignItems="center" justifyContent="center">
                            <Typography variant="h5" align="center" gutterBottom ></Typography>
                        </Box>
                    </Box>
                    <div className='footer-container'>
                        <div className='footer-titulo'>
                            <h1>Repositório</h1>
                            <div className='git-link'>
                                <a href="https://github.com/rafaelsotnas/dev-test" target="_blank"><GitHubIcon /></a>
                            </div>
                        </div>
                    </div>
                    <Box>
                        <Box paddingTop={3}>
                        </Box>
                        <div className='rodape'>
                            <p>Copyright 2022 © Todos os direitos reservados</p>
                        </div>
                    </Box>
                </Grid>
            </Grid>
    }

    return (
        <>
            {footerComponent}
        </>
    )
}

export default Footer;