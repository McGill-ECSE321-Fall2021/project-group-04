package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.ArchiveRepository;
import ca.mcgill.ecse321.library.model.Archive;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class TestArchive {

    private static final Date ARCHIVE_DATE = Date.valueOf("2021-11-11");
    private static final String ARCHIVE_TITLE = "Win";
    private static final Integer ARCHIVE_NUMBER_OF_PAGES = 69;
    @Mock
    private ArchiveRepository archiveRepository;
    @InjectMocks
    private ArchiveService archiveService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(archiveRepository.findArchiveByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARCHIVE_TITLE)) {
                Archive archive = new Archive();

                archive.setDate(ARCHIVE_DATE);
                archive.setTitle(ARCHIVE_TITLE);
                archive.setNumberOfPages(ARCHIVE_NUMBER_OF_PAGES);

                return archive;
            }
            return null;
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(archiveRepository.save(any(Archive.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateArchive() {
        assertEquals(0, archiveService.getAllArchives().size());
        Archive archive = null;
        String date = "2020-09-15";
        String num = "100";
        String tit = "Trophy";

        try {
            archive = archiveService.createArchive(date, num, tit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            fail();

        }
        assertNotNull(archive);
        assertEquals(Date.valueOf(date), archive.getDate());
        assertEquals(Integer.parseInt(num), archive.getNumberOfPages());
        assertEquals(tit, archive.getTitle());
    }


    @Test
    public void testCreateArchiveNullTitle() {
        assertEquals(0, archiveService.getAllArchives().size());
        Archive archive = null;
        String date = "2020-09-15";
        String num = "100";
        String tit = "";

        String error = "";
        try {
            archive = archiveService.createArchive(date, num, tit);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(archive);
        assertEquals(error, "title needs to be specified ");
    }


    @Test
    public void testCreateArchiveNullDate() {
        assertEquals(0, archiveService.getAllArchives().size());
        Archive archive = null;
        String date = "";
        String num = "100";
        String tit = "N";

        String error = "";
        try {
            archive = archiveService.createArchive(date, num, tit);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(archive);
        assertEquals(error, "date needs to be specified date format is not correct");
    }

    @Test
    public void testCreateArchiveNullNumberOfPages() {
        assertEquals(0, archiveService.getAllArchives().size());
        Archive archive = null;
        String date = "2020-09-15";
        String num = "";
        String tit = "N";

        String error = "";
        try {
            archive = archiveService.createArchive(date, num, tit);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(archive);
        assertEquals(error, "number of pages needs to be specified number of pages is not a number");
    }

    @Test
    public void testDeleteArchive() {
        assertEquals(0, archiveService.getAllArchives().size());
        Archive archive = null;
        String date = "2020-09-15";
        String num = "50";
        String tit = "N";

        String error = "";
        try {
            archive = archiveService.createArchive(date, num, tit);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNotNull(archive);

        try {
            archiveService.deleteArchive(tit);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(archiveService.getArchiveByTitle(tit));

    }


}
