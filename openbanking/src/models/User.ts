import Conta from './Conta';

interface User {
    id: number,
    nome: string,
    cpf: string,
    email: string,
    senha: string,
    conta?: Conta | null
}

export default User;