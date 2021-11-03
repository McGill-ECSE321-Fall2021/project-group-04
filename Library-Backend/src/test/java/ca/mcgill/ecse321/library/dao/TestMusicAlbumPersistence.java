package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.BookingType;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import java.sql.Date;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMusicAlbumPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MusicAlbumRepository musicAlbumRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LendingRepository lendingRepository;

    @AfterEach
    public void clearDatabase() {
        musicAlbumRepository.deleteAll();
        bookingRepository.deleteAll();
        lendingRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadMusicAlbum() {

        //Generate dependencies------------------>
        Member libMember = generateMember();
        Lending lend = generateBookingType();
        Booking booking = generateBooking(libMember, lend);
        //-------------------------------------->
        //Generate Testing Element------------------>
        MusicAlbum testMusicAlbum = generateMusicAlbum(booking);
        //------------------------------------------->
        //Save to DB dependencies and testing element
        lendingRepository.save(lend);
        memberRepository.save(libMember);
        bookingRepository.save(booking);
        musicAlbumRepository.save(testMusicAlbum);
        //------------>
        //Retrieve from DB and compare with testMusicAlbum
        assertEquals(true, musicAlbumRepository.existsMusicAlbumById(testMusicAlbum.getId()));
        compareToDB(testMusicAlbum, musicAlbumRepository.findMusicAlbumById(testMusicAlbum.getId()));
        compareToDB(testMusicAlbum, musicAlbumRepository.findMusicAlbumByBarcode(testMusicAlbum.getBarcode()));
        compareToDB(testMusicAlbum, musicAlbumRepository.findMusicAlbumByTitle(testMusicAlbum.getTitle()));
        compareToDB(testMusicAlbum, musicAlbumRepository.findByBooking(booking).get(0));
        compareToDB(testMusicAlbum, musicAlbumRepository.findByAuthor(testMusicAlbum.getAuthor()).get(0));
        //Done

    }

    private void compareToDB(MusicAlbum expected, MusicAlbum received) {

        assertNotNull(received);
        assertEquals(expected.getNumberOfSongs(), received.getNumberOfSongs());
        assertEquals(expected.getTotalLength(), received.getTotalLength());
        assertEquals(expected.getBarcode(), received.getBarcode());
        assertEquals(expected.getTitle(), received.getTitle());
        assertEquals(expected.getAuthor(), received.getAuthor());
        assertEquals(expected.getDateOfRelease(), received.getDateOfRelease());
        assertEquals(expected.getPrice(), received.getPrice());
        assertEquals(expected.getBooking().getBookingDate(), received.getBooking().getBookingDate());


    }

    private Member generateMember() {
        Member tUser = new Member();
        tUser.setUsername("Simo4");
        tUser.setPassword("12341234");
        tUser.setAddress("123 street");

        return tUser;
    }

    private Booking generateBooking(Member m, BookingType bT) {
        Booking booking1 = new Booking();
        booking1.setBookingDate(Date.valueOf("2015-03-30"));
        //booking1.setId((long) 33233);
        booking1.setBookingType(bT);
        booking1.setUser(m);

        return booking1;
    }

    private Lending generateBookingType() {
        Lending bT = new Lending();

        return bT;
    }

    private MusicAlbum generateMusicAlbum(Booking booking) {

        int mNumSongs = 12;
        float mLength = (float) 222.3;
        String barCode = "121212121p";
        String bTitle = "Boss";
        String bAuthor = "habibi";
        Date bDateRelease = Date.valueOf("2015-03-31");
        float bPrice = (float) 22.99;
        //long itemId = 1234;

        MusicAlbum testmAlbum = new MusicAlbum();
        testmAlbum.setNumberOfSongs(mNumSongs);
        testmAlbum.setTotalLength(mLength);
        testmAlbum.setBarcode(barCode);
        testmAlbum.setTitle(bTitle);
        testmAlbum.setAuthor(bAuthor);
        testmAlbum.setDateOfRelease(bDateRelease);
        testmAlbum.setPrice(bPrice);
        //testmAlbum.setId(itemId);
        testmAlbum.setBooking(booking);

        return testmAlbum;
    }

}
