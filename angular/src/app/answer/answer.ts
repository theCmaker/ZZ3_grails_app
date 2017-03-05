// @TODO : check the fields

export class Answer {

    id: number;    
    content: string;
    accepted: boolean;
    date: Date;
    question: number;
    user: number;
    upVoters: Array<number>;
    downVoters: Array<number>;

    constructor(
        id: number,
        content: string,
        accepted: boolean,
        date: Date,
        question: number,
        user: number,
        upVoters: Array<number>,
        downVoters: Array<number>
    ) {

        this.id = id;        
        this.content = content;
        this.accepted = accepted
        this.date = date;
        this.question = question;
        this.user = user;
        this.upVoters = upVoters;
        this.downVoters = downVoters;

    }

    getScore(): number {
        return this.upVoters.length - this.downVoters.length;
    }

}