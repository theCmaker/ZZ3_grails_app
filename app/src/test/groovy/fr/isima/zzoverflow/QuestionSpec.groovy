package fr.isima.zzoverflow

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Question)
class QuestionSpec extends Specification {

    def question

    def setup() {
        question = new Question()
    }

    def cleanup() {
        question = null
    }

    void "test init score"() {
        question.upVoters.removeAll()
        question.downVoters.removeAll()
        
        expect:"score is 0"
            question.getScore() == 0
    }

    void "test positive score"() {
        question.upVoters.removeAll()
        question.downVoters.removeAll()

        // Adding 3 users
        question.upVoters += 1
        question.upVoters += 2
        question.upVoters += 3

        expect:"score is 3"
            question.getScore() == 3
    }

    void "test positive score should be the same"() {
        question.upVoters.removeAll()
        question.downVoters.removeAll()

        // Adding user one three times
        question.upVoters += 1
        question.upVoters += 1
        question.upVoters += 1

        expect:"score is still 1"
            question.getScore() == 1
    }

    void "test negative score"() {
        question.upVoters.removeAll()
        question.downVoters.removeAll()

        // Adding 3 users
        question.downVoters += 1
        question.downVoters += 2
        question.downVoters += 3

        expect:"score is -3"
            question.getScore() == -3
    }

    void "test negative score should be the same"() {
        question.upVoters.removeAll()
        question.downVoters.removeAll()

        // Adding user one three times
        question.downVoters += 1
        question.downVoters += 1
        question.downVoters += 1

        expect:"score is still -3"
            question.getScore() == -1
    }
}
