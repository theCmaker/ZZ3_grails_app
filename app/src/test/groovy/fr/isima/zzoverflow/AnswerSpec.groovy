package fr.isima.zzoverflow

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Answer)
class AnswerSpec extends Specification {

    def answer

    def setup() {
        answer = new Answer()
    }

    def cleanup() {
        answer = null
    }

    void "test init score"() {
        answer.upVoters.removeAll()
        answer.downVoters.removeAll()
        
        expect:"score is 0"
            answer.getScore() == 0
    }

    void "test positive score"() {
        answer.upVoters.removeAll()
        answer.downVoters.removeAll()

        // Adding 3 users
        answer.upVoters += 1
        answer.upVoters += 2
        answer.upVoters += 3

        expect:"score is 3"
            answer.getScore() == 3
    }

    void "test positive score should be the same"() {
        answer.upVoters.removeAll()
        answer.downVoters.removeAll()

        // Adding user one three times
        answer.upVoters += 1
        answer.upVoters += 1
        answer.upVoters += 1

        expect:"score is still 1"
            answer.getScore() == 1
    }

    void "test negative score"() {
        answer.upVoters.removeAll()
        answer.downVoters.removeAll()

        // Adding 3 users
        answer.downVoters += 1
        answer.downVoters += 2
        answer.downVoters += 3

        expect:"score is -3"
            answer.getScore() == -3
    }

    void "test negative score should be the same"() {
        answer.upVoters.removeAll()
        answer.downVoters.removeAll()

        // Adding user one three times
        answer.downVoters += 1
        answer.downVoters += 1
        answer.downVoters += 1

        expect:"score is still -3"
            answer.getScore() == -1
    }
    
}
