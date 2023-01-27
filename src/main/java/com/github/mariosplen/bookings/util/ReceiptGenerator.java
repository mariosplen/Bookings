package com.github.mariosplen.bookings.util;

import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReceiptGenerator {

    public void genReceipt(
            String name, LocalDate dateOfArrivalLD, LocalDate dateOfDepartureLD, int total
    ) throws IOException {

        String dateOfArrival = dateOfArrivalLD.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy").withLocale(Locale.ENGLISH));
        String dateOfDeparture = dateOfDepartureLD.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy").withLocale(Locale.ENGLISH));
        int days = dateOfArrivalLD.until(dateOfDepartureLD).getDays();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        //first page
        PDPage page1 = document.getPage(0);

        //initialisation of content stream
        PDPageContentStream cs = new PDPageContentStream(document, page1);

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 40);
        cs.setNonStrokingColor(81, 0, 252);
        cs.newLineAtOffset(75, 700);
        cs.showText("HOTELINO");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 28);
        cs.setNonStrokingColor(0, 0, 0);
        cs.newLineAtOffset(100, 675);
        cs.showText("Invoice");
        cs.endText();

        java.util.Date date = new java.util.Date();
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(400, 675);
        cs.showText("" + date);
        cs.endText();

        // Predetermined text
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(100, 590);
        cs.showText("Name:");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(100, 570);
        cs.showText("Date of arrival:");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(100, 550);
        cs.showText("Date of departure:");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(100, 530);
        cs.showText("Length of stay:");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 20);
        cs.setNonStrokingColor(81, 0, 252);
        cs.newLineAtOffset(100, 210);
        cs.showText("Total:");
        cs.setNonStrokingColor(0, 0, 0);
        cs.endText();

        // Dynamic data
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(250, 590);
        cs.showText(name);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(250, 570);
        cs.showText(dateOfArrival);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(250, 550);
        cs.showText(dateOfDeparture);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 14);
        cs.newLineAtOffset(250, 530);
        cs.showText("" + days);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 21);
        cs.setNonStrokingColor(81, 0, 252);
        cs.newLineAtOffset(250, 210);
        cs.showText(total + "€");
        cs.setNonStrokingColor(0, 0, 0);
        cs.endText();

        cs.close();

        // Save the pdf
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        File dir = fileChooser.showSaveDialog(Nav.content.getScene().getWindow());
        String directory = dir + String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) + ".pdf";
        document.save(directory);

    }
}
