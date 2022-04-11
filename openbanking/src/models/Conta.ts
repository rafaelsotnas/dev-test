import User from './User';

interface Conta {
    id: number,
    agencia: string,
    numeroConta: string,
    tipoConta: string,
    user?: User | null
}

export default Conta;