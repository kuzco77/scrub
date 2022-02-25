package com.rochefort.scrub.service

import spock.lang.Specification

class StartUpServiceTest extends Specification {
    def "testCheckPower"() {
        expect:
        1 + 1 == 3
    }

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }
}
