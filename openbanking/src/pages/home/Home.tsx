import React from 'react';

import { Grid, Box, Typography, Button } from '@material-ui/core';

import { useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { TokenState } from '../../store/tokens/tokensReducer';

import './Home.css';

function Home() {

    let history = useHistory();
    const token = useSelector<TokenState, TokenState['tokens']>(
        (state) => state.tokens
    );

    return (
        <>
            <Grid className="home-container" container direction="row" justifyContent="center" alignItems="center" style={{ backgroundColor: "#3F51B5" }}>
                <Grid alignItems="center" item xs={6}>
                    <Box paddingX={20}>
                        <Typography variant="h3" gutterBottom color="textPrimary"
                            component="h3" align="center" style={{
                                color: "white",
                                fontWeight: "bold"
                            }}>Seja bem-vindo(a) à Bankadia!</Typography>
                    </Box>
                    <Box display="flex" justifyContent="center">
                        <Box marginRight={1}>
                        </Box>
                        <Button variant="outlined" style={{ borderColor: "white", color: "white" }}>Consulte nossos clientes</Button>
                    </Box>
                </Grid>
                <Grid item xs={6} className="back-img">
                    <img src="https://i.imgur.com/H88yIo2.png" alt="" width="500px" height="544px" />
                </Grid>
                <h1 className="sobrenos-titulo">Sobre nós</h1>
                <Grid className="sobrenos-texto" xs={12}>
                    <Box display="flex" justifyContent="center">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi aliquet orci et
                            lacinia aliquet. Curabitur nisi ipsum, sagittis ut odio in, rutrum malesuada mi. 
                            Ut vel dignissim ipsum. Nam ut purus quis mi pretium tincidunt nec quis eros. Sed
                            ut mi tortor. Sed arcu est, faucibus eget efficitur nec, laoreet a ligula. Mauris
                            mollis sit amet massa id venenatis. Etiam sodales fermentum hendrerit. Maecenas vel
                            tristique ante. Donec placerat lobortis nisi, eu scelerisque neque rutrum id. Fusce 
                            congue elit eget lorem condimentum accumsan. Aliquam elementum ultrices justo. Sed 
                            aliquet fermentum mi non facilisis.
                        </p>
                    </Box>
                </Grid>
                <div className="background-proposito">
                <h1 className="sobrenos-titulo2">Nosso propósito</h1>
                <Grid className="sobrenos-texto2" xs={12}>
                    <Box display="flex" justifyContent="center">
                        <p>
                        Sed vestibulum tristique libero, malesuada semper purus vulputate sed. Nullam non magna 
                        porttitor, semper mi sit amet, tempus felis. Vivamus aliquam lobortis ante hendrerit 
                        congue. Integer sit amet purus vel libero ultrices lobortis. Morbi congue neque in feugiat
                        venenatis. Donec nibh odio, pharetra in orci eget, ornare mollis ipsum. Nunc consectetur 
                        diam lacus, tincidunt viverra turpis laoreet ut. Nulla facilisi. Integer imperdiet diam 
                        libero, et consequat augue euismod rhoncus. Interdum et malesuada fames ac ante ipsum 
                        primis in faucibus. Vivamus porta rhoncus ligula, elementum facilisis augue sollicitudin 
                        et. Cras dui ante, pharetra sit amet leo vel, molestie tincidunt nunc.
                        </p>
                    </Box>
                </Grid>
                </div>
            </Grid>
        </>
    );
}

export default Home;