package pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * itextpdf测试类
 *
 * @author gaoyanzhen
 * @since 2022-07-20
 */
public class PdfTest {
    @Test
    public void setAcroFieldsValue() throws IOException, DocumentException {
        String pdfName = "doc3";
        String outFile = "E:\\" + pdfName + "_fill.pdf";
        FileOutputStream file = new FileOutputStream(outFile);

        PdfReader reader = new PdfReader(new FileInputStream("E:\\" + pdfName + ".pdf"));
        PdfStamper stamper = new PdfStamper(reader, file);
        AcroFields acroFields = stamper.getAcroFields();

        BaseFont font = BaseFont.createFont("c:\\windows\\fonts\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        acroFields.addSubstitutionFont(font);

        acroFields.setField("name", "高彦珍");
        acroFields.setField("id", "337665666");
        acroFields.setField("mobile", "18615605638");
        stamper.setFormFlattening(true);
        stamper.close();
    }

}
