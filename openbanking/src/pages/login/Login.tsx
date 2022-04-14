import React, { useState, useEffect, ChangeEvent } from 'react';

import { Grid, Box, Typography, TextField, Button } from '@material-ui/core';

import { login } from '../../services/Service';
import UserLogin from '../../models/UserLogin';

import { useDispatch } from 'react-redux';
import { addToken } from '../../store/tokens/actions';
import { Link, useHistory } from 'react-router-dom';

import './Login.css';

function Login() {

    let history = useHistory();
    const dispatch = useDispatch();
    const [token, setToken] = useState('');
    const [userLogin, setUserLogin] = useState<UserLogin>(
        {
            id: 0,
            email: '',
            senha: '',
            token: ''
        }
    )

    function updatedModel(e: ChangeEvent<HTMLInputElement>) {
        setUserLogin({
            ...userLogin,
            [e.target.name]: e.target.value
        })
    }

    useEffect(() => {
        if (token !== '') {
            dispatch(addToken(token))
            history.push('/home')
        }
    }, [history, token])

    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault();
        try {
            await login(`/usuario/logar`, userLogin, setToken)

            alert('Usuário logado com sucesso!');
        } catch (error) {
            alert('Dados do usuário inconsistentes');
        }
    }

    return (
        <Grid className="login-container" container direction='row' justifyContent='center' alignItems='center'>
            <Grid alignItems='center' xs={6}>
                <Box paddingX={20}>
                    <form onSubmit={onSubmit} className="form">
                        <Typography className="login-titulo" variant='h3' gutterBottom color='textPrimary' component='h3' align='center' style={{ fontWeight: 'bold' }}>Entrar</Typography>
                        <TextField value={userLogin.email} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='email' label='E-mail' variant='outlined' name='email' margin='normal' fullWidth />
                        <TextField value={userLogin.senha} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='senha' label='Senha' variant='outlined' name='senha' margin='normal' type='password' fullWidth />
                        <Box className="login-botao" marginTop={2} textAlign='center'>
                            <Button type='submit' variant='contained' color='primary'>
                                Logar
                            </Button>
                        </Box>
                    </form>
                    <Box display='flex' justifyContent='center' marginTop={2}>
                        <Box className="cadastro-botao">
                            <Typography variant='subtitle1' gutterBottom align='center'>Não tem uma conta?</Typography>
                        </Box>
                        <Link className="cadastro-botao"to="/cadastro">
                            <Box className="cadastro-botao">
                                <Typography className="cadastro-botao" variant='subtitle1' gutterBottom align='center'>Cadastre-se!</Typography>
                            </Box>
                        </Link>
                    </Box>
                </Box>
            </Grid>
        </Grid>
    );
}

export default Login;