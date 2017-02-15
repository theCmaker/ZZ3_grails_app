export class User {

    private id: Number;
    private answers: Array<Number>;
    private badges: Array<Number>;
    private points: Number;
    private questions: Array<Number>;
    private username: String;

    constructor(id: Number,
        answers: Array<Number>,
        badges: Array<Number>,
        points: Number,
        questions: Array<Number>,
        username: String
    ) {
        this.id = id;
        this.answers = answers;
        this.badges = badges;
        this.points = points;
        this.questions = questions;
        this.username = username;
    }

    getId(): Number {
        return this.id;
    }

    getAnswers(): any {
        // @TODO : make request
        return this.answers;
    }

    getBadges(): any {
        // @TODO : make request
        return this.badges;
    }

    getPoints(): Number {
        return this.points;
    }

    getQuestions(): any {
        // @TODO : make request
        return this.questions;
    }

    getUsername(): String {
        return this.username;
    }

}