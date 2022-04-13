import React, { useEffect, useState } from 'react';

import { useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';

import { busca } from '../../../services/Service';

import { TokenState } from '../../../store/tokens/tokensReducer';

import User from '../../../models/User';

function ListarUsuarios() {

    let history = useHistory();
    const [user, setUser] = useState<User[]>([])
    const token = useSelector<TokenState, TokenState['tokens']>(
        (state) => state.tokens
    );

    useEffect(() => {
        if (token === "") {
            history.push('/login')
        }

    }, [history, token])

    async function getUser() {
        await busca("/usuario", setUser, {
            headers: {
                "Authorization": token
            }
        })
    }

    useEffect(() => {
        getUser()
    }, [user.length])
    
    return(
        <>
        
        </>
    );
}

export default ListarUsuarios;