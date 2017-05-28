package fr.slickteam.cities.repositories

import fr.slickteam.cities.dto.Account
import fr.slickteam.cities.mappers.IUserMapper
import fr.slickteam.cities.service.IUserService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by jguidoux on 28/05/2017.
 */
class GetUserStory extends DbUnitIntegratonTestBase {

    @Autowired
    IUserService userService
    @Autowired
    IUserMapper userMapper

    def "get user"() {

        given: "I insert a user call halyna"
        Account newAcount = new Account("halyna", "halyna2@slickteam.fr", "123456")
        userService.registerNewUserAccount(newAcount)

        when: "I ask for a user"
        def user = userMapper.findByUsername("halyna")

        then: "user should have role ROLE_USER"
        user.getRoles()[0].roleName == "ROLE_USER"

    }
}
