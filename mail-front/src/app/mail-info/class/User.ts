import { Contact } from "./Contact";

export class User {
    password: string = '';
    userName: string = '';
    email: string = '';
    contacts: Contact[] = [];

    constructor() { }
}