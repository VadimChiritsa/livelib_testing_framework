package excel;

import livelib.api.ApiClient;
import livelib.api.Book;
import livelib.api.ExcelWriter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class BooksImportTest {
    private ArrayList<Book> bookArrayList;
    private ApiClient apiClient;

    @BeforeClass
    public void createObjects() {
        apiClient = new ApiClient();
    }

    @Test
    @Parameters("genre")
    public void testWriteToExcel(String genreName) {
        bookArrayList = apiClient.getBooksByGenre(genreName);
        ExcelWriter.generateExcelDocument("scibooks.xls", bookArrayList);
    }
}
