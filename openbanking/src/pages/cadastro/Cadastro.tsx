import { Grid, Box, Typography, Button, TextField } from '@material-ui/core';
import { ChangeEvent, useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import User from '../../models/User';
import { cadastro } from '../../services/Service';
import './Cadastro.css';

function Cadastro() {

    let history = useHistory();
    const [confirmarSenha,setConfirmarSenha] = useState<String>("")

    /*Estrututa de cadastro.*/
    const [user, setUser] = useState<User>(
        {
            id: 0,
            nome: '',
            cpf: '',
            email: '',
            senha: ''
        })

    /*Estrutura de login.*/
    const [userResult, setUserResult] = useState<User>(
        {
            id: 0,
            nome: '',
            cpf: '',
            email: '',
            senha: ''
        })
    
    /*Hook que verifica se o ID cadastrado é diferente de 0. Se for verdade, redirecionará pra tela de login.*/
    useEffect(() => {
        if (userResult.id !== 0) {
            history.push("/login")
        }
    }, [history, userResult])

    /*Função que verifica, através do evento, se as senhas batem.*/
    function confirmarSenhaHandle(e: ChangeEvent<HTMLInputElement>){
        setConfirmarSenha(e.target.value)
    }

    /*Atualiza a model User, através do evento, e seta os dados cadastrados pelo usuário.*/
    function updatedModel(e: ChangeEvent<HTMLInputElement>) {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        })
    }

    /*Função assíncrona que, após o envio dos dados, confirma se as senhas coincidem e guarda os dados do usuário através da rota de cadastro.*/
    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault()
        if (confirmarSenha === user.senha){
        cadastro(`/usuario/cadastrar`, user, setUserResult)
        alert('Usuario cadastrado com sucesso')
        } else {
            alert('Dados inconsistentes.')
        }
    }

    return (
        <Grid container direction='row' justifyContent='center' alignItems='center'>
            <Grid item xs={6} className='imagem'></Grid>
            <Grid item xs={6} alignItems='center'>
                <Box paddingX={10}>
                    <form onSubmit={onSubmit}>
                        <Typography className='cadastro-titulo' variant='h3' gutterBottom color='textPrimary' component='h3' align='center'>Junte-se a nós!</Typography>
                        <TextField value={user.nome} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='nome' label='Nome' variant='outlined' name='nome' margin='normal' fullWidth required/>
                        <TextField value={user.cpf} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='cpf' label='CPF' variant='outlined' name='cpf' margin='normal' fullWidth required placeholder='CPF deve conter 11 caracteres'/>
                        <TextField value={user.email} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='email' label='E-mail' variant='outlined' name='email' margin='normal' fullWidth required/>
                        <TextField value={user.senha} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='senha' label='Senha' variant='outlined' name='senha' margin='normal' type='password' fullWidth required placeholder='Senha deve conter, no mínimo, 8 caracteres'/>
                        <TextField value={confirmarSenha} onChange={(e: ChangeEvent<HTMLInputElement>) => confirmarSenhaHandle(e)} id='confirmarSenha' label='Confirmar Senha' variant='outlined' name='confirmarSenha' margin='normal' type='password' fullWidth required/>
                        <Box marginTop={2} textAlign='center'>
                            <Link to='/login' className='decorator'>
                                <Button variant='contained' color='secondary' className='btnCancelar'>
                                    Cancelar
                                </Button>
                            </Link>
                            <Button type='submit' variant='contained' color='primary'>
                                    Cadastrar
                            </Button>
                        </Box>
                    </form>
                </Box>
            </Grid>



        </Grid>
    );
}

export default Cadastro;