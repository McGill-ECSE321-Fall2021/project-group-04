package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.MemberDto;
import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {




    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/member/{username}"})
    public ResponseEntity<?> viewMember(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(DTOConverter.convertToDto(memberService.getMember(username)),HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Error Message: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author Jewoo Lee
     * @param address
     * @param username
     * @param password
     * @param memberType
     * @param memberStatus
     * @return
     */
    @PostMapping(value = {"/signup_user/", "/signup_user"})
    public ResponseEntity<?> signupUser(@RequestParam String address, @RequestParam String username, @RequestParam String password,
                                        @RequestParam Member.MemberType memberType, @RequestParam Member.MemberStatus memberStatus) {

        Member member = null;
        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            memberService.deleteMember(username);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(DTOConverter.convertToDto(member), HttpStatus.CREATED);
    }

    /*
    @PostMapping(value = {"/create_member"})
    public ResponseEntity<?> createMember(@RequestParam("username") String aUsername,
                                          @RequestParam("password") String aPassword,
                                          @RequestParam("address") String aAddress,
                                          @RequestParam("member_type") Member.MemberType aMemberType,
                                          @RequestParam("member_status") Member.MemberStatus aMemberStatus) {
        try {
            Member member = memberService.createMember(aUsername, aPassword, aAddress, aMemberType, aMemberStatus);
            return new ResponseEntity<>(DTOConverter.convertToDto(member), HttpStatus.CREATED) ;
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

    @GetMapping(value = {"/members"})
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }

}
