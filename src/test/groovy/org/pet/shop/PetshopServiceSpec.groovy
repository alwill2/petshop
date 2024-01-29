package org.pet.shop

import org.pet.shop.service.PetshopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PetshopServiceSpec extends Specification {

    @Autowired
    private PetshopService petshopService

    def "should add two numbers"() {

        given: 'first factor'
        int a = 5

        and: 'second factor'
        int b = 7

        when: 'add is called'
        int x = petshopService.add(a, b)

        then: 'the expected value is returned'
        x == 12
    }
}
