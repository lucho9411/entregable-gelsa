export interface LoginDTO {
    User: string;
    Password: string;
}

export interface UserDTO {
    IdUser?: number;
    Name: string;
    Password?: string;
    Status: number;
    token?: string; 
}