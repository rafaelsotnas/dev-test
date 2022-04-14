import React, { useEffect, useState } from 'react';

import { Box, Card, CardContent, Typography } from '@mui/material';

import { useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';

import { TokenState } from '../../../store/tokens/tokensReducer';

import { busca } from '../../../services/Service';
import User from '../../../models/User';

import './ListarUsuarios.css';

function ListarUsuarios() {

    let history = useHistory();
    const [users, setUsers] = useState<User[]>([])
    const token = useSelector<TokenState, TokenState['tokens']>(
        (state) => state.tokens
    );

    useEffect(() => {
        if (token === "") {
            history.push('/login')
        }

    }, [history, token])

    async function getUser() {
        await busca("/usuario/listar", setUsers, {
            headers: {
                "Authorization": token
            }
        })
    }

    useEffect(() => {
        getUser()
    }, [users.length])
    
    return(
        <>
                        {
                users.map(user => (
                    <Box m={2} className="lista-container">
                        <Card className="lista-card-container">
                            <CardContent className="lista-card-container">
                                <Typography className="lista-dados" variant="h5" component="h2">
                                    {user.nome}
                                </Typography>
                                <Typography className="lista-dados" variant="body2" component="p">
                                    CPF: {user.cpf}
                                </Typography>
                                <Typography className="lista-dados" variant="body2" component="p">
                                    E-mail: {user.email}
                                </Typography>
                                <Typography className="lista-dados" variant="body2" component="p">
                                    Agência: {user.conta?.agencia}
                                </Typography>
                                <Typography className="lista-dados" variant="body2" component="p">
                                    Número da conta: {user.conta?.numeroConta}
                                </Typography>
                                <Typography className="lista-dados" variant="body2" component="p">
                                    Tipo da conta: {user.conta?.tipoConta}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Box>
                ))
            }
        </>
    );
}

export default ListarUsuarios;