import { UserService } from '../user/user.service'

export class Question {

    private userService: UserService;

    id: Number;
    title: String;
    content: String;
    answers: Array<Number>;
    date: Date;
    downVoters: Array<Number>;
    upVoters: Array<Number>;
    user: Number;

    constructor(
        id: Number,
        title: String,
        content: String,
        answers: Array<Number>,
        date: Date,
        downVoters: Array<Number>,
        upVoters: Array<Number>,
        user: Number,
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
    
    getScore(): Number {
        return this.upVoters.length - this.downVoters.length
    }
    
    getUser(): any {
        // @TODO : create a user instance

        var a;

        this.userService.getUserById(this.user).subscribe(res => {
            console.log(res);
            a = res;  
        });

        return a;
    }

}