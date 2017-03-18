export class User {

    // Required for creation    
    id: number;
    answers: Array<number>;
    badges: Array<number>;
    points: number;
    questions: Array<number>;
    username: string;

    constructor(id: number,
        answers: Array<number>,
        badges: Array<number>,
        points: number,
        questions: Array<number>,
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