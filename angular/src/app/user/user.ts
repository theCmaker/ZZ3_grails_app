export class User {

    id: number;
    answers: Array<Number>;
    badges: Array<Number>;
    points: number;
    questions: Array<Number>;
    username: string;

    constructor(id: number,
        answers: Array<Number>,
        badges: Array<Number>,
        points: number,
        questions: Array<Number>,
        username: string
    ) {
        this.id = id;
        this.answers = answers;
        this.badges = badges;
        this.points = points;
        this.questions = questions;
        this.username = username;
    }

}