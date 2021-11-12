package ca.mcgill.ecse321.library.service;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;


import java.sql.Date;

@ExtendWith(MockitoExtension.class)
public class TestMusicAlbumService {

    @Mock
    private MusicAlbumRepository musicAlbumRepository;

    @InjectMocks
    private MusicAlbumService musicAlbumService;

    private static final String LIBRARIAN_USERNAME ="TestCustomer";

    private static final String AUTHOR = "LOLLS";
    private static final float LENGTH = 233f;
    private static final String DATE_OF_RELEASE = "2021-11-01";
    private static final int PRICE = 30;
    private static final String TITLE = " The meaning of life";
    private static final String BARCODE = "122344";
    private static final int NUMBER_OF_SONGS = 20;
    private static final int TOTAL_LENGTH = 20;



    @BeforeEach
    public void setMockOutput(){

        lenient().when(musicAlbumRepository.findMusicAlbumByBarcode(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(BARCODE)) {
                MusicAlbum musicAlbum = new MusicAlbum();
                musicAlbum.setAuthor(AUTHOR);
                musicAlbum.setTotalLength(LENGTH);
                musicAlbum.setNumberOfSongs(NUMBER_OF_SONGS);
                musicAlbum.setDateOfRelease(Date.valueOf(DATE_OF_RELEASE));
                musicAlbum.setPrice(PRICE);
                musicAlbum.setTitle(TITLE);

                return musicAlbum;
            }
            else return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(musicAlbumRepository.save(any(MusicAlbum.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreatemusicAlbum(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";


        MusicAlbum musicAlbum = null;
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            String error = e.getMessage();
            fail();
        }

        assertNotNull(musicAlbum);
        assertEquals(Float.valueOf(length), musicAlbum.getTotalLength());
        assertEquals(Integer.valueOf(numberOfSongs), musicAlbum.getNumberOfSongs());
        assertEquals(barCode, musicAlbum.getBarcode());
        assertEquals(title, musicAlbum.getTitle());
        assertEquals(author, musicAlbum.getAuthor());
        assertEquals(Date.valueOf(dateOfRelease), musicAlbum.getDateOfRelease());
        assertEquals(Float.valueOf(price), musicAlbum.getPrice());
        assertNull(musicAlbum.getBooking());
    }

    @Test
    public void testCreateMusicAlbumNullBarcode(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "barcode needs to be specified ");
    }

    @Test
    public void testCreateMusicAlbumNullTitle(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "title needs to be specified ");
    }

    @Test
    public void testCreateMusicAlbumNullAuthor(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "author needs to be specified ");
    }

    @Test
    public void testCreateMusicAlbumNullDateOfRelease(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "dateOfRelease needs to be specified date format is not correct");
    }

    @Test
    public void testCreateMusicAlbumNullPrice(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "";
        String length = "160";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "price needs to be specified price is not a number");
    }


    @Test
    public void testCreateMusicAlbumNullLength(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "";
        String numberOfSongs = "33";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "total length needs to be specified ");
    }

    @Test
    public void testCreateMusicAlbumNullNumberOfSongs(){
        assertEquals(0, musicAlbumService.getAllMusicAlbums().size());
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "20";
        String numberOfSongs = "";

        MusicAlbum musicAlbum = null;

        String error = "";
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, length);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(musicAlbum);
        assertEquals(error, "number of songs needs to be specified ");
    }

    @Test
    public void testDeleteMusicAlbum(){
        
    }

}
