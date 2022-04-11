import React from 'react';

import './Home.css';

import { Grid, Box, Typography, Button } from '@material-ui/core';


function Home() {

    return (
        <>
            <Grid className="back" container direction="row" justifyContent="center" alignItems="center" style={{ backgroundColor: "#3F51B5" }}>
                <Grid alignItems="center" item xs={6}>
                    <Box paddingX={20} >
                        <Typography variant="h3" gutterBottom color="textPrimary" 
                        component="h3" align="center" style={{ color: "white", 
                        fontWeight: "bold" }}>Seja bem-vindo(a) à Bankadia!</Typography>
                    </Box>
                    <Box display="flex" justifyContent="center">
                        <Box marginRight={1}>
                        </Box>
                        <Button variant="outlined" style={{ borderColor: "white", color: "white" }}>Consulte nossos clientes</Button>
                    </Box>
                </Grid>
                <Grid item xs={6} >
                    <img src="https://i.imgur.com/H88yIo2.png" alt="" width="500px" height="544px" />
                </Grid>
                <Grid xs={12} style={{ backgroundColor: "white" }}>
                </Grid>
            </Grid>
        </>
    );
}

export default Home;