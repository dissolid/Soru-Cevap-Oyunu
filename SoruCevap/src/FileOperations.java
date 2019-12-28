import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileOperations {
	FileInputStream file;
	Workbook excel;
	Sheet sorular;
	Soru[] soru;
	int soruSayisi;
	boolean okundu;

	public FileOperations(String path) {

		try {
			file = new FileInputStream(path);
			excel = new XSSFWorkbook(file);
			sorular = excel.getSheetAt(0);
			soru = new Soru[sorular.getLastRowNum() + 1];
			soruSayisi = sorular.getLastRowNum() + 1;
			getData();
			okundu = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean readFile(String path) {

		if (okundu) {
			return true;
		} else {
			return false;
		}

	}

	// bu metod excel listesindeki verileri diziye yerleştirir.
	public void getData() {
		Iterator<Row> iterator = sorular.iterator();

		while (iterator.hasNext()) {
			Row mevcutSatir = iterator.next();

			Iterator<Cell> hucreIterator = mevcutSatir.iterator();

			// iterasyonun exceldeki satır sayısı. Her satır bir soru bilgisi içerdiği için,
			// iterasyonun kaçıncı satırda oldunuğunu bilmemiz gerekiyor.
			int currentIndex = mevcutSatir.getRowNum();
			// Obje bilgisi tuttuğumuz için her indexte oluşturmamız gerekiyor.
			soru[currentIndex] = new Soru();
			while (hucreIterator.hasNext()) {

				Cell mevcutHucre = hucreIterator.next();
				// iterasyonun exceldeki kolon sayısı. Kaçıncı kolonda olduğunu bilmemiz
				// gerekiyor.
				int iteration = mevcutHucre.getColumnIndex();

				if (iteration == 0) {
					soru[currentIndex].soruCumlesi = cellValue(mevcutHucre);

				} else if (iteration == 1) {
					soru[currentIndex].cevapA = cellValue(mevcutHucre);

				} else if (iteration == 2) {

					soru[currentIndex].cevapB = cellValue(mevcutHucre);

				} else if (iteration == 3) {

					soru[currentIndex].cevapC = cellValue(mevcutHucre);

				} else if (iteration == 4) {

					soru[currentIndex].cevapD = cellValue(mevcutHucre);

				} else if (iteration == 5) {

					soru[currentIndex].dogruCevap = cellValue(mevcutHucre);

				}
			}

		}
		try {

			file.close();
			excel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// HÜCREDEKİ DEĞERİ DÖNDÜRÜR
	private String cellValue(Cell cell) {
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return Integer.toString((int) cell.getNumericCellValue());
		} else {
			return null;
		}
	}

	// GETTERS & SETTERS

}
