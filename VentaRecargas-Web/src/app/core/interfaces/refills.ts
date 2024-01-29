import { UserDTO } from "./session";

export  interface Operators {
    IdOperador?: number;
    NombreOperador: string;
    ImagenOperador: string;
}

export interface Refills {
    IdRecarga?: number;
    FechaRecarga?: string;
    NumeroTelefono: string;
    Precio: number;
    Vendedor?: UserDTO;
    Operador?: Operators;
}