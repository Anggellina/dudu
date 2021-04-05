import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DuTest {
    Du du1 = new Du(true, false, false);
    Du du2 = new Du(false, true, true);

    @Test
    void getHumanFormat() {
        String expected0 = "1 B";
        String expected1 = "342 KB";
        String expected2 = "2 MB";
        String expected3 = "4 GB";
        assertEquals(expected0, du1.getHumanFormat(1L));
        assertEquals(expected1, du1.getHumanFormat(349874L));
        assertEquals(expected2, du1.getHumanFormat(2230987L));
        assertEquals(expected3, du1.getHumanFormat(4459666812L));

        assertEquals(expected0, du2.getHumanFormat(1L));
        assertEquals("350 KB", du2.getHumanFormat(349874L));
        assertEquals("668 MB", du2.getHumanFormat(667633099L));
        assertEquals("346 GB", du2.getHumanFormat(345678009999L));

    }

    @Test
    void size() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("files\\test1.txt: 23 B");
        expected.add("files\\test2.txt: 21 B");
        expected.add("files\\dir1: 798 KB");
        assertEquals(expected, du1.size(Arrays.asList("files/test1.txt", "files/test2.txt", "files/dir1")));
    }

    @Test
    void getFolderSize() {
        assertEquals(817208L, du1.getFolderSize(new File("files/dir1")));
        assertEquals(817252L, du1.getFolderSize(new File("files")));
    }
}