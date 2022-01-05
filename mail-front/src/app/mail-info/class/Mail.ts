export class Mail {
    folder: string = 'sent';
    //id: number
    //sender: string
    recievers: string[] = [];
    subject: string = '';
    time: string = '';
    priority: number = 1;
    body: string = '';
    //attachments: FormData = new FormData();

    constructor() { }

}
  
  