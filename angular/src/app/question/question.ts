export class Question {

    id: number;
    title: string;
    content: string;
    answers: Array<Number>;
    date: Date;
    downVoters: Array<Number>;
    upVoters: Array<Number>;
    user: number;

    constructor(
        id: number,
        title: string,
        content: string,
        answers: Array<Number>,
        date: Date,
        downVoters: Array<Number>,
        upVoters: Array<Number>,
        user: number,
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.answers = answers;
        this.date = date;
        this.downVoters = downVoters;
        this.upVoters = upVoters;
        this.user = user;
    }
    
    getScore(): number {
        return this.upVoters.length - this.downVoters.length
    }

}